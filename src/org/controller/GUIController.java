package org.controller;


import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.application.Application;

// LineChart
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;

// textArea
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.MenuItem;

import javafx.scene.layout.AnchorPane;


public class GUIController
{

    @FXML
    private Pane paneHome, paneModCon, pane3, pane4;

    @FXML
    private AnchorPane anchr_1;

    @FXML
    private Button btnHome, btnModCon, btn3, btn4, setThirtyDayAvg;

    @FXML
    private Menu mnu_edit, mnu_file, mnnu_help;

    @FXML
    private MenuItem mnu_file_btn;

    @FXML
    private LineChart<String, Integer> thirtyDayAvg;

    @FXML
    private NumberAxis thirtyDayAvgY;

    @FXML
    private CategoryAxis thirtyDayAvgX;

    public void setPane(ActionEvent e){
        System.out.println("You clicked on a button");

        if (e.getSource() == btnHome)
            paneHome.toFront();
        else if (e.getSource() == btnModCon)
            paneModCon.toFront();
        else if (e.getSource() == btn3)
            pane3.toFront();
        else if (e.getSource() == btn4)
            pane4.toFront();
        else if (e.getSource() == mnu_file_btn)
            System.out.println("you clicked a menu button");
    }

    @FXML
    public void setChart(ActionEvent e){
        if (e.getSource() == setThirtyDayAvg){
            System.out.println("setChart clicked");
            XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
            //populating the series with data
            series.getData().add(new XYChart.Data("1", 23));
            thirtyDayAvg.getData().addAll(series);
        }
    }

    public void initialize(){
        System.out.println("Initialize");
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        //populating the series with data
        series.getData().add(new XYChart.Data<String, Integer>("Hello", 23));
        //thirtyDayAvg.getData().addAll(series);
    }



    /*@FXML
    public void setThirtyDayAvg(ActionEvent e){
        // setting thirtyDayAvg

        //final NumberAxis thirtyDayAvgX = new NumberAxis();
        //final NumberAxis thirtyDayAvgY = new NumberAxis();

        //thirtyDayAvgX.setLabel("Date");
        //thirtyDayAvgY.setLabel("Daily Average (m)");

        //final LineChart<Number, Number> thirtyDayAvg = new LineChart<Number, Number>(thirtyDayAvgX, thirtyDayAvgY);

        XYChart.Series series = new XYChart.Series();
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        thirtyDayAvg.getData().addAll(series);
    }*/

    /*
    @FXML
    private TextArea routeText;
    @FXML
    private TextArea routeInformation;
    @FXML
    private TextField busCap;
    @FXML
    private TextField busCost;
    */

    /*
    @FXML
    public void route1Pressed(ActionEvent e)
    {
        String s = BusController.getInstance().DrivingInstructions(0);

        System.out.println("Route 1 pressed");
        routeText.setText(s);
    }
    */
}
