package biciklisharing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BikeRentalControllerIT {

    @Autowired
    BikeRentalController controller;


    @Test
    void testGetUserIdsContainsData() {
        assertThat(controller.getUsersIds())
                .contains("FH631")
                .contains("FH675");
    }

    @Test
    void testGetUserIdsNotContainsData() {
        assertThat(controller.getUsersIds())
                .doesNotContain("Not contains")
                .doesNotContain("US3434")
                .doesNotContain(String.valueOf(
                        String.CASE_INSENSITIVE_ORDER));
    }

    @Test
    void testGetAllRentalsContainsData() {
        assertThat(controller.getAllRentals().get(4))
                .extracting(BikeRental::getDistanceKm)
                .isEqualTo(2.9);
    }

    @Test
    void testGetAllRentalsNotContainsData() {
        assertThat(controller.getAllRentals().get(4))
                .extracting(BikeRental::getDistanceKm)
                .isNotEqualTo("Not equals")
                .isNotEqualTo(Integer.MAX_VALUE)
                .isNotEqualTo(Integer.MIN_VALUE);
    }
}