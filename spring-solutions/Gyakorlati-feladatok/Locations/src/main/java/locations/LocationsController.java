package locations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class LocationsController {

    private List<Location> locations = new ArrayList<>(Arrays.asList(new Location(
            1L, "TesztHejj", 2, 3)));

    @GetMapping("/location")
    public String getLocation() {
        return locations.toString();
    }

// Ha kollekción dolgozik, akkor az OP (Win10) def. háttérszíne aktív.

    @GetMapping("/locations")
    public List<Location> getLocations() {
        return locations;
    }


}
