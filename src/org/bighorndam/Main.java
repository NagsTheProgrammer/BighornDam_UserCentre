package org.bighorndam;

//import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        System.out.println("aaa");
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        System.out.println("bbb");
        primaryStage.setTitle("Bighorn Dam User Centre");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();

    }
    public static void main(String args[])
    {
        launch(args);
    }
}
