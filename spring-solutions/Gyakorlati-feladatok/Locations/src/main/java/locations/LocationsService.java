package locations;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class LocationsService {

    private List<Location> locations = new ArrayList<>(List.of(new Location(
            1L, "Langerhans-szigetek", 10.0, 10.0), new Location(
            2L, "Tatooine", 9_999_999.0, 9_999_999)));

    public LocationsService(List<Location> locations) {
        this.locations = locations;
    }

     public List<Location> getLocations() {
        return locations;
    }
}
