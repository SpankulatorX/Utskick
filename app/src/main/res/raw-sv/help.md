## Grundläggande användning

1. Importera en medlemslista (Excel, CSV eller JSON)
2. Peka ut vilken kolumn som innehåller telefonnumret
3. Skriv SMS-mallen (med valfria `${variabler}` från kolumnerna)
4. Tryck på **Skicka** och bekräfta

Tips: du kan också dela en Excel/CSV/JSON-fil från en annan app till Utskick, så öppnas importflödet automatiskt.

## Filformat som stöds

- **Excel** (`.xls`, `.xlsx`) — första raden är kolumnrubriker
- **CSV** (`.csv`, `.tsv`, `.txt`) — första raden är kolumnrubriker, separatorn detekteras automatiskt (`,`, `;`, tab), UTF-8 med eller utan BOM
- **JSON** (`.json`) — array av objekt, t.ex. `[{"namn":"Anna","nummer":"+46..."}]`

Gränser: max 200 rader per fil, max 50 MB filstorlek.

## Variabler (`${...}`)

Varje kolumn i din importerade fil blir en variabel du kan använda i meddelandemallen. Exempel med denna lista:

| Namn | Nummer        |
|------|---------------|
| Anna | +46701234567  |
| Bo   | +46702345678  |

Mall:

> Hej ${Namn}, ditt medlemsnummer är ${Nummer}.

Ger två unika meddelanden:

| Namn | Nummer        | Resulterande SMS                              |
|------|---------------|-----------------------------------------------|
| Anna | +46701234567  | Hej Anna, ditt medlemsnummer är +46701234567. |
| Bo   | +46702345678  | Hej Bo, ditt medlemsnummer är +46702345678.   |

Varför det här är viktigt: **operatörernas spam-filter reagerar på identiska meddelanden som skickas i bulk.** Att personalisera varje SMS med åtminstone en variabel är det enskilt mest effektiva skyddet.

## Undvika operatörsblockering

Operatörerna övervakar utgående volym. Tips för att hålla dig under deras radar:

- Behåll **Sändningsfördröjning** på 3 s eller mer och låt **Slumpmässig fördröjning** vara på
- Aktivera **Batch-paus** (Inställningar → Anti-spam-skydd): pausar 5–15 min efter var 30:e SMS
- Aktivera **Tids-spärr (nattvila)**: skicka inte mellan 22:00 och 08:00
- För mycket stora listor, aktivera **Tidsspridning** så utskicket sprids över timmar i stället för minuter

Om du börjar få fel — sakta ner och försök igen nästa dag.

## GDPR / samtycke

För svenska föreningar: säkerställ att era medlemmar har **samtyckt** till att ta emot SMS från er, och erbjud en tydlig möjlighet att avregistrera sig (en formulering som *"Svara STOP för att avregistrera"* i meddelandet är god praxis). Appen hanterar inte detta åt er — det är ert ansvar som avsändare.
