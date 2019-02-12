package org.controller;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import java.lang.*;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.application.Application;
import org.model.*;
import javafx.collections.FXCollections;
import java.util.*;

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
    private Button btnHome, btnModCon, btn3, btn4, btnReadFile;

    @FXML
    private Menu mnu_edit, mnu_file, mnnu_help;

    @FXML
    private MenuItem mnu_file_btn;

    @FXML
    private LineChart<Integer, Integer> lcTDA;

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
            //accLeftModCon.toFront();
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
    public void readFile(ActionEvent e){
        System.out.println("Read File Clicked");
                if (e.getSource() == btnReadFile){
                }
    }

    public void initialize(){
        // parse file
        String fileExt = "D:\\1. Programming\\GitHub\\BighornDam_UserCentre\\BighornDam_UserCentre\\src\\org\\model\\";
        String filename = "data.txt";
        parser prs = new parser(fileExt + filename);
        int arrTDA[][] = prs.parseThirtyDayTotal();
        int arrNodeTDA[][][] = parser.parseNodeDataThirtyDays();
        int meas = prs.getMeasPerDay();
        int tran = prs.getNumOfTrans();


        // -- Thirty Day Avg Line Chart --
        setTDALineChart(arrTDA, tran, meas);


        // -- Thirty Day Avg Stacked Bar Chart --
        setTDAModuleStackBar(arrTDA, tran, meas);


        // -- Status Label Alert --
        setStatusLabel(arrNodeTDA, tran, meas);

        // end
    }

    private void setTDAModuleStackBar(int arr[][], int tran, int meas){
        // set chart axis
        /*stkbrTDAx.setAutoRanging(false);
        stkbrTDAx.setLowerBound(0);
        stkbrTDAx.setUpperBound(30);
        stkbrTDAx.setTickUnit(1);
        stkbrTDAy.setAutoRanging(false);
        stkbrTDAy.setLowerBound(0);
        stkbrTDAy.setUpperBound(1000);
        stkbrTDAy.setTickUnit(1000);*/
        stkbrTDA.setLegendVisible(true);

        // set series data
        XYChart.Series<String, Integer> seriesTDHigh = new XYChart.Series<>();
        XYChart.Series<String, Integer> seriesTDLow = new XYChart.Series<>();
        XYChart.Series<String, Integer> seriesTDAvg = new XYChart.Series<>();

        seriesTDHigh.setName("High");
        seriesTDLow.setName("Low");
        seriesTDAvg.setName("Average");
        stkbrTDAx.setLabel("Node");
        stkbrTDAy.setLabel("Water Level (m)");

        ArrayList<String> stkbrArray = new ArrayList<String>();

        // automation direct
        int tranID[] = new int[tran];
        boolean match = true;
        int count = 0;
        for (int z = 0; z < tran*3; z++){
            for (int x = 0; x < tran; x++){
                if (arr[z][0] == tranID[x])
                    match = false;
            }
            if (match){
                tranID[count] = arr[z][0];
                count++;
            }
            match = true;
        }


        // thirty day high / low
        count = 0;
        int max, min, total, avg;
        for (int z = 0; z < tran; z++){
            stkbrArray.add(String.valueOf(tranID[z]));
            int temp[] = new int[30*meas];
            count = 0;
            total = 0;
            max = 0;
            min = arr[z][1];
            for (int x = 0; x < arr.length; x++){
                if (arr[x][0] == tranID[z]) {
                    temp[count] = arr[x][1];
                    count++;
                }
            }
            for (int y = 0; y < temp.length; y++){
                if (temp[y] > max)
                    max = temp[y];
                //System.out.println(max);
                if (temp[y] < min)
                    min = temp[y];
                //System.out.println(min);
                total += temp[y];
            }
            avg = total / temp.length;

            seriesTDHigh.getData().add(new XYChart.Data<>(String.valueOf(tranID[z]), max - min - avg));
            seriesTDAvg.getData().add(new XYChart.Data<>(String.valueOf(tranID[z]), avg - min));
            seriesTDLow.getData().add(new XYChart.Data<>(String.valueOf(tranID[z]), min));
        }

        // Adding to stack bar thirty day average
        stkbrTDAx.setCategories(FXCollections.<String>observableArrayList(stkbrArray));
        stkbrTDA.getData().add(seriesTDLow);
        stkbrTDA.getData().add(seriesTDAvg);
        stkbrTDA.getData().add(seriesTDHigh);
    }

    private void setTDALineChart(int arr[][], int tran, int meas){
        // set chart axis
        brTDAx.setAutoRanging(false);
        brTDAx.setLowerBound(0);
        brTDAx.setUpperBound(29);
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
        lcTDA.getData().add(seriesThirtyDayAvg);
    }

    private void setStatusLabel(int arr[][][], int tran, int meas){
        int alertNodes[] = new int[tran];
        boolean cont = false;
        for (int z = 0; z < tran; z++){
            if (arr[z][1].length < 30*meas - 3) {
                alertNodes[z] = arr[z][0][0];
                cont = true;
            }
        }

        if (cont) {
            String s = "Node Alert: ";
            for (int z = 0; alertNodes[z] != 0; z++) {
                s += alertNodes[z] + ", ";
            }
            statusLabel.setText(s);
        }
        else
            statusLabel.setText("All Nodes Working Properly");
    }


}
