package fitness.application.exceptions;

public class usernameAlreadyExists extends Exception{
    private String username;

    public usernameAlreadyExists(String username) {
        super(String.format("An account with the username %s already exists!", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
