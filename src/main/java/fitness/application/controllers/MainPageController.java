package fitness.application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import fitness.application.user.*;
import fitness.application.services.*;
import fitness.application.controllers.RegisterController;

import java.io.IOException;


public class MainPageController {

    @FXML
    Button exitButton,logOutButton,myProfileButton;
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
        window.setScene(new Scene(root, 509,339));
    }

    public void handleMyProfile(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/MyProfilePage.fxml"));
        Stage  window = (Stage)myProfileButton.getScene().getWindow();
        window.setTitle("My Profile");
        window.setScene(new Scene(root, 600,400));
    }

    public void handleMyExercises(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/MyExercisesPage.fxml"));
        Stage  window = (Stage)myProfileButton.getScene().getWindow();
        window.setTitle("My Exercises");
        window.setScene(new Scene(root, 600,400));
    }

}
