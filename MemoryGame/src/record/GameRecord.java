package record;

import java.io.FileWriter;
import java.io.IOException;

public class GameRecord {
    private String playerName;
    private int score;

    public GameRecord(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public void saveRecordToFile() {
        try (FileWriter writer = new FileWriter("game_records.txt", true)) {
            writer.write(playerName + ": " + score + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadRecords() {
        // Φόρτωση των αποτελεσμάτων
        System.out.println("Loading records...");
    }
}