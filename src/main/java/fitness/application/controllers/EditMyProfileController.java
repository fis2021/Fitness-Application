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

import java.awt.*;
import java.io.IOException;

public class EditMyProfileController {
    @FXML
    public Button backButton;

    @FXML
    private TextField heightField, weightField, ageField, descriptionField;

    @FXML
    public ChoiceBox gender;

    @FXML
    public void initialize() { gender.getItems().addAll("Male", "Female"); }

    public void handleBack(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/MyProfilePage.fxml"));
        Stage  window = (Stage)backButton.getScene().getWindow();
        window.setTitle("My Profile");
        window.setScene(new Scene(root, 600,400));
    }

    public void handleSaveChanges(javafx.scene.input.MouseEvent mouseEvent) throws IOException{
        Customer user = ((Customer) UserServices.FindTheUser(UserServices.getLoggedInUsername()));
        user.setCustomer(heightField.getText(),weightField.getText(),ageField.getText(),(String) gender.getValue(),descriptionField.getText());
        UserServices.customerRepository.update(user);
        Parent root = FXMLLoader.load(getClass().getResource("/MyProfilePage.fxml"));
        Stage  window = (Stage)backButton.getScene().getWindow();
        window.setTitle("My Profile");
        window.setScene(new Scene(root, 600,400));
    }
}
