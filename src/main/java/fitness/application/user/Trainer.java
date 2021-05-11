package fitness.application.user;

import org.dizitart.no2.objects.InheritIndices;

@InheritIndices
public class Trainer extends User{

    public Trainer(){

    }
    public Trainer(String username, String email,String password,String fullName, String role){
        super(username,email,password,fullName,"Trainer");
    }
}
