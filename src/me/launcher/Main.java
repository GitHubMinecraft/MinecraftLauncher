package me.launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.util.Log;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("launcher.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        Log.out("Opened Window");
    }


    public static void main(String[] args) {
        if (args.length != 0) {
            Log.DEBUG = Boolean.parseBoolean(args[0]);
        }
        launch(args);
    }
}
