package incrementer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IncrementServiceIT {

    @Autowired
    IncrementService service;

    @BeforeEach
    void init() {
        service.setCounter(0);
    }


    @Test
    void increment() {
    }

    @Test
    void setCounter() {
    }
}