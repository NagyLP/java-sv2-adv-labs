package football_teams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerRepositoryIT {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory(
            "TEST-pu");

    PlayerRepository repository = new PlayerRepository(factory);

    @BeforeEach
    void deleteData() {
        repository.deleteAll();
    }

    @Test
    void testSavePlayer(){
        Player player = new Player("John Doe", 100_000);
        repository.savePlayer(player);

        assertThat(repository.findAll())
                .hasSize(1)
                .extracting(Player::getName)
                .containsOnly("John Doe");
    }
}
