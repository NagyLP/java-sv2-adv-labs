package empapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeTest {

    @Test
    void testGetAge() {
        // GIVEN
        Employee employee = new Employee("John Doe", 1970);
        // WHEN
        int age = employee.getAge(2022);
        // THEN
        assertEquals(52, age);

        assertEquals(52, new Employee("John Doe", 1970).getAge(2022));
    }
}