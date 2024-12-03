import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameUI extends Application {
    private GameController gameController;
    private GridPane grid;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Memory Game");
        grid = new GridPane();

        Player player = new Player("Player1");
        gameController = new GameController(player, 4, 10); // 4x4 board, max 10 incorrect attempts

        initializeUI();

        Scene scene = new Scene(grid, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeUI() {
        int index = 0;
        for (Card card : gameController.getBoard().getCards()) {
            // Δημιουργία κουμπιών ή εικονιδίων για κάθε κάρτα
            // Αν η κάρτα είναι ανοιχτή ή κλειστή
            // Παράδειγμα: για κάθε κάρτα προσθέτουμε τη δυνατότητα να την αποκαλύψει ο παίκτης
        }
    }

    private void showGameOverMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(message);
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
