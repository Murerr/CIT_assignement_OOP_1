import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

public class LotteryGamePanel extends Tab
{
    //-----------------------------------------------------------------
    //  Sets up this panel with two labels.
    //-----------------------------------------------------------------
    public LotteryGamePanel()
    {
        this.setText("Lottery Game Panel");

        VBox vb = new VBox();
        Button b1 = new Button ("BUTTON 1");
        Button b2 = new Button ("BUTTON 2");
        Button b3 = new Button ("BUTTON 3");
        Button b4 = new Button ("BUTTON 4");
        Button b5 = new Button ("BUTTON 5");

        vb.getChildren().add (b1);

        vb.getChildren().add (b2);

        vb.getChildren().add (b3);
        vb.getChildren().add (b4);

        vb.getChildren().add (b5);
        setContent(vb);
    }
}
