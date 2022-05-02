package football_teams;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class TeamRepository {

    private final EntityManagerFactory factory;
    private EntityManager manager;

    public TeamRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }


    public void addTeam(Team team) {
        manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(team);
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
    }

    public Team fetchTeamByPlayerName(String name) {
        manager = factory.createEntityManager();
        try {
            return manager.createQuery(
                            "select distint t from Team t" +
                                    "left join fetch t.players where t.name = :name",
                            Team.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } finally {
            manager.close();
        }
    }
}


