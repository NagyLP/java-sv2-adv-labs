package incrementer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class IncrementServiceIT {

    @Autowired
    IncrementService service;

    @BeforeEach
    void init() {
        service.setCounter(0);
    }


    @Test
    void testIncrement() {
        assertThat(service.increment())
                .isEqualTo(1);
    }
}
