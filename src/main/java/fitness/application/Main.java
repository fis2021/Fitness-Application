package fitness.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import fitness.application.services.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        UserServices.initDatabase();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        primaryStage.setTitle("Log In");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 509, 339));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}