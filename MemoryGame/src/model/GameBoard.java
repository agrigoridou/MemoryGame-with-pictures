package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameBoard {
    private int rows;
    private int cols;
    private List<Card> cards;

    public GameBoard(int rows, int cols, String theme) {
        this.rows = rows;
        this.cols = cols;
        this.cards = new ArrayList<>();
        initializeBoard(theme);
    }

    private void initializeBoard(String theme) {
        List<String> imagePaths = loadThemeImages(theme);

        // Ensure there are enough images for the board
        if (imagePaths.size() < (rows * cols) / 2 - 1) {  // Adjusted for joker cards
            throw new IllegalArgumentException("Not enough images for the selected theme.");
        }

        // Create pairs of cards
        for (int i = 0; i < (rows * cols) / 2 - 1; i++) {
            String imagePath = imagePaths.get(i);
            cards.add(new ImageCard(i, imagePath)); // First pair
            cards.add(new ImageCard(i + 1, imagePath)); // Second pair
        }

        // Add joker cards
        cards.add(new JokerCard(999, imagePaths.get(0))); // First joker
        cards.add(new JokerCard(1000, imagePaths.get(1))); // Second joker

        // Shuffle the cards
        Collections.shuffle(cards);
    }

    private List<String> loadThemeImages(String theme) {
        List<String> imagePaths = new ArrayList<>();
        switch (theme) {
            case "Animals":
                imagePaths.add("images/animal_fish.png");
                imagePaths.add("images/animal_owl.png");
                imagePaths.add("images/animal_cat.png");
                imagePaths.add("images/animal_dog.png");
                break;
            case "Numbers":
                imagePaths.add("images/number_1.png");
                imagePaths.add("images/number_2.png");
                imagePaths.add("images/number_3.png");
                imagePaths.add("images/number_4.png");
                break;
            case "Letters":
                imagePaths.add("images/letter_A.png");
                imagePaths.add("images/letter_B.png");
                imagePaths.add("images/letter_C.png");
                imagePaths.add("images/letter_D.png");
                break;
            default:
                throw new IllegalArgumentException("Invalid theme selected.");
        }
        return imagePaths;
    }

    public List<Card> getCards() {
        return cards;
    }

    // Add the missing getter methods
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    // Add the missing getFirstFlippedCard method
    public Card getFirstFlippedCard() {
        for (Card card : cards) {
            if (card.isFlipped()) {
                return card;
            }
        }
        return null; // No flipped card found
    }
}
