package fitness.application.exceptions;

public class incorrectUsername extends Exception{
    public incorrectUsername()
    {
        super("too few characters for username");
    }
}
