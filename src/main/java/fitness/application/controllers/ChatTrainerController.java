package fitness.application.controllers;

import fitness.application.exceptions.emptyFieldException;
import fitness.application.exceptions.selectCustomerExeption;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import fitness.application.models.*;
import fitness.application.services.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatTrainerController implements Initializable {
    @FXML
    public Button backButton;
    @FXML
    public ChoiceBox customerBox;
    @FXML
    private TableView chatTable;
    @FXML
    private Text messageText;
    @FXML
    private TextField enterMessage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerBox.getItems().addAll(UserServices.CustomerListUsername());
    }

    public void handleBack(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/CustomerListPage.fxml"));
        Stage window = (Stage)backButton.getScene().getWindow();
        window.setTitle("Customer List");
        window.setResizable(false);
        window.setScene(new Scene(root, 600,400));
    }

    public void handleSelect(javafx.scene.input.MouseEvent mouseEvent) throws selectCustomerExeption {
        try {
            if (customerBox.getValue() == null)
                throw new selectCustomerExeption();
            messageText.setText("Trainer " + (String)customerBox.getValue() + " was selected! ");
        }catch(selectCustomerExeption e){
            messageText.setText(e.getMessage());
        }
        TableColumn chat = new TableColumn("Chat");
        chat.setMinWidth(350);
        chatTable.getColumns().addAll(chat);
        ObservableList<Chat> data = null;
        if(UserServices.FindTheChat(UserServices.getLoggedInUsername()+(String)customerBox.getValue())==true){
            data = FXCollections.observableArrayList(UserServices.FindTheChatObj(UserServices.getLoggedInUsername()+(String)customerBox.getValue()));
        }
        else if(UserServices.FindTheChat( (String)customerBox.getValue() + UserServices.getLoggedInUsername() )){
            data = FXCollections.observableArrayList(UserServices.FindTheChatObj((String)customerBox.getValue() + UserServices.getLoggedInUsername()));
        }
        chat.setCellValueFactory(new PropertyValueFactory<Exercise,String>("finalMessage"));
        chatTable.setItems(data);
    }

    public void handleSend(javafx.scene.input.MouseEvent mouseEvent) throws emptyFieldException, selectCustomerExeption {
        try {
            if(messageText.getText().equals(""))
                throw new emptyFieldException();
            UserServices.addChat(UserServices.getLoggedInUsername(), (String) customerBox.getValue(),UserServices.getLoggedInUsername()+": "+enterMessage.getText());
            messageText.setText("The message was successfully sent!");
            enterMessage.setText("");
        } catch (emptyFieldException e) {
            messageText.setText("You cannot leave empty text fields!");
        }
        handleSelect(mouseEvent);
    }


}