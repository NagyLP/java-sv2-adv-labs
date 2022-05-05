package locations;

import java.util.List;

//@Service
public class LocationsService {

    private final List<Location> locations;

    public LocationsService(List<Location> locations) {
        this.locations = locations;
    }


    public List<Location> getLocations() {
        return locations;
    }
}
