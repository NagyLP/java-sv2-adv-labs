# Vizsga feladat

A vizsga feladatban egy focibajnokságot nyilvántartó alkalmazást kell megvalósítanod.


## Entitások

* Csapat (id, név, játékosok)
* Játékos (id, név, születési idő, pozíció, csapat)

Az entitások között kétirányú egy több kapcsolat van, vagyis egy csapatnak lehet több játékosa,
de egy játékos egyszerre csak egy csapatban játszhat.
A pontos elnevezéseket a tesztben megtalálod.
Lehetséges pozíciók: GOALKEEPER,RIGHT_FULLBACK,LEFT_FULLBACK,CENTER_BACK,DEFENDING_MIDFIELDER,RIGHT_WINGER,LEFT_WINGER,STRIKER


## Megvalósítás

A feladat megoldásához a következő funkciókat kell megvalósítanod a megfelelő végpontokon.

* A `PlayerController` a `/api/players` végponton figyel
    * Lehessen lekérdezni az összes adatbázisban szereplő játékost
    * Lehessen felvenni új játékost. (Ekkor bekerül a liga nyilvántartásába, de lehet hogy még egy csapat
      sem igazolta le)
    * Lehessen törölni egy játékost id alapján a `/api/players/{id}` végponton

* A `TeamContorller` a `/api/teams= végponton figyel
    * Lehessen lekérdezni a csapatokat, az összes játékosukkal együtt
    * Lehessen új csapatot létrehozni
    * Lehessen egy csapathoz új játékost hozzáadni a `/api/teams/{id}/players` végponton keresztül post metódussal. Ebben az esetben
      egy játékos minden adatát várjuk és úgy adjuk hozzá a csapathoz, hogy egyúttal a játékosk táblába is lementjük
    * Lehessen a meglévő, szabad játékosok közül játékost igazolni a `/api/teams/{id}/players` véponton keresztül put metódussal.
      Ekkor a következő feltételeknek kell teljesülnie:
        * A játékosnak ne legyen csapata
        * A csapatban kevesebb mint kettő ezen a pozíción szereplő játékos legyen
        * Ha a fenti kettő közül valamelyik nem teljesül, ne történjen meg az igazolás