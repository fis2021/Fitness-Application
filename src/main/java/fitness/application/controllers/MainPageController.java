package fitness.application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import fitness.application.services.*;
import fitness.application.exceptions.*;
import fitness.application.user.*;
import java.io.IOException;
import fitness.application.controllers.LoginController;


public class MainPageController {

    @FXML
    Button exitButton,logOutButton;
    @FXML
    private Text welcomeText;
    public void initialize() throws IOException {
        welcomeText.setText("Welcome");
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

}
