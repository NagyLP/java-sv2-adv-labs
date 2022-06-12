package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from locations")
class LocationsControllerIT {

    @Autowired
    LocationsController locationsController;

    LocationDTO locationDTO;


// ISMÉTLÉS - pl. bizonyos létrehozás
//@RepeatedTest(2)

    @BeforeEach
    @Sql(statements = "delete from locations")
    void setUp() {
        locationsController.createLocation(new CreateLocationCommand(
                "Budapest", 47.497912, 19.040235));
        locationsController.createLocation(new CreateLocationCommand(
                "Osli", 47.633, 17.08347));
        locationDTO = locationsController.createLocation(new CreateLocationCommand(
                "Antarktisz", 90, 0));
    }


    @Test
    void testGetLocations() {
        assertThat(locationsController.getLocations(Optional.empty())
            .getLocations())
                .hasSize(3)
                .extracting(LocationDTO::getName)
                .containsExactly("Budapest", "Osli", "Antarktisz");
    }

// HA nincs inicializálás BeforeEach....
    //        when(locationsController.getLocations(Optional.empty()))
//                .thenReturn(new LocationsDTO(new ArrayList<>(List.of(new LocationDTO(
//                        1L, "Budapest", 47.1234, 19.1234), new LocationDTO(
//                        2L, "Osli", 47.633, 17.08347)))));
//        assertThat(locationsController.getLocations(Optional.empty()).getLocations())
//                .hasSize(2)
//                .isEqualTo(String.valueOf(List.of(new Location(
//                        1L, "Budapest", 47.1234, 19.1234))));}

    @Test
    void testFindLocationById() {
        assertThat(locationsController.fetchLocationById(locationDTO.getId()))
                .extracting(LocationDTO::getName)
                .isEqualTo("Antarktisz");
    }

    @Test
    void testGetLocationsByPrefix() {
        assertThat(locationsController.getLocations(
            Optional.of("Bu")).getLocations())
                .hasSize(1)
                .extracting(LocationDTO::getName)
                .containsExactly("Budapest");
    }

    @Test
    void testUpdateLocation() {
        LocationDTO expected = locationsController.updateLocation(locationDTO.getId(), new UpdateLocationCommand(
                "AntarktiszUpdateUpgrade", 1.1, 21.21));

        assertThat(locationsController.fetchLocationById(locationDTO.getId()))
                .extracting(LocationDTO::getName, LocationDTO::getLat, LocationDTO::getLon)
                .containsExactly("AntarktiszUpdateUpgrade", 1.1, 21.21);
    }

    @Test
    void testDeleteLocation() {
        locationsController.deleteLocation(locationDTO.getId());

        assertThat(locationsController.getLocations(Optional.empty())
            .getLocations())
                .hasSize(2)
                .extracting(LocationDTO::getName)
                .containsExactly("Budapest", "Osli");
    }
}