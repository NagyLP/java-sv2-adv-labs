package locations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/locations")
public class LocationsController {

    private final LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }


    @GetMapping()
    public List<LocationDto> getLocation() {
        return locationsService.getLocations();
    }

// Ha kollekción dolgozik, akkor az OP (Win10) def. háttértéma(theme) aktív.

//    @GetMapping("/locations")
//    public List<Location> getLocations() {
//        return locations;
//    }


}
