package locations;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationsService {

    private final List<Location> locations;

    public LocationsService(List<Location> locations) {
        this.locations = new ArrayList<>(List.of(new Location(
                1L, "TesztHejj", 2, 3)));
    }


    public List<Location> getLocations() {
        return locations;
    }
}
