package lottery.game;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class LotteryGamePanel extends Tab
{
    //-----------------------------------------------------------------
    //  Sets up this panel with two labels.
    //-----------------------------------------------------------------
    Button guessButton;
    Button startButton;
    Label logLabel;
    TextField random1Text;
    TextField random2Text;
    TextField random3Text;
    TextField random4Text;
    TextField random5Text;

    public LotteryGamePanel()
    {
        this.setText("Lottery Cure");

        GridPane gb = new GridPane();
        Label l1 = new Label("Press Choose 5 Digits to win a prize ! ");
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
            LotteryGame lotteryGame = new LotteryGame();
            printLogsInWindow(displayStartGame(lotteryGame));
            guessButton.setOnAction(event1 -> {
                int []inputUserNumbersArray = getUserRandomNumbers(random1Text.getText());
                guessLotteryGame(lotteryGame,inputUserNumbersArray);
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
    private void guessLotteryGame(LotteryGame lotteryGame,int []userInputNumber ) {
        int counter = 0;
        int []lotteryGameRandomNumbersArray = lotteryGame.getRandomNumbersArray();

        for(int i = 0; i <lotteryGame.getLotteryMaxNumbers(); i++) {
            if (userInputNumber[i] == lotteryGameRandomNumbersArray[i] ){
                counter++;
            }
        }

        if(isGameWonWith4Numbers(counter)){
            printLogsInWindow(displayGameWonWith4Numbers(lotteryGame));
        } else if(isGameWonWith5Numbers(counter)){
            printLogsInWindow(displayGameWonWith5Numbers(lotteryGame));
        }
        else {printLogsInWindow(displayGameLost(lotteryGame));}
    }

    private boolean isGameWonWith4Numbers(int counter) {
        return counter == 4;
    }
    private boolean isGameWonWith5Numbers(int counter) {
        return counter == 5;
    }
    private String displayGameWonWith5Numbers(LotteryGame lotteryGame) {
        return "Game Won the correct answer was" +lotteryGame.toString()+"\n You won a 5 Stars";
    }

    private String displayGameWonWith4Numbers(LotteryGame lotteryGame) {
        return "Game Won the correct answer was" +lotteryGame.toString()+"\n You won a 4 Stars";
    }

    private String displayGameLost(LotteryGame lotteryGame) {
        return  "Game Lost the correct answer was " + lotteryGame.toString() + "\nTry Again ?";
    }

    private String displayStartGame(LotteryGame lotteryGame){
        return  "[CHEAT]" + lotteryGame.toString()+"\n";
    }

    private int[] getUserRandomNumbers(String input){
        int[] intArray = new int[5];
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            intArray[i] = Character.getNumericValue(c);
        }
        return  intArray;
    }


}
