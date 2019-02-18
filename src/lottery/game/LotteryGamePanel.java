package lottery.game;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
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
        Label l1 = new Label("Press Start and choose a Number Between 1 & 50");
        random1Text = new TextField();
        random2Text = new TextField();
        random3Text = new TextField();
        random4Text = new TextField();
        random5Text = new TextField();
        guessButton = new Button ("Guess");
        guessButton.setDefaultButton(true);
        startButton = new Button ("Start A New Game");
        Button clearButton = new Button ("Clear");
        logLabel = new Label("Logs :\n");
        gb.add(l1,1,0);
        gb.add(startButton,2,0);
        gb.add(random1Text,1,1);
        gb.add(random2Text,2,1);
        gb.add(random3Text,3,1);
        gb.add(random4Text,4,1);
        gb.add(random5Text,5,1);
        gb.add(clearButton,3,2);
        gb.add(guessButton,1,2);

        gb.add(logLabel,1,4);
        gb.setAlignment(Pos.TOP_LEFT);
        this.setContent(gb);
        startButton.setOnAction(event -> {
            LotteryGame lotteryGame = new LotteryGame();
            printLogsInWindow(displayStartGame(lotteryGame));
            guessButton.setOnAction(event1 -> {
                int random1 = Integer.parseInt(random1Text.getText());
                int random2 = Integer.parseInt(random2Text.getText());
                int random3 = Integer.parseInt(random3Text.getText());
                int random4 = Integer.parseInt(random4Text.getText());
                int random5 = Integer.parseInt(random5Text.getText());
                guessLotteryGame(lotteryGame,random1,random2,random3,random4,random5);
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
    private void guessLotteryGame(LotteryGame lotteryGame,int random1,int random2,int random3, int random4, int random5) {
        int counter = 0;
        if(lotteryGame.getRandom1() == random1){counter++;};
        if(lotteryGame.getRandom2() == random2){counter++;};
        if(lotteryGame.getRandom3() == random3){counter++;};
        if(lotteryGame.getRandom4() == random4){counter++;};
        if(lotteryGame.getRandom5() == random5){counter++;};

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
        return "Game Won the correct answer was" +lotteryGame.getRandom1()+lotteryGame.getRandom2()+lotteryGame.getRandom3()+lotteryGame.getRandom4()+lotteryGame.getRandom5()+"\n You won a 4 Stars";
    }

    private String displayGameLost(LotteryGame lotteryGame) {
        return  "Game Lost the correct answer was " + lotteryGame.toString() + "\nTry Again ?";
    }

    private String displayStartGame(LotteryGame lotteryGame){
        return  "[CHEAT]" + lotteryGame.getRandom1()+lotteryGame.getRandom2()+lotteryGame.getRandom3()+lotteryGame.getRandom4()+lotteryGame.getRandom5();
    }


}
