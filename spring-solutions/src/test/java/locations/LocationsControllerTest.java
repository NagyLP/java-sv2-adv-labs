package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
//@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {

@Autowired
//    @Mock
    LocationsService testLocationsService;

@Autowired
//    @InjectMocks
    LocationsController testLocationsController;

//    @Test
//    void testGetLocation() {
//        when(testLocationsService.getLocations()).thenReturn(List.of(new Location(
//                1L, "Budapest", 47.1234, 19.1234)));
//
//        assertThat(testLocationsController.getLocation())
//                .isNotNull()
//                .isEqualTo(String.valueOf(List.of(new Location(
//                        1L, "Budapest", 47.1234, 19.1234))));
//    }

//    @BeforeEach
//    void init() {
//        testLocationsService.deleteLocation(1);
//        testLocationsService.deleteLocation(2);
//        testLocationsService.deleteLocation(3);
//
//        testLocationsController.createLocation(new CreateLocationCommand("Budapest", 47.497912, 19.040235));
//        testLocationsController.createLocation(new CreateLocationCommand("Róma", 41.90383, 12.50557));
//        testLocationsController.createLocation(new CreateLocationCommand("Athén", 37.97954, 23.72638));
//    }

    @Test
    void testGetLocationsByPrefix() {
//        LocationsDTO expected = testLocationsController.getLocations(Optional.of("B"));
        List<LocationDTO> locations = List.of(
                new LocationDTO("Budapest", 47.497912, 19.040235));
        when(testLocationsService.getLocations(any()))
                .thenReturn(locations);
        LocationsDTO expected = testLocationsController.getLocations(Optional.of("Bud"));

        assertThat(expected.getLocations())
                .hasSize(1)
                .extracting(LocationDTO::getName)
                .containsExactly("Budapest");
    }

    @Test
    void testGetLocations() {
        List<LocationDTO> locations = List.of(
                new LocationDTO("Osli", 1.35, -100.1)
        );
        when(testLocationsService.getLocations(any())).thenReturn(locations);
        LocationsDTO expected = testLocationsController.getLocations(Optional.empty());

        assertThat(expected.getLocations())
                .hasSize(1)
                .extracting(LocationDTO::getName)
                .containsOnly("Osli");
    }

    @Test
    void testFindLocationById() {
//        LocationDTO expected = testLocationsController.fetchLocationsById(2);
        when(testLocationsService.fetchLocationById(anyLong()))
                .thenReturn(new LocationDTO("Róma", 2.2, 3.3));
        LocationDTO expected = testLocationsController.fetchLocationById(2);

        assertEquals("Róma", expected.getName());
    }

    @Test
    void testUpdateLocation() {
        when(testLocationsService.updateLocation(anyLong(), any()))
                .thenReturn(new LocationDTO("Róma", 2.2, 3.3));
        LocationDTO expected = testLocationsController.updateLocation(
                2, new UpdateLocationCommand("Róma", 1.1, 1.1));

//        testLocationsController.updateLocation(2, new UpdateLocationCommand("Róma", 2.2, 3.3));
//
//        LocationDTO expected = testLocationsController.fetchLocationsById(2);

        assertEquals("Róma", expected.getName());
        assertEquals(2.2, expected.getLat());
        assertEquals(3.3, expected.getLon());
    }

    @Test
    void testDeleteLocation() {
        testLocationsController.deleteLocation(2);
        verify(testLocationsService).deleteLocation(2);

//        LocationsDTO expected = testLocationsController.getLocations(Optional.empty());
//
//        assertThat(expected.getLocations())
//                .hasSize(2)
//                .extracting(LocationDTO::getName)
//                .containsExactly("Budapest", "Athén");
    }
}