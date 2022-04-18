package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LocationITest {

    LocationParser locationParser;

    String testName = "Budapest";
    double testLat = 47.497912;
    double testLon = 19.040235;
    String testString = "Budapest,47.497912,19.040235";

    @BeforeEach
    void initLocationParser() {
        locationParser = new LocationParser();
    }

    @Test
    @DisplayName("CREATE: Location-object is successfully. ")
    void testCreate() {
        Location location = new Location(testName, testLat, testLon);

        assertEquals("Budapest", location.getName());
        assertEquals(47.497912, location.getLatitude());
        assertEquals(19.040235, location.getLongitude());
    }

    @Test
    @DisplayName("OPERATION: Location-object from Text is successfully.")
    void testParse() {
        Location location = locationParser.parse(String.format("%s,%a,%a", testName, testLat, testLon));

        assertEquals("Budapest", location.getName());
        assertEquals(47.497912, location.getLatitude());
        assertEquals(19.040235, location.getLongitude());
    }

    @Test
    @DisplayName("RETURN: Name's Latitude is on Equator")
    void testIsOnEquatorTrue() {
        Location location = new Location(
                "Bonjol - Indonesia", 0.0, 100.222504);
        assertTrue(location.isOnEquator());
    }

    @Test
    @DisplayName("RETURN: Name's Latitude is NOT on Equator")
    void testIsOnEquatorFalse() {
        Location location = new Location(
                testName, testLat, testLon);
        assertFalse(location.isOnEquator());
    }

    @Test
    @DisplayName("RETURN: Name's Latitude is on Greenwich meridian")
    void testIsOnPrimeMeridianTrue() {
        Location location = new Location(
                "Greenwich", 51.284, 0.0);
        assertTrue(location.isOnPrimeMeridian());
    }

    @Test
    @DisplayName("RETURN: Name's Latitude is NOT on Greenwich meridian")
    void testIsOnPrimeMeridianFalse() {
        Location location = new Location(
                testName, testLat, testLon);
        assertFalse(location.isOnPrimeMeridian());
    }

    @Test
    @DisplayName("OBJECT: Different instance-check")
    void testLocationParserDifferentInstance() {
        LocationParser otherLocationParser = new LocationParser();
        assertNotEquals(locationParser, otherLocationParser);
        assertNotSame(locationParser, otherLocationParser);
    }

    @Test
    @DisplayName("VALUE: Distance different value-check")
    void testDistanceDifferent() {
        Location location = locationParser.parse(String.format(
                "%s,%a,%a", testName, testLat, testLon));
        Location otherLocation = locationParser.parse(
                "Debrecen,47.52997,21.63916");

        assertEquals(195.2,location.distanceFrom(otherLocation));
    }

    @Test
    @DisplayName("VALUE: Distance zero value-check")
    void testDistanceResult() {
        Location location = locationParser.parse(String.format(
                "%s,%a,%a", testName, testLat, testLon));
        Location otherLocation = locationParser.parse(String.format(
                "%s,%a,%a", testName, testLat, testLon));

        assertEquals(0d,location.distanceFrom(otherLocation));
    }

    @Test
    @DisplayName("VALUE: Distance all value-check")
    void testParseValues() {
        Location location = locationParser.parse(testString);

        assertAll(
                () -> assertEquals(testName, location.getName()),
                () -> assertEquals(testLat, location.getLatitude()),
                () -> assertEquals(testLon, location.getLongitude())
        );
    }

    @Test
    @DisplayName("EQUALS-NotSame: Different Location")
    void testDifferentLocations() {
        Location location = new Location(testName, testLat, testLon);
        Location otherLocation = new Location(testName, testLat, testLon);

        assertAll(
                () -> assertEquals(location, otherLocation),
                () -> assertNotSame(location, otherLocation)
        );
    }

    @Test
    @DisplayName("EXEPTION: Latitude is too low.")
    void testLowLatitude() {
        Exception testEx = assertThrows(IllegalArgumentException.class, ()-> new Location(
                "Tatuin", -90.5, 19));
        assertEquals("True Latitude [-90;90]: -90.5", testEx.getMessage());
    }
        @Test
    @DisplayName("EXEPTION: Latitude is too high.")
    void testHighLatitude() {
        Exception testEx = assertThrows(IllegalArgumentException.class, ()-> new Location(
                "Tatuin", 90.5, 19));
        assertEquals("True Latitude [-90;90]: 90.5", testEx.getMessage());
    }
    @Test
    @DisplayName("EXEPTION: Longitude is too high.")
    void testLowLongitude() {
        Exception testEx = assertThrows(IllegalArgumentException.class, ()-> new Location(
                "Tatuin", 90, -180.5));
        assertEquals("True Longitude [-180;180]: -180.5", testEx.getMessage());
    }  @Test
    @DisplayName("EXEPTION: Longitude is too high.")
    void testHighLongitude() {
        Exception testEx = assertThrows(IllegalArgumentException.class, ()-> new Location(
                "Tatuin", 90, 180.5));
        assertEquals("True Longitude [-180;180]: 180.5", testEx.getMessage());
    }

}