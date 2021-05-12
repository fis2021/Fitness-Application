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




public class MyExercisesController {
    @FXML
    Button backButton;
    public void handleBack(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/MainPage.fxml"));
        Stage window = (Stage)backButton.getScene().getWindow();
        window.setTitle("Main Page");
        window.setScene(new Scene(root, 600,400));
    }
}
