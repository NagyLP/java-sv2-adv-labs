package biciklisharing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class BikeRental {

    private String userId;
    private String lastUserID;
    private LocalDateTime deliveryTime;
    private double distanceKm;

}
