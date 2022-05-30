package Nesting;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class NestDao {

    private EntityManagerFactory factory;

    public NestDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveNest(Nest nest) {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(nest);
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
    }

    public Nest findNestById(long id) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.find(Nest.class, id);
        } finally {
            manager.close();
        }
    }

    public Nest findNestWithMinBirds() {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery(
                            "select distinct n from Nest n left join fetch n.bird where n.bird.size" +
                                    " = (select min(n.bird.size) from Nest n)", Nest.class)
                    .getSingleResult();
        } finally {
            manager.close();
        }
    }

    public long countNestWithEggsGiven(int eggs) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery(
                            "select count(n) from Nest n where n.numberOfEggs = :eggs", Long.class)
                    .getSingleResult();
        } finally {
            manager.close();
        }
    }

}
