package fitness.application.exceptions;

public class emptyFieldException extends Exception {
    public emptyFieldException() {
        super(String.format("You must complete all fields! "));
    }
}
