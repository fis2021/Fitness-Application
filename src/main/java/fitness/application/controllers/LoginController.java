package fitness.application.controllers;

import fitness.application.exceptions.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import fitness.application.services.*;
import fitness.application.models.User;
import java.io.IOException;

public class LoginController {
    @FXML
    Button createAccountButton,loginButton;
    public void handleCreateAccount(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Register.fxml"));
        Stage  window = (Stage)createAccountButton.getScene().getWindow();
        window.setTitle("Create Account");
        window.setResizable(false);
        window.setScene(new Scene(root, 509,339));
    }

    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    private Text loginMessage;
    public void handleLogIn(javafx.scene.input.MouseEvent mouseEvent) throws IOException, usernameDoesNotExist, emptyFieldException {
        try {
            UserServices.checkEmptyFieldsLogIn(usernameField.getText(),passwordField.getText());
            if (UserServices.checkUserExist(usernameField.getText()).equals("Customer")) {
                User c = UserServices.FindTheUser(usernameField.getText());

                if (UserServices.encodePassword(usernameField.getText(), passwordField.getText()).compareTo(c.getPassword()) == 0) {
                    try {
                        UserServices.setLoggedInUsername(usernameField.getText());
                        Parent root = FXMLLoader.load(getClass().getResource("/MainPage.fxml"));
                        Stage window = (Stage) loginButton.getScene().getWindow();
                        window.setTitle("Main Page");
                        window.setResizable(false);
                        window.setScene(new Scene(root, 600, 400));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else
                {loginMessage.setText("Incorrect Password");passwordField.setText("");}
            }


            if (UserServices.checkUserExist(usernameField.getText()).equals("Trainer")) {
                User c = UserServices.FindTheUser(usernameField.getText());

                if (UserServices.encodePassword(usernameField.getText(), passwordField.getText()).compareTo(c.getPassword()) == 0) {
                    try {
                        UserServices.setLoggedInUsername(usernameField.getText());
                        Parent root = FXMLLoader.load(getClass().getResource("/CustomerListPage.fxml"));
                        Stage window = (Stage) loginButton.getScene().getWindow();
                        window.setTitle("Customer List");
                        window.setResizable(false);
                        window.setScene(new Scene(root, 600, 400));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else
                {loginMessage.setText("Incorrect Password");passwordField.setText("");}
            }

        } catch (usernameDoesNotExist e)
        {
            loginMessage.setText(e.getMessage());
        }
        catch (emptyFieldException e)
        {
            loginMessage.setText(e.getMessage());
        }
    }

}