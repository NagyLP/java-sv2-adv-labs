package football_teams;

public class NotEnoughBudgetException extends RuntimeException {

    public NotEnoughBudgetException (String message) {
        super(message);
    }
}
