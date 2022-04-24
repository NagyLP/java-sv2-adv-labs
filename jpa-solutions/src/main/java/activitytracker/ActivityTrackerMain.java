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
        Activity oneActivity = new Activity(LocalDateTime.of(2022, 4, 20, 23, 0),
                "Running", ActivityType.RUNNING);
        Activity twoActivity = new Activity(LocalDateTime.of(2022, 4, 21, 19, 0),
                "Biking", ActivityType.BIKING);
        Activity threeActivity = new Activity(LocalDateTime.of(2022, 4, 23, 20, 0),
                "Hiking", ActivityType.HIKING);
        Activity fourActivity = new Activity(LocalDateTime.of(2022, 4, 24, 0, 0),
                "Deleting", ActivityType.HIKING);
        List<Activity> activities = new LinkedList<>();
        activities.add(oneActivity);
        activities.add(twoActivity);
        activities.add(threeActivity);
        activities.add(fourActivity);

        main.manager.getTransaction().begin();
        main.insertActivities(activities, main.manager);
        main.manager.getTransaction().commit();

        System.out.println("\n" +
                main.listQuery());
        System.out.println("\n" +
                main.manager.find(Activity.class, activities.get(0).getId()));


        main.manager.getTransaction().begin();
        main.manager.find(Activity.class, activities.get(2).getId()).setDescription("HiKingOfEdward!");
        main.manager.getTransaction().commit();

        System.out.println("\n" +
                main.manager.find(Activity.class, activities.get(0).getId()));
        System.out.println("\n" +
                main.listQuery());


        main.removeById(activities, 3);
        System.out.println("\n" +
                main.listQuery());


        main.entityModulClose();
    }

    private void removeById(List<Activity> activities, int id) {
        manager.getTransaction().begin();
        manager.remove(manager.find(Activity.class, activities.get(id).getId()));
        manager.getTransaction().commit();
    }

    private List<Activity> listQuery() {
        return manager.createQuery("select a from Activity a", Activity.class)
                .getResultList();
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
