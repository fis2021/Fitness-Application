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

public class RemoveExercisesController {
    @FXML
    public Button backButton,removeButton;
    @FXML
    public ChoiceBox customerBox,exerciseBox;
    @FXML
    private Text messageText;
    @FXML
    public void initialize() {
        customerBox.getItems().addAll(UserServices.CustomerListUsername());

    }

    public void handleSelect(javafx.scene.input.MouseEvent mouseEvent) throws selectCustomerExeption,IOException {
       try {
           if (customerBox.getValue() == null)
               throw new selectCustomerExeption();
           exerciseBox.getItems().addAll(UserServices.ExercisesNameList((String) customerBox.getValue()));
           messageText.setText("Customer " + (String)customerBox.getValue() + " was selected! ");
       }catch(selectCustomerExeption e){
           messageText.setText(e.getMessage());
       }
    }

    public void handleBack(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/CustomerListPage.fxml"));
        Stage window = (Stage)backButton.getScene().getWindow();
        window.setTitle("Customer List");
        window.setScene(new Scene(root, 600,400));
    }

    public void handleRemove(javafx.scene.input.MouseEvent mouseEvent) throws emptyFieldException,IOException {
        try {
            if(customerBox.getValue()==null)
                throw new emptyFieldException();
            if(exerciseBox.getValue()==null)
                throw new emptyFieldException();
            UserServices.removeExercise((String) exerciseBox.getValue());
            messageText.setText("Exercise " + (String)exerciseBox.getValue() + " was removed! ");
            exerciseBox.getItems().remove((String)exerciseBox.getValue());
            exerciseBox.setValue(null);
        }catch(emptyFieldException e){
            messageText.setText(e.getMessage());
        }
    }
}
