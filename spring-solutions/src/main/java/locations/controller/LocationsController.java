package locations.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import locations.dtos.CreateLocationCommand;
import locations.dtos.LocationDTO;
import locations.dtos.LocationsDTO;
import locations.dtos.UpdateLocationCommand;
import locations.service.LocationsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
//import org.zalando.problem.Problem;
//import org.zalando.problem.Status;

//import java.util.List;
import javax.validation.Valid;
//import java.net.URI;
//import java.util.List;
import java.util.Optional;
//import java.util.stream.Collectors;


@RestController
@AllArgsConstructor
@RequestMapping("/api/locations")
@Tag(name = "Location-Controller operations")
public class LocationsController {

    //    private final static String RED = "\u001B[31m";
    private final LocationsService locationsService;


    @GetMapping
//    public List<LocationDTO> getLocations(
    @Operation(summary = "List All Locations", description = "Switch the details ranger.")
    @ApiResponse(responseCode = "200", description = "Locations-Query successful: \"We're in the pipe: 5 by 5\"")
    @ApiResponse(responseCode = "404", description = "Not found Location instance, my Lord.")
    public LocationsDTO getLocations(
            @Parameter(description = "Location's prefix", example = "Bud")
            @RequestParam Optional<String> prefix) {
        return new LocationsDTO(locationsService.getLocations(prefix));
//        return locationsService.getLocations(prefix);
    }


    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "We're in the pipe: 5 by 5")
    @ApiResponse(responseCode = "404", description = "Danger Zone: Not found Location")
    public LocationDTO fetchLocationById(
            @PathVariable("id") long id) {
        return locationsService.fetchLocationById(id);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity fetchLocationById(@PathVariable("id") long id) {
//        try {
//            return ResponseEntity.ok(
//                    locationsService.fetchLocationById(id));
//        } catch (IllegalArgumentException iae) {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @PostMapping
// ST??TUSZK??D 200-r??l 201-re v??ltozik.
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Location-Creator")
    @ApiResponse(responseCode = "201", description = "Location has been created.")
    public LocationDTO createLocation(
            @Valid @RequestBody CreateLocationCommand command) {
        return locationsService.createLocation(command);
    }


    @PutMapping(value = "/{id}")
    @Operation(summary = "Changes-Status: Location")
    @ApiResponse(responseCode = "200",
            description = "Status changing was successfully.")

    public LocationDTO updateLocation(
            @PathVariable("id") long id,
            @RequestBody UpdateLocationCommand command) {
        return locationsService.updateLocation(id, command);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "DELETE: Location")
    @ApiResponse(responseCode = "204",
            description = "{id}" + " ID-s Location deleted: \"Location over man, Location over\"")

    public void deleteLocation(
            @PathVariable("id") long id) {
        locationsService.deleteLocation(id);
    }


//    @GetMapping()
//    public List<LocationDto> getLocation() {
//        return locationsService.getLocations();
//    }

// Ha kollekci??n dolgozik, akkor az OP (Win10) def. h??tt??rt??ma(theme) akt??v.

//    @GetMapping("/locations")
//    public List<Location> getLocations() {
//        return locations;
//    }


//    @ExceptionHandler(LocationNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//
//    public ResponseEntity<Problem> handleNotFound(LocationNotFoundException lnfException) {
//        Problem problem = Problem.builder()
//                .withType(URI.create("locations/NOT-FOUND"))
//                .withTitle("NOT FOUND")
//                .withStatus(Status.NOT_FOUND)
//                .withDetail(lnfException.getMessage())
//                .build();
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
////    APPlication.json-k??nt jelenik meg a v??lasz header-j??ben
//                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
////   -- || -- T??rzs??ben pedig ez:
//                .body(problem);
//    }
//
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Problem> handleValidExeption(MethodArgumentNotValidException manvExeption){
//        List<Violation> violations =
//                manvExeption.getBindingResult().getFieldErrors().stream()
//                        .map(fieldError -> new Violation(fieldError.getField(), fieldError.getDefaultMessage()))
//                        .collect(Collectors.toList());
//        Problem problem = Problem.builder()
//                .withType(URI.create("locations/NOT-VALID"))
//                .withTitle("Error: Argument(s)")
//// 400-as ST??TUSZK??D
//                .withStatus(Status.BAD_REQUEST)
//                .withDetail(manvExeption.getMessage())
//// HIB??T TARTALMAZ?? JSON ??zenetben egyedi mez??k
//                .with("violations", violations)
//                .build();
//
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
//                .body(problem);
//    }

}
