import guessing.game.GuessingGameController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lottery.game.LotteryGameController;
import prize.game.PrizeGamePanel;


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

            ObservableList<Integer> starPrizeWon = FXCollections.observableArrayList();

            TabPane tp = new TabPane();
            tp.getTabs().add (new GuessingGameController(starPrizeWon));
            tp.getTabs().add (new LotteryGameController(starPrizeWon));

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
