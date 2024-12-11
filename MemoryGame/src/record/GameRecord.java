package record;

public class GameRecord {
    private String playerName;
    private int score;

    public GameRecord(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public void saveRecordToFile() {
        // Αποθήκευση του σκορ σε αρχείο
        System.out.println("Record saved for " + playerName + " with score " + score);
    }

    public void loadRecords() {
        // Φόρτωση των αποτελεσμάτων
        System.out.println("Loading records...");
    }
}
