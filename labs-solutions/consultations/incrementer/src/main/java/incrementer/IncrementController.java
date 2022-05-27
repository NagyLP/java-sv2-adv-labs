package incrementer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IncrementController {

    private IncrementService service;

    public IncrementController(IncrementService service) {
        this.service = service;
    }

    @GetMapping("/")
    public int increment() {
        return service.increment();
    }
}
