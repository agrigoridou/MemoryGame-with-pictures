package gui;
import model.GameBoard;


public interface GUIInterface {
    void updateBoard(GameBoard board);
    void showMessage(String message);
    void saveGameRecord(String playerName, int score);
}
