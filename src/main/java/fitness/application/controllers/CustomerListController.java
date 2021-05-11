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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import fitness.application.user.*;
import fitness.application.services.*;
import fitness.application.controllers.RegisterController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CustomerListController implements Initializable {
    @FXML
    Button logOutButton,addExercisesButton;

    @FXML
    private TableView customersTable;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        TableColumn name = new TableColumn("Username");
        TableColumn height = new TableColumn("Height");
        TableColumn weight = new TableColumn("Weight");
        TableColumn age =  new TableColumn("Age");
        TableColumn gender =  new TableColumn("Gender");
        customersTable.getColumns().addAll(name, height, weight, age,gender);

        final ObservableList<Customer> data = FXCollections.observableArrayList(UserServices.CustomerList());

        name.setCellValueFactory(new PropertyValueFactory<Customer,String>("username"));
        height.setCellValueFactory(new PropertyValueFactory<Customer,String>("height"));
        weight.setCellValueFactory(new PropertyValueFactory<Customer,String>("weight"));
        age.setCellValueFactory(new PropertyValueFactory<Customer,String>("age"));
        gender.setCellValueFactory(new PropertyValueFactory<Customer,String>("gender"));

        customersTable.setItems(data);
    }
    
    public void handleLogOut(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Stage window = (Stage)logOutButton.getScene().getWindow();
        window.setTitle("Log In");
        window.setScene(new Scene(root, 509,339));
    }

    public void handleAddExercises(MouseEvent mouseEvent) {
    }
}