package locations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationsService {

    private final ModelMapper modelMapper;

    private final List<Location> locations = Collections.synchronizedList(new ArrayList<>(List.of(
            new Location(1L, "Langerhans-szigetek", 0.001, 0.001),
            new Location(2L, "Tatooine", 9_999_999.0, 9_999_999),
            new Location(3L, "Osli", 47.3760, 17.0460))));

//    public LocationsService(List<Location> locations) {
//        this.locations = locations;
//    }

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

//    public List<LocationDto> getLocations() {
//        Type targetGetType = new TypeToken<List<Location>>() {}
//                .getType();
//        return modelMapper.map(locations, targetGetType);
//    }

    public List<LocationDto> getLocations(Optional<String> prefix) {
        Type targetGetType = new TypeToken<List<LocationDto>>() {}
                .getType();
        List<Location> filteredLocations = locations.stream()
                .filter(location -> prefix.isEmpty()
                        || location.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .collect(Collectors.toList());
        return modelMapper.map(filteredLocations, targetGetType);
    }

    public LocationDto fetchLocationById(long id) {
        return modelMapper.map(
                locations.stream()
                        .filter(location -> location.getId() == id)
                        .findAny()
                        .orElseThrow(() -> new IllegalArgumentException("Location ID not fund: " + id))
                , LocationDto.class);
    }
}
