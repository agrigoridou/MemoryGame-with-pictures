package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBoard {
    private List<Card> cards;
    private int rows;
    private int columns;
    private int maxTries;
    private int tries;
    private String playerName;

    public GameBoard(int rows, int columns, String playerName) {
        this.rows = rows;
        this.columns = columns;
        this.cards = new ArrayList<>();
        this.maxTries = (rows * columns) / 2;  // Καθορίζουμε το ανώτατο όριο προσπαθειών
        this.tries = 0;
        this.playerName = playerName;
        initializeBoard();
    }

    private void initializeBoard() {
        Random rand = new Random();
        for (int i = 0; i < rows * columns / 2; i++) {
            cards.add(new ImageCard(i, "image" + i + ".png"));
            cards.add(new ImageCard(i, "image" + i + ".png"));
        }
        // Προσθήκη καρτών "Joker"
        cards.add(new JokerCard(99, "Joker Effect"));
        cards.add(new JokerCard(99, "Joker Effect"));

        shuffleCards();
    }

    private void shuffleCards() {
        Random rand = new Random();
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public void increaseTries() {
        tries++;
    }

    public int getTries() {
        return tries;
    }

    public int getMaxTries() {
        return maxTries;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isGameOver() {
        return tries > maxTries;
    }

    public boolean isGameWon() {
        // Κάνουμε έλεγχο για το αν όλα τα ζεύγη έχουν αποκαλυφθεί
        for (Card card : cards) {
            if (!card.isFlipped()) {
                return false;
            }
        }
        return true;
    }
}
