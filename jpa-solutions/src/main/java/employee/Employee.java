package employee;

import javax.persistence.*;

//@Entity
//@Table(name = "employees")

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "emp_name")
    private String name;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

}
