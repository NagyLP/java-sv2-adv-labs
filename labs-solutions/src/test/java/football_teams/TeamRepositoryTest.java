package football_teams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TeamRepositoryTest {

    TeamRepository teamRepo;

    @BeforeEach
    public void setUp() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(
                "football_teams");
        teamRepo = new TeamRepository(factory);
    }

    @Test
    @DisplayName("Test:Create-Read")
    void fetchTeamWithPlayerName() {
        Team team = new Team("Ajax", "Netherlands", League.EREDIVISIE, 99);
        team.addPlayers("Edwin van der Sart");
        team.addPlayers("Hendrik Johannes Cruijff");
        team.addPlayers("és John Doe");
        teamRepo.insertTeam(team);
        Team teamFromDao = teamRepo.fetchTeamWithPlayerName("Ajax");

        assertThat(teamFromDao.getPlayers())
                .isNotNull()
                .hasSize(3)
                .extracting(t -> teamFromDao.getPlayers())
                .containsOnly(List.of("Edwin van der Sart",
                        "Hendrik Johannes Cruijff",
                        "és John Doe"));
    }


    @Test
    void testUpdateTeamScoreById() {
        Team team = new Team("Ajax", "Netherlands", League.EREDIVISIE, 0);
        teamRepo.insertTeam(team);
        teamRepo.updateTeamScoreById(team.getId(),
                987_654_321);

        assertThat(teamRepo.fetchTeamWithPlayerName("Ajax").getScore())
                .isEqualTo(987_654_321);
    }

    @Test
    void fetchTeamsByCountryNLeague() {
        teamRepo.insertTeam(new Team("Ajax", "Netherlands", League.EREDIVISIE, 4));
        teamRepo.insertTeam(new Team("PSV Eindhoven", "Netherlands", League.EREDIVISIE, 3));
        teamRepo.insertTeam(new Team("Ferencvárosi TC", "Magyarország", League.EREDIVISIE, 2));
        teamRepo.insertTeam(new Team("FC Emmen", "Netherlands", League.EERSTE_DIVISIE, 1));

        assertThat(teamRepo.fetchTeamsByCountryNLeague("Netherlands", League.EREDIVISIE))
                .isNotNull()
                .hasSize(2)
                .extracting(Team::getName)
                .containsExactly("Ajax", "PSV Eindhoven");

        assertThat(teamRepo.fetchTeamsByCountryNLeague("Magyarország", League.EREDIVISIE))
                .isNotNull()
                .hasSize(1)
                .extracting(Team::getName)
                .containsOnly("Ferencvárosi TC");

        assertThat(teamRepo.fetchTeamsByCountryNLeague("Netherlands", League.EERSTE_DIVISIE))
                .isNotNull()
                .hasSize(1)
                .extracting(Team::getScore)
                .containsOnly(1);
    }

}