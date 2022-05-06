package football_teams;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TeamPlayerServiceTest {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory(
            "pu");
    TeamRepository teamRepository = new TeamRepository(factory);
    PlayerRepository playerRepository = new PlayerRepository(factory);
    TeamPlayerService service = new TeamPlayerService(teamRepository, playerRepository);


    @Test
    void testTransfer() {
        Team team = teamRepository.insertTeam(new Team("Ajax", "Netherland", League.EREDIVISIE, 10_000_000));
        Player player = playerRepository.savePlayer(new Player("Sart", 100_000));
        service.transferPlayer(team.getId(), player.getId());
        System.out.println(teamRepository.fetchTeamByNameWithPlayers("Ajax"));

        Team teamTwoo = teamRepository.insertTeam(new Team("Honvéd SC", "Magyarország", League.EREDIVISIE, 10_000_000));
        service.transferPlayer(teamTwoo.getId(), player.getId());
        System.out.println(teamTwoo.getPlayers());

        Team teamThree = teamRepository.insertTeam(new Team("Soroksár SC", "Magyarország", League.EREDIVISIE, 10_000));
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
                () -> service.transferPlayer(teamThree.getId(), player.getId()));
        assertEquals("Cannot transfer player, Team budget: " + teamThree.getBudget(), iae.getMessage());
    }
}
