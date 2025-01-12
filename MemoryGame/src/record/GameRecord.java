package record;

import java.io.FileWriter;
import java.io.IOException;

public class GameRecord {
    private final String playerName;  // Όνομα του παίκτη που σχετίζεται με το σκορ
    private final int score;  // Το σκορ του παίκτη

    // Κατασκευαστής της κλάσης GameRecord που αρχικοποιεί το όνομα του παίκτη και το σκορ
    public GameRecord(String playerName, int score) {
        this.playerName = playerName;  // Αναθέτουμε το όνομα του παίκτη
        this.score = score;  // Αναθέτουμε το σκορ του παίκτη
    }

    // Μέθοδος για την αποθήκευση του αποτελέσματος στο αρχείο "game_records.txt"
    public void saveRecordToFile() {
        try (FileWriter writer = new FileWriter("game_records.txt", true)) {
            // Άνοιγμα του αρχείου για εγγραφή (με παραμονή στο τέλος του αρχείου)
            writer.write(playerName + ": " + score + "\n");  // Γράφουμε το όνομα του παίκτη και το σκορ στο αρχείο
        } catch (IOException e) {
            // Αν παρουσιαστεί σφάλμα κατά την εγγραφή στο αρχείο, το εκτυπώνουμε
            e.printStackTrace();  // Εκτύπωση του σφάλματος
        }
    }

    // Μέθοδος για την φόρτωση των αποτελεσμάτων (προς το παρόν εκτυπώνει απλά μήνυμα)
    public void loadRecords() {
        // Φόρτωση των αποτελεσμάτων
        System.out.println("Loading records...");  // Για την ώρα, απλώς εκτυπώνουμε μήνυμα για την φόρτωση
    }
}
