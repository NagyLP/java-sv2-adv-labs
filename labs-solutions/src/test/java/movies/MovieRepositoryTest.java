package movies;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PrePersist;
import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class MovieRepositoryTest {

//    Flyway flyway;
    MovieRepository repository;
    EntityManagerFactory emf;
    Movie testMovie = new Movie("Titanic", LocalDate.of(1997,12,19), 194);

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("TEST-pu");
        repository = new MovieRepository(emf);
    }

    @Test
    void testSaveMovie() {
        Movie movie = repository.saveMovie(testMovie);

        assertThat(movie.getId()).isNotNull();
    }

    @Test
    void testFindByTitle() {

    }

    @Test
    void testFindByTitleWithRatings() {
        testMovie.addRating(new Rating(6.7, "user1"));
        testMovie.addRating(new Rating(6.9, "user2"));
        repository.saveMovie(testMovie);
        Movie testResult = repository.findMovieByTitleWithRatings("Titanic");
        System.out.println(testResult.getRatings());
        assertThat(testResult.getLength()).isEqualTo(194);
    }

    @Test
    void findRatingsByUsername() {

    }

    //    @BeforeEach
//    void init() {
//        JdbcDataSource dataSource = new JdbcDataSource();
//
//        dataSource.setUrl("jdbc:h2:~/test");
//        dataSource.setUser("sa");
//        dataSource.setPassword("sa");
//
//        flyway = Flyway.configure().
//
//                dataSource(dataSource).
//
//                load();
//        flyway.clean();
//        flyway.migrate();
//
//        repository = new
//
//                MovieRepository(dataSource);
//    }
//
//    @Test
//    void testSaveMovie() {
//        repository.saveMovie(new Movie("Titanic", LocalDate.of(2021, 1, 2), 121));
//    }

}