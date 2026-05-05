/*
 * Copyright (C) 2026 yztz
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package top.yztz.msggo.data;

public class Settings {
    // edit after import
    public static final boolean EDIT_AFTER_IMPORT_DEFAULT = false;
    // Send delay (ms)
    public static final int SEND_DELAY_DEFAULT = 3000;
    public static final int SEND_DELAY_MIN = 1000;
    public static final int SEND_DELAY_MAX = 8000;
    public static final int SEND_DELAY_STEP_UNIT = 500;
    public static final boolean SEND_DELAY_RANDOMIZATION_DEFAULT = true;
    // SMS rate
    public static final float SMS_RATE_DEFAULT = 0.1f;
    public static final float SMS_RATE_MIN = 0.0f;
    public static final float SMS_RATE_MAX = 10.0f;
    // privacy and disclaimer
    public static final boolean PRIVACY_ACCEPTED_DEFAULT = false;
    public static final boolean DISCLAIMER_ACCEPTED_DEFAULT = false;
    // language
    public static final String LANGUAGE_DEFAULT = "auto";

    public static final int EXCEL_ROW_COUNT_MAX = 200;
    public static final long EXCEL_FILE_SIZE_MAX = 50 * 1024 * 1024; // 50MB

    // Anti-spam protection
    public static final boolean BATCH_PAUSE_DEFAULT = true;
    public static final int BATCH_SIZE_DEFAULT = 30;
    public static final int BATCH_SIZE_MIN = 10;
    public static final int BATCH_SIZE_MAX = 100;
    public static final int BATCH_PAUSE_MIN_MIN_DEFAULT = 5;   // minutes
    public static final int BATCH_PAUSE_MAX_MIN_DEFAULT = 15;  // minutes
    public static final int BATCH_PAUSE_MIN_RANGE = 1;
    public static final int BATCH_PAUSE_MAX_RANGE = 60;

    public static final boolean QUIET_HOURS_DEFAULT = true;
    public static final int QUIET_HOURS_START_DEFAULT = 22 * 60; // 22:00 (minutes from midnight)
    public static final int QUIET_HOURS_END_DEFAULT = 8 * 60;    // 08:00

    public static final boolean SCHEDULE_SPREAD_DEFAULT = false;
    public static final int SCHEDULE_SPREAD_HOURS_DEFAULT = 4;
    public static final int SCHEDULE_SPREAD_HOURS_MIN = 1;
    public static final int SCHEDULE_SPREAD_HOURS_MAX = 24;
}
