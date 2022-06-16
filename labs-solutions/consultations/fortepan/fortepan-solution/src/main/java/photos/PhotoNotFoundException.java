package photos;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class PhotoNotFoundException extends AbstractThrowableProblem {

    public PhotoNotFoundException(long id) {
        super(URI.create("photos/photo-not-found"),
                "Not found",
                Status.NOT_FOUND,
                String.format("Photo with id: %d not found", id));
    }
}
