package fitness.application.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import fitness.application.user.*;
import fitness.application.services.*;
import fitness.application.controllers.RegisterController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MyExercisesController implements Initializable {
    @FXML
    Button backButton;
    @FXML
    private TableView exercisesTable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {


        TableColumn muscleGroup = new TableColumn("Muscle Group");
        TableColumn exerciseName =  new TableColumn("Exercise Name");
        TableColumn sets = new TableColumn("Sets");
        TableColumn series = new TableColumn("Series");
        TableColumn dueDate = new TableColumn("Due date");
        TableColumn trainerName = new TableColumn("Trainer");

        exercisesTable.getColumns().addAll(muscleGroup, exerciseName,sets,series,dueDate,trainerName);

        final ObservableList<Exercise> data = FXCollections.observableArrayList(UserServices.ExercisesList());
        muscleGroup.setCellValueFactory(new PropertyValueFactory<Exercise,String>("muscleGroup"));
        exerciseName.setCellValueFactory(new PropertyValueFactory<Exercise, Integer>("exerciseName"));
        sets.setCellValueFactory(new PropertyValueFactory<Exercise,Integer>("sets"));
        series.setCellValueFactory(new PropertyValueFactory<Exercise,Integer>("series"));
        dueDate.setCellValueFactory(new PropertyValueFactory<Exercise,String>("dueDate"));
        trainerName.setCellValueFactory(new PropertyValueFactory<Exercise,String>("trainerName"));


        exercisesTable.setItems(data);


    }

    public void handleBack(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/MainPage.fxml"));
        Stage window = (Stage)backButton.getScene().getWindow();
        window.setTitle("Main Page");
        window.setScene(new Scene(root, 600,400));
    }
}
