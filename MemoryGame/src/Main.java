import javax.swing.JOptionPane;
import controller.GameController;
import gui.GameGUI;
import model.Player;

public class Main {
    public static void main(String[] args) {
        // Get the player's name from a dialog box
        String playerName = JOptionPane.showInputDialog("Enter your name:");
        Player player = new Player(playerName);

        // Display a dialog to select the theme
        String[] themes = {"Animals", "Numbers", "Letters"};
        String theme = (String) JOptionPane.showInputDialog(null, "Select a theme:", "Theme Selection",
                JOptionPane.QUESTION_MESSAGE, null, themes, themes[0]);

        // Initialize the game controller with the selected theme
        GameController controller = new GameController(theme, 4, 4, player);

        // Initialize the game GUI
        GameGUI gui = new GameGUI(controller);

        // Start the game
        controller.startGame();
    }
}
