package nesting;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class BirdDao {

    private EntityManagerFactory factory;

    public BirdDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveBird(Bird bird){
        EntityManager manager = factory.createEntityManager();
        try{
            manager.getTransaction().begin();
            manager.persist(bird);
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
    }

    public List<Bird> listBirds(){
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery(
                    "select b from Bird b", Bird.class)
                    .getResultList();
        } finally {
            manager.close();
        }
    }

    public List<Bird> listBirdsSpeciesGiven(BirdSpecies species){
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery(
                            "select b from Bird b where b.birdSpecies = :species", Bird.class)
                    .setParameter("species", species)
                    .getResultList();
        } finally {
            manager.close();
        }
    }

    public List<Bird> listBirdsWithEggsGiven(int eggs){
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery(
                            "select b from Bird b where b.nest.numberOfEggs = :eggs", Bird.class)
                    .setParameter("eggs", eggs)
                    .getResultList();
        } finally {
            manager.close();
        }
    }

    public void deleteBird(long id){
        EntityManager manager = factory.createEntityManager();
        try{
            manager.getTransaction().begin();
//            manager.getReference(Bird.class, id);
//            manager.remove(id);
            manager.createQuery(
                    "delete from Bird where id = :id");
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
    }


}
