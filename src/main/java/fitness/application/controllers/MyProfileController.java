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

import java.awt.*;
import java.io.IOException;
public class MyProfileController {
    @FXML
    Button mainPageButton, editProfileButton;

    public void handleMainPage(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/MainPage.fxml"));
        Stage window = (Stage)mainPageButton.getScene().getWindow();
        window.setTitle("Main Page");
        window.setScene(new Scene(root, 600,400));
    }
    public void handleEditProfile(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/EditMyProfilePage.fxml"));
        Stage  window = (Stage)editProfileButton.getScene().getWindow();
        window.setTitle("Edit My Profile");
        window.setScene(new Scene(root, 600,400));
    }
}
