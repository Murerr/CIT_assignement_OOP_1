import guessing.game.GuessingGamePanel;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lottery.game.LotteryGamePanel;
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

            TabPane tp = new TabPane();
            tp.getTabs().add (new GuessingGamePanel());
            tp.getTabs().add (new LotteryGamePanel());
            tp.getTabs().add (new PrizeGamePanel());


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
}
