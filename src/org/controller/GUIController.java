package org.controller;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import java.lang.*;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.application.Application;
import org.model.*;

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
import javafx.scene.chart.ScatterChart;

import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;


public class GUIController
{

    @FXML
    private Pane paneHome, paneModCon, pane3, pane4;

    @FXML
    private AnchorPane anchr_1, anchrLeftHome;

    @FXML
    private Label statusLabel;

    @FXML
    private Accordion accLeftModCon;

    @FXML
    private Button btnHome, btnModCon, btn3, btn4, setThirtyDayAvg;

    @FXML
    private Menu mnu_edit, mnu_file, mnnu_help;

    @FXML
    private MenuItem mnu_file_btn;

    @FXML
    private LineChart<Integer, Integer> brTDA;

    @FXML
    private NumberAxis brTDAy, brTDAx, stkbrTDAy;

    @FXML
    private CategoryAxis stkbrTDAx;

    @FXML
    private StackedBarChart<String, Integer> stkbrTDA;




    public void setPane(ActionEvent e){
        System.out.println("You clicked on a button");

        if (e.getSource() == btnHome) {
            paneHome.toFront();
            anchrLeftHome.toFront();
        }
        else if (e.getSource() == btnModCon){
            paneModCon.toFront();
            accLeftModCon.toFront();
        }
        else if (e.getSource() == btn3) {
            pane3.toFront();
            anchrLeftHome.toFront();
        }
        else if (e.getSource() == btn4) {
            pane4.toFront();
            anchrLeftHome.toFront();
        }
    }

    @FXML
    public void setChart(ActionEvent e){
        if (e.getSource() == setThirtyDayAvg){
            System.out.println("Read File Clicked");

            // parse file
            parser prs = new parser("D:\\1. Programming\\GitHub\\BighornDam_UserCentre\\BighornDam_UserCentre\\src\\org\\model\\data.txt");
            int arr[][] = prs.readThirtyDayTotal();
            int meas = prs.getMeasPerDay();
            int tran = prs.getNumOfTrans();

            // -- Thirty Day Avg Bar Chart --

            // set chart axis
            brTDAx.setAutoRanging(false);
            brTDAx.setLowerBound(0);
            brTDAx.setUpperBound(30);
            brTDAx.setTickUnit(1);
            /*brTDAy.setAutoRanging(false);
            brTDAy.setLowerBound(0);
            brTDAy.setUpperBound(1000);
            brTDAy.setTickUnit(1000);*/

            // set series data
            XYChart.Series<Integer, Integer> seriesThirtyDayAvg = new XYChart.Series<>();
            int hold = 0;
            for (int z = 0; z < 30; z++) {
                for (int x = 0; x < meas*tran; x++){
                    hold += arr[x + z*meas*tran][1];
                }
                hold = hold / (meas*tran);
                seriesThirtyDayAvg.getData().add(new XYChart.Data<>(29-z, hold));
                hold = 0;
            }
            hold = 0;
            brTDA.getData().add(seriesThirtyDayAvg);

            // -- Thirty Day Avg Stacked Bar Chart --

            // set chart axis
            /*stkbrTDAx.setAutoRanging(false);
            stkbrTDAx.setLowerBound(0);
            stkbrTDAx.setUpperBound(30);
            stkbrTDAx.setTickUnit(1);
            stkbrTDAy.setAutoRanging(false);
            stkbrTDAy.setLowerBound(0);
            stkbrTDAy.setUpperBound(1000);
            stkbrTDAy.setTickUnit(1000);*/

            // set series data
            XYChart.Series<String, Integer> seriesTDHigh = new XYChart.Series<>();
            XYChart.Series<Integer, Integer> seriesTDLow = new XYChart.Series<>();
            XYChart.Series<Integer, Integer> seriesTDAvg = new XYChart.Series<>();

            int tranID[] = new int[tran];
            boolean match = false;
            int count = 0;
            for (int z = 0; z < tran*3; z++){
                for (int x = 0; x < tran; x++){
                    if (arr[z][0] != tranID[x])
                        match = true;
                }
                if (match){
                    tranID[count] = arr[z][0];
                    count++;
                }
            }

            count = 0;
            int max = 0;
            // thirty day high
            for (int z = 0; z < tran; z++){
                int temp[] = new int[30*meas];
                for (int x = 0; x < meas*tran*30; x++){
                    if (arr[x][0] == tranID[z]) {
                        temp[count] = arr[x][1];
                        count++;
                    }
                }
                for (int y = 0; y < temp.length; y++){
                    if (temp[y] > max)
                        max = temp[y];
                }
                seriesThirtyDayAvg.getData().add(new XYChart.Data<>(tranID[z], max));
                max = 0;
            }
            stkbrTDA.getData().add(seriesTDHigh);


        }
    }

    public void initialize(){
    }
}
