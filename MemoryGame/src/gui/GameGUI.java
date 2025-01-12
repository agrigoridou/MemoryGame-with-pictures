package gui;

import controller.GameController;
import model.Card;
import model.GameBoard;
import model.ImageCard;
import model.JokerCard;

import javax.swing.*;
import java.awt.*;

public class GameGUI implements GUIInterface {
    private final GameController controller; // Ο controller διαχειρίζεται τη λογική του παιχνιδιού
    private JFrame frame; // Το κύριο παράθυρο του παιχνιδιού
    private JPanel boardPanel; // Το panel που περιέχει τα κουμπιά-κάρτες
    private JLabel scoreLabel; // Ετικέτα που εμφανίζει το σκορ του παίκτη
    private JLabel attemptsLabel; // Ετικέτα που εμφανίζει τις αποτυχημένες προσπάθειες
    private JLabel remainingCardsLabel; // Ετικέτα που εμφανίζει τον αριθμό των υπολειπόμενων καρτών

    // Κατασκευαστής της κλάσης GameGUI
    public GameGUI(GameController controller) {
        this.controller = controller; // Αποθήκευση του controller που διαχειρίζεται το παιχνίδι
        initializeGUI(); // Κλήση της μεθόδου για αρχικοποίηση του γραφικού περιβάλλοντος
    }

    // Αρχικοποίηση του γραφικού περιβάλλοντος
    private void initializeGUI() {
        // Δημιουργία του παραθύρου της εφαρμογής
        frame = new JFrame("Memory Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Το παράθυρο κλείνει όταν ο χρήστης επιλέξει "Close"
        frame.setSize(800, 600); // Καθορισμός των διαστάσεων του παραθύρου

        // Δημιουργία μενού επιλογών
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options"); // Δημιουργία μενού "Options"
        JMenuItem themeItem = new JMenuItem("Select Theme"); // Δημιουργία επιλογής για αλλαγή θέματος
        themeItem.addActionListener(e -> selectTheme()); // Σύνδεση του κουμπιού με τη μέθοδο επιλογής θέματος
        menu.add(themeItem); // Προσθήκη επιλογής στο μενού
        menuBar.add(menu); // Προσθήκη του μενού στο μενού της εφαρμογής
        frame.setJMenuBar(menuBar); // Σύνδεση του μενού με το παράθυρο

        // Δημιουργία panel για εμφάνιση του σκορ, αποτυχιών, και υπολειπόμενων καρτών
        JPanel statusPanel = new JPanel(new GridLayout(1, 3)); // Χρήση GridLayout για τρεις στήλες
        scoreLabel = new JLabel("Score: 0"); // Αρχικό μήνυμα για το σκορ
        attemptsLabel = new JLabel("Failed Attempts: 0"); // Αρχικό μήνυμα για τις αποτυχημένες προσπάθειες
        remainingCardsLabel = new JLabel("Remaining Cards: " + controller.getGameBoard().getCards().size()); // Υπολογισμός καρτών
        statusPanel.add(scoreLabel); // Προσθήκη του label σκορ στο panel
        statusPanel.add(attemptsLabel); // Προσθήκη του label αποτυχιών στο panel
        statusPanel.add(remainingCardsLabel); // Προσθήκη του label υπολειπόμενων καρτών
        frame.add(statusPanel, BorderLayout.NORTH); // Τοποθέτηση του panel στο πάνω μέρος του παραθύρου

        // Δημιουργία panel για την απεικόνιση των καρτών
        boardPanel = new JPanel();
        // Χρήση GridLayout για να τοποθετηθούν οι κάρτες σε πλέγμα
        boardPanel.setLayout(new GridLayout(controller.getGameBoard().getRows(), controller.getGameBoard().getCols()));
        frame.add(boardPanel, BorderLayout.CENTER); // Τοποθέτηση του panel στο κέντρο του παραθύρου

        frame.setVisible(true); // Εμφάνιση του παραθύρου στον χρήστη
        updateBoard(controller.getGameBoard()); // Ενημέρωση του πίνακα με τις κάρτες
    }

    // Μέθοδος για επιλογή θέματος από τον χρήστη
    private void selectTheme() {
        String[] themes = {"Animals", "Numbers", "Letters"}; // Διαθέσιμα θέματα
        // Εμφάνιση παραθύρου επιλογής θέματος
        String selectedTheme = (String) JOptionPane.showInputDialog(frame, "Select a theme:", "Theme Selection",
                JOptionPane.QUESTION_MESSAGE, null, themes, themes[0]);

        if (selectedTheme != null) { // Αν ο χρήστης επιλέξει θέμα
            controller.resetGame(); // Επαναφορά του παιχνιδιού στο αρχικό στάδιο
            updateBoard(controller.getGameBoard()); // Ενημέρωση του πίνακα καρτών
        }
    }

    // Ενημέρωση του πίνακα καρτών με βάση την κατάσταση του παιχνιδιού
    @Override
    public void updateBoard(GameBoard board) {
        boardPanel.removeAll(); // Καθαρισμός όλων των στοιχείων του panel για να ανανεωθεί

        for (Card card : board.getCards()) { // Για κάθε κάρτα στον πίνακα
            JButton cardButton = new JButton(); // Δημιουργία κουμπιού που αντιπροσωπεύει την κάρτα
            cardButton.setPreferredSize(new Dimension(50, 80)); // Ορισμός διαστάσεων κουμπιού

            if (card.isFlipped()) {
                if (card instanceof ImageCard imageCard) {
                    String imagePath = imageCard.getImagePath();

                    java.net.URL imageUrl = getClass().getClassLoader().getResource(imagePath);
                    if (imageUrl != null) {
                        ImageIcon icon = new ImageIcon(imageUrl);
                        if (icon.getIconWidth() == -1 || icon.getIconHeight() == -1) {
                            System.out.println("Error loading image at path: " + imagePath);
                        } else {
                            System.out.println("Image loaded successfully from path: " + imagePath);
                            Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                            cardButton.setIcon(new ImageIcon(scaledImage));
                        }
                    } else {
                        System.out.println("Image not found: " + imagePath);
                        cardButton.setText("Image not found");
                        cardButton.setBackground(Color.GRAY);
                    }

                } else if (card instanceof JokerCard jokerCard) {
                    cardButton.setText("JOKER");
                    cardButton.setBackground(Color.GRAY);
                    String imagePath = jokerCard.getImagePath();

                    // Βρες τις άλλες κάρτες με την ίδια εικόνα και αποκάλεσέ τες
                    for (Card c : board.getCards()) {
                        if (c instanceof ImageCard imageCard && imageCard.getImagePath().equals(imagePath)) {
                            imageCard.setFlipped(true); // Αποκάλυψη της αντίστοιχης κάρτας
                        }
                    }

                    // Φόρτωσε την εικόνα για το μπαλαντέρ
                    java.net.URL imageUrl = getClass().getClassLoader().getResource(imagePath);
                    if (imageUrl != null) {
                        ImageIcon icon = new ImageIcon(imageUrl);
                        if (icon.getIconWidth() == -1 || icon.getIconHeight() == -1) {
                            System.out.println("Error loading image at path: " + imagePath);
                        } else {
                            Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                            cardButton.setIcon(new ImageIcon(scaledImage));
                        }
                    } else {
                        cardButton.setText("Image not found");
                        cardButton.setBackground(Color.GRAY);
                    }
                }
            } else { // Αν η κάρτα είναι κλειστή
                cardButton.setText("");
                cardButton.setBackground(Color.YELLOW);
                cardButton.setIcon(null);
            }

            // Επεξεργασία ποντικιού για αλλαγή χρώματος όταν ο χρήστης τοποθετεί το δείκτη πάνω στο κουμπί
            cardButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    cardButton.setBackground(Color.LIGHT_GRAY); // Ελαφρύ γκρι κατά την είσοδο
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    cardButton.setBackground(card.isFlipped() ? Color.WHITE : Color.YELLOW); // Επαναφορά χρώματος
                }
            });

