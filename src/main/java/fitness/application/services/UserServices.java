package fitness.application.services;

import fitness.application.models.*;
import fitness.application.exceptions.*;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static fitness.application.services.FileSystemServices.getPathToFile;
import java.time.*;


public class UserServices {
    public static ObjectRepository<Customer> customerRepository;
    public static ObjectRepository<Trainer> trainerRepository;
    public static ObjectRepository<Exercise> exerciseRepository;
    public static ObjectRepository<Chat> chatRepository;

    private static LocalDate currentDate=LocalDate.now();
    private static int currentDay=currentDate.getDayOfMonth();
    private static int currentMonth=currentDate.getMonthValue();
    private static int currentYear=currentDate.getYear();


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
        exerciseRepository = database.getRepository(Exercise.class);
        chatRepository = database.getRepository(Chat.class);

    }
    public static void addCustomer(String username,String email, String password,String fullName, String role) throws usernameAlreadyExists,incorrectUsername, incorrectPassword, emptyFieldException {
        checkUserDoesNotAlreadyExist(username);
        if(username.length()<3) throw new incorrectUsername();
        if(password.length()<3) throw new incorrectPassword();
        checkEmptyFields(username, fullName, password, email, role);
        customerRepository.insert(new Customer(username,email,encodePassword(username, password),fullName,role));
    }

    public static void addTrainer(String username,String email, String password,String fullName, String role) throws usernameAlreadyExists,incorrectUsername, incorrectPassword, emptyFieldException{
        checkUserDoesNotAlreadyExist(username);
        if(username.length()<3) throw new incorrectUsername();
        if(password.length()<3) throw new incorrectPassword();
        checkEmptyFields(username, fullName, password, email, role);
        trainerRepository.insert(new Trainer(username,email,encodePassword(username, password),fullName,role));
    }

    public static void addExercise(String trainerName, String customerUsername, String muscleGroup, String exerciseName, String sets,String series, int year, int month, int day) throws emptyFieldException {
        checkEmptyFieldsAddExercise(customerUsername,muscleGroup,exerciseName,sets,series,year);
        String customer = getCustomerUsername(customerUsername);
        String trainer = getTrainerName();
        exerciseRepository.insert(new Exercise(trainer, customer, muscleGroup, exerciseName, sets, series, year, month, day));
    }
    public static void addChat(String sender,String reciever,String message) throws emptyFieldException {
        checkEmptyFieldsChat(message);
        if (FindTheChat(sender+reciever)==true)
        {
            String text = FindTheChatObj(sender+reciever).getFinalMessage()+"\n"+message;
            Chat c = new Chat(sender+reciever, text);
            chatRepository.update(c);
        }
        else if(FindTheChat(reciever+sender)==true)
        {
            String text = FindTheChatObj(reciever+sender).getFinalMessage()+"\n"+message;
            Chat c = new Chat(reciever+sender, text);
            chatRepository.update(c);
        }
        else chatRepository.insert(new Chat(sender+reciever,message));

    }

    public static void removeExercise(String exerciseName) {
        Exercise exercise=FindTheExercise(exerciseName);
        exerciseRepository.remove(exercise);
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

    public static boolean checkExerciseInDataBase(String name,String customer){
        boolean b=false;
        for (Exercise ex : exerciseRepository.find()) {
            if (Objects.equals(name, ex.getExerciseName()) && Objects.equals(customer, ex.getCustomerUsername()))
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

    public static Exercise FindTheExercise(String exerciseName){
        Exercise a=new Exercise();
        for (Exercise user : exerciseRepository.find()) {
            if (Objects.equals(exerciseName, user.getExerciseName()))
                a=user;
        }
        return a;
    }

    public static boolean FindTheChat(String name) {
        Chat c=new Chat();
        for (Chat chat : chatRepository.find()) {
            if (Objects.equals(name, chat.getName()))
                return true;
        }
        return false;
    }

    public static Chat FindTheChatObj(String name) {
        Chat c=new Chat();
        for (Chat chat : chatRepository.find()) {
            if (Objects.equals(name, chat.getName()))
                c=chat;
        }
        return c;
    }

    public static void checkExerciseExpired(){
        for (Exercise ex : exerciseRepository.find()) {
            if (checkDateValid(ex.getDay(),ex.getMonth(), ex.getYear())==false)
            {
                exerciseRepository.remove(ex);
            }
        }
    }

    public static boolean checkDateValid(int day, int month,int year){
        if(year<currentYear)
            return false;
        else if(year==currentYear && month<currentMonth)
            return false;
        else if(year==currentYear && month==currentMonth && day<currentDay)
            return false;
        else return true;
    }

    public static void checkEmptyFields(String username, String fullName, String password, String email, String role) throws emptyFieldException {
        if (Objects.equals(username, ""))
            throw new emptyFieldException();
        else if (Objects.equals(fullName, ""))
            throw new emptyFieldException();
        else if (Objects.equals(password, ""))
            throw new emptyFieldException();
        else if (Objects.equals(email, ""))
            throw new emptyFieldException();
        else if (Objects.equals(role, null))
            throw new emptyFieldException();
    }

    public static void checkEmptyFieldsLogIn(String username, String password) throws emptyFieldException {
        if (Objects.equals(username, ""))
            throw new emptyFieldException();
        else if (Objects.equals(password, ""))
            throw new emptyFieldException();
    }

    public static void checkEmptyFieldsAddExercise(String username, String muscleGroup,String exerciseName,String sets,String series,int year) throws emptyFieldException {
        if (Objects.equals(username, ""))
            throw new emptyFieldException();
        else if (Objects.equals(muscleGroup, ""))
            throw new emptyFieldException();
        else if (Objects.equals(exerciseName, ""))
            throw new emptyFieldException();
        else if (Objects.equals(sets, ""))
            throw new emptyFieldException();
        else if (Objects.equals(series, ""))
            throw new emptyFieldException();
        else if (Objects.equals(year, 0))
            throw new emptyFieldException();
    }

    public static void checkEmptyFieldsChat(String message) throws emptyFieldException {
        if (Objects.equals(message, ""))
            throw new emptyFieldException();
    }


    public static List CustomerList() {
        List<Customer> customer = new ArrayList<>();
        for (Customer user : customerRepository.find()) {
            customer.add(user);
        }
        return customer;
    }

    public static List TrainerList() {
        List<Trainer> trainers = new ArrayList<>();
        for (Trainer user : trainerRepository.find()) {
            trainers.add(user);
        }
        return trainers;
    }

    public static List ExercisesList() {
        List<Exercise> exercises = new ArrayList<>();
        for (Exercise ex : exerciseRepository.find()) {
            if(ex.getCustomerUsername().equals(loggedInUsername)) {
                exercises.add(ex);
            }
        }
        return exercises;
    }

    public static List ChatList() {
        List<Chat> chat = new ArrayList<>();
        for (Chat c : chatRepository.find()) {
            if(c.getSender().equals(loggedInUsername)) {
                chat.add(c);
            }
        }
        return chat;
    }


    public static List CustomerListUsername() {
        List<String> customers = new ArrayList<>();
        for (Customer user : customerRepository.find()) {
             customers.add(user.getUsername());
        }
        return customers;
    }

    public static List TrainerListUsername() {
        List<String> trainers = new ArrayList<>();
        for (Trainer user : trainerRepository.find()) {
            trainers.add(user.getUsername());
        }
        return trainers;
    }

    public static List ExercisesNameList(String username) {
        List<String> exercises = new ArrayList<>();
        for (Exercise ex : exerciseRepository.find()) {
            if(ex.getCustomerUsername().equals(username)) {
                exercises.add(ex.getExerciseName());
            }
        }
        return exercises;
    }

    public static String getTrainerName() {
        for (Trainer user : trainerRepository.find()) {
            if (Objects.equals(loggedInUsername, user.getUsername())) {
                return user.getUsername();
            }
        }
        return null;
    }

    public static String getCustomerUsername(String customerUsername) {
        for (Customer user : customerRepository.find()) {
            if (Objects.equals(customerUsername, user.getUsername()))
                return user.getUsername();
        }
        return null;
    }

    public static boolean isStringInt(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
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
