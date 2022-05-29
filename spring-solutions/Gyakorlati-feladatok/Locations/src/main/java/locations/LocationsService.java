package locations;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LocationsService {

    private List<Location> locations = List.of(
            new Location(1L, "Langerhans-szigetek", 10.0, 10.0),
            new Location(2L, "Tatooine", 9_999_999.0, 9_999_999),
            new  Location(1L, "Osli", 47.3760, 17.0460));

    public LocationsService(List<Location> locations) {
        this.locations = locations;
    }

     public List<Location> getLocations() {
        return locations;
    }
}
