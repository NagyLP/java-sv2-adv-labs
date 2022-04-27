package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ActivityDao {

    private final EntityManagerFactory factory;
    private EntityManager manager;

    public ActivityDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveActivity(Activity activity) {
        manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(activity);
        manager.getTransaction().commit();
        manager.close();
    }

    public List<Activity> listActivities() {
        manager = factory.createEntityManager();
        try {
            return manager
                    .createQuery("select a from Activity a", Activity.class)
                    .getResultList();
        } finally {
            manager.close();
        }
    }

    public Activity findActivityById(long id) {
        manager = factory.createEntityManager();
        try {
            return manager.find(Activity.class, id);
        } finally {
            manager.close();
        }
    }

    public void deleteActivity(long id) {
        manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.remove(manager
                    .getReference(Activity.class, id));
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
    }

    public void updateActivity(long id, String description) {
        manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            (manager.find(Activity.class, id))
                    .setDescription(description);
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
    }

    public Activity findActivityByIdWithLabels(long id) {
        manager = factory.createEntityManager();
        try {
            return manager
                    .createQuery("select a from Activity a join fetch a.labels where id = :id", Activity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } finally {
            manager.close();
        }
    }

}
