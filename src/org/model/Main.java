package org.model;

//import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import org.controller.GUIController;
import javafx.scene.chart.LineChart;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        System.out.println("Running: Big Horn Dam User Centre\n");
        FXMLLoader loader = new FXMLLoader();
        // getClass().getResource("../view/Main.fxml")
        Parent root = loader.load(getClass().getResource("../view/Main.fxml"));
        System.out.println("Connection Established\n");
        primaryStage.setTitle("Bighorn Dam User Centre");
        primaryStage.setScene(new Scene(root, 1275, 800));
        primaryStage.show();
    }
    public static void main(String args[])
    {
        launch(args);
    }
}