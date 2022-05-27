package incrementer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IncrementRepositoryTest {

    IncrementRepository repository;

    @BeforeEach
    void setUp() {
        repository = new IncrementRepository();
    }

    @Test
    void getCounter() {
        assertThat(repository.getCounter())
                .isZero();
    }

    @Test
    void setCounter() {
        repository.setCounter(10);
        assertThat(repository.getCounter())
                .isEqualTo(10);
    }
}