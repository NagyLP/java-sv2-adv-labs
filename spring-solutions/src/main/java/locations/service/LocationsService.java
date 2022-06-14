package locations.service;

import locations.dtos.CreateLocationCommand;
import locations.dtos.LocationDTO;
import locations.dtos.UpdateLocationCommand;
import locations.exeptions.LocationNotFoundException;
import locations.model.Location;
import locations.repository.LocationsRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class LocationsService {

    private LocationsRepository repository;
    private final ModelMapper modelMapper;
    // SZÁLBIZTOS
    private final AtomicLong idGenerator = new AtomicLong();

//    private final List<Location> locations = Collections.synchronizedList(new ArrayList<>(List.of(
//            new Location(idGenerator.incrementAndGet(), "Langerhans-szigetek", 0.001, 0.001),
//            new Location(idGenerator.incrementAndGet(), "Tatooine", 9_999_999.0, 9_999_999),
//            new Location(idGenerator.incrementAndGet(), "Osli", 47.3760, 17.0460))));

//    public LocationsService(LocationsRepository repository, ModelMapper modelMapper) {
//        this.repository = repository;
//        this.modelMapper = modelMapper;
//    }


//    public LocationsService(List<Location> locations) {
//        this.locations = locations;
//    }

//    public LocationsService(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }

//    public List<LocationDto> getLocations() {
//        Type targetGetType = new TypeToken<List<Location>>() {}
//                .getType();
//        return modelMapper.map(locations, targetGetType);
//    }


    public List<LocationDTO> getLocations(Optional<String> prefix) {
        Type targetGetType = new TypeToken<List<LocationDTO>>() {
        }
                .getType();
        List<Location> filteredLocations = repository.findAll()
                .stream()
                .filter(location -> prefix.isEmpty()
                        || location.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .collect(Collectors.toList());
        return modelMapper.map(filteredLocations, targetGetType);
    }


    public LocationDTO fetchLocationById(long id) {
        return modelMapper.map(
                repository.findById(id)
//                locations.stream()
//                        .filter(location -> location.getId() == id)
//                        .findAny()
//                        .orElseThrow(() -> new IllegalArgumentException("Location ID not fund: " + id))
//                        .orElseThrow(() -> new LocationNotFoundException("Location not found ID: " + id))
                        .orElseThrow(() -> new LocationNotFoundException(id))
                , LocationDTO.class);
    }


    public LocationDTO createLocation(CreateLocationCommand command) {
        Location location = new Location(
                idGenerator.incrementAndGet(), command.getName(), command.getLat(), command.getLon());
        repository.save(location);
//        locations.add(location);
        return modelMapper.map(location, LocationDTO.class);
    }


//    @Transactional
    public LocationDTO updateLocation(long id, UpdateLocationCommand command) {
        Location location =
                repository.findById(id)
//                locations.stream()
//                        .filter(l -> l.getId() == id)
//                        .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Location not found: " + id));
//                        .orElseThrow(() -> new LocationNotFoundException("Location not found ID: " + id));
                        .orElseThrow(() -> new LocationNotFoundException(id));
        location.setName(command.getName());
        location.setLat(command.getLat());
        location.setLon(command.getLon());
        return modelMapper.map(location, LocationDTO.class);
    }


    public void deleteLocation(long id) {
        repository.deleteById(id);
//        Location location = locations.stream()
//                .filter(l -> l.getId() == id)
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Location not found: " + id));
//                .orElseThrow(() -> new LocationNotFoundException("Location not found ID: " + id));
//        locations.remove(location);
    }


    public void deleteAllLocations(){
        repository.deleteAll();
    }
}
