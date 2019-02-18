package guessing.game;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class GuessingGamePanel extends javafx.scene.control.Tab {
    private static int WIN_STREAK = 0;

    private final int MAX_ATTEMPTS = 4;

    private static ArrayList<Prize> prizeArrayList = new ArrayList<>();

    private enum Prize {
        TWO_STAR_PRIZE,
        THREE_STAR_PRIZE
    }

    private Label logLabel;
    private TextField inputText;
    private Button guessButton;
    private Button startButton;

    public GuessingGamePanel() {

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
                startGuessingGame(guessingGame);
                inputText.setText("");
            });
        });
        clearButton.setOnAction(event1 -> {
            logLabel.setText("");
        });
    }

    private void startGuessingGame(GuessingGame guessingGame) {
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
            prizeArrayList.add(getPrize());
            displayAllPrizes(prizeArrayList);
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

    private Prize getPrize() {
        if (WIN_STREAK == 5) {
            return Prize.THREE_STAR_PRIZE;
        }
        return Prize.TWO_STAR_PRIZE;
    }

    private void displayAllPrizes(ArrayList<Prize> prizeArrayList) {
        for (Prize prize : prizeArrayList) {
            System.out.println(prize.name());
            printLogsInWindow(prize.name()+"\n");
        }

    }

    private void printLogsInWindow(String str){
        String previousLogs = logLabel.getText();
        logLabel.setText((previousLogs+str));
    }

}
