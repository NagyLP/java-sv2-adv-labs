package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {


    @Mock
    LocationsService testLocationsService;

    @InjectMocks
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
}