package incrementer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IncrementServiceTest {

    @Test
    void testIncrement() {
        IncrementService service = new IncrementService();

        assertThat(service.increment())
                .isEqualTo(1);
    }
}