/*
 * Copyright (C) 2026 Jonas Millard
 * Based on MsgGo, Copyright (C) 2026 yztz
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later
 * version.
 */

package top.yztz.msggo.util;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import top.yztz.msggo.R;
import top.yztz.msggo.data.Settings;
import top.yztz.msggo.exception.DataLoadFailed;

/**
 * Reads a JSON array of objects, e.g.
 *   [{"namn":"Anna","nummer":"+46..."}, {"namn":"Bo","nummer":"+46..."}]
 *
 * Column titles are the union of keys, ordered by first appearance.
 */
public class JsonReader {
    private static final String TAG = "jsonReader";

    private String[] titles = null;
    private JSONArray array = null;

    public void read(String path) throws DataLoadFailed {
        File file = new File(path);
        if (file.exists() && file.length() > Settings.EXCEL_FILE_SIZE_MAX) {
            throw new DataLoadFailed(R.string.file_too_large);
        }

        String content;
        try (FileInputStream is = new FileInputStream(path);
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buf = new byte[8192];
            int n;
            while ((n = is.read(buf)) != -1) out.write(buf, 0, n);
            content = out.toString(StandardCharsets.UTF_8.name()).trim();
        } catch (IOException e) {
            throw new DataLoadFailed(e);
        }

        if (TextUtils.isEmpty(content)) throw new DataLoadFailed(R.string.error_empty_content);

        try {
            array = new JSONArray(content);
        } catch (JSONException e) {
            throw new DataLoadFailed(e);
        }

        if (array.length() == 0) throw new DataLoadFailed(R.string.error_empty_content);
        if (array.length() > Settings.EXCEL_ROW_COUNT_MAX) {
            throw new DataLoadFailed(R.string.file_too_much_row);
        }

        Set<String> keys = new LinkedHashSet<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.optJSONObject(i);
            if (obj == null) continue;
            for (Iterator<String> it = obj.keys(); it.hasNext(); ) {
                keys.add(it.next());
            }
        }
        if (keys.isEmpty()) throw new DataLoadFailed(R.string.error_no_header);

        titles = keys.toArray(new String[0]);
        Log.i(TAG, String.format("rows=%d, cols=%d", array.length(), titles.length));
    }

    public ArrayList<HashMap<String, String>> readContent() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.optJSONObject(i);
            if (obj == null) continue;
            HashMap<String, String> row = new HashMap<>();
            boolean anyValue = false;
            for (String key : titles) {
                String value = obj.isNull(key) ? "" : obj.optString(key, "").trim();
                row.put(key, value);
                if (!TextUtils.isEmpty(value)) anyValue = true;
            }
            if (anyValue) list.add(row);
        }
        return list;
    }

    public String[] getTitles() {
        return titles;
    }
}
