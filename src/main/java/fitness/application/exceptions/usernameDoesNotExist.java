package fitness.application.exceptions;

public class usernameDoesNotExist extends Exception{
private String username;

public usernameDoesNotExist(String username) {
        super(String.format("An account with the username %s does not exist!", username));
        this.username = username;
        }

public String getUsername() {
        return username;
        }
}
