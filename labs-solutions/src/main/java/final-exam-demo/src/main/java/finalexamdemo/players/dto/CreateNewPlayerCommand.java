package finalexamdemo.players.dto;


import finalexamdemo.players.model.PlayerPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateNewPlayerCommand {

    @NotBlank
    private String name;
    @Past
    private LocalDate dateOfBirth;
    private PlayerPosition playerPosition;
}
