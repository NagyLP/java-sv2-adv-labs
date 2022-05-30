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
    public List<LocationDto> getLocations(@RequestParam Optional<String> prefix) {
        return locationsService.getLocations(prefix);
    }

    @GetMapping("/{id}")
    public LocationDto fetchLocationsById(@PathVariable("id") long id) {
        return locationsService.fetchLocationById(id);
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
