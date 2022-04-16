package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    LocationParser locationParser = new LocationParser();
    String trueName = "Budapest";
    double trueLat = 47.497912;
    double trueLon = 19.040235;


    @Test
    @DisplayName("TEST-Create: Location-object is successfully. ")
    void testCreate() {
        Location location = new Location(trueName, trueLat, trueLon);

        assertEquals("Budapest", location.getName());
        assertEquals(47.497912, location.getLatitude());
        assertEquals(19.040235, location.getLongitude());
    }

    @Test
    @DisplayName("TEST-Parse: Location-object from Text is successfully.")
    void testParse() {
        Location location = locationParser.parse(String.format("%s,%a,%a", trueName, trueLat, trueLon));

        assertEquals("Budapest", location.getName());
        assertEquals(47.497912, location.getLatitude());
        assertEquals(19.040235, location.getLongitude());
    }
}