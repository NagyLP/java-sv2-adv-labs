package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class LocationsServiceTest {

    LocationsService testLocationsService;

//    @BeforeEach
//    void setUp() {
//        Location location1 = new Location(
//                1L, "Tatooine", 1, 1);
//        Location location2 = new Location(
//                2L, "Messier 87", 2, 2);
//        Location location3 = new Location(
//                3L, "Langerhans-szigetek", 3, 3);
//        testLocationsService = new LocationsService(List.of(location1, location2, location3));
//    }

//    @Test
//    void testGetLocations() {
//        assertThat(testLocationsService.getLocations())
//                .isNotNull()
//                .hasSize(3)
//                .extracting(Location::getId, Location::getName, Location::getLon)
//                .containsOnly(tuple(1L, "Tatooine", 1.0),
//                        tuple(2L, "Messier 87", 2.0),
//                        tuple(3L, "Langerhans-szigetek", 3.0));
//    }


}