import javax.swing.JOptionPane;
import controller.GameController;
import gui.GameGUI;
import model.Player;

public class Main {
    public static void main(String[] args) {
        String playerName = JOptionPane.showInputDialog("Enter your name:");
        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Player";
        }

        String[] themes = {"Animals", "Numbers", "Letters"};
        String theme = (String) JOptionPane.showInputDialog(null, "Select a theme:", "Theme Selection",
                JOptionPane.QUESTION_MESSAGE, null, themes, themes[0]);

        GameController controller = new GameController(theme, 4, 4, new Player(playerName));

        new GameGUI(controller);

        controller.startGame();
    }
}
