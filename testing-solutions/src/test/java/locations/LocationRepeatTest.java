package locations;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LocationRepeatTest {

    final double[][] testLocations = {{0, 2, 1}, {4, 5, 0}, {0, 8, 1}, {9, 1, 0}};

    @Test
    @RepeatedTest(value = 4, name = "{currentRepetition} / {totalRepetitions}")
    void testEquator(RepetitionInfo info) {
        int i = info.getCurrentRepetition()-1;
        Location testLocation = new Location(
                "TestLoc", testLocations[i][0], testLocations[i][1]);
        assertEquals(testLocations[i][2] == 1, testLocation.isOnEquator());

    }


}
