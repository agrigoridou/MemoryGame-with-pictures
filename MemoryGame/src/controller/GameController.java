package controller;

import model.*;

public class GameController implements GameInterface {
    private GameBoard gameBoard;
    private final Player player;
    private final String theme;
    private int score;
    private int matchedPairs;

    public GameController(String theme, int rows, int cols, Player player) {
        this.theme = theme;
        this.player = player;
        this.gameBoard = new GameBoard(rows, cols, theme);
        this.score = 0;
        this.matchedPairs = 0;
    }

    public GameController() {
        this("Default", 4, 4, new Player("Player"));
    }

    public Player getPlayer() {
        return player;
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

            if (card instanceof JokerCard jokerCard) {
                // Reveal all cards matching the Joker image
                String jokerImagePath = jokerCard.getImagePath();
                for (Card c : gameBoard.getCards()) {
                    if (c instanceof ImageCard imageCard && imageCard.getImagePath().equals(jokerImagePath)) {
                        c.flip();
                    }
                }
                System.out.println("Joker card used! Revealed matching cards.");
                matchedPairs += 2; // Assume Joker reveals two pairs
                score += 20; // Bonus for Joker
            } else {
                // Handle regular card flipping logic
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
        player.setFailedAttempts(0);
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