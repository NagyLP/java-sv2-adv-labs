package movies;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    String testTitle = "Címe";
    LocalDate testDate = LocalDate.of(1950, 12, 31);
    int testLenght = 100;
    long testId = 1;

    @Test
    @DisplayName("Test if Movie is correctly created.")
    void testCreate() {
        Movie movie = new Movie(testId, testTitle, testDate, testLenght);

        assertEquals(testTitle, movie.getTitle());
        assertEquals(testDate, movie.getReleaseDate());
        assertEquals(testLenght, movie.getLength());
    }

}