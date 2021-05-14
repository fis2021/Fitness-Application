package fitness.application.controllers;

import fitness.application.exceptions.intException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import fitness.application.services.*;
import fitness.application.models.*;

import java.io.IOException;

import static java.lang.Float.NaN;

public class EditMyProfileController {
    @FXML
    private Button backButton;

    @FXML
    private TextField heightField, weightField, ageField, descriptionField;

    @FXML
    private ChoiceBox gender;

    @FXML
    private Text messageText;

    @FXML
    public void initialize() {
        gender.getItems().addAll("Male", "Female");
        heightField.setText(((Customer) UserServices.FindTheUser(UserServices.getLoggedInUsername())).getHeight());
        weightField.setText(((Customer) UserServices.FindTheUser(UserServices.getLoggedInUsername())).getWeight());
        ageField.setText(((Customer) UserServices.FindTheUser(UserServices.getLoggedInUsername())).getAge());
        gender.setValue(((Customer) UserServices.FindTheUser(UserServices.getLoggedInUsername())).getGender());
        descriptionField.setText(((Customer) UserServices.FindTheUser(UserServices.getLoggedInUsername())).getDescription());
    }

    public void handleBack(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/MyProfilePage.fxml"));
        Stage  window = (Stage)backButton.getScene().getWindow();
        window.setTitle("My Profile");
        window.setResizable(false);
        window.setScene(new Scene(root, 600,400));
    }

    public void handleSaveChanges(javafx.scene.input.MouseEvent mouseEvent) throws intException,IOException{
        try {
            if (UserServices.isStringInt(heightField.getText())==false || UserServices.isStringInt(weightField.getText())==false || UserServices.isStringInt(ageField.getText())==false)
            {
                throw new intException("height, weight and age");
            }
            Customer user = ((Customer) UserServices.FindTheUser(UserServices.getLoggedInUsername()));
            user.setCustomer(heightField.getText(), weightField.getText(), ageField.getText(), (String) gender.getValue(), descriptionField.getText());
            UserServices.customerRepository.update(user);
            Parent root = FXMLLoader.load(getClass().getResource("/MyProfilePage.fxml"));
            Stage window = (Stage) backButton.getScene().getWindow();
            window.setTitle("My Profile");
            window.setResizable(false);
            window.setScene(new Scene(root, 600, 400));
        }catch (intException e){
            messageText.setText(e.getMessage());
        }
    }
}
