import guessing.game.GuessingGamePanel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lottery.game.LotteryGamePanel;
import prize.game.PrizeGamePanel;

import java.util.ArrayList;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane mainPane = new BorderPane();
            Group root = new Group();
            Scene scene = new Scene(root,1200,800);

            TabPane tp = new TabPane();
            ObservableList<Integer> starPrizeWon = FXCollections.observableArrayList();
            tp.getTabs().add (new GuessingGamePanel(starPrizeWon));
            tp.getTabs().add (new LotteryGamePanel(starPrizeWon));
            PrizeGamePanel prizeGamePanelTab = new PrizeGamePanel(starPrizeWon);
            prizeGamePanelTab.setDisable(true);
            tp.getTabs().add (prizeGamePanelTab);
            starPrizeWon.addListener((ListChangeListener<Integer>) c -> enablePrizeTable(prizeGamePanelTab));

            mainPane.setCenter(tp);
            mainPane.prefHeightProperty().bind(scene.heightProperty());
            mainPane.prefWidthProperty().bind(scene.widthProperty());

            root.getChildren().add(mainPane);
            primaryStage.setTitle("Assignement Rudy MURER R00171701");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void enablePrizeTable(PrizeGamePanel prizeGamePanelTab) {
        prizeGamePanelTab.setDisable(false);
    }
}
