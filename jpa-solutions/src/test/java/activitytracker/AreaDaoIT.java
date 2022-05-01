package activitytracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
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
    void testSaveArea() {
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
    void testCitysSaveAndFind() {
        Area testArea = new Area("Hanság");
        testArea.putCity(new City("Bősárkány", 2046));
        testArea.putCity(new City("Rábcakapi", 181));
        testArea.putCity(new City("Osli", 911));

        areaDao.saveArea(testArea);
        Area areaFromDao = areaDao.findAreaById(testArea.getId());

        assertThat(areaFromDao.getCities())
                .isNotNull()
                .hasSize(3)
                .containsKeys("Bősárkány", "Rábcakapi", "Osli")
                .extractingFromEntries(this::extractCityName,
                        this::extractCityPopulation)
                .containsOnly(tuple("Bősárkány", 2046),
                        tuple("Rábcakapi", 181),
                        tuple("Osli", 911));
    }

//        assertThat(areaFromDao.getCities().values())
//                .extracting(City::getName, City::getPopulation)
//                .containsOnly(tuple("Bősárkány", 2046),
//                        tuple("Rábcakapi", 181),
//                        tuple("Osli", 911));

    @Test
    void findActivityByIdWithTrackPoints() {
        Activity testActivity = new Activity(LocalDateTime.of(2022, 4, 29, 2, 45), "MindRunning", ActivityType.RUNNING);
        testActivity.addTrackpoint(new TrackPoint(LocalDate.of(2022, 4, 29), 1, 2));
        testActivity.addTrackpoint(new TrackPoint(LocalDate.of(2022, 4, 30), 3, 4));
        testActivity.addTrackpoint(new TrackPoint(LocalDate.of(2022, 5, 1), 5, 6));

        activityDao.saveActivity(testActivity);
        Activity activityFromDao = activityDao.findActivityByIdWithTrackPoints(
                testActivity.getId());

        assertThat(activityFromDao.getTrackPoints())
                .isNotNull()
                .hasSize(3)
                .extracting(TrackPoint::getLatitude, TrackPoint::getLongitude)
                .containsOnly(tuple(1.0, 2.0),
                        tuple(3.0, 4.0),
                        tuple(5.0, 6.0));
    }


    private String extractCityName(Map.Entry<String, City> entry) {
        return entry.getValue().getName();
    }

    private int extractCityPopulation(Map.Entry<String, City> entry) {
        return entry.getValue().getPopulation();
    }

}
