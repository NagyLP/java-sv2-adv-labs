package finalexamdemo.teams.dto;

import finalexamdemo.players.dto.CreateNewPlayerCommand;
import finalexamdemo.players.model.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateTeamCommand {

    private String name;
    private List<CreateNewPlayerCommand> players;
}
