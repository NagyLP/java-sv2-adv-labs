package incrementer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IncrementControllerTest {

    @Mock
    IncrementService service;

    @InjectMocks
    IncrementController controller;


    @Test
    void testIncrement() {
        when(service.increment()).thenReturn(10);
        int exepted = controller.increment();

        assertEquals(10, exepted);
    }
}