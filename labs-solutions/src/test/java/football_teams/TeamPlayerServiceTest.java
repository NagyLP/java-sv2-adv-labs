package football_teams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamPlayerServiceTest {

    @Mock
    TeamRepository teamRepository;
    @Mock
    PlayerRepository playerRepository;
    @InjectMocks
    TeamPlayerService service;
    Team ajax;
    Team jongAjax;
    Player sart;

    @BeforeEach
    void setUp() {
// Given
        ajax = new Team("Ajax", "Netherland", League.EREDIVISIE,
                999_000_000);
        jongAjax = new Team("Jong Ajax", "Netherland", League.EERSTE_DIVISIE,
                1_000_000);
        sart = new Player("Sart",
                1_000_000);
    }

    @Test
    @DisplayName("When Budget is to low.")
    void transferPlayer() {
        when(teamRepository.fetchTeamById(1L)).thenReturn(jongAjax);
        sart.setTeam(ajax);
        when(playerRepository.findPlayerById(2L)).thenReturn(sart);

        assertThrows(IllegalArgumentException.class,
                () -> service.transferPlayer(1L, 2L))
                .getMessage()
                .contentEquals("Cannot transfer player, Team budget: "
                        + ajax.getBudget());
    }

    @Test
    @DisplayName("When Player have no Team.")
    void transferPlayerPlayerWithNoTeam() {
        when(teamRepository.fetchTeamById(1L)).thenReturn(ajax);
        sart.setTeam(ajax);

        assertThat(teamRepository.fetchTeamById(1L).getBudget())
                .isEqualTo(999_000_000);
    }

}
