package activitytracker;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivityDaoIT {

    EntityManagerFactory factory;
    ActivityDao activityDao;
    Activity testOneActivity;

    @BeforeEach
    void setUp() {
        activityDao = new ActivityDao(Persistence.createEntityManagerFactory("pu"));
        testOneActivity = new Activity(LocalDateTime.of(2022, 4, 24, 23, 0),
                "NightFlow", ActivityType.RUNNING);
    }

//    @AfterEach
//    void closeFactory() {
//        manager.close();
//    }

    @Test
    void testSaveActivity() {
        activityDao.saveActivity(testOneActivity);
        Activity expected = activityDao.findActivityById(testOneActivity.getId());

        assertEquals("NightFlow", expected.getDescription());
        assertEquals(ActivityType.RUNNING, expected.getType());
    }

    @Test
    void testListActivities() {
        Activity oneActivity = new Activity(LocalDateTime.of(2022, 4, 20, 23, 0),
                "Running", ActivityType.RUNNING);
        Activity twoActivity = new Activity(LocalDateTime.of(2022, 4, 21, 19, 0),
                "Biking", ActivityType.BIKING);
        Activity threeActivity = new Activity(LocalDateTime.of(2022, 4, 23, 20, 0),
                "Hiking", ActivityType.HIKING);
        activityDao.saveActivity(oneActivity);
        activityDao.saveActivity(twoActivity);
        activityDao.saveActivity(threeActivity);

        assertEquals(3, activityDao.listActivities().size());
        assertThat(activityDao.listActivities())
                .hasSize(3)
                .extracting(Activity::getId, Activity::getType)
                .contains(tuple(1L, ActivityType.RUNNING),
                        tuple(3L, ActivityType.HIKING));
    }

    @Test
    void deleteActivity() {
        activityDao.saveActivity(testOneActivity);
        activityDao.deleteActivity(testOneActivity.getId());

        assertThat(activityDao.listActivities())
                .hasSize(0);
    }
}