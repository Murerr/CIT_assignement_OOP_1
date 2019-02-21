package guessing.game;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class GuessingGamePanel extends javafx.scene.control.Tab {
    private static int WIN_STREAK = 0;

    private final int MAX_ATTEMPTS = 4;


    private Label logLabel;
    private TextField inputText;
    private Button guessButton;
    private Button startButton;

    public GuessingGamePanel(ObservableList<Integer> starPrizeWon) {

        this.setText("Guessing Game");
        GridPane gb = new GridPane();
        Label l1 = new Label("Press Start and choose a Number Between 1 & 50");
        inputText = new TextField ();
        guessButton = new Button ("Guess");
        guessButton.setDefaultButton(true);
        startButton = new Button ("Start A New Game");
        Button clearButton = new Button ("Clear");
        logLabel = new Label("Logs :\n");
        gb.add(l1,1,0);
        gb.add(inputText,1,1);
        gb.add(startButton,2,0);
        gb.add(guessButton,1,2);
        gb.add(clearButton,2,1);
        gb.add(logLabel,1,3);
        gb.setAlignment(Pos.TOP_LEFT);
        this.setContent(gb);

        startButton.setOnAction(event -> {
        GuessingGame guessingGame = new GuessingGame(MAX_ATTEMPTS);
        printLogsInWindow(guessingGame.displayStartGame());
            guessButton.setOnAction(event1 -> {
                startGuessingGame(guessingGame,starPrizeWon);
                inputText.setText("");
            });
        });
        clearButton.setOnAction(event1 -> {
            logLabel.setText("");
        });
    }

    private void startGuessingGame(GuessingGame guessingGame, ObservableList<Integer> starPrizeWon) {
        int userNumberGuessedInput = Integer.parseInt(inputText.getText());
        logLabel.setText("");

        if (guessingGame.isGuessedNumberBigger(userNumberGuessedInput)) {
            guessingGame.decreaseAttempt();
            printLogsInWindow(guessingGame.displayNumberIsBigger(userNumberGuessedInput));
        }

        if (guessingGame.isGuessedNumberSmaller(userNumberGuessedInput)) {
            guessingGame.decreaseAttempt();
            printLogsInWindow(guessingGame.displayNumberIsSmaller(userNumberGuessedInput));
        }

        if (guessingGame.isGameWon(userNumberGuessedInput)) {
            printLogsInWindow(guessingGame.displayGameWon());
            increaseWinStreak();
            addPrize(starPrizeWon);
            displayAllPrizes(starPrizeWon);
            startButton.fire();

        }

        if (guessingGame.isGameLost()) {
            printLogsInWindow(guessingGame.displayGameLost());
            setWinStreakToZero();
            startButton.fire();
        }
    }

    private void setWinStreakToZero() {
        WIN_STREAK = 0;
    }

    private void increaseWinStreak() {
        WIN_STREAK++;
    }

    private void addPrize(ObservableList<Integer> starPrizeWon) {
        if (WIN_STREAK == 5) {
            addStarPrize(starPrizeWon, 3);
        } else {
            addStarPrize(starPrizeWon, 2);
        }
    }

    private void addStarPrize(ObservableList<Integer> starPrizeWon, int i) {
        starPrizeWon.add(i);
    }

    private void displayAllPrizes(ObservableList<Integer> prizeArrayList) {
        for (Integer integer : prizeArrayList) {
            System.out.println(integer);
            printLogsInWindow(integer + "* Prize \n");
        }
    }

    private void printLogsInWindow(String str){
        String previousLogs = logLabel.getText();
        logLabel.setText((previousLogs+str));
    }

}
