package finalexamdemo.players.dto;


import finalexamdemo.players.model.PlayerPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerDto {

    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private PlayerPosition playerPosition;
}
