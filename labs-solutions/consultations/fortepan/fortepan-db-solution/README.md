# Fortepan fényképadatbázis

## Adatbázis

Írd át az alkalmazást, hogy valós adatbázissal dolgozzon! Az adatbázis táblát 
is egy 3rd party eszköz (pl. Flyway) hozza létre!

## Integrációs tesztek

Írj tesztet a Repository osztály általunk használt metódusaira! A tesztesetek 
előkészítő adatait megadhatod egyedileg is vagy használhatod ezeket az 
utasításokat (itt szükséged lesz egy megfelelő konstruktorra is a `Photo` 
osztályban):

```java
repository.save(new Photo("Ló a mezőn", 1921, "Tóth Béla"));
repository.save(new Photo("Nő fák alatt, napernyővel", 1935, "Kiss József"));
repository.save(new Photo("Örkény István a feleségével", 1952, "Tóth Béla"));
repository.save(new Photo("Körjáték", 1965, "Szabó Géza"));
```

## Egyedi JPQL lekérdezések

Írd meg az alábbi JPQL lekérdezéseket a Repository osztályban:

* Keresd ki azokat a fényképeket, amelyeknek a leírásában szerepel egy paraméterül 
  átadott szövegrészlet (akár csak egy betű)!
* Keresd ki azokat a fényképeket, amelyek a paraméterül átadott év után készültek!
* Add vissza a fényképészek listáját (minden név csak egyszer szerepeljen)!

Írj mindegyikre egy-egy tesztesetet is!