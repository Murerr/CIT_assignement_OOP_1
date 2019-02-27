package guessing.game;

import javafx.collections.ObservableList;

import java.util.Random;



public class GuessingGame {

    private static int WIN_STREAK = 0;
    private int attempt;
    private int randomNumber;

    public GuessingGame(int attempt) {
        this.attempt = attempt;
        this.randomNumber = new Random().nextInt(50) + 1;
    }

    public boolean isGuessedNumberBigger(int numberGuessed) {
        return this.randomNumber > numberGuessed;
    }

    public boolean isGuessedNumberSmaller(int numberGuessed) {
        return this.randomNumber < numberGuessed;
    }

    public void decreaseAttempt() {
        this.attempt--;
    }

    public boolean isGameWon(int numberGuessed) {
        return numberGuessed == this.randomNumber;
    }

    public boolean isGameLost() {
        return this.attempt == 0;
    }


    public String displayGameWon() {
        return "Game Won Number was : " + this.randomNumber + " found with \t" + this.attempt + " chances left\n";
    }

    public String displayGameLost() {
        return "Game Lost the correct Number was :\t" + this.randomNumber + "\n";
    }

    public String displayNumberIsBigger(int numberGuessed) {
        return ("The number is bigger than:\t" + numberGuessed + "\t" +this.attempt + " attempts left\n");
    }

    public String displayNumberIsSmaller(int numberGuessed) {
        return "The number is smaller than:\t" + numberGuessed + "\t" +this.attempt + " attempts left\n";
    }

    public String displayStartGame() {
        return "[CHEAT] the Number is " + this.randomNumber + "\n" +
                "Please Guess a Number Between 1 & 50 \t" + this.attempt + "Chances Left\n";
    }

    private void setWinStreakToZero() {
        WIN_STREAK = 0;
    }

    private void increaseWinStreak() {
        WIN_STREAK++;
    }

    private void addPrize(ObservableList<Integer> starPrizeWon) {
        if (WIN_STREAK == 5) {
            setWinStreakToZero();
            addStarPrize(starPrizeWon, 3);
        } else {
            addStarPrize(starPrizeWon, 2);
        }
    }

    private void addStarPrize(ObservableList<Integer> starPrizeWon, int i) {
        starPrizeWon.add(i);
    }

    public String displayAllPrizes(ObservableList<Integer> prizeArrayList) {
        String allPrizeToString="";
        for (Integer integer : prizeArrayList) {
            System.out.println(integer);
            allPrizeToString += integer + "* Prize \n";
        }
        return allPrizeToString;
    }

    public String startGuessingGame(int userNumberGuessedInput,ObservableList<Integer> starPrizeWon) {

        decreaseAttempt();

        if (isGuessedNumberBigger(userNumberGuessedInput) && !isGameLost()) {
            return displayNumberIsBigger(userNumberGuessedInput);
        }

        else if (isGuessedNumberSmaller(userNumberGuessedInput) && !isGameLost()) {
            return displayNumberIsSmaller(userNumberGuessedInput);
        }

        if (isGameWon(userNumberGuessedInput)) {
            increaseWinStreak();
            addPrize(starPrizeWon);
            return displayGameWon() + displayAllPrizes(starPrizeWon);
        }

        else if (isGameLost()){
            setWinStreakToZero();
            return displayGameLost() + displayAllPrizes(starPrizeWon);
        }

        return "";
    }

}
