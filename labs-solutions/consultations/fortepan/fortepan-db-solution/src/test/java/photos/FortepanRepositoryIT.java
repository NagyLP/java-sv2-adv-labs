package photos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class FortepanRepositoryIT {

    @Autowired
    FortepanRepository repository;

    Photo photo;

    @BeforeEach
    void init() {
        repository.save(new Photo("Ló a mezőn", 1921, "Tóth Béla"));
        photo = repository.save(new Photo("Nő fák alatt, napernyővel", 1935, "Kiss József"));
        repository.save(new Photo("Örkény István a feleségével", 1952, "Tóth Béla"));
        repository.save(new Photo("Körjáték", 1965, "Szabó Géza"));
    }

    @Test
    void testListAll() {
        List<Photo> photos = repository.findAll();

        assertThat(photos)
                .hasSize(4)
                .extracting(Photo::getDescription)
                .containsExactly("Ló a mezőn", "Nő fák alatt, napernyővel", "Örkény István a feleségével", "Körjáték");
    }

    @Test
    void testFindById() {
        Photo expected = repository.findById(photo.getId()).get();

        assertEquals("Nő fák alatt, napernyővel", expected.getDescription());
    }

    @Test
    void testCreatePhotoWithDescription() {
        repository.save(new Photo("Lakodalom"));
        List<Photo> expected = repository.findAll();

        assertThat(expected)
                .hasSize(5)
                .extracting(Photo::getDescription)
                .containsExactly("Ló a mezőn", "Nő fák alatt, napernyővel", "Örkény István a feleségével", "Körjáték", "Lakodalom");
    }

    @Test
    void testCreatePhotoWithDescriptionAndYear() {
        repository.save(new Photo("Lakodalom", 1976));
        List<Photo> expected = repository.findAll();

        assertThat(expected)
                .hasSize(5)
                .extracting(Photo::getYear)
                .containsExactly(1921, 1935, 1952, 1965, 1976);
    }

    @Test
    void testUpdatePhotoWithPhotographerAndYear() {
        Photo expected = repository.findById(photo.getId()).get();
        assertEquals("Kiss József", expected.getNameOfPhotographer());
        assertEquals(1935, expected.getYear());

        new FortepanService(repository, new ModelMapper())
                .updatePhotoWithPhotographerAndYear(photo.getId(),
                        new UpdatePhotoWithPhotographerAndYearCommand(1900, "Nagy Béla"));
        expected = repository.findById(photo.getId()).get();
        assertEquals("Nagy Béla", expected.getNameOfPhotographer());
        assertEquals(1900, expected.getYear());
    }

    @Test
    void testUpdatePhotoWithInfo() {
        Photo expected = repository.findById(photo.getId()).get();
        assertEquals(0, expected.getAdditionalInfo().size());

        new FortepanService(repository, new ModelMapper())
                .updatePhotoWithInfo(photo.getId(),
                        new UpdatePhotoWithInfoCommand("szakadozott szélű"));
        expected = repository.findById(photo.getId()).get();
        assertEquals(1, expected.getAdditionalInfo().size());
        assertEquals("szakadozott szélű", expected.getAdditionalInfo().get(0));
    }

    @Test
    void testDelete() {
        repository.deleteById(photo.getId());
        List<Photo> expected = repository.findAll();

        assertThat(expected)
                .hasSize(3)
                .extracting(Photo::getDescription)
                .containsExactly("Ló a mezőn", "Örkény István a feleségével", "Körjáték");
    }

    @Test
    void testDeleteAll() {
        repository.deleteAll();
        List<Photo> expected = repository.findAll();

        assertEquals(0, expected.size());
    }

    @Test
    void testListPhotosWithTextInDescription() {
        List<Photo> expected = repository.listPhotosWithTextInDescription("ő");

        assertThat(expected)
                .hasSize(2)
                .extracting(Photo::getDescription)
                .containsExactly("Ló a mezőn", "Nő fák alatt, napernyővel");
    }

    @Test
    void testListPhotosAfter() {
        List<Photo> expected = repository.listPhotosAfter(1940);

        assertThat(expected)
                .hasSize(2)
                .extracting(Photo::getDescription)
                .containsExactly("Örkény István a feleségével", "Körjáték");
    }

    @Test
    void testListPhotographers() {
        List<String> expected = repository.listPhotographers();

        assertThat(expected)
                .hasSize(3)
                .containsExactlyInAnyOrder("Tóth Béla", "Kiss József", "Szabó Géza");
    }
}
