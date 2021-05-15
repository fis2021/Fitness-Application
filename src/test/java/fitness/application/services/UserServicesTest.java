package fitness.application.services;

import fitness.application.exceptions.*;
import fitness.application.models.Chat;
import fitness.application.models.Customer;
import fitness.application.models.Exercise;
import fitness.application.models.Trainer;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.testfx.assertions.api.Assertions.assertThat;

class UserServicesTest {
    @BeforeAll
    static void beforeAll() {
        System.out.println("Before Class");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After Class");
    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemServices.APPLICATION_FOLDER = ".test-fitnessapplication";
        FileSystemServices.initDirectory();
        FileUtils.cleanDirectory(FileSystemServices.getApplicationHomeFolder().toFile());
        UserServices.initDatabase();
    }

    @AfterEach
    void tearDown() {
        UserServices.getDatabase().close();
        System.out.println("After Each");
    }

    @Test
    @DisplayName("Customer is successfully added to the database")
    void testCustomerIsAddedToDatabase() throws incorrectUsername, emptyFieldException, incorrectPassword, usernameAlreadyExists {
        UserServices.addCustomer("CUSTOMER","EMAIL","PASSWORD","FULLNAME","Customer");
        assertThat(UserServices.CustomerList()).isNotEmpty();
        assertThat(UserServices.CustomerList()).size().isEqualTo(1);
        Customer customer = (Customer) UserServices.CustomerList().get(0);
        assertThat(customer).isNotNull();
        assertThat(customer.getUsername()).isEqualTo("CUSTOMER");
        //assertThat(customer.getFullName()).isEqualTo("FULLNAME");
        assertThat(customer.getEmail()).isEqualTo("EMAIL");
        assertThat(customer.getRole()).isEqualTo("Customer");
        assertThat(customer.getPassword()).isEqualTo(UserServices.encodePassword("CUSTOMER","PASSWORD"));
    }

    @Test
    @DisplayName("Trainer is successfully added to the database")
    void testTrainerIsAddedToDatabase() throws incorrectUsername, emptyFieldException, incorrectPassword, usernameAlreadyExists {
        UserServices.addTrainer("TRAINER","EMAIL","PASSWORD","FULLNAME","Trainer");
        assertThat(UserServices.getAllTrainers()).isNotEmpty();
        assertThat(UserServices.getAllTrainers()).size().isEqualTo(1);
        Trainer trainer = (Trainer) UserServices.getAllTrainers().get(0);
        assertThat(trainer).isNotNull();
        assertThat(trainer.getUsername()).isEqualTo("TRAINER");
        //assertThat(trainer.getFullName()).isEqualTo("FULLNAME");
        assertThat(trainer.getEmail()).isEqualTo("EMAIL");
        assertThat(trainer.getRole()).isEqualTo("Trainer");
        assertThat(trainer.getPassword()).isEqualTo(UserServices.encodePassword("TRAINER","PASSWORD"));
    }

    @Test
    @DisplayName("Exercise is successfully added to the database")
    void testExerciseIsAddedToDatabase() throws incorrectUsername, emptyFieldException, incorrectPassword, usernameAlreadyExists {
        UserServices.addCustomer("CUSTOMER","EMAIL","PASSWORD","FULLNAME","Customer");
        UserServices.addTrainer("TRAINER","EMAIL","PASSWORD","FULLNAME","Trainer");
        UserServices.addExercise("TRAINER","CUSTOMER","Chest","Exercise Name","4","10",2021,8,4);
        assertThat(UserServices.getAllExercises()).isNotEmpty();
        assertThat(UserServices.getAllExercises()).size().isEqualTo(1);
        Exercise exercise = (Exercise) UserServices.getAllExercises().get(0);
        assertThat(exercise).isNotNull();
        assertThat(exercise.getExerciseName()).isEqualTo("Exercise Name");
        assertThat(exercise.getCustomerUsername()).isEqualTo("CUSTOMER");
        assertThat(exercise.getMuscleGroup()).isEqualTo("Chest");
        assertThat(exercise.getTrainerName()).isEqualTo("TRAINER");
        assertThat(exercise.getSets()).isEqualTo("4");
        assertThat(exercise.getSeries()).isEqualTo("10");
        assertThat(exercise.getDay()==4);
        assertThat(exercise.getMonth()==8);
        assertThat(exercise.getYear()==2021);
    }

