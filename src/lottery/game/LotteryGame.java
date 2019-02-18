package lottery.game;

import java.util.Random;

public class LotteryGame {

    private int random1;
    private int random2;
    private int random3;
    private int random4;
    private int random5;

    public LotteryGame() {
        this.random1 = new Random().nextInt(9) + 1;
        this.random2 = new Random().nextInt(9) + 1;
        this.random3 = new Random().nextInt(9) + 1;
        this.random4 = new Random().nextInt(9) + 1;
        this.random5 = new Random().nextInt(9) + 1;
    }

    @Override
    public String toString() {
        return "LotteryGame{" +
                "random1=" + random1 +
                ", random2=" + random2 +
                ", random3=" + random3 +
                ", random4=" + random4 +
                ", random5=" + random5 +
                '}';
    }
    public int getRandom1() {
        return random1;
    }

    public int getRandom2() {
        return random2;
    }

    public int getRandom3() {
        return random3;
    }

    public int getRandom4() {
        return random4;
    }

    public int getRandom5() {
        return random5;
    }

}
