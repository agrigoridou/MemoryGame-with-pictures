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
                imagePaths.add("resources/animal_fish.png");
                imagePaths.add("resources/animal_owl.png");
                imagePaths.add("resources/animal_cat.png");
                imagePaths.add("resources/animal_dog.png");
                imagePaths.add("resources/animal_lion.png");
                imagePaths.add("resources/animal_elephant.png");
                imagePaths.add("resources/animal_tiger.png");
                imagePaths.add("resources/animal_bear.png");
                break;
            case "Numbers": // Θέμα αριθμών
                imagePaths.add("resources/number_1.png");
                imagePaths.add("resources/number_2.png");
                imagePaths.add("resources/number_3.png");
                imagePaths.add("resources/number_4.png");
                imagePaths.add("resources/number_5.png");
                imagePaths.add("resources/number_6.png");
                imagePaths.add("resources/number_7.png");
                imagePaths.add("resources/number_8.png");
                break;
            case "Letters": // Θέμα γραμμάτων
                imagePaths.add("resources/letter_A.png");
                imagePaths.add("resources/letter_B.png");
                imagePaths.add("resources/letter_C.png");
                imagePaths.add("resources/letter_D.png");
                imagePaths.add("resources/letter_E.png");
                imagePaths.add("resources/letter_F.png");
                imagePaths.add("resources/letter_G.png");
                imagePaths.add("resources/letter_H.png");
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
