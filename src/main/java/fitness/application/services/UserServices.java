package fitness.application.services;

import fitness.application.user.*;
import fitness.application.services.*;
import fitness.application.exceptions.*;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.RemoveOptions;
import org.dizitart.no2.exceptions.NitriteIOException;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import static fitness.application.services.FileSystemServices.getPathToFile;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;


public class UserServices {
    public static ObjectRepository<Customer> customerRepository;
    public static ObjectRepository<Trainer> trainerRepository;

    private static String loggedInUsername = "";

    public static void setLoggedInUsername(String username){
        loggedInUsername=username;
    }

    public static String getLoggedInUsername(){return loggedInUsername;}

    public static void initDatabase() {

        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("users.db").toFile())
                .openOrCreate("admin", "admin");
        customerRepository = database.getRepository(Customer.class);
        trainerRepository = database.getRepository(Trainer.class);

    }
    public static void addCustomer(String username,String email, String password,String fullName, String role) throws usernameAlreadyExists,incorrectUsername, incorrectPassword {
        checkUserDoesNotAlreadyExist(username);
        if(username.length()<3) throw new incorrectUsername();
        if(password.length()<3) throw new incorrectPassword();
        customerRepository.insert(new Customer(username,email,encodePassword(username, password),fullName,role));
    }

    public static void addTrainer(String username,String email, String password,String fullName, String role) throws usernameAlreadyExists,incorrectUsername, incorrectPassword {
        checkUserDoesNotAlreadyExist(username);
        if(username.length()<3) throw new incorrectUsername();
        if(password.length()<3) throw new incorrectPassword();
        trainerRepository.insert(new Trainer(username,email,encodePassword(username, password),fullName,role));
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws usernameAlreadyExists{
        for (User user : customerRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new usernameAlreadyExists(username);
        }
        for (User user : trainerRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new usernameAlreadyExists(username);
        }
        for (User user : trainerRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new usernameAlreadyExists(username);
        }
    }
    public static String checkUserExist(String username) throws usernameDoesNotExist{
        int ok = 0;
        String role = null;
        for (User user : customerRepository.find()) {
            if (Objects.equals(username, user.getUsername())) {
                ok = 1;
                role = user.getRole();
                break;
            }
        }
        for (User user : trainerRepository.find()) {
            if (Objects.equals(username, user.getUsername())) {
                ok = 1;
                role = user.getRole();
                break;
            }
        }
        if (ok == 0) {
            throw new usernameDoesNotExist(username);
        } else {
            return role;
        }
    }
    public static String checkUserExist(String username) throws usernameDoesNotExist{
        int ok = 0;
        String role = null;
        for (User user : customerRepository.find()) {
            if (Objects.equals(username, user.getUsername())) {
                ok = 1;
                role = user.getRole();
                break;
            }
        }
        for (User user : trainerRepository.find()) {
            if (Objects.equals(username, user.getUsername())) {
                ok = 1;
                role = user.getRole();
                break;
            }
        }
        if (ok == 0) {
            throw new usernameDoesNotExist(username);
        } else {
            return role;
        }
    }

    public static boolean checkIsInDataBase(String username){
        boolean b=false;
        for (User user : customerRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                b=true;
        }
        for (User user : trainerRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                b=true;
        }
        return b;
    }

    public static User FindTheUser(String username){
        User a=new User();
        for (User user : customerRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                a=user;
        }
        for (User user : trainerRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                a=user;
        }
        return a;
    }

    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }
}
