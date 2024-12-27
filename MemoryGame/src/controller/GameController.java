package controller;

import model.Card;
import model.GameBoard;
import model.Player;

public class GameController implements GameInterface {
    private GameBoard gameBoard;  // Made final
    private final Player player;        // Made final
    private final String theme;         // Made final
    private int score;
    private int matchedPairs;

    public GameController(String theme, int rows, int cols, Player player) {
        this.theme = theme;
        this.player = player;
        this.gameBoard = new GameBoard(rows, cols, theme);
        this.score = 0;
        this.matchedPairs = 0;
    }

    @Override
    public void startGame() {
        System.out.println("Game started with theme: " + theme);
    }

    @Override
    public void cardSelected(Card card) {
        if (!card.isFlipped()) {
            card.flip();
            System.out.println("Card flipped: " + card);

            // Check if the cards match
            Card firstFlipped = gameBoard.getFirstFlippedCard();
            if (firstFlipped != null && firstFlipped != card) {
                if (firstFlipped.isMatch(card)) {
                    System.out.println("Match found!");
                    matchedPairs++;
                    score += 10; // Add score for matched pairs
                } else {
                    System.out.println("No match!");
                    player.incrementFailed(); // Increment failed attempts
                    firstFlipped.flip(); // Flip back the first card
                    card.flip(); // Flip back the current card
                }
            }
        }
    }

    @Override
    public void updateScore() {
        System.out.println("Score: " + score);
        System.out.println("Failed attempts: " + player.getFailedAttempts());
    }

    @Override
    public void resetGame() {
        this.gameBoard = new GameBoard(gameBoard.getRows(), gameBoard.getCols(), theme);
        this.score = 0;
        this.matchedPairs = 0;
        player.incrementFailed(); // Reset failed attempts
        System.out.println("Game reset with theme: " + theme);
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public boolean isGameWon() {
        return matchedPairs == (gameBoard.getRows() * gameBoard.getCols()) / 2;
    }

    public int getScore() {
        return score;
    }
}
