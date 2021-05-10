package fitness.application.user;

public class Trainer extends User{

    public Trainer(){

    }
    public Trainer(String username, String email,String password,String fullName, String role){
        super(username,email,password,fullName,"Trainer");
    }
}
