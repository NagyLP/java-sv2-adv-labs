package locations;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

//import java.util.List;
import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/api/locations")
public class LocationsController {

    private final LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping
//    public List<LocationDTO> getLocations(
    public LocationsDTO getLocations(
            @RequestParam Optional<String> prefix) {
//        return locationsService.getLocations(prefix);
        return new LocationsDTO(locationsService.getLocations(prefix));
    }

    @GetMapping("/{id}")
    public LocationDTO fetchLocationsById(
            @PathVariable("id") long id) {
        return locationsService.fetchLocationById(id);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity fetchLocationsById(@PathVariable("id") long id) {
//        try {
//            return ResponseEntity.ok(
//                    locationsService.fetchLocationById(id));
//        } catch (IllegalArgumentException iae) {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
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

    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(LocationNotFoundException lnfException) {
        Problem problem = Problem.builder()
                .withType(URI.create("locations/NOT-FOUND"))
                .withTitle("NOT FOUND")
                .withStatus(Status.NOT_FOUND)
                .withDetail(lnfException.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
//    APPlication.json-ként jelenik meg a válasz header-jében
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
//   -- || -- Törzsében pedig ez:
                .body(problem);
    }

}
