**Senast uppdaterad: maj 2026**

Utskick (nedan "appen") är byggd för att hålla dina uppgifter på din enhet. Denna integritetspolicy förklarar hur appen hanterar data och vilka behörigheter den behöver.

### 1. Ingen nätverksåtkomst — all hantering sker lokalt
Utskick är byggd för att vara ett fullständigt privat verktyg för SMS-utskick. **Appen begär ingen behörighet att använda internet.** Detta upprätthålls av Android-systemet självt (se manifestet), vilket innebär att:
- Dina uppgifter — telefonnummer, meddelandemallar, importerade filer — **kan inte** lämna din enhet.
- All bearbetning (variabel-substitution, generering av meddelanden, sändning) sker helt lokalt.

### 2. Behörigheter och vad de används till
- **Skicka SMS (`SEND_SMS`)** — krävs för att skicka de meddelanden du förberett.
- **Läs extern lagring** — används för att läsa den fil (Excel/CSV/JSON) som du själv väljer.
- **Förgrundstjänst (`FOREGROUND_SERVICE`)** och **Aviseringar (`POST_NOTIFICATIONS`)** — används för att appen inte ska avbrytas av systemet under ett större utskick, och för att visa förlopp.

### 3. Lagring och livscykel
- **Importerad data** kopieras till appens privata cache. När du rensar cachen försvinner den direkt.
- **Lokal historik** (senast öppnade filer, mallar, kolumnval) lagras i appens privata mapp. Använd *Inställningar → Rensa cache* för att radera den.
- **Felsökningsloggar** innehåller endast appens egna aktivitet. De lämnar aldrig enheten, om du inte själv aktivt exporterar och delar dem.

### 4. Inga tredjepartstjänster
Utskick innehåller ingen analys, inga spårare, inga annons-SDK:er och ingen telemetri. Utan internet-behörighet är all form av fjärrinsamling av data tekniskt omöjlig.

### 5. Dina rättigheter
- Du kan när som helst återkalla behörigheter via Androids systeminställningar.
- Du kan radera all lokalt sparad data via *Inställningar → Rensa cache*.

### 6. Om denna version
Utskick är en öppen källkods-fork av [MsgGo](https://github.com/yztz/MsgGo) (GPL-3.0) av yztz, anpassad av Jonas Millard för svenska föreningars behov.
