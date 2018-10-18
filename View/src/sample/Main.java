package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, Screen.getPrimary().getBounds().getWidth()/2, Screen.getPrimary().getBounds().getHeight()/2));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
