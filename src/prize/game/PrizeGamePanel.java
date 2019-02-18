package prize.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class PrizeGamePanel extends javafx.scene.control.Tab {

    private static ObservableList<Prize> prizeList;



    TextField allThePrizeText;
    Button pickButton;
    Label logLabel;
    ChoiceBox cb;
    public PrizeGamePanel()
    {
        prizeList = getPrizeList();
        this.setText("Prize Panel");

        GridPane gb = new GridPane();
        Label l1 = new Label("Choose a Prize");
        cb = new ChoiceBox<>(prizeList);
        Button pickButton = new Button();
        logLabel = new Label("Logs:\n");
        pickButton.setText("Confirm");
        //cb.setItems(FXCollections.observableArrayList(prizeList));
        gb.add(l1,1,0);
        gb.add(cb,1,1);
        gb.add(pickButton,1,2);
        gb.add(logLabel,1,3);

        pickButton.setOnAction(event -> {
            pickAPrize();
        });

        gb.setAlignment(Pos.TOP_LEFT);
        this.setContent(gb);
    }

    private void pickAPrize() {
        if(isPrizeAvailable()){
            Prize selectedPrize= (Prize)cb.getSelectionModel().getSelectedItem();
            printLogsInWindow(selectedPrize.toString());
        }
    }

    private boolean isPrizeAvailable() {
        return true;
    }


    private ObservableList<Prize> getPrizeList() {
        return FXCollections.observableArrayList(
            new Prize("1000",5,"Apple"),
            new Prize("100",4,"Banana"),
            new Prize("Trip to Donegal",5,"Grape"),
            new Prize("Trip to Carabean",5,"Pear"),
            new Prize("Trip to France",5,"Strawberry"),
            new Prize("Trip To London",5,"Kiwi"),
            new Prize("Paris Tour",5,"Plum"),
            new Prize("Climb Effel Tower",5,"Orange"),
            new Prize("Throw Tomatoes on The US President",5,"Tomatoes"),
            new Prize("London Tour",5,"Litchi")
        );
    }

    private void printLogsInWindow(String str) {
        String previousLogs = logLabel.getText();
        logLabel.setText((previousLogs+str));
    }

}
