package fitness.application.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import fitness.application.models.*;
import fitness.application.services.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MyExercisesController implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private TableView exercisesTable;
    @FXML
    private Text bmrText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn muscleGroup = new TableColumn("Muscle Group");
        TableColumn exerciseName =  new TableColumn("Exercise Name");
        TableColumn sets = new TableColumn("Sets");
        TableColumn series = new TableColumn("Series");
        TableColumn dueDate = new TableColumn("Due date");
        TableColumn trainerName = new TableColumn("Trainer");
        muscleGroup.setMinWidth(100);
        exerciseName.setMinWidth(150);
        sets.setMinWidth(50);
        series.setMinWidth(50);
        dueDate.setMinWidth(80);
        trainerName.setMinWidth(145);


        exercisesTable.getColumns().addAll(muscleGroup, exerciseName,sets,series,dueDate,trainerName);

        final ObservableList<Exercise> data = FXCollections.observableArrayList(UserServices.ExercisesList());
        muscleGroup.setCellValueFactory(new PropertyValueFactory<Exercise,String>("muscleGroup"));
        exerciseName.setCellValueFactory(new PropertyValueFactory<Exercise, Integer>("exerciseName"));
        sets.setCellValueFactory(new PropertyValueFactory<Exercise,Integer>("sets"));
        series.setCellValueFactory(new PropertyValueFactory<Exercise,Integer>("series"));
        dueDate.setCellValueFactory(new PropertyValueFactory<Exercise,String>("dueDate"));
        trainerName.setCellValueFactory(new PropertyValueFactory<Exercise,String>("trainerName"));


        exercisesTable.setItems(data);



        Customer c = (Customer) UserServices.FindTheUser(UserServices.getLoggedInUsername());

        if(c.getGender()==null || c.getHeight()==null || c.getWeight()==null || c.getAge()==null || c.getHeight().equals("") || c.getWeight().equals("") || c.getAge().equals("") )
        {
            bmrText.setText("Go to My Profile page to set up your account");
        }else if(c.getGender().equals("Female"))
        {
            double bmr = 10 * Integer.parseInt(c.getWeight()) + 6.25 * Integer.parseInt(c.getHeight()) - 5 * Integer.parseInt(c.getAge()) -161;
            bmrText.setText("You need to eat "+(int)bmr+" calories per day!");
        }
        else if(c.getGender().equals("Male"))
        {
            double bmr = 10 * Integer.parseInt(c.getWeight()) + 6.25 * Integer.parseInt(c.getHeight()) - 5 * Integer.parseInt(c.getAge()) +5;
            bmrText.setText("You need to eat "+(int)bmr+" calories per day!");
        }
    }

    public void handleBack(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/MainPage.fxml"));
        Stage window = (Stage)backButton.getScene().getWindow();
        window.setTitle("Main Page");
        window.setResizable(false);
        window.setScene(new Scene(root, 600,400));
    }
}
