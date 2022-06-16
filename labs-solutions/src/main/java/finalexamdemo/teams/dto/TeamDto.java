package finalexamdemo.teams.dto;


import finalexamdemo.players.dto.PlayerDto;
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
public class TeamDto {

    private Long id;
    private String name;
    private List<PlayerDto> players;
}
