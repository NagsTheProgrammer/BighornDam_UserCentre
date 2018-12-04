import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GuiController
{
    @FXML
    private TextArea routeText;

    @FXML
    public void route1Pressed(ActionEvent e)
    {
        String s = BusController.getInstance().DrivingInstructions(0);

        System.out.println("Route 1 pressed");
        routeText.setText(s);
    }
}