package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LocationsReaderTest {

    LocationReader locationReader;

    @BeforeEach
    void setUp() {
        locationReader = new LocationReader();
    }

    @Test
    void testReadLocations() {
        List<Location> locations = locationReader.readLocation(
                Path.of("src/test/resources/locations.csv"));
        assertThat(locations)
                .hasSize(4)
                .extracting(Location::getName)
                .contains("TestName1");
    }
}
