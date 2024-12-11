import controller.GameController;
import gui.GameGUI;
import model.GameBoard;
import model.Player;
import record.GameRecord;

public class Main {
    public static void main(String[] args) {
        // Δημιουργία αντικειμένων
        GameBoard gameBoard = new GameBoard(4, 4);
        Player player = new Player("John");
        GameController controller = new GameController(gameBoard, player);
        GameGUI gui = new GameGUI(controller);
        GameRecord gameRecord = new GameRecord(player.getName(), 0);

        // Εκκίνηση του παιχνιδιού
        controller.startGame();
        gui.showMessage("Game started for player: " + player.getName());
        gameRecord.saveRecordToFile();
    }
}
