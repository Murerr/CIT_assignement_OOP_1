package guessing.game;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

public class GuessingGamePanel extends javafx.scene.control.Tab {
    private static int WIN_STREAK = 0;

    private final int MAX_ATTEMPTS = 4;

    private static ArrayList<Prize> prizeArrayList = new ArrayList<>();

    private enum Prize {
        TWO_STAR_PRIZE,
        THREE_STAR_PRIZE
    }

    public GuessingGamePanel() {
        Scanner scanner = new Scanner(System.in);
        while (true) { // maybe while window on focus ?
            GuessingGame guessingGame = createANewGuessingGame();

            while (!guessingGame.isGameLost()) {
                int userNumberGuessedInput = scanner.nextInt();

                if (guessingGame.isGameWon(userNumberGuessedInput)) {
                    increaseWinStreak(guessingGame);
                    prizeArrayList.add(getPrize());
                    displayAllPrizes(prizeArrayList);
                    break;

                } else if (guessingGame.isGameLost()) {
                    setWinStreakToZero(guessingGame);
                    break;
                }
            }
        }

    }

    private GuessingGame createANewGuessingGame() {
        GuessingGame guessingGame = new GuessingGame(MAX_ATTEMPTS);
        System.out.println("[CHEAT] the Number is " + guessingGame.getRandomNumber());
        System.out.println("Please Guess a Number Between 1 & 50\t" + MAX_ATTEMPTS + " Chances Left");
        return guessingGame;
    }

    private void setWinStreakToZero(GuessingGame guessingGame) {
        System.out.println("You LOOSE the number was\t" + guessingGame.getRandomNumber() + "\t");
        WIN_STREAK = 0;
    }

    private void increaseWinStreak(GuessingGame guessingGame) {
        System.out.println("You WON the number was\t" + guessingGame.getRandomNumber());
        WIN_STREAK++;
    }

    private Prize getPrize() {
        if (WIN_STREAK == 5) {
            return Prize.THREE_STAR_PRIZE;
        }
        return Prize.TWO_STAR_PRIZE;
    }

    private void displayAllPrizes(ArrayList<Prize> prizeArrayList) {
        System.out.println("Here is all Your Prizes :");
        for (Prize prize : prizeArrayList) {
            System.out.println(prize.name());
        }
    }


}
