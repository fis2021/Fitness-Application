package fitness.application.controllers;

import fitness.application.exceptions.*;
import fitness.application.services.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(ApplicationExtension.class)
class ChatCustomerTest {
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

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        primaryStage.setTitle("Log In");
        primaryStage.setScene(new Scene(root, 509, 339));
        primaryStage.show();

    }

    @Test
    @DisplayName("Successfully Chat")
    void successfulChat(FxRobot robot) throws incorrectUsername, incorrectPassword, usernameAlreadyExists, emptyFieldException, usernameDoesNotExist {
        UserServices.addCustomer("CUSTOMER","EMAIL","PASSWORD","FULLNAME","Customer");
        UserServices.addTrainer("TRAINER","EMAIL","PASSWORD","FULLNAME","Trainer");
        robot.clickOn("#usernameField");
        robot.write("CUSTOMER");
        robot.clickOn("#passwordField");
        robot.write("PASSWORD");
        robot.clickOn("#loginButton");
        robot.clickOn("#chatButton");
        robot.clickOn("#trainerBox");
        robot.type(KeyCode.ENTER);
        robot.clickOn("#selectButton");
        robot.clickOn("#enterMessage");
        robot.write("HELLO");
        robot.clickOn("#sendButton");
    }
}