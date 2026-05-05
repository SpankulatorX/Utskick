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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import top.yztz.msggo.R;
import top.yztz.msggo.data.Settings;
import top.yztz.msggo.exception.DataLoadFailed;

public class CsvReader {
    private static final String TAG = "csvReader";
    private static final char BOM = '﻿';

    private String[] titles = null;
    private List<List<String>> rows = new ArrayList<>();
    private char separator = ',';

    public void read(String path) throws DataLoadFailed {
        File file = new File(path);
        if (file.exists() && file.length() > Settings.EXCEL_FILE_SIZE_MAX) {
            throw new DataLoadFailed(R.string.file_too_large);
        }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {

            String first = br.readLine();
            if (first == null) throw new DataLoadFailed(R.string.error_no_header);
            if (!first.isEmpty() && first.charAt(0) == BOM) first = first.substring(1);

            separator = detectSeparator(first);
            List<String> header = parseLine(first, separator);
            if (header.isEmpty()) throw new DataLoadFailed(R.string.error_no_header);
            for (String h : header) {
                if (TextUtils.isEmpty(h.trim())) {
                    throw new DataLoadFailed(R.string.error_empty_title_column);
                }
            }
            titles = new String[header.size()];
            for (int i = 0; i < header.size(); i++) titles[i] = header.get(i).trim();

            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) continue;
                List<String> cells = parseLine(line, separator);
                if (isAllEmpty(cells)) continue;
                rows.add(cells);
                if (rows.size() > Settings.EXCEL_ROW_COUNT_MAX) {
                    throw new DataLoadFailed(R.string.file_too_much_row);
                }
            }

            if (rows.isEmpty()) throw new DataLoadFailed(R.string.error_empty_content);
            Log.i(TAG, String.format("rows=%d, cols=%d, sep='%c'",
                    rows.size(), titles.length, separator));
        } catch (IOException e) {
            throw new DataLoadFailed(e);
        }
    }

    public ArrayList<HashMap<String, String>> readContent() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        for (List<String> row : rows) {
            HashMap<String, String> content = new HashMap<>();
            for (int j = 0; j < titles.length; j++) {
                String value = j < row.size() ? row.get(j).trim() : "";
                content.put(titles[j], value);
            }
            list.add(content);
        }
        return list;
    }

    public String[] getTitles() {
        return titles;
    }

    private static char detectSeparator(String headerLine) {
        int commas = countOutsideQuotes(headerLine, ',');
        int semis = countOutsideQuotes(headerLine, ';');
        int tabs = countOutsideQuotes(headerLine, '\t');
        if (semis > commas && semis >= tabs) return ';';
        if (tabs > commas) return '\t';
        return ',';
    }

    private static int countOutsideQuotes(String s, char c) {
        int count = 0;
        boolean inQuotes = false;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '"') inQuotes = !inQuotes;
            else if (ch == c && !inQuotes) count++;
        }
        return count;
    }

    private static List<String> parseLine(String line, char sep) {
        List<String> out = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (inQuotes) {
                if (c == '"') {
                    if (i + 1 < line.length() && line.charAt(i + 1) == '"') {
                        cur.append('"');
                        i++;
                    } else {
                        inQuotes = false;
                    }
                } else {
                    cur.append(c);
                }
            } else {
                if (c == '"') {
                    inQuotes = true;
                } else if (c == sep) {
                    out.add(cur.toString());
                    cur.setLength(0);
                } else {
                    cur.append(c);
                }
            }
        }
        out.add(cur.toString());
        return out;
    }

    private static boolean isAllEmpty(List<String> cells) {
        for (String s : cells) if (!TextUtils.isEmpty(s.trim())) return false;
        return true;
    }
}
