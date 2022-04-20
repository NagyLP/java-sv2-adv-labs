package locations;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@ExtendWith(SoftAssertionsExtension.class)
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
                .hasSize(5)
                .extracting(Location::getName)
                .contains("TestName1");
    }

    @Test
    void testFilterLocationsBeyondArcticCircle() {
        List<Location> filteredLocation =
                locationReader.filterLocationsBeyondArcticCircle(
                        locationReader.readLocation(
                                Path.of("src/test/resources/locations.csv")));

        assertThat(filteredLocation)
                .hasSize(2)
                .extracting(Location::getName)
                .contains("TestName4")
                .doesNotContain("TestName1")
                .containsOnly("TestName5", "TestName4")
                .containsExactly("TestName4", "TestName5");

        assertThat(filteredLocation)
                .filteredOn(l -> l.getLatitude() == l.getLongitude())
                .extracting(Location::getName, Location::getLatitude)
                .contains(tuple("TestName5", 80d));
    }

    @Test
    void testSoftAssertion(SoftAssertions softly) {
        Location location = new Location("Abc", 0,0);
        softly.assertThat(location.getName().startsWith("c"));
        softly.assertThat(location.getName().endsWith("A"));
        softly.assertAll();
    }
}
