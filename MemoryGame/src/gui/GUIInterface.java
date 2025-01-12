package gui;  // Ορίζουμε ότι η κλάση ανήκει στο πακέτο gui, το οποίο αναφέρεται στην διεπαφή του παιχνιδιού (Graphical User Interface)

import model.GameBoard;  // Εισαγωγή της κλάσης GameBoard από το πακέτο model, η οποία αναπαριστά τον πίνακα του παιχνιδιού

// Δηλώνει ένα interface για τη διαχείριση των λειτουργιών του GUI του παιχνιδιού
public interface GUIInterface {

    // Μέθοδος για την ενημέρωση του πίνακα του παιχνιδιού στην διεπαφή χρήστη (GUI)
    void updateBoard(GameBoard board);

    // Μέθοδος για την εμφάνιση μηνυμάτων στην GUI (π.χ. ενημερώσεις, επιτυχίες ή αποτυχίες)
    void showMessage(String message);

    // Μέθοδος για την αποθήκευση του σκορ του παιχνιδιού μετά από ολοκλήρωσή του
    void saveGameRecord(String playerName, int score);
}
