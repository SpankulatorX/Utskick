## Basic usage

1. Import a member list (Excel, CSV or JSON)
2. Pick which column holds the phone number
3. Write the SMS template (with optional `${variables}` from the columns)
4. Tap **Send** and confirm

Tip: you can also share an Excel/CSV/JSON file from another app to Utskick, and it will open the import flow automatically.

## Supported file formats

- **Excel** (`.xls`, `.xlsx`) — first row is column titles
- **CSV** (`.csv`, `.tsv`, `.txt`) — first row is column titles, separator is auto-detected (`,`, `;`, tab), UTF-8 with or without BOM
- **JSON** (`.json`) — array of objects, e.g. `[{"name":"Anna","number":"+46..."}]`

Limits: max 200 rows per file, max 50 MB file size.

## Variables (`${...}`)

Each column in your imported file becomes a variable you can drop into the message template. Example with this list:

| Name | PhoneNumber |
|------|-------------|
| Anna | +46701234567 |
| Bo   | +46702345678 |

Template:

> Hello ${Name}, your member number is ${PhoneNumber}.

Will produce two unique messages:

| Name | PhoneNumber  | Resulting SMS                                    |
|------|--------------|--------------------------------------------------|
| Anna | +46701234567 | Hello Anna, your member number is +46701234567.  |
| Bo   | +46702345678 | Hello Bo, your member number is +46702345678.    |

Why this matters: **carrier spam filters react to identical messages sent in bulk.** Personalising every SMS with at least one variable is the single most effective defense.

## Avoiding carrier blocks

Operators monitor outgoing volume. To stay below the radar:

- Keep **Send delay** at 3 s or higher and leave **Randomize delay** on
- Enable **Batch pause** (Settings → Anti-spam protection): pause 5–15 min after every 30 SMS
- Enable **Quiet hours**: don't send between 22:00 and 08:00
- For very large lists, enable **Time spread** so the send is spread over hours rather than minutes

If you start seeing failures, slow down and try again the next day.

## GDPR / consent

For Swedish associations: make sure your members have **opted in** to receive SMS from your organisation, and offer a clear way to opt out (a phrase like *"Reply STOP to unsubscribe"* in the message is good practice). The app does not handle this for you — it is your responsibility as the sender.
