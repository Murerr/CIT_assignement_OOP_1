package lottery.game;

import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.Random;

public class LotteryGame {

    private static final int LOTTERY_MAX_NUMBERS = 5;
    private int[] intArray = new int[5];

    public LotteryGame() {
        for (int i=0; i<LOTTERY_MAX_NUMBERS;i++){
            this.intArray[i] = new Random().nextInt(9) + 1;
        }
    }

    public int[] getRandomNumbersArray() {
        return intArray;
    }

    @Override
    public String toString() {
        return "LotteryGame{" +
                "intArray=" + Arrays.toString(intArray) +
                '}';
    }
    public int getLotteryMaxNumbers() {
        return LOTTERY_MAX_NUMBERS;
    }

    public String guessLotteryGame(int[] userInputNumber, ObservableList<Integer> starPrizeWon) {
        int counter = 0;
        counter = checkNumbers(userInputNumber,this.getRandomNumbersArray());

        if(isGameWonWith4Numbers(counter)){
            addStarPrize(starPrizeWon,4);
            return displayGameWonWith4Numbers() + displayAllPrizes(starPrizeWon);

        } else if(isGameWonWith5Numbers(counter)){
            addStarPrize(starPrizeWon,5);
            return displayGameWonWith5Numbers() + displayAllPrizes(starPrizeWon);
        }
        else {
            return (displayGameLost());
        }
    }

    private int checkNumbers(int[] userInputNumber,int[] lotteryGameRandomNumbersArray) {
        int counter=0;
        for(int i = 0; i <this.getLotteryMaxNumbers(); i++) {
            if (userInputNumber[i] == lotteryGameRandomNumbersArray[i] ){
                counter++;
            }
        }
        return counter;
    }

    private boolean isGameWonWith4Numbers(int counter) {
        return counter == 4;
    }

    private boolean isGameWonWith5Numbers(int counter) {
        return counter == 5;
    }

    private String displayGameWonWith5Numbers() {
        return "Game Won the correct answer was" +this.toString()+"\n You won a 5 Stars prize \n";
    }

    private String displayGameWonWith4Numbers() {
        return "Game Won the correct answer was" +this.toString()+"\n You won a 4 Stars prize \n";
    }

    private String displayGameLost() {
        return  "Game Lost the correct answer was " + this.toString() + "\nTry Again ?\n";
    }

    public String displayStartGame(){
        return  "[CHEAT]" + this.toString()+"\n";
    }

    private int[] getUserRandomNumbers(String input){
        int[] intArray = new int[5];
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            intArray[i] = Character.getNumericValue(c); //char to int
        }
        return  intArray;
    }

    private void addStarPrize(ObservableList<Integer> starPrizeWon, int i) {
        starPrizeWon.add(i);
    }

    private String displayAllPrizes(ObservableList<Integer> prizeArrayList) {
        String allPrizeToString="";
        for (Integer integer : prizeArrayList) {
            System.out.println(integer);
            allPrizeToString += integer + "* Prize \n";
        }
        return allPrizeToString;
    }





}
