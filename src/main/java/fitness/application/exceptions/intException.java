package fitness.application.exceptions;

public class intException extends Exception{
    public intException(String fields)
    {
        super(String.format("The fields %s must be integers!", fields));
    }
}
