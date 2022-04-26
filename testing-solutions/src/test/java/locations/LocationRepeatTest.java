package locations;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LocationRepeatTest {

    final double[][] testLocations = {{0, 2, 1}, {4, 5, 0}, {0, 8, 1}, {9, 1, 0}};

    public static Stream<Arguments> getLocations() {
        return Stream.of(
                Arguments.arguments(2, 0, true),
                Arguments.arguments(4, 5, false),
                Arguments.arguments(8, 0, true),
                Arguments.arguments(9, 1, false)
        );
    }

    @RepeatedTest(value = 4, name = "{currentRepetition} / {totalRepetitions}")
    void testEquator(RepetitionInfo info) {
        int i = info.getCurrentRepetition() - 1;
        Location testLocation = new Location(
                "TestLoc", testLocations[i][0], testLocations[i][1]);
        assertEquals(testLocations[i][2] == 1, testLocation.isOnEquator());

    }

    @ParameterizedTest(name = "Latitude = {0}, Longitude = {1}, Expected = {2}")
    @MethodSource("getLocations")
    void testPrimeMeridian(int latitude, int longitude, boolean expected) {
        Location testLocation = new Location(
                "TestLoc", latitude, longitude);
        assertEquals(expected, testLocation.isOnPrimeMeridian());

    }

    @ParameterizedTest(name = "Latitude = {0}, Longitude = {1}, Expected = {2}")
    @CsvFileSource(resources = "/location.csv", numLinesToSkip = 1)
    void testPrimeMeridianFromFile(int latitude, int longitude, boolean expected) {
        Location testLocation = new Location(
                "TestLoc", latitude, longitude);
        assertEquals(expected, testLocation.isOnPrimeMeridian());

    }

}
