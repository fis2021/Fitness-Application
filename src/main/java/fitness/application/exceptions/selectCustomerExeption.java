package fitness.application.exceptions;

public class selectCustomerExeption extends Exception {
    public selectCustomerExeption() {
        super(String.format("No customer was selected! "));
    }
}
