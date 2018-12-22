package org.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GuiController
{
    @FXML
    private TextArea routeText;
    @FXML
    private TextArea routeInformation;
    @FXML
    private TextField busCap;
    @FXML
    private TextField busCost;

    @FXML
    public void route1Pressed(ActionEvent e)
    {
        String s = BusController.getInstance().DrivingInstructions(0);

        System.out.println("Route 1 pressed");
        routeText.setText(s);
    }

    @FXML
    public void route2Pressed(ActionEvent e)
    {
        String s = BusController.getInstance().DrivingInstructions(1);

        System.out.println("Route 2 pressed");
        routeText.setText(s);
    }

    @FXML
    public void route3Pressed(ActionEvent e)
    {
        String s = BusController.getInstance().DrivingInstructions(2);

        System.out.println("Route 1 pressed");
        routeText.setText(s);
    }


    @FXML
    public void changeValuesPressed(ActionEvent e)
    {
        //setCost(costText);
    }

    @FXML
    public void showRouteInfo(ActionEvent e)
    {
        routeInformation.setText(BusController.getInstance().PrintInfo());
    }

    @FXML
    public void changeBusInfo(ActionEvent e)
    {
        int val = 0;
        try
        {
            val = Integer.parseInt(busCap.getText());
        } catch(Exception ex) { return; }
        if(val <= 0)
            return;
        BusController.getInstance().ChangeBusCap(val);

        int val2 = 0;
        try
        {
            val2 = Integer.parseInt(busCost.getText());
        } catch(Exception ex) { return; }
        if(val2 <= 0)
            return;
        BusController.getInstance().ChangeBusCost(val2);
    }


}
