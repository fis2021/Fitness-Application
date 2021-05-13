package fitness.application.controllers;

import fitness.application.exceptions.emptyFieldException;
import fitness.application.exceptions.selectCustomerExeption;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import fitness.application.user.*;
import fitness.application.services.*;
import fitness.application.controllers.RegisterController;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class ChatCustomerController implements Initializable {
    @FXML
    public Button backButton;
    @FXML
    public ChoiceBox trainerBox;
    @FXML
    private TableView chatTable;
    @FXML
    private TextField enterMessage;
    @FXML
    private Text messageText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        trainerBox.getItems().addAll(UserServices.TrainerListUsername());
    }

    public void handleBack(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/MainPage.fxml"));
        Stage window = (Stage)backButton.getScene().getWindow();
        window.setTitle("Main Page");
        window.setScene(new Scene(root, 600,400));
    }

    public void handleSelect(javafx.scene.input.MouseEvent mouseEvent) throws selectCustomerExeption {
        try {
            if (trainerBox.getValue() == null)
                throw new selectCustomerExeption();
            messageText.setText("Trainer " + (String)trainerBox.getValue() + " was selected! ");
        }catch(selectCustomerExeption e){
            messageText.setText(e.getMessage());
        }
        TableColumn chat = new TableColumn("Chat");
        chat.setMinWidth(350);
        chatTable.getColumns().addAll(chat);
        ObservableList<Chat> data = null;
        if(UserServices.FindTheChat(UserServices.getLoggedInUsername()+(String)trainerBox.getValue())==true){
            data = FXCollections.observableArrayList(UserServices.FindTheChatObj(UserServices.getLoggedInUsername()+(String)trainerBox.getValue()));
        }
        else if(UserServices.FindTheChat( (String)trainerBox.getValue() + UserServices.getLoggedInUsername() )){
            data = FXCollections.observableArrayList(UserServices.FindTheChatObj((String)trainerBox.getValue() + UserServices.getLoggedInUsername()));
        }
        chat.setCellValueFactory(new PropertyValueFactory<Exercise,String>("finalMessage"));
        chatTable.setItems(data);
    }

    public void handleSend(javafx.scene.input.MouseEvent mouseEvent) throws emptyFieldException, selectCustomerExeption {
        try {
            if(messageText.getText().equals(""))
                throw new emptyFieldException();
            UserServices.addChat(UserServices.getLoggedInUsername(), (String) trainerBox.getValue(),UserServices.getLoggedInUsername()+": "+enterMessage.getText());
            messageText.setText("The message was successfully sent!");
            enterMessage.setText("");
        } catch (emptyFieldException e) {
            messageText.setText("You cannot leave empty text fields!");
        }
        handleSelect(mouseEvent);
    }
}
