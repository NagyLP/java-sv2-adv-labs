package locations;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LocationsController {

    private List<Location> locations = new ArrayList<>();

    @GetMapping("/")
    @ResponseBody
    public List<Location> getLocation() {
        locations.add(new Location(1L, "Osli", 2, 3));
        return locations;
    }

}
