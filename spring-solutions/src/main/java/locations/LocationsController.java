package locations;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/locations")
public class LocationsController {

    private final LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping
    public List<LocationDTO> getLocations(
            @RequestParam Optional<String> prefix) {
        return locationsService.getLocations(prefix);
    }

    @GetMapping("/{id}")
    public LocationDTO fetchLocationsById(
            @PathVariable("id") long id) {
        return locationsService.fetchLocationById(id);
    }

    @PostMapping
    public LocationDTO createLocation(@RequestBody CreateLocationCommand command) {
        return locationsService.createLocation(command);
    }

    @PutMapping(value = "/{id}")
    public LocationDTO updateLocation(
            @PathVariable("id") long id,
            @RequestBody UpdateLocationCommand command) {
        return locationsService.updateLocation(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable("id") long id) {
        locationsService.deleteLocation(id);
    }



//    @GetMapping()
//    public List<LocationDto> getLocation() {
//        return locationsService.getLocations();
//    }

// Ha kollekción dolgozik, akkor az OP (Win10) def. háttértéma(theme) aktív.

//    @GetMapping("/locations")
//    public List<Location> getLocations() {
//        return locations;
//    }

}
