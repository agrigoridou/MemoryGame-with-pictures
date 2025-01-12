package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameBoard {
    private final int rows; // Αριθμός γραμμών στον πίνακα
    private final int cols; // Αριθμός στηλών στον πίνακα
    private final List<Card> cards; // Λίστα που περιέχει τις κάρτες του παιχνιδιού

    // Κατασκευαστής για την κλάση GameBoard
    public GameBoard(int rows, int cols, String theme) {
        this.rows = rows; // Αρχικοποίηση αριθμού γραμμών
        this.cols = cols; // Αρχικοποίηση αριθμού στηλών
        this.cards = new ArrayList<>(); // Δημιουργία νέας λίστας καρτών
        initializeBoard(theme); // Κλήση της μεθόδου για την αρχικοποίηση του πίνακα με το θέμα
    }

    // Μέθοδος για την αρχικοποίηση του πίνακα με κάρτες, βάσει του θέματος
    private void initializeBoard(String theme) {
        System.out.println("Initializing board with theme: " + theme); // Εκτύπωση μηνύματος για την αρχικοποίηση

        // Φορτώνουμε τις εικόνες του θέματος
        List<String> imagePaths = loadThemeImages(theme);

        // Ελέγχουμε αν υπάρχουν αρκετές εικόνες για να γεμίσουν όλες τις θέσεις του πίνακα
        if (imagePaths.size() < (rows * cols) / 2 - 1) {
            // Αν δεν υπάρχουν αρκετές εικόνες, πετάμε εξαίρεση
            throw new IllegalArgumentException("Not enough images for the selected theme: " + theme);
        }

        // Προσθέτουμε τις κάρτες στο παιχνίδι
        // Προσθέτουμε κάθε εικόνα δύο φορές για να υπάρχουν ζεύγη
        for (int i = 0; i < (rows * cols) / 2 - 1; i++) {
            String imagePath = imagePaths.get(i); // Παίρνουμε την διαδρομή της εικόνας
            cards.add(new ImageCard(i, imagePath)); // Δημιουργούμε την πρώτη κάρτα εικόνας
            cards.add(new ImageCard(i, imagePath)); // Δημιουργούμε την δεύτερη κάρτα εικόνας (ζεύγος)
        }

        // Προσθέτουμε δύο κάρτες Joker στο παιχνίδι
        cards.add(new JokerCard(999, imagePaths.get(0))); // Κάρτα Joker με την πρώτη εικόνα
        cards.add(new JokerCard(1000, imagePaths.get(1))); // Κάρτα Joker με την δεύτερη εικόνα

        // Ανακατεύουμε τις κάρτες για να τυχαία κατανεμηθούν στον πίνακα
        Collections.shuffle(cards);
    }

    // Μέθοδος για την φόρτωση των εικόνων που αντιστοιχούν στο θέμα
    private List<String> loadThemeImages(String theme) {
        List<String> imagePaths = new ArrayList<>(); // Λίστα με τις διαδρομές εικόνας

        // Ανάλογα με το επιλεγμένο θέμα, επιστρέφουμε τις αντίστοιχες εικόνες
        switch (theme) {
            case "Animals": // Θέμα ζώων
                imagePaths.add("animal_fish.jpg");
                imagePaths.add("animal_owl.jpg");
                imagePaths.add("animal_cat.jpg");
                imagePaths.add("animal_dog.jpg");
                imagePaths.add("animal_lion.jpg");
                imagePaths.add("animal_elephant.jpg");
                imagePaths.add("animal_tiger.jpg");
                imagePaths.add("animal_bear.jpg");
                break;
            case "Numbers": // Θέμα αριθμών
                imagePaths.add("number_1.jpg");
                imagePaths.add("number_2.jpg");
                imagePaths.add("number_3.jpg");
                imagePaths.add("number_4.jpg");
                imagePaths.add("number_5.jpg");
                imagePaths.add("number_6.jpg");
                imagePaths.add("number_7.jpg");
                imagePaths.add("number_8.jpg");
                break;
            case "Letters": // Θέμα γραμμάτων
                imagePaths.add("letter_A.jpg");
                imagePaths.add("letter_B.jpg");
                imagePaths.add("letter_C.jpg");
                imagePaths.add("letter_D.jpg");
                imagePaths.add("letter_E.jpg");
                imagePaths.add("letter_F.jpg");
                imagePaths.add("letter_G.jpg");
                imagePaths.add("letter_H.jpg");
                break;
            default:
                // Εάν το θέμα είναι άγνωστο, πετάμε εξαίρεση
                throw new IllegalArgumentException("Invalid theme selected.");
        }
        return imagePaths; // Επιστρέφουμε τη λίστα με τις εικόνες του θέματος
    }

    // Επιστρέφει τη λίστα με όλες τις κάρτες του παιχνιδιού
    public List<Card> getCards() {
        return cards;
    }

    // Επιστρέφει τον αριθμό των γραμμών του πίνακα
    public int getRows() {
        return rows;
    }

    // Επιστρέφει τον αριθμό των στηλών του πίνακα
    public int getCols() {
        return cols;
    }

    // Επιστρέφει την πρώτη κάρτα που έχει αναστραφεί (ανοιχτεί)
    public Card getFirstFlippedCard() {
        // Διατρέχουμε τις κάρτες και αν βρούμε κάποια που είναι ανοιχτή (flipped), την επιστρέφουμε
        for (Card card : cards) {
            if (card.isFlipped()) {
                return card;
            }
        }
        return null; // Επιστρέφουμε null αν δεν υπάρχει καμία ανοιχτή κάρτα
    }
}
