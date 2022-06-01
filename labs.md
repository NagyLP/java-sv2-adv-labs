# Konzultációs gyakorlati feladatok

## Week 01

### 2022-04-12 (Csoportmunka)
Írjatok egy webshop üzemeltetését szimuláló backend alkalmazást. A webshopban felhasználók és termékek vannak. <br>
Funkcionális követelmények:
* Felhasználó regisztráció/bejelentkezés
* Termékek belehelyezése a vásárló kosarába
* Termék kivétele vásárló kosarából 
* Termék mennyiség növelése
* Rendelés leadása
* Termékek listázása/betöltése fájlból


## Week 02

### 2022-04-21

Adott a `lab-solutions/mockito-lab-demo` project. A feladat, hogy a `MovieService` osztályra írj unit teszteket!

## Week 03

### 2022-04-26
A filmes projektet egészítsd ki a következő elemekkel. Legyen egy Rating osztályod ami embeddable.
Ebben legyen egy `double rating` és egy `String username` attribútum. A movie osztályban vedd fel attribútumként ratingek listáját és egy metódust is amivel ratinget lehet hozzáadni a filmhez.<br>
A `MovieRepository` osztályt bővítsd egy metódussal amiben film cím alapján lehet lekérni az adatokat (ratinggel együtt)!

### 2022-04-29

Készíts egy `Team` nevű entitást mely egy focicsapatot fog reprezentálni. Minden focicsapatnak legyen egy neve, egy ország ahol található, egy `Enum` típusú attribútum ami azt reprezentálja, hogy hányadosztályban szerepel a csapat, egy aktuális pontszám és játékosok listája akik a focicsapatban játszanak. A lista egyszerűen a játékosok nevét tárolja. Legyen egy `addPlayer` metódus amivel játékost tudok hozzáadni a listához. 

Készíts egy `TeamRepository` osztályt melyen keresztül le tudsz menteni egy csapatot. Legyen egy metódus amiben le tudsz kérdezni egy csapatot játékosokkal együtt csapatnév alapján. Ezen kívül ami frissíti egy csapat pontszámát a kapott értékre id alapján és egy metódus ami ország és osztály alapján visszaadja az összes csaptot pontszám alapján csökkenő sorrendben.     

## Week 04

### 2022-05-04
Adott egy `Person` és egy `Child` entitás. A `Person`-nek van neve és életkora, a `Child`-nak neve és születési éve.
Ezenkívül a `Person` tartalmaz egy `List<Child>` típusú attribútumot, a `Child` pedig egy `Person` típusú attribútumot.
A két entitás között kétirányú egy-több kapcsolat van.
A feladat a megfelelő JPQL lekérdezések megírása az alábbiakra:

Keressük meg:

- azokat a gyerekeket, akik egy megadott év után születtek
- azokat a felnőtteket, akiknek több gyerekük is van
- azt a felnőttet, akinek a legtöbb gyereke van
- azt a gyereket, akinek a szülője a megadott nevű, és a megadott évben született (a gyerek)
- azt a szülőt, akihez a paraméterül megadott nevű gyerek tartozik
- az átlagos gyerekszámot
- azokat a gyerekeket, akik a legtöbben vannak testvérek

### 2022-05-05
Fejleszd tovább a Focis programot! Legyen egy `Player` entitás aminek attribútumai a neve és a játékos értéke. Egy csapathoz több játékos tartozik viszont egy játékos csak egy csapathoz tartozhat. Valósíts meg kétirányú kapcsolatot. <br>

Készíts egy `PlayerRepository` osztályt. Játékost kétféleképpen lehet lementeni, csapat id-val vagy anélkül. (Ha csapat id nélkül mentődik le akkor ún. free agent lesz akit később lehet majd egy csapathoz adni.) Legyen egy metódus ami megtalál egy játékost `id` alapján.<br>

A `Team` entitást bővítsd egy `int budget` attribútummal, mely a csapat költségvetését reprezentálja!

Hozzd létre a `TeamSrevice` osztályt és valósítsd meg a következő üzleti logikát. Egy cspat tudjon leigazolni egy játékost. Ha egy játékos már játszik valahol akkor a játékos értékén kell megvásárolni, ha nem játszik sehol akkor ingyenesen igazolható. További feltétel, hogy egy csapat nem költhet többet egy játékosra mint a költségvetésének a 20%-a! (Ha további repository metódusokra van szükséged hozzd létre nyugodtan)

