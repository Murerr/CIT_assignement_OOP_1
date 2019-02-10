package guessing.game;

import java.util.Random;

public class GuessingGame {

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
        return "Game Won Number was :\t" + this.randomNumber + "found with \t" + this.attempt + "left\n";
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

}
