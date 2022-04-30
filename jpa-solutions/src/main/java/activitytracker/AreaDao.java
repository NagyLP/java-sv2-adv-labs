package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AreaDao {

    EntityManagerFactory factory;

    public AreaDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveArea(Area area) {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(area);
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
    }

    public Area findAreaById(long id) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery("select a from Area a join fetch a.citys where a.id = :id",
                            Area.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } finally {
            manager.close();
        }
    }

    public Area findAreaByName(String name) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager
                    .createQuery("select a from Area a join fetch a.activities where a.name = :name", Area.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } finally {
            manager.close();
        }
    }

}