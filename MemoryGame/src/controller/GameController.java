package controller;

import model.GameBoard;
import model.Card;
import model.Player;

public class GameController implements GameInterface {
    private final GameBoard gameBoard;
    private final Player currentPlayer;
    private int failedAttempts;

    public GameController(GameBoard gameBoard, Player currentPlayer) {
        this.gameBoard = gameBoard;
        this.currentPlayer = currentPlayer;
        this.failedAttempts = 0;
    }

    @Override
    public void startGame() {
        System.out.println("Game started!");
    }

    @Override
    public void cardSelected(Card card) {
        System.out.println("Card selected: " + card.getId());
        // Λογική για έλεγχο αν οι κάρτες ταιριάζουν
        failedAttempts++;
        if (failedAttempts > 3) {
            System.out.println("Too many failed attempts! Game over.");
            resetGame();
        }
    }

    @Override
    public void updateScore() {
        System.out.println("Score updated");
    }

    @Override
    public void resetGame() {
        System.out.println("Game reset");
        failedAttempts = 0; // Επαναφορά των αποτυχημένων προσπαθειών
    }
}
