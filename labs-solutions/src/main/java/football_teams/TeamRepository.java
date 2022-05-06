package football_teams;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class TeamRepository {

    private final EntityManagerFactory factory;

    public TeamRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }


    public Team insertTeam(Team team) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(team);
        manager.getTransaction().commit();
        manager.close();
        return team;
    }

    public Team fetchTeamById(long id) {
        EntityManager manager = factory.createEntityManager();
        Team team = manager.find(Team.class, id);
        manager.close();
        return team;
    }

    public Team fetchTeamByNameWithPlayers(String name) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery(
                            "select distinct t from Team t" +
                                    " left join fetch t.players where t.name = :name", Team.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } finally {
            manager.close();
        }
    }

    public void updateTeamScoreById(long id, int score) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Team team = manager.getReference(Team.class, id);
        team.setScore(team.getPoints() + score);
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

    public Team updateBudget(long teamId, int price) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Team team = manager.getReference(Team.class, teamId);
        team.setBudget(price);
        manager.getTransaction().commit();
        manager.close();
        return team;
    }
}


