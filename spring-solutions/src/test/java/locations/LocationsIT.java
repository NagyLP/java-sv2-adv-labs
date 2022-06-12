package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LocationsIT {

    @Autowired
    LocationsController controller;

    @Autowired
    LocationsService service;


//    @Test
//    void testLocation() {
//        controller = new LocationsController(new LocationsService(List.of(new Location(1L, "Test", 2, 3))));
//        assertThat(controller.getLocation())
//                .isNotNull()
//                .hasSize(47)
//                .contains("");
//    }

    //    @Test
//    void testGetLocations() {
//        String expected = locationsController.getLocations();
//
//        assertThat(expected).containsSubsequence("Budapest", "Róma", "Athén");
//    }

    // REST webszolgáltatások - GET művelet
//    @Test
//    void testGetLocations() {
//        List<LocationDto> expected = locationsController.getLocations();
//
//        assertThat(expected)
//                .hasSize(3)
//                .extracting(LocationDto::getName)
//                .containsExactly("Budapest", "Róma", "Athén");
//    }

//    @Test
//    void testGetLocations() {
//        List<LocationDto> expected = locationsController.getLocations(Optional.of("B"));
//
//        assertThat(expected)
//                .hasSize(1)
//                .extracting(LocationDto::getName)
//                .containsExactly("Budapest");
//    }

    @BeforeEach
    void init() {
        service.deleteLocation(1);
        service.deleteLocation(2);
        service.deleteLocation(3);

        controller.createLocation(new CreateLocationCommand("Budapest", 47.497912, 19.040235));
        controller.createLocation(new CreateLocationCommand("Róma", 41.90383, 12.50557));
        controller.createLocation(new CreateLocationCommand("Athén", 37.97954, 23.72638));
    }

    // Content Negotiation
    @Test
    void testGetLocations() {
        LocationsDTO expected = controller.getLocations(Optional.empty());

        assertThat(expected.getLocations())
                .hasSize(3)
                .extracting(LocationDTO::getName)
                .containsExactly("Budapest", "Róma", "Athén");
    }

    @Test
    void testGetLocationsByPrefix() {
        LocationsDTO expected = controller.getLocations(Optional.of("B"));

        assertThat(expected.getLocations())
                .hasSize(1)
                .extracting(LocationDTO::getName)
                .containsExactly("Budapest");
    }

    @Test
    void testFindLocationById() {
        LocationDTO expected = controller.fetchLocationById(2);

        assertEquals("Róma", expected.getName());
    }

    @Test
    void testUpdateLocation() {
        controller.updateLocation(2, new UpdateLocationCommand("Róma", 2.2, 3.3));

        LocationDTO expected = controller.fetchLocationById(2);

        assertEquals("Róma", expected.getName());
        assertEquals(2.2, expected.getLat());
        assertEquals(3.3, expected.getLon());
    }

    @Test
    void testDeleteLocation() {
        controller.deleteLocation(2);

        LocationsDTO expected = controller.getLocations(Optional.empty());

        assertThat(expected.getLocations())
                .hasSize(2)
                .extracting(LocationDTO::getName)
                .containsExactly("Budapest", "Athén");
    }
}
