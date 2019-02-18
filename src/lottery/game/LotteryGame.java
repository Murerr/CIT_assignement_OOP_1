package lottery.game;

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





}
