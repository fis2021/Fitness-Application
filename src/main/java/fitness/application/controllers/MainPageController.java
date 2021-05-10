package fitness.application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import fitness.application.services.*;
import fitness.application.user.User;
import java.io.IOException;



public class MainPageController {

    @FXML
    Button exitButton;
    public void handleExit(MouseEvent mouseEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }


}
