package fitness.application.controllers;

import fitness.application.models.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import fitness.application.services.*;

import java.io.IOException;
public class MyProfileController {
    @FXML
    Text heightText,weightText,ageText,genderText,descriptionText;
    @FXML
    Button mainPageButton, editProfileButton;

    public void initialize() {
        heightText.setText(((Customer) UserServices.FindTheUser(UserServices.getLoggedInUsername())).getHeight());
        weightText.setText(((Customer) UserServices.FindTheUser(UserServices.getLoggedInUsername())).getWeight());
        ageText.setText(((Customer) UserServices.FindTheUser(UserServices.getLoggedInUsername())).getAge());
        genderText.setText(((Customer) UserServices.FindTheUser(UserServices.getLoggedInUsername())).getGender());
        descriptionText.setText(((Customer) UserServices.FindTheUser(UserServices.getLoggedInUsername())).getDescription());
    }

    public void handleMainPage(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/MainPage.fxml"));
        Stage window = (Stage)mainPageButton.getScene().getWindow();
        window.setTitle("Main Page");
        window.setResizable(false);
        window.setScene(new Scene(root, 600,400));
    }
    public void handleEditProfile(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/EditMyProfilePage.fxml"));
        Stage  window = (Stage)editProfileButton.getScene().getWindow();
        window.setTitle("Edit My Profile");
        window.setResizable(false);
        window.setScene(new Scene(root, 600,400));
    }
}
