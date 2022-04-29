package activitytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivityDaoIT {

    ActivityDao activityDao;
    Activity testOneActivity;

    @BeforeEach
    void setUp() {
        activityDao = new ActivityDao(Persistence.createEntityManagerFactory(
                "TEST-pu"));
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
    void testDeleteActivity() {
        activityDao.saveActivity(testOneActivity);
        activityDao.deleteActivity(testOneActivity.getId());

        assertThat(activityDao.listActivities())
                .isEmpty();
    }


    @Test
    void testUpdateActivity() {
        activityDao.saveActivity(testOneActivity);
        assertThat(activityDao.listActivities())
                .hasSize(1);

        activityDao.updateActivity(testOneActivity.getId(),
                "(Norbi) UPDATED");

        assertEquals("(Norbi) UPDATED",
                activityDao.findActivityById(testOneActivity.getId()).getDescription());
    }

    @Test
    void findActivityByIdWithLabels() {
        testOneActivity.setLabels(List.of("Cardio", "Country"));
        activityDao.saveActivity(testOneActivity);

        assertThat(testOneActivity.getLabels())
                .isNotNull()
                .hasSize(2)
                .containsOnly("Cardio", "Country");
        assertEquals(List.of("Cardio", "Country"),
                testOneActivity.getLabels());
    }

    @Test
    void findActivityByIdWithTrackPoints() {
        testOneActivity.addTrackpoint(new TrackPoint(LocalDate.of(2022,1,1),
                1.0,2.0));
        testOneActivity.addTrackpoint(new TrackPoint(LocalDate.of(2022,1,2),2.0,3.0));
        testOneActivity.addTrackpoint(new TrackPoint(LocalDate.of(2022,1,3),
                3.0,4.0));
        activityDao.saveActivity(testOneActivity);
        Activity acivityFromDao = activityDao.findActivityByIdWithTrackPoints(
                testOneActivity.getId());

        assertThat(acivityFromDao.getTrackPoints())
                .isNotNull()
                .hasSize(3);
        assertEquals(LocalDate.of(2022,1,1), acivityFromDao
                .getTrackPoints().get(0).getTime());
        assertEquals(1.0, acivityFromDao
                .getTrackPoints().get(0).getLatitude());
        assertEquals(4.0, acivityFromDao
                .getTrackPoints().get(2).getLongitude());
    }
}