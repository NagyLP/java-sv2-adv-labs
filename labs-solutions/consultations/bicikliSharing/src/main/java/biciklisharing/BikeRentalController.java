package biciklisharing;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;


@RestController
@AllArgsConstructor
public class BikeRentalController {

    private BikesRentalService service;


    @GetMapping("history")
    public List<BikeRental> getAllRentals() {
        return service.getAllRentals();
    }

    @GetMapping("user")
    public Set<String> getUsersIds() {
        return service.getUserIds();
    }
}
