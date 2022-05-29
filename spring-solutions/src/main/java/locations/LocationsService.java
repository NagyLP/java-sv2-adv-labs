package locations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

@Service
public class LocationsService {

    private final ModelMapper modelMapper;

    private final List<Location> locations = Collections.synchronizedList(List.of(
            new Location(1L, "Langerhans-szigetek", 0.001, 0.001),
            new Location(2L, "Tatooine", 9_999_999.0, 9_999_999),
            new Location(1L, "Osli", 47.3760, 17.0460)));

//    public LocationsService(List<Location> locations) {
//        this.locations = locations;
//    }

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<LocationDto> getLocations() {
        Type targetGetType = new TypeToken<List<Location>>() {}
                .getType();
        return modelMapper.map(locations, targetGetType);
    }
}
