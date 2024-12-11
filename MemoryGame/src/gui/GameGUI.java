package gui;
import controller.GameController;
import model.GameBoard;


public class GameGUI implements GUIInterface {
    private GameController controller;

    public GameGUI(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void updateBoard(GameBoard board) {
        // Ενημέρωση του ταμπλό
        System.out.println("Board updated.");
    }

    @Override
    public void showMessage(String message) {
        // Εμφάνιση μηνύματος
        System.out.println("Message: " + message);
    }

    @Override
    public void saveGameRecord(String playerName, int score) {
        // Αποθήκευση του σκορ
        System.out.println("Game record saved for " + playerName + " with score " + score);
    }
}


