package fitness.application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import fitness.application.services.*;

import java.io.IOException;


public class MainPageController {

    @FXML
    private Button exitButton,logOutButton,myProfileButton,chatButton;
    @FXML
    private Text welcomeText;

    public void initialize() throws IOException {
        welcomeText.setText("Welcome "+ UserServices.FindTheUser(UserServices.getLoggedInUsername()).getUsername());
    }
    public void handleExit(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void handleLogOut(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Stage  window = (Stage)logOutButton.getScene().getWindow();
        window.setTitle("Log In");
        window.setResizable(false);
        window.setScene(new Scene(root, 509,339));
    }

    public void handleMyProfile(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/MyProfilePage.fxml"));
        Stage  window = (Stage)myProfileButton.getScene().getWindow();
        window.setTitle("My Profile");
        window.setResizable(false);
        window.setScene(new Scene(root, 600,400));
    }

    public void handleMyExercises(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        UserServices.checkExerciseExpired();
        Parent root = FXMLLoader.load(getClass().getResource("/MyExercisesPage.fxml"));
        Stage  window = (Stage)myProfileButton.getScene().getWindow();
        window.setTitle("My Exercises");
        window.setResizable(false);
        window.setScene(new Scene(root, 600,400));
    }

    public void handleChat(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ChatCustomerPage.fxml"));
        Stage  window = (Stage)chatButton.getScene().getWindow();
        window.setTitle("Chat");
        window.setResizable(false);
        window.setScene(new Scene(root, 600,400));
    }

}
