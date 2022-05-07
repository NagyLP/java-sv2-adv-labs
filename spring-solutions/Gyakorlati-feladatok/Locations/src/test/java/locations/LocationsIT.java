package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LocationsIT {

    @Autowired
    LocationsController controller;


    @Test
    void testLocation() {
        controller = new LocationsController(new LocationsService(List.of(new Location(1L, "Test", 2, 3))));
        assertThat(controller.getLocation())
                .isNotNull()
                .hasSize(47)
                .contains("");
    }
}
