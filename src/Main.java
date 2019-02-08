
import guessing.game.GuessingGamePanel;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {

            StackPane root = new StackPane();
            TabPane tabPane = new TabPane();

            Tab guessingGameTab = new Tab("Guessing Game Panel");
            Tab lotteryGameTab = new Tab("Lottery Game Panel");

            tabPane.getTabs().add(guessingGameTab);
            tabPane.getTabs().add(lotteryGameTab);

            root.getChildren().add(tabPane);
            primaryStage.setScene(new Scene(root, 1200, 800));
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }



    }




}