## Week 05

### 2022-05-09
A vizsga gyakorló feladat kiírása itt található:
[kiírás](https://github.com/Strukturavaltas2-Halado-Java/java-strukturavalto2-halado/tree/master/lab-solutions/jpa-testing-exam-prepare)

## Week 07

### 2022-05-26
Hozz létre egy új Springes akalmazást `incrementer` néven, amely  
két réteget tartalmaz (controller, service) és a localhost:8080-t 
megnyitva vagy a böngészőt frissítve mindig eggyel nagyobb számot 
jelenít meg a böngészőablakban!

Opcionális: Valósítsd meg ugyanezt három réteggel(controller, service, repository)!

Valós adatbázist egyelőre természetesen nem kell használni a projektben, amit szükséges, 
azt a memóriában tárold el.
Figyelj arra, hogy az egyes rétegek tényleg a nekik szánt feladatot 
valósítsák meg!

Írj az `incrementer` alkalmazáshoz teszteket!

Kétrétegű alkalmazás esetén a következő tesztosztályok legyenek:

* service osztály tesztelésére:
	* unit teszt (nem indul el a Spring Boot)
* controller osztály tesztelésére:
	* unit teszt (mockolt service osztállyal, nem indul el a Spring Boot)
	* integrációs teszt a teljes alkalmazás elindításával

Háromrétegű alkalmazás esetén a következő tesztosztályok legyenek:

* repository osztály tesztelésére:
	* unit teszt (nem indul el a Spring Boot)
* service osztály tesztelésére:
	* unit teszt (mockolt repository osztállyal, nem indul el a Spring Boot)
	* integrációs teszt a teljes alkalmazás elindításával
* controller osztály tesztelésére:
	* unit teszt (mockolt service osztállyal, nem indul el a Spring Boot)
	* integrációs teszt a teljes alkalmazás elindításával
	
### 2022-05-27
A mai feladatban bicikli sharing alkalmazást készítünk.<br>

Adott a bikes.csv (https://github.com/Strukturavaltas2-Halado-Java/java-strukturavalto2-halado/blob/master/resources/bikes.csv) állomány, melyben egy-egy bicikli bérlés adatai találhatók:
* A bicikli azonosítója
* Az utolsó felhasználó egyedi azonosítója
* Az utolsó leadás pontos ideje
* Az utolsó úton megtett távolság kilométerben

Legyen egy `BikeService` nevű osztályod ami beolvassa a fájlt és eltárolja egy listában.
A beolvasás ne a program indulásakor, hanem az első kérés alkalmával valósuljon meg.
Azaz a listát visszaadó hívás esetén ellenőrizzük, hogyha a lista üres akkor beolvasunk,
ha nem akkor visszaadjuk a listát.<br>

A BikeController osztály a `/history` végponton kersztül érje el a lista minden elemét
minden adattal együtt.<br>

A `/users` végponton keresztül kapjuk meg a userek azonosítóit


## Week 08

### 2022-05-31

A mai feladatban ismét egy filmekkel foglalkozó alkalmazást kell összeraknod.<br>

A `Movie` entitásnak legyen egy azonosítója, egy címe, egy hossza, egy, az eddigi értékeléseket
tartalmazó listája és egy értékelésátlaga.
Minden egyes alkalommal amikor egy értékelést kap a film, akkor az értékelésátlag ennek megfelelően változik!<br>

Legyen egy `MovieService` osztályod, ami listában tárolja a filmeket. Kezdetben a lista üres, később tudunk filmet hozzáadni. <br>

Legyen egy `MovieController` ami alapértelmezetten az `api/movies` URL-en várja a kéréseket.<br>

A következő funkciókat kell megvalósítani:

* Lehessen lekérni az összes filmet illetve új filmet hozzáadni (cím és hossz) a `api/movies` végponton.
* A `/{id}` URL-en keresztül lehessen egy aktuális filmet lekérdezni.
* A `/{id}/ratings` URL-en keresztül lehessen egy filmre értékelést adni és az értékeléseit lekérdezni. GET esetén adjuk vissza a film értékeléseinek listáját. POST esetén egy számot várunk, de az értékelések listájával térünk vissza.


