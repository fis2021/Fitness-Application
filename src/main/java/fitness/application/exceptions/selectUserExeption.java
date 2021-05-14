package fitness.application.exceptions;

public class selectUserExeption extends Exception {
    public selectUserExeption() {
        super(String.format("No user was selected! "));
    }
}
