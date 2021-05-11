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

public class RegisterController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField fullnameField;
    @FXML
    private ChoiceBox roleField;
    @FXML
    private Text registerMessage;
    @FXML
    public void initialize() {
        roleField.getItems().addAll("Customer", "Trainer");
    }

    @FXML
    public void handleRegister() {
        try {
            if(((String) roleField.getValue()).equals("Customer")) {
                UserServices.addCustomer(usernameField.getText(), emailField.getText(), passwordField.getText(), fullnameField.getText(), (String) roleField.getValue());
                registerMessage.setText("Account created successfully!");
            }
            else if(((String) roleField.getValue()).equals("Trainer")) {
                UserServices.addTrainer(usernameField.getText(), emailField.getText(), passwordField.getText(), fullnameField.getText(), (String) roleField.getValue());
                registerMessage.setText("Account created successfully!");
            }

        } catch (usernameAlreadyExists e) {
            registerMessage.setText(e.getMessage());
        }
        catch (incorrectUsername e)
        {
            registerMessage.setText(e.getMessage());
        }
        catch (incorrectPassword e)
        {
            registerMessage.setText(e.getMessage());
        }
    }

    @FXML
    Button backButton;
    public void handleBack(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Stage  window = (Stage)backButton.getScene().getWindow();
        window.setTitle("Log In");
        window.setScene(new Scene(root, 509,339));

    }



}