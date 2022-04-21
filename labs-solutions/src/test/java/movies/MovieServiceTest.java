package movies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    String testTitle = "Címe";
    LocalDate testDate = LocalDate.of(1950, 12, 31);
    int testLenght = 100;
    long testId = 1;

    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    MovieService movieService;

    @Test
    void testSaveMovie() {
        when(movieRepository.saveMovie(any(Movie.class))).thenReturn(
                new Movie(testId, testTitle, testDate, testLenght)
        );

        Movie movie = movieService.saveMovie(testTitle, testDate, testLenght);

        assertThat(movie.getId()).isEqualTo(1L);

        verify(movieRepository).saveMovie(argThat(m -> m.getTitle().equals(testTitle)));
        verify(movieRepository).saveMovie(argThat(m -> m.getLength() == testLenght));

    }

    @Test
    void testSaveMovieWrongDate() {
        assertThatThrownBy(() -> movieService.saveMovie("Cím", LocalDate.of(1900, 12, 31), 121))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Date is not correct: 1900-12-31");

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> movieService.saveMovie("Cím", LocalDate.of(1900, 12, 31), 121))
                .withMessage("Date is not correct: 1900-12-31");

        verify(movieRepository, never()).saveMovie(any());
    }


}