package incrementer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class IncrementControllerIT {

    @Autowired
    IncrementController controller;

    @Autowired
    IncrementService service;

    @BeforeEach
    void init() {
        service.setCounter(0);
    }

    @Test
    void testIncrement() {
        assertThat(controller.increment())
                .isEqualTo(1);
    }

    @Test
    void testTestIncrement() {
        assertThat(controller.increment())
                .isEqualTo(1);
    }
}