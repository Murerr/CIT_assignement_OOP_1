package lottery.game;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class LotteryGameController extends Tab
{
    //-----------------------------------------------------------------
    //  Sets up this panel with two labels.
    //-----------------------------------------------------------------
    Button guessButton;
    Button startButton;
    Label logLabel;
    TextField random1Text;


    public LotteryGameController(ObservableList<Integer> starPrizeWon)
    {
        this.setText("Lottery Cure");

        GridPane gb = new GridPane();
        Label l1 = new Label("Choose 5 Digits to win a prize ! ");
        random1Text = new TextField();
        guessButton = new Button ("Guess");
        guessButton.setDefaultButton(true);
        startButton = new Button ("Start A New Game");
        Button clearButton = new Button ("Clear");
        logLabel = new Label("Logs :\n");

        gb.add(l1,1,0);
        gb.add(startButton,2,0);
        gb.add(random1Text,1,1);
        gb.add(clearButton,2,1);
        gb.add(guessButton,1,2);
        gb.add(logLabel,1,4);
        gb.setAlignment(Pos.TOP_LEFT);

        this.setContent(gb);

        startButton.setOnAction(event -> {
            clearLogs();
            LotteryGame lotteryGame = new LotteryGame();
            printLogsInWindow(lotteryGame.displayStartGame());
            guessButton.setOnAction(event1 -> {
                int []inputUserNumbersArray = getUserRandomNumbers(random1Text.getText());
                String gameLogs = lotteryGame.guessLotteryGame(inputUserNumbersArray,starPrizeWon);
                printLogsInWindow(gameLogs);
            });
        });

        clearButton.setOnAction(event1 -> {
            logLabel.setText("");
        });
    }

    private void printLogsInWindow(String str) {
        String previousLogs = logLabel.getText();
        logLabel.setText((previousLogs+str));
    }

    private int[] getUserRandomNumbers(String input){
        int[] intArray = new int[5];
        try {
            for(int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                intArray[i] = Character.getNumericValue(c); //char to int
            }
            return  intArray;
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.toString(), ButtonType.OK);
            alert.showAndWait();
        }
        return intArray;
    }

    private void clearLogs() {
        logLabel.setText("");
    }



}
