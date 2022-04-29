package activitytracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AreaDaoIT {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory(
            "TEST-pu");

    ActivityDao activityDao = new ActivityDao(factory);
    AreaDao areaDao = new AreaDao(factory);

    @AfterEach
    void contextClose() {
        factory.close();
    }

    @Test
    void saveArea() {
        Activity oneActivity = new Activity(LocalDateTime.of(2022, 4, 29, 2, 45), "MindRunning", ActivityType.RUNNING);
        Activity twoActivity = new Activity(LocalDateTime.of(2022, 5, 29, 2, 45), "Hiking", ActivityType.HIKING);
        Activity threeActivity = new Activity(LocalDateTime.of(2023, 4, 29, 2, 45), "Biking", ActivityType.BIKING);
        Area oneArea = new Area("Duna-Tisza köze");
        Area twooArea = new Area("Aggteleki-hegység");
        Area threeArea = new Area("Fertő-Hanság");
        oneArea.addActivity(oneActivity);
        twooArea.addActivity(oneActivity);
        twooArea.addActivity(twoActivity);
        threeArea.addActivity(oneActivity);
        threeArea.addActivity(twoActivity);
        threeArea.addActivity(threeActivity);
        activityDao.saveActivity(oneActivity);
        activityDao.saveActivity(twoActivity);
        activityDao.saveActivity(threeActivity);
        areaDao.saveArea(oneArea);
        areaDao.saveArea(twooArea);
        areaDao.saveArea(threeArea);

        assertThat(areaDao.findAreaByName("Fertő-Hanság"))
                .extracting(Area::getActivities)
                .isNotNull()
                .isNotEqualTo(List.of(oneActivity, twoActivity, threeActivity));

        assertEquals(List.of("MindRunning", "Hiking", "Biking"), areaDao
                .findAreaByName("Fertő-Hanság")
                .getActivities()
                .stream().map(Activity::getDescription).toList());
    }

    @Test
    void findAreaByName() {
    }
}