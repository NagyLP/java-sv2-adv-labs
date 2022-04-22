package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    Location testLocation = new Location(
            "Sári", 47.1150, 19.1528);

    @Mock
    LocationsRepository locationsRepository;

    @InjectMocks
    LocationService testLocationService;

    @Test
    void testCalculateDistanceEmptyLocations() {
        LocationsRepository testLocationsRepository = new LocationsRepository() {
            @Override
            public Optional<Location> findByName(String name) {
                return Optional.empty();
            }

            @Override
            public Optional<Double> findLatitudeByName(String name) {
                return Optional.empty();
            }
        };
        testLocationService = new LocationService(testLocationsRepository);

        assertEquals(Optional.empty(),
                testLocationService.calculateDistance("Pornóapáti", "Záhony"));
    }

    @Test
    void testCalculateDistanceNoFirstCity() {
        when(locationsRepository
                .findByName("Bugyi"))
                .thenReturn(Optional.empty()
        );

        when(locationsRepository
                .findByName("Sári"))
                .thenReturn(Optional.of(testLocation)
        );

        assertEquals(Optional.empty(),
                testLocationService.calculateDistance("Sári", "Bugyi"));
        verify(locationsRepository)
                .findByName(argThat(l->l.equals("Bugyi")));
        verify(locationsRepository)
                .findByName(argThat(l->l.equals("Sári")));
    }


}