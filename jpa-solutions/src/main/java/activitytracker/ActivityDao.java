package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ActivityDao {

    private final EntityManagerFactory factory;
    private final EntityManager manager;

    public ActivityDao(EntityManagerFactory factory) {
        this.factory = factory;
        this.manager = factory.createEntityManager();
    }

    public void saveActivity(Activity activity) {
        manager.getTransaction().begin();
        manager.persist(activity);
        manager.getTransaction().commit();
        entityModulClose();
    }

    public List<Activity> listActivities() {
        return manager
                .createQuery("select a from Activity a", Activity.class)
                .getResultList();
    }

    public Activity findActivityById(long id) {
        return manager.find(Activity.class, id);
    }

    public void deleteActivity(long id) {
        manager.getTransaction().begin();
        manager.remove(manager.getReference(Activity.class, id));
    }

    private void entityModulClose() {
        manager.close();
        factory.close();
    }
}
