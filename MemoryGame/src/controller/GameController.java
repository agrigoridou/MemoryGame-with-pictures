package controller;

import model.*;

// Η κλάση GameController διαχειρίζεται τη λογική του παιχνιδιού
public class GameController implements GameInterface {
    private GameBoard gameBoard; // Το ταμπλό του παιχνιδιού
    private final Player player; // Ο παίκτης
    private final String theme; // Το θέμα του παιχνιδιού
    private int score; // Το σκορ του παίκτη
    private int matchedPairs; // Ο αριθμός των ζευγαριών που έχουν βρεθεί

    // Κατασκευαστής της κλάσης GameController
    public GameController(String theme, int rows, int cols, Player player) {
        this.theme = theme; // Αποθηκεύουμε το θέμα που επιλέχθηκε
        this.player = player; // Αποθηκεύουμε τον παίκτη
        this.gameBoard = new GameBoard(rows, cols, theme); // Δημιουργούμε το ταμπλό με τις παραμέτρους που δόθηκαν
        this.score = 0; // Μηδενικό αρχικό σκορ
        this.matchedPairs = 0; // Κανένα αρχικό ταίριασμα ζευγαριών
    }

    // Ξεκινάει το παιχνίδι και εμφανίζει μήνυμα εκκίνησης
    @Override
    public void startGame() {
        System.out.println("Game started with theme: " + theme);
    }

    // Διαχειρίζεται την επιλογή κάρτας από τον χρήστη
    @Override
    public void cardSelected(Card card) {
        System.out.println("Card selected: " + card); // Εμφάνιση της κάρτας που επιλέχθηκε για debugging

        if (!card.isFlipped()) { // Ελέγχει αν η κάρτα δεν είναι ήδη ανοιχτή
            card.flip(); // Αναποδογυρίζει την κάρτα για να φανεί η εικόνα της

            if (card instanceof JokerCard jokerCard) { // Ελέγχει αν η κάρτα είναι τύπου Joker
                for (Card c : gameBoard.getCards()) { // Επανάληψη για όλες τις κάρτες στο ταμπλό
                    if (c instanceof ImageCard imageCard && imageCard.getImagePath().equals(jokerCard.getImagePath())) {
                        c.flip(); // Αναποδογύρισε όλες τις κάρτες με την ίδια εικόνα όπως το Joker
                    }
                }
                matchedPairs += 2; // Προσθέτει δύο στα ζευγάρια καθώς όλα ταιριάζουν
                score += 20; // Αυξάνει το σκορ λόγω Joker
            } else {
                Card firstFlipped = gameBoard.getFirstFlippedCard(); // Εύρεση της πρώτης ανοιχτής κάρτας (αν υπάρχει)
                if (firstFlipped != null && firstFlipped != card) { // Αν υπάρχει άλλη κάρτα ανοιχτή και δεν είναι η ίδια
                    if (firstFlipped.isMatch(card)) { // Έλεγχος αν ταιριάζουν οι δύο κάρτες
                        matchedPairs++; // Αυξάνει τον αριθμό των ταιριασμένων ζευγαριών
                        score += 10; // Αυξάνει το σκορ για επιτυχημένο ταίριασμα
                    } else { // Αν οι κάρτες δεν ταιριάζουν
                        player.incrementFailed(); // Αυξάνει τις αποτυχημένες προσπάθειες του παίκτη
                        firstFlipped.flip(); // Κλείνει την πρώτη κάρτα
                        card.flip(); // Κλείνει τη δεύτερη κάρτα
                    }
                }
            }
        } else {
            System.out.println("Card is already flipped."); // Ενημερώνει ότι η κάρτα είναι ήδη ανοιχτή
        }
    }

    // Ενημερώνει και εμφανίζει το σκορ και τις αποτυχημένες προσπάθειες του παίκτη
    @Override
    public void updateScore() {
        System.out.println("Score: " + score); // Εμφάνιση τρέχοντος σκορ
        System.out.println("Failed attempts: " + player.getFailedAttempts()); // Εμφάνιση αποτυχημένων προσπαθειών
    }

    // Επαναφέρει το παιχνίδι στην αρχική του κατάσταση
    @Override
    public void resetGame() {
        this.gameBoard = new GameBoard(gameBoard.getRows(), gameBoard.getCols(), theme); // Επαναδημιουργία του ταμπλό
        this.score = 0; // Μηδενισμός σκορ
        this.matchedPairs = 0; // Μηδενισμός ταιριασμένων ζευγαριών
        player.setFailedAttempts(0); // Μηδενισμός αποτυχημένων προσπαθειών
        System.out.println("Game reset with theme: " + theme); // Μήνυμα επαναφοράς
    }

    // Επιστρέφει το τρέχον ταμπλό του παιχνιδιού
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    // Ελέγχει αν ο παίκτης έχει κερδίσει το παιχνίδι
    public boolean isGameWon() {
        return matchedPairs == (gameBoard.getRows() * gameBoard.getCols()) / 2; // Έλεγχος αν έχουν βρεθεί όλα τα ζευγάρια
    }

    // Επιστρέφει το τρέχον σκορ
    public int getScore() {
        return score;
    }

    // Επιστρέφει τον παίκτη
    public Player getPlayer() {
        return player;
    }
}