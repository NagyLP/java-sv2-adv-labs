package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class LocationOperatorsTest {

    List<Location> testLocations = new LinkedList<>();
    LocationOperators testLocationOperators = new LocationOperators();

    @BeforeEach
    void createLocations() {
        testLocations.add(new Location("One", 47, 19));
        testLocations.add(new Location("Twoo", -47, 19));
        testLocations.add(new Location("Three", 74, 19));
    }

    @Test
    @DisplayName("filterOnNorth(): List Value-test ")
    void testNorthLocations() {
        List<String> north = testLocationOperators.filterOnNorth(testLocations)
                .stream()
                .map(Location::getName)
                .collect(Collectors.toList());

        assertEquals(List.of("One", "Three"), north);
    }
}