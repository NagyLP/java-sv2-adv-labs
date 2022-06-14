package locations.exeptions;

import org.springframework.web.util.UriBuilder;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;
import org.zalando.problem.ThrowableProblem;

import java.net.URI;
import java.util.Map;

public class LocationNotFoundException extends AbstractThrowableProblem {
//public class LocationNotFoundException extends RuntimeException {

    public LocationNotFoundException(long id) {
        super(URI.create("locations/location-NOT-FOUND"),
                "NOT FOUND",
                Status.NOT_FOUND,
                String.format("Can not find Location with id: %d", id)
                );
    }


//    public LocationNotFoundException(){
//    }
//
//    /**
//     * Constructs a new runtime exception with the specified detail message.
//     * The cause is not initialized, and may subsequently be initialized by a
//     * call to {@link #initCause}.
//     *
//     * @param message the detail message. The detail message is saved for
//     *                later retrieval by the {@link #getMessage()} method.
//     */
//    public LocationNotFoundException(String message) {
//        super(message);
//    }
//
//    /**
//     * Constructs a new runtime exception with the specified detail message and
//     * cause.  <p>Note that the detail message associated with
//     * {@code cause} is <i>not</i> automatically incorporated in
//     * this runtime exception's detail message.
//     *
//     * @param message the detail message (which is saved for later retrieval
//     *                by the {@link #getMessage()} method).
//     * @param cause   the cause (which is saved for later retrieval by the
//     *                {@link #getCause()} method).  (A {@code null} value is
//     *                permitted, and indicates that the cause is nonexistent or
//     *                unknown.)
//     * @since 1.4
//     */
//    public LocationNotFoundException(String message, Throwable cause) {
//        super(message, cause);
//    }
}
