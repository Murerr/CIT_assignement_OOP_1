package prize.game;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class PrizeGamePanel extends javafx.scene.control.Tab {

    private static ObservableList<Prize> prizeList;
    private static  ArrayList<Prize> shoppingList = new ArrayList<Prize>();


    Label allTheStarPrizeText;
    Label shoppingListText;
    Button pickButton;
    Label logLabel;
    ChoiceBox cb;
    DialogPane dp;
    GridPane gb;
    public PrizeGamePanel(ObservableList<Integer> starPrizeWon)
    {
        this.setText("Prize Panel");
        prizeList = getPrizeList();

        gb = new GridPane();
        Label l1 = new Label("Choose a Prize");
        cb = new ChoiceBox<>(prizeList);
        Button pickButton = new Button();
        logLabel = new Label("Logs:\n");
        allTheStarPrizeText = new Label("Star Prize List : \n");
        shoppingListText = new Label("Shopping List : \n");
        pickButton.setText("Confirm");
        gb.add(l1,1,0);
        gb.add(cb,1,1);
        gb.add(allTheStarPrizeText,2,1);
        gb.add(shoppingListText,2,2);
        gb.add(pickButton,1,2);
        gb.add(logLabel,1,3);
        starPrizeWon.addListener((ListChangeListener<Integer>) c -> displayStarPrizes(starPrizeWon));

        pickButton.setOnAction(event -> {
            pickAPrize(starPrizeWon);
        });

        gb.setAlignment(Pos.TOP_LEFT);
        this.setContent(gb);
    }

    private void pickAPrize(ObservableList<Integer> starPrizeWon) {
        Prize selectedPrize= (Prize)cb.getSelectionModel().getSelectedItem();
        if(isPrizeAvailable(starPrizeWon,selectedPrize)){
            removePrizeFromPrizeList(starPrizeWon, selectedPrize);
            addPrizeToCart(selectedPrize);
            printLogsInWindow(itemHasBeenAddedToYourShoppingCart(selectedPrize));
        } else {
            printLogsInWindow(selectedPrize.toString()+"Is not available \n Do you have enough Star Prize Points ?");
            Alert alert = new Alert(Alert.AlertType.ERROR, selectedPrize.toString()+"Is not available \n Do you have enough Star Prize Points ?" , ButtonType.OK);
            alert.showAndWait();
        }
    }

    private String itemHasBeenAddedToYourShoppingCart(Prize selectedPrize) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "You won "+selectedPrize.toString() , ButtonType.OK);
        alert.showAndWait();
        return "The follow item :" + selectedPrize.toString() + "item has been added to your cart \n";
    }

    private void removePrizeFromPrizeList(ObservableList<Integer> starPrizeWon, Prize selectedPrize) {
        prizeList.remove(selectedPrize);
        starPrizeWon.remove(Integer.valueOf(selectedPrize.getWeight()));
    }

    private void addPrizeToCart(Prize selectedPrize) {
        shoppingList.add(selectedPrize);
        displayCartPrizes();
    }

    private void displayCartPrizes() {
        String previousListTest = shoppingListText.getText();
        shoppingListText.setText(previousListTest + shoppingList.toString()+"\n");
    }

    private void displayStarPrizes(ObservableList<Integer> starPrizeWon) {
        String previousListTest = allTheStarPrizeText.getText();
        allTheStarPrizeText.setText(previousListTest + starPrizeWon.toString()+"\n");
    }

    private boolean isPrizeAvailable(ObservableList<Integer> starPrizeWon, Prize selectedPrize) {
        return  starPrizeWon.contains(selectedPrize.getWeight());
    }


    private ObservableList<Prize> getPrizeList() {
        return FXCollections.observableArrayList(
            new Prize("1000",5,"Apple"),
            new Prize("100",4,"Banana"),
            new Prize("Trip to Donegal",5,"Grape"),
            new Prize("Trip to Carabean",5,"Pear"),
            new Prize("Trip to France",5,"Strawberry"),
            new Prize("Trip To London",5,"Kiwi"),
            new Prize("Paris Tour",3,"Plum"),
            new Prize("Climb Effel Tower",2,"Orange"),
            new Prize("Throw Tomatoes on The US President",1,"Tomatoes"),
            new Prize("London Tour",1,"Litchi")
        );
    }

    private void printLogsInWindow(String str) {
        String previousLogs = logLabel.getText();
        logLabel.setText((previousLogs+str));
    }

    /**
     * VBox dialogVbox = new VBox(20);
     *                 dialogVbox.getChildren().add(new Text("This is a Dialog"));
     */

}