    @Test
    @DisplayName("Chat is successfully added to the database")
    void testChatIsAddedToDatabase() throws incorrectUsername, emptyFieldException, incorrectPassword, usernameAlreadyExists {
        UserServices.addCustomer("CUSTOMER","EMAIL","PASSWORD","FULLNAME","Customer");
        UserServices.addTrainer("TRAINER","EMAIL","PASSWORD","FULLNAME","Trainer");
        UserServices.addChat("CUSTOMER","TRAINER","MESSAGE");
        assertThat(UserServices.getAllChats()).isNotEmpty();
        assertThat(UserServices.getAllChats()).size().isEqualTo(1);
        Chat chat = UserServices.getAllChats().get(0);
        assertThat(chat).isNotNull();
        assertThat(chat.getName()).isEqualTo("CUSTOMERTRAINER");
        assertThat(chat.getMessage()).isEqualTo("MESSAGE");
    }

    @Test
    @DisplayName("Chat is successfully added to the database")
    void testRemoveExercise() throws incorrectUsername, emptyFieldException, incorrectPassword, usernameAlreadyExists {
        UserServices.addCustomer("CUSTOMER","EMAIL","PASSWORD","FULLNAME","Customer");
        UserServices.addTrainer("TRAINER","EMAIL","PASSWORD","FULLNAME","Trainer");
        UserServices.addExercise("TRAINER","CUSTOMER","Chest","Exercise Name","4","10",2021,8,4);
        UserServices.removeExercise("Exercise Name");
        if(UserServices.checkExerciseInDataBase("Exercise Name","CUSTOMER")==true){
            fail();
        }
    }

    // EXCEPTIONS
    @Test
    @DisplayName("Username does not exist")
    void testUsernameDoesNotExist() {
        assertThrows(usernameDoesNotExist.class, () -> {
            UserServices.checkUserExist("");
        });
        assertThrows(usernameDoesNotExist.class, () -> {
            UserServices.checkUserExist(null);
        });
    }
    @Test
    @DisplayName("Check empty fields")
    void testEmptyFields() {
        assertThrows(emptyFieldException.class, () -> {
            UserServices.checkEmptyFieldsChat("");
        });
        assertThrows(emptyFieldException.class, () -> {
            UserServices.checkEmptyFields("","FULLNAME","PASSWORD","EMAIL","Customer");
        });
        assertThrows(emptyFieldException.class, () -> {
            UserServices.checkEmptyFields("USER","","PASSWORD","EMAIL","Customer");
        });
        assertThrows(emptyFieldException.class, () -> {
            UserServices.checkEmptyFields("USER","FULLNAME","","EMAIL","Customer");
        });
        assertThrows(emptyFieldException.class, () -> {
            UserServices.checkEmptyFields("USER","FULLNAME","PASSWORD","","Customer");
        });
        assertThrows(emptyFieldException.class, () -> {
            UserServices.checkEmptyFields("USER","FULLNAME","PASSWORD","EMAIL",null);
        });
        assertThrows(emptyFieldException.class, () -> {
            UserServices.checkEmptyFieldsAddExercise(null,"Chest","ExerciseName","4","10",2021);
        });
        assertThrows(emptyFieldException.class, () -> {
            UserServices.checkEmptyFieldsAddExercise("USER",null,"ExerciseName","4","10",2021);
        });
        assertThrows(emptyFieldException.class, () -> {
            UserServices.checkEmptyFieldsAddExercise("USER","Chest","","4","10",2021);
        });
        assertThrows(emptyFieldException.class, () -> {
            UserServices.checkEmptyFieldsAddExercise("USER","Chest","ExerciseName","","10",2021);
        });
        assertThrows(emptyFieldException.class, () -> {
            UserServices.checkEmptyFieldsAddExercise("USER","Chest","ExerciseName","4","",2021);
        });
        assertThrows(emptyFieldException.class, () -> {
            UserServices.checkEmptyFieldsAddExercise("USER","Chest","ExerciseName","4","10",0);
        });
        assertThrows(emptyFieldException.class, () -> {
            UserServices.checkEmptyFieldsLogIn("","PASSWORD");
        });
        assertThrows(emptyFieldException.class, () -> {
            UserServices.checkEmptyFieldsLogIn("USER","");
        });
    }
}