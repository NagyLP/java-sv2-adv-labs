package incrementer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IncrementServiceTest {

    @Mock
    IncrementRepository repository;

    @InjectMocks
    IncrementService service;


    @Test
    void testIncrement() {
        when(repository.getCounter())
                .thenReturn(10);
        assertThat(service.increment())
                .isEqualTo(11);
        verify(repository)
                .setCounter(11);
    }
}