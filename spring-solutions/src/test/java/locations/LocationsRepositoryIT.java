package locations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import locations.dtos.UpdateLocationCommand;
import locations.model.Location;
import locations.repository.LocationsRepository;
import locations.service.LocationsService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class LocationsRepositoryIT {

    @Autowired
    LocationsRepository repository;

    @Test
    void testPersist(){
        Location testLocation = new Location("Róma", 41.90383, 12.50557);
        repository.save(testLocation);
        List<Location> testLocations = repository.findAll();

        assertThat(testLocations)
                .hasSize(1)
                .extracting(Location::getName)
                .containsExactly("Róma");

    }

    @Test
    void testFindAll() {
        Location location1 = new Location("Róma", 41.90383, 12.50557);
        Location location2 = new Location("Athén", 37.97954, 23.72638);
        Location location3 = new Location("Budapest", 47.497912, 19.040235);

        repository.save(location1);
        repository.save(location2);
        repository.save(location3);

        List<Location> expected = repository.findAll();

        assertThat(expected)
                .hasSize(3)
                .extracting(Location::getName)
                .containsExactly("Róma", "Athén", "Budapest");
    }

    @Test
    void testFindById() {
        Location location = new Location("Budapest", 47.497912, 19.040235);
        repository.save(location);
        List<Location> locations = repository.findAll();
        long id = locations.get(0).getId();

        Location expected = repository.findById(id).get();

        assertEquals("Budapest", expected.getName());
        assertEquals(47.497912, expected.getLat());
        assertEquals(19.040235, expected.getLon());
    }

    @Test
    void testUpdate() {
        Location location = new Location("Budapest", 47.497912, 19.040235);
        repository.save(location);
        List<Location> locations = repository.findAll();
        long id = locations.get(0).getId();

        new LocationsService(repository, new ModelMapper()).updateLocation(
                id, new UpdateLocationCommand("Budapest", 2.2, 3.3));

        Location expected = repository.findById(id).get();

        assertEquals("Budapest", expected.getName());
        assertEquals(2.2, expected.getLat());
        assertEquals(3.3, expected.getLon());
    }

    @Test
    void testDeleteById() {
        Location location = new Location("Budapest", 47.497912, 19.040235);
        repository.save(location);
        List<Location> locations = repository.findAll();
        long id = locations.get(0).getId();

        repository.deleteById(id);

        List<Location> expected = repository.findAll();

        assertEquals(0, expected.size());
    }

    @Test
    void testDeleteAll() {
        Location location1 = new Location("Róma", 41.90383, 12.50557);
        Location location2 = new Location("Athén", 37.97954, 23.72638);
        Location location3 = new Location("Budapest", 47.497912, 19.040235);

        repository.save(location1);
        repository.save(location2);
        repository.save(location3);
        repository.deleteAll();
        List<Location> expected = repository.findAll();

        assertEquals(0, expected.size());
    }
}