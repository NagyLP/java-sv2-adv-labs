package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class ActivityTrackerMain {

    private final EntityManager manager = entityModulOpen();


    public static void main(String[] args) {
        ActivityTrackerMain main = new ActivityTrackerMain();
        Activity oneActivity = new Activity(LocalDateTime.of(2022, 4, 20, 23, 0), "Running", ActivityType.RUNNING);
        Activity twoActivity = new Activity(LocalDateTime.of(2022, 4, 21, 19, 0), "Biking", ActivityType.BIKING);
        Activity threeActivity = new Activity(LocalDateTime.of(2022, 4, 23, 20, 0), "Hiking", ActivityType.HIKING);
        List<Activity> activities = new LinkedList<>();
        activities.add(oneActivity);
        activities.add(twoActivity);
        activities.add(threeActivity);


        main.manager.getTransaction().begin();
        main.insertActivities(activities, main.manager);
        main.manager.getTransaction().commit();
        main.entityModulClose();

    }

    private void insertActivities(List<Activity> activities, EntityManager manager) {
        for (Activity item : activities) {
            manager.persist(item);
        }
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
