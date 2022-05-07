package football_teams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

public class TeamPlayerServiceIT {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory(
            "TEST-pu");
    TeamRepository teamRepository = new TeamRepository(factory);
    PlayerRepository playerRepository = new PlayerRepository(factory);
    TeamPlayerService service = new TeamPlayerService(teamRepository, playerRepository);

    Team ajax;
    Team jongAjax;
    Player sart;

    @BeforeEach
    void setUp() {
// Given
        ajax = teamRepository.insertTeam(new Team("Ajax", "Netherland", League.EREDIVISIE, 999_000_000));
        sart = playerRepository.savePlayer(new Player("Sart", 1_000_000));
        service.transferPlayer(ajax.getId(), sart.getId());

        jongAjax = teamRepository.insertTeam(new Team("Jong Ajax", "Netherland", League.EERSTE_DIVISIE, 10_000_000));
    }

    @Test
    void testTransfer() {
// When
        service.transferPlayer(jongAjax.getId(), sart.getId());
//Then
        long id = sart.getId();
        Player loaded = playerRepository.findPlayerById(sart.getId());
        assertEquals("Jong Ajax", loaded.getTeam().getName());

        ajax = teamRepository.fetchTeamById(ajax.getId());
        assertEquals(1_000_000_000, ajax.getBudget());
        jongAjax = teamRepository.fetchTeamById(jongAjax.getId());
        assertEquals(9_000_000, jongAjax.getBudget());

// JUnit tesztbe a Kivételdobás tesztet.



//        assertThatThrownBy();
//        assertThatIllegalArgumentException();
    }
}
