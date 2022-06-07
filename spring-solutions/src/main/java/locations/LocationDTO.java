package locations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.internal.bytebuddy.asm.Advice;

import javax.validation.constraints.DecimalMax;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {

    private Long id;
    private String name;
    private double lat;
    private double lon;


//    public LocationDTO(String name, double lat, double lon) {
//        this.name = name;
//        this.lat = lat;
//        this.lon = lon;
//    }
}
