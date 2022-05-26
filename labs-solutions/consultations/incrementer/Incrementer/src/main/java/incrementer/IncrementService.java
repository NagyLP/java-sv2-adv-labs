package incrementer;

import org.springframework.stereotype.Service;

@Service
public class IncrementService {

    private int counter;

    public int increment() {
        counter++;
        return counter;
    }
}
