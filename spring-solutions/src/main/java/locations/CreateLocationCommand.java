package locations;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLocationCommand {

    @NotNull(message = "Aye, aye Sir: Name can not be null.")
    @NotBlank(message = "Name can not be blank, my Lord.")
    @Schema(description = "Name of the Location",
            example = "Machu Picchu")
    private String name;

    @NotNull
    @Schema(description = "Latitude of the Location",
            example = "-13.157832702")
    private double lat;

    @NotNull
    @Schema(description = "Longitude of the Location",
            example = "-72.540664504")
    private double lon;
}
