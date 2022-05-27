package incrementer;

import org.springframework.stereotype.Service;

@Service
public class IncrementService {

    private IncrementRepository repository;

    public IncrementService(IncrementRepository repository) {
        this.repository = repository;
    }

    public int increment() {
        int counter = repository.getCounter();
        counter++;
        repository.setCounter(counter);
        return counter;
    }

    public void setCounter(int counter) {
        repository.setCounter(counter);
    }
}
