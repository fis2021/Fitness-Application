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
class EditMyProfileTest {
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
        primaryStage.setTitle("Edit My Profile");
        primaryStage.setScene(new Scene(root, 509, 339));
        primaryStage.show();

    }

    @Test
    @DisplayName("Successfully Edit My Profile")
    void successfulEditMyProfile(FxRobot robot) throws incorrectUsername, incorrectPassword, usernameAlreadyExists, emptyFieldException, usernameDoesNotExist {
        UserServices.addCustomer("USERNAME","EMAIL","PASSWORD","FULLNAME","Customer");
        robot.clickOn("#usernameField");
        robot.write("USERNAME");
        robot.clickOn("#passwordField");
        robot.write("PASSWORD");
        robot.clickOn("#loginButton");
        robot.clickOn("#myProfileButton");
        robot.clickOn("#editProfileButton");
        robot.clickOn("#heightField");
        robot.write("190");
        robot.clickOn("#weightField");
        robot.write("80");
        robot.clickOn("#ageField");
        robot.write("20");
        robot.clickOn("#gender");
        robot.type(KeyCode.ENTER);
        robot.clickOn("#descriptionField");
        robot.write("DESCRIPTION");
        robot.clickOn("#saveButton");

    }
}