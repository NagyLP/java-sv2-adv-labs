package locations;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLocationCommand {

    @Schema(description = "Name of the Location",
            example = "Machu Picchu")
//    @NotNull(message = "Aye, aye Sir: Name can not be null.")
    @NotBlank(message = "Name can not be blank, my Lord.")
    @Size(min = 2, max = 25)
    private String name;

    @Schema(description = "Latitude of the Location",
            example = "-13.157832702")
    @NotNull
    @Min(value = -90)
    @Max(value = 90)
    private double lat;

    @Schema(description = "Longitude of the Location",
            example = "-72.540664504")
    @NotNull
    @Min(value = -180)
    @Max(value = 180)
    private double lon;
}
