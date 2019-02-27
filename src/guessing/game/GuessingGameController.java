package guessing.game;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class GuessingGameController extends javafx.scene.control.Tab {
    private static int WIN_STREAK = 0;

    private final int MAX_ATTEMPTS = 4;


    private Label logLabel;
    private TextField inputText;
    private Button guessButton;
    private Button startButton;

    public GuessingGameController(ObservableList<Integer> starPrizeWon) {

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
        clearLogs();
        GuessingGame guessingGame = new GuessingGame(MAX_ATTEMPTS);
        printLogsInWindow(guessingGame.displayStartGame());
            guessButton.setOnAction(event1 -> {
                int userNumberGuessedInput = getUserNumberGuessedInput();
                String gameLogs = guessingGame.startGuessingGame(userNumberGuessedInput,starPrizeWon);
                printLogsInWindow(gameLogs);
            });
        });
        clearButton.setOnAction(event1 -> {
            clearLogs();
        });
    }

    private int getUserNumberGuessedInput() {
        try {
            return Integer.parseInt(inputText.getText());
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.toString(), ButtonType.OK);
            alert.showAndWait();
        }
        return 0;
    }

    private void clearLogs() {
        logLabel.setText("");
    }

    private void printLogsInWindow(String str){
        String previousLogs = logLabel.getText();
        logLabel.setText((previousLogs+str));
    }

}
