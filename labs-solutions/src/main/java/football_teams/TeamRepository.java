package football_teams;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class TeamRepository {

    private final EntityManagerFactory factory;

    public TeamRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }


    public void insertTeam(Team team) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(team);
        manager.getTransaction().commit();
        manager.close();
    }

    public Team fetchTeamWithPlayerName(String name) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery(
                            "select distinct t from Team t" +
                                    " left join fetch t.players where t.name = :name",
                            Team.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } finally {
            manager.close();
        }
    }

    public void updateTeamScoreById(long id, int score) {
        EntityManager manager = factory.createEntityManager();
        Team team = manager.getReference(Team.class, id);
        manager.getTransaction().begin();
        team.setScore(score);
        manager.getTransaction().commit();
        manager.close();
    }


    public List<Team> fetchTeamsByCountryNLeague(String country, League league) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createNamedQuery("fetchTeamsByCountryNLeague")
                    .setParameter("country", country)
                    .setParameter("league", league)
                    .getResultList();
        } finally {
            manager.close();
        }
    }
}


