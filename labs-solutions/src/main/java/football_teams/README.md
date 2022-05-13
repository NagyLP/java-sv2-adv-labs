
>## Focicsapatok
 
### *2022-04-29*

Készíts egy Team nevű entitást mely egy focicsapatot fog reprezentálni. Minden focicsapatnak legyen egy neve, egy ország ahol található, egy Enum típusú attribútum ami azt reprezentálja, hogy hányadosztályban szerepel a csapat, egy aktuális pontszám és játékosok listája akik a focicsapatban játszanak. A lista egyszerűen a játékosok nevét tárolja. Legyen egy addPlayer metódus amivel játékost tudok hozzáadni a listához.<>

Készíts egy TeamRepository osztályt melyen keresztül le tudsz menteni egy csapatot. Legyen egy metódus amiben le tudsz kérdezni egy csapatot játékosokkal együtt csapatnév alapján. Ezen kívül ami frissíti egy csapat pontszámát a kapott értékre id alapján és egy metódus ami ország és osztály alapján visszaadja az összes csaptot pontszám alapján csökkenő sorrendben.

### *2022-05-05*
Fejleszd tovább a Focis programot! Legyen egy `Player` entitás aminek attribútumai a neve és a játékos értéke. Egy csapathoz több játékos tartozik viszont egy játékos csak egy csapathoz tartozhat. Valósíts meg kétirányú kapcsolatot. <br>

Készíts egy `PlayerRepository` osztályt. Játékost kétféleképpen lehet lementeni, csapat id-val vagy anélkül. (Ha csapat id nélkül mentődik le akkor ún. free agent lesz akit később lehet majd egy csapathoz adni.) Legyen egy metódus ami megtalál egy játékost `id` alapján.<br>

A `Team` entitást bővítsd egy `int budget` attribútummal, mely a csapat költségvetését reprezentálja!

Hozzd létre a `TeamSrevice` osztályt és valósítsd meg a következő üzleti logikát. Egy cspat tudjon leigazolni egy játékost. Ha egy játékos már játszik valahol akkor a játékos értékén kell megvásárolni, ha nem játszik sehol akkor ingyenesen igazolható. További feltétel, hogy egy csapat nem költhet többet egy játékosra mint a költségvetésének a 20%-a! (Ha további repository metódusokra van szükséged hozzd létre nyugodtan)
