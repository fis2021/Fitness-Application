package fitness.application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import fitness.application.services.*;
import fitness.application.exceptions.*;
import fitness.application.user.*;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

public class ChatTrainerController {
    @FXML
    public Button backButton;
    @FXML
    public ChoiceBox customerBox;
    @FXML
    public void initialize() {
        customerBox.getItems().addAll(UserServices.CustomerListUsername());

    }

    public void handleBack(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/CustomerListPage.fxml"));
        Stage window = (Stage)backButton.getScene().getWindow();
        window.setTitle("Customer List Page");
        window.setScene(new Scene(root, 600,400));
    }
}