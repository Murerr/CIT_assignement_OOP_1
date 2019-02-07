
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main extends Application {
    private static int MAX_ATTEMPTS;
    private static int RANDOM_NUMBER;
    private static int WIN_STREAK;
    private static ArrayList<Prize> prizeArrayList = new ArrayList<>();
    public enum Prize {
        TWO_STAR_PRIZE,
        THREE_STAR_PRIZE
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        createANewGame();
        Scanner scanner = new Scanner(System.in);

        while (isAttemptAvailable()){
            int userNumberGuessedInput = scanner.nextInt();
            if(isguessedNumberCorrect(userNumberGuessedInput)){
                System.out.println("You WON the number was\t" + RANDOM_NUMBER);
                WIN_STREAK++;
                prizeArrayList.add(getPrize());
                System.out.println("You got a "+ getPrize().toString());
                displayAllPrizes(prizeArrayList);
                createANewGame();
            }
            if (!isAttemptAvailable()){
                System.out.println("You LOOSE the number was\t" + RANDOM_NUMBER+"\t");
                WIN_STREAK = 0;
                createANewGame();
            }
        }

        /*primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();*/
    }

    private void displayAllPrizes(ArrayList<Prize> prizeArrayList) {
        System.out.print("Here is all Your Prizes :");
        for (Prize prize : prizeArrayList) {
            System.out.println(prize.name());
        }
    }

    private Prize getPrize() {
        if (WIN_STREAK==5){
            return Prize.THREE_STAR_PRIZE;
        }
        return Prize.TWO_STAR_PRIZE;
    }

    private boolean isguessedNumberCorrect(int numberGuessed) {
        if (numberGuessed>RANDOM_NUMBER){
            MAX_ATTEMPTS--;
            System.out.println("The Number is Smaller than\t"+ numberGuessed +"\t"+ MAX_ATTEMPTS + " Chances Left");
            return false;
        } else if (numberGuessed<RANDOM_NUMBER){
            MAX_ATTEMPTS--;
            System.out.println("The Number is Bigger than\t"+ numberGuessed +"\t"+ MAX_ATTEMPTS + " Chances Left");
            return false;
        }
        return true;
    }

    private boolean isAttemptAvailable(){
        return MAX_ATTEMPTS!=0;
    }

    private void createANewGame(){
        MAX_ATTEMPTS = 4;
        RANDOM_NUMBER = new Random().nextInt(50);
        System.out.println("Print Cheat the Number is " + RANDOM_NUMBER);
        System.out.println("Please Guess a Number Between 1 & 50\t" + MAX_ATTEMPTS + " Chances Left");
    }



}