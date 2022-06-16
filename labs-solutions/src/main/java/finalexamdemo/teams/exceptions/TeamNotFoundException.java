package finalexamdemo.teams.exceptions;

import org.zalando.problem.AbstractThrowableProblem;

public class TeamNotFoundException extends AbstractThrowableProblem {
    public TeamNotFoundException(Long id) {
    }
}
