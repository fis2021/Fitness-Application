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

public class AddExercisesController {
    @FXML
    public Button backButton;
    @FXML
    public ChoiceBox customer,muscleGroup;
    @FXML
    private DatePicker dueDate;
    @FXML
    private Text messageText;
    @FXML
    private TextField exercisesNameField,setsField,seriesField;
    @FXML
    public void initialize() {
        customer.getItems().addAll(UserServices.CustomerListUsername());
        muscleGroup.getItems().addAll("Chest", "Back","Arms","Abdominals","Legs","Shoulders");
    }


    public void handleBack(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/CustomerListPage.fxml"));
        Stage window = (Stage)backButton.getScene().getWindow();
        window.setTitle("Customer List");
        window.setScene(new Scene(root, 600,400));
    }

    public void handleSubmit(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            LocalDate date = dueDate.getValue();
            int year = 0;
            int month = 0;
            int day = 0;
            if (!Objects.equals(date, null)) {
                year = date.getYear();
                month = date.getMonthValue();
                day = date.getDayOfMonth();
            }
            UserServices.addExercise(UserServices.getLoggedInUsername(), (String)customer.getValue(), (String)muscleGroup.getValue(), exercisesNameField.getText(), setsField.getText(),seriesField.getText(),year, month, day);
            messageText.setText("Exercise was successfully added!");
            customer.setValue(null);
            muscleGroup.setValue(null);
            exercisesNameField.setText("");
            setsField.setText("");
            seriesField.setText("");
            dueDate.setValue(null);
        } catch (emptyFieldException e) {
            messageText.setText("You cannot leave empty text fields!");
        }
    }
}
