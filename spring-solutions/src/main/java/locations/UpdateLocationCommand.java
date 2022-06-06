package locations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLocationCommand {

    @NotNull(message = "Aye, aye Sir: Name can not be null.")
    @NotBlank(message = "Name can not be blank, my Lord.")
    private String name;

    private double lat;

    private double lon;
}
