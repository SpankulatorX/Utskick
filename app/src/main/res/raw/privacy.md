**Last updated: May 2026**

Utskick (hereafter "this app") is designed to keep your data on your device. This privacy policy explains how the app handles data and which permissions it needs.

### 1. No network access — local processing only
Utskick is built to be a fully private SMS tool. **The app does not request internet permissions.** This is enforced by the Android system itself (see the manifest), which means:
- Your data — phone numbers, message templates, imported files — **cannot** leave your device.
- All processing (variable substitution, message generation, sending) happens entirely locally.

### 2. Permissions and how they are used
- **Send SMS (`SEND_SMS`)** — required to send the messages you have prepared.
- **Read external storage** — used to read the file (Excel/CSV/JSON) that you yourself select.
- **Foreground service (`FOREGROUND_SERVICE`)** and **Notifications (`POST_NOTIFICATIONS`)** — used so the app is not interrupted by the system while sending many messages, and to show progress.

### 3. Storage and lifecycle
- **Imported data** is copied to the app's private cache. Clearing the cache removes it immediately.
- **Local history** (recently opened files, message templates, column choices) is stored in the app's private folder. Use *Settings → Clear cache* to delete it.
- **Debug logs** record only the app's own activity. They never leave the device unless you manually export and share them.

### 4. No third-party services
Utskick contains no analytics, no trackers, no ad SDKs, and no telemetry. Without internet permission, any form of remote data collection is technically impossible.

### 5. Your rights
- You can revoke any permission at any time in Android system settings.
- You can delete all locally stored data via *Settings → Clear cache*.

### 6. About this version
Utskick is an open-source fork of [MsgGo](https://github.com/yztz/MsgGo) (GPL-3.0) by yztz, modified by Jonas Millard for Swedish association use.
