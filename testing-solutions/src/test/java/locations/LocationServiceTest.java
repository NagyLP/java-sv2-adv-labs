package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

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
    void testCalculateDistanceNoFullfilledFirstLocation() {
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
                .findByName(argThat(l -> l.equals("Bugyi")));
        verify(locationsRepository)
                .findByName(argThat(l -> l.equals("Sári")));
    }

    @Test
    void testCalculateDistanceNoFullfilledSecondLocation() {
        when(locationsRepository
                .findByName("Sári"))
                .thenReturn(Optional.of(testLocation)
                );

        when(locationsRepository
                .findByName("Bugyi"))
                .thenReturn(Optional.empty()
                );

        assertEquals(Optional.empty(),
                testLocationService.calculateDistance("Sári", "Bugyi"));
        verify(locationsRepository)
                .findByName(argThat(l -> l.equals("Bugyi")));
        verify(locationsRepository)
                .findByName(argThat(l -> l.equals("Sári")));
    }

    @Test
    void testCalculateDistanceEqualsLocation() {
        when(locationsRepository
                .findByName("Sári"))
                .thenReturn(Optional.of(testLocation)
                );

        assertEquals(Optional.of(0.0),
                testLocationService.calculateDistance("Sári", "Sári"));
//        verify(locationsRepository, only())
//                .findByName(argThat(l -> l.equals("Sári")));
        verify(locationsRepository, never())
                .findByName(argThat(l -> l.equals("Bugyi")));
        verify(locationsRepository, times(2) )
                .findByName(argThat(l -> l.equals("Sári")));
    }

    @Test
    void testCalculateDistanceDatasOK() {
        when(locationsRepository
                .findByName("Sári"))
                .thenReturn(Optional.of(testLocation)
                );

        when(locationsRepository
                .findByName("Bugyi"))
                .thenReturn(Optional.of(new Location("Bugyi", 47.226, 19.1516 ))
                );

        assertEquals(0.0,testLocationService.calculateDistance("Sári", "Bugyi").get());

    }


}