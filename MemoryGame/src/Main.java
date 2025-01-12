import javax.swing.JOptionPane;
import controller.GameController;
import gui.GameGUI;
import model.Player;

public class Main {
    public static void main(String[] args) {
        // Εμφανίζουμε ένα παράθυρο διαλόγου για την εισαγωγή του ονόματος του παίκτη
        String playerName = JOptionPane.showInputDialog("Enter your name:");

        // Αν δεν εισαχθεί όνομα (ή αν είναι κενό ή ακυρωθεί το παράθυρο), αναθέτουμε το όνομα "Player"
        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Player";  // Ορίζουμε το default όνομα για τον παίκτη
        }

        // Δημιουργούμε έναν πίνακα με τα διαθέσιμα θέματα του παιχνιδιού
        String[] themes = {"Animals", "Numbers", "Letters"};

        // Εμφανίζουμε ένα παράθυρο διαλόγου για την επιλογή του θέματος από τον παίκτη
        String theme = (String) JOptionPane.showInputDialog(null, "Select a theme:", "Theme Selection",
                JOptionPane.QUESTION_MESSAGE, null, themes, themes[0]);

        // Δημιουργούμε έναν νέο παίκτη με το όνομα που εισήχθη
        Player player = new Player(playerName);

        // Δημιουργούμε τον GameController με το επιλεγμένο θέμα και διαστάσεις πίνακα 4x4
        // Παράλληλα, δίνουμε τον παίκτη που δημιουργήθηκε παραπάνω
        GameController controller = new GameController(theme, 4, 4, player);

        // Δημιουργούμε το γραφικό περιβάλλον παιχνιδιού (GUI) δίνοντας τον controller
        new GameGUI(controller);

        // Ξεκινάμε το παιχνίδι καλώντας την startGame() μέθοδο του controller
        controller.startGame();
    }
}