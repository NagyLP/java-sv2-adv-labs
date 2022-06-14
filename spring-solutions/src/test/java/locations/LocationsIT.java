package locations;

import locations.dtos.UpdateLocationCommand;
import locations.model.Location;
import locations.repository.LocationsRepository;
import locations.service.LocationsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class LocationsIT {

    @Autowired
    LocationsRepository locationsRepository;

    @BeforeEach
    void setUp() {
        locationsRepository.deleteAll();
        Location location1 = new Location("Róma", 41.90383, 12.50557);
        Location location2 = new Location("Athén", 37.97954, 23.72638);
        Location location3 = new Location("Budapest", 47.497912, 19.040235);

        locationsRepository.save(location1);
        locationsRepository.save(location2);
        locationsRepository.save(location3);
    }

    @Test
    void testGetLocations() {
        List<Location> expected = locationsRepository.findAll();
        assertThat(expected)
                .hasSize(3)
                .extracting(Location::getName)
                .containsExactly("Róma", "Athén", "Budapest");
    }

    @Test
    void testGetLocationById() {
        long id = locationsRepository.findAll().get(0).getId();
        Location expected = locationsRepository.findById(id).get();

        assertEquals("Róma", expected.getName());
    }

    @Test
    void testUpdateLocation() {
        long id = locationsRepository.findAll().get(0).getId();
        new LocationsService(locationsRepository, new ModelMapper())
                .updateLocation(id, new UpdateLocationCommand("Róma Update Success", 1.1, 21.21));

        assertThat(locationsRepository.findById(id).get())
                .extracting(Location::getName, Location::getLat, Location::getLon)
                .contains("Róma Update Success", 1.1, 21.21);
    }

    @Test
    void testDeleteLocation() {
        long id = locationsRepository.findAll().get(0).getId();
        locationsRepository.deleteById(id);

        assertThat(locationsRepository.findAll())
                .hasSize(2)
                .extracting(Location::getName)
                .contains("Athén", "Budapest");
    }
}
