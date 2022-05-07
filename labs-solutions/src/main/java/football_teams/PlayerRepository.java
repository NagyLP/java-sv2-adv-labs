package football_teams;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class PlayerRepository {

    private EntityManagerFactory factory;

    public PlayerRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }

// Focicsapat 3.0

    public void deleteAll() {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("delete from Player p").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Player> findAll() {
        EntityManager em = factory.createEntityManager();
        try {
            return em.createQuery("select p from Player p", Player.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

// Focicsapat 2.1

    public Player savePlayer(Player player) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(player);
        manager.getTransaction().commit();
        manager.close();
        return player;
    }

    public Player savePlayerWithTeam(Player player, long teamId) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Team team = em.getReference(Team.class, teamId);
        player.setTeam(team);
        em.persist(player);
        em.getTransaction().commit();
        em.close();
        return player;
    }

    public Player findPlayerById(long playerId) {
        EntityManager em = factory.createEntityManager();
        Player player = em.find(Player.class, playerId);
        em.close();
        return player;
    }

    public Player updatePlayerTeam(long playerId, long teamId) {
        EntityManager em = factory.createEntityManager();
        Team team = em.getReference(Team.class, teamId);
        Player player = em.getReference(Player.class, playerId);
        player.setTeam(team);
        em.getTransaction().begin();
        em.persist(player);
        em.getTransaction().commit();
        em.close();
        return player;
    }
}
