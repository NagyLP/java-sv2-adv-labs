# 2022-04-29

>**Focicsapatok**

Készíts egy Team nevű entitást mely egy focicsapatot fog reprezentálni. Minden focicsapatnak legyen egy neve, egy ország ahol található, egy Enum típusú attribútum ami azt reprezentálja, hogy hányadosztályban szerepel a csapat, egy aktuális pontszám és játékosok listája akik a focicsapatban játszanak. A lista egyszerűen a játékosok nevét tárolja. Legyen egy addPlayer metódus amivel játékost tudok hozzáadni a listához.

Készíts egy TeamRepository osztályt melyen keresztül le tudsz menteni egy csapatot. Legyen egy metódus amiben le tudsz kérdezni egy csapatot játékosokkal együtt csapatnév alapján. Ezen kívül ami frissíti egy csapat pontszámát a kapott értékre id alapján és egy metódus ami ország és osztály alapján visszaadja az összes csaptot pontszám alapján csökkenő sorrendben.