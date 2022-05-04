package elearning;

public class JPQL_Querys {

// Adott év után született gyerekek:
    private String queryChildBirthAfter =
            "select c from Child c where c.yearOfBirth > :year," +
                    "Child.class";

// Felnőttek, akiknek több gyerekük van:
    private String adultWithMoreChild =
            "select p from Person p where p.children.size > 1 order by p.name," +
                    "Person.class";

// Felnőtt, akinek a legtöbb gyereke van:
    private String adultWithMaxChild =
        " select p form Person p where p.children.size = (select max(p.chidren.size) from Person p)," +
                "Person.class";

// Gyerek, akinek adott nevű a szűlője, és adott évben született
    private String childWithPersonNameNBirthday =
            "select distinct c from Child c join fetch c. person where c.person.name = :name and c.yeraOfBirth = :year " +
                    "Child.class";

// Szülő, akihez adott nevű gyerek tartozik:
    private String personWithChildName =
            "select c.person from Child c where c.name = :name)" +
                    "Person.class";

// Átlagos gyekeszám:
    private String averageChildAmount =
            "select avg(c.person) from Person p," +
                    "Double.class";

// Gyerekek, akik legtöbben testvérek:
private String fullyBrother =
        "select c from Child c where c.person.children.size = (select max(q.children.size) form Person q)," +
                "Child.class";



}
