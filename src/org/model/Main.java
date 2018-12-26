package org.model;

// Exception imports
//import java.io.IOException;

// JavaFX imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// Database imports
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    /*public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:C:/Users/anagr/OneDrive/Documents/Github/BighornDam_UserCentre/src/org/database" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }*/

    public static void main(String args[])
    {

        //createNewDatabase("test.db");

        launch(args);
    }
}