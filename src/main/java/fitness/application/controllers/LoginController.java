package fitness.application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    Button createAccountButton,loginButton;
    public void handleCreateAccount(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Register.fxml"));
        Stage  window = (Stage)createAccountButton.getScene().getWindow();
        window.setTitle("Create Account");
        window.setScene(new Scene(root, 509,339));
    }
}
