package empapp;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)

public class EmployeeTest {

    Employee employee;

    public EmployeeTest() {
        System.out.println("\nCONSTRUCTOR");
    }

    @BeforeAll
    static void before_All() {
        System.out.println("\nBEFORE ALL");
    }

    @BeforeEach
    void initEmployee() {
        System.out.println("\nINIT");
        employee = new Employee("John Doe", 1970);
    }

    @Test
    void testCreateEmloyeeWithYearOfBirth1700(){
        IllegalArgumentException iaexVar = assertThrows(IllegalArgumentException.class, ()->new Employee("John Doe", 1700));
        assertEquals("Year: 1700", iaexVar.getMessage());
    }


    @Test
//    @Order(2)
//    @Disabled("Until fix Projektmunka Rendelés")
//    @DisabledOnOs(OS.WINDOWS)
//    @DisplayNameGeneration
//            (DisplayNameGenerator.ReplaceUnderscores.class)
    void testGetAgeWithZero() {
        System.out.println("TC2");
        System.out.println(employee);
        employee.setName("Jack Doe");
        assertEquals(0,
                new Employee("John Doe", 1970)
                        .getAge(1970));
    }

    @Test
//    @Order(1)
    void testGetAge() {
        System.out.println("TC1");
        System.out.println(employee);
        assertEquals(52,
                new Employee("John Doe", 1970)
                        .getAge(2022));
    }


}