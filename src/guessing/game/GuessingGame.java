package guessing.game;

import java.util.Random;

public class GuessingGame {

    private int attempt;
    private int randomNumber;

    public GuessingGame(int attempt) {
        this.attempt = attempt;
        this.randomNumber =  new Random().nextInt(50);
    }


    public boolean isGameWon(int numberGuessed) {
        if (numberGuessed>this.randomNumber){
            this.attempt--;
            System.out.println("The Number is Smaller than\t"+ numberGuessed +"\t"+ this.attempt + " Chances Left");
            return false;
        } else if (numberGuessed<this.randomNumber){
            this.attempt--;
            System.out.println("The Number is Bigger than\t"+ numberGuessed +"\t"+ this.attempt + " Chances Left");
            return false;
        }
        return true;
    }

    public boolean isGameLost(){
        return this.attempt == 0;
    }

    public int getAttempt() {
        return attempt;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

}
