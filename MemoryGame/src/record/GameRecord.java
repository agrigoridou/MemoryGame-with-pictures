package record;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameRecord {
    private final String playerName;
    private final int score;
    private final LocalDateTime completionTime;

    public GameRecord(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
        this.completionTime = LocalDateTime.now(); // Capture the current time of the game completion
    }

    // Method to save the game record to a file
    public void saveRecordToFile() {
        // Use try-with-resources to ensure the file is properly closed
        try (FileWriter writer = new FileWriter("game_records.txt", true)) {
            String formattedDate = completionTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String record = String.format("Player: %s, Score: %d, Time: %s%n", playerName, score, formattedDate);
            writer.write(record); // Write the record to the file
            System.out.println("Record saved: " + record);
        } catch (IOException e) {
            System.err.println("Error saving game record: " + e.getMessage());
        }
    }
}
