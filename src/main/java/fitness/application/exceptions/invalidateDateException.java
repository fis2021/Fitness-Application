package fitness.application.exceptions;

public class invalidateDateException extends Exception {
    public invalidateDateException() {
        super(String.format("Date is not valid! "));
    }
}
