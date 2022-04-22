package employee;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EmployeeMain {

    private final EntityManager manager = entityModulOpen();


    public static void main(String[] args) {
        EmployeeMain main = new EmployeeMain();

        main.manager.getTransaction().begin();

        main.manager.persist(new Employee("John Doe"));

        main.manager.getTransaction().commit();
        main.entityModulClose();

    }

    private EntityManager entityModulOpen() {
        return Persistence
                .createEntityManagerFactory("pu")
                .createEntityManager();
    }
    private void entityModulClose() {
        manager.close();
    }
}
