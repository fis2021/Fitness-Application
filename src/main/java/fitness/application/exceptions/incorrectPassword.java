package fitness.application.exceptions;

public class incorrectPassword extends Exception{
    public incorrectPassword()
    {
        super("too few characters for password");
    }
}
