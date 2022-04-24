package activitytracker;

import org.junit.jupiter.api.BeforeEach;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class ActivityDaoTest {

    EntityManagerFactory factory;
    ActivityDao activityDao;

    @BeforeEach
    void setUp() {
        activityDao = new ActivityDao(Persistence.createEntityManagerFactory("pu"));
    }


}