            // Ενέργεια όταν ο χρήστης κάνει κλικ σε μια κάρτα
            cardButton.addActionListener(e -> {
                controller.cardSelected(card); // Ενημέρωση controller για την επιλογή της κάρτας
                updateBoard(board); // Ενημέρωση του πίνακα με βάση τη νέα κατάσταση
                scoreLabel.setText("Score: " + controller.getScore()); // Ενημέρωση του σκορ
                attemptsLabel.setText("Failed Attempts: " + controller.getPlayer().getFailedAttempts()); // Ενημέρωση των αποτυχιών
                // Υπολογισμός των υπολειπόμενων καρτών
                remainingCardsLabel.setText("Remaining Cards: " +
                        (board.getCards().size() - controller.getGameBoard().getCards().stream().filter(Card::isFlipped).count()));

                if (controller.isGameWon()) { // Αν ο παίκτης νίκησε
                    showMessage("Congratulations, you won!"); // Εμφάνιση μηνύματος νίκης
                } else if (controller.getPlayer().getFailedAttempts() >= controller.getGameBoard().getRows() * controller.getGameBoard().getCols() / 4) { // Αν ο παίκτης έχασε
                    showMessage("Game Over, you lost!"); // Εμφάνιση μηνύματος ήττας
                }
            });

            boardPanel.add(cardButton); // Προσθήκη του κουμπιού στο panel
        }
        // Ενημέρωση του layout και της εμφάνισης του panel
        boardPanel.revalidate(); // Ενημέρωση του layout του panel
        boardPanel.repaint(); // Ενημέρωση της εμφάνισης του panel
    }

    // Εμφάνιση μηνύματος στον χρήστη
    @Override
    public void showMessage(String message) {
        // Εμφάνιση παραθύρου μηνύματος με το κείμενο που δίνεται
        JOptionPane.showMessageDialog(frame, message);
    }

    // Αποθήκευση εγγραφής παιχνιδιού
    @Override
    public void saveGameRecord(String playerName, int score) {
        // Προσωρινή αποθήκευση του σκορ (εδώ εμφανίζεται στην κονσόλα για απλότητα)
        System.out.println("Game record saved for " + playerName + " with score " + score);
    }
}
