package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameBoard {
    private final int rows;
    private final int cols;
    private final List<Card> cards;

    public GameBoard(int rows, int cols, String theme) {
        this.rows = rows;
        this.cols = cols;
        this.cards = new ArrayList<>();
        initializeBoard(theme);
    }

    private void initializeBoard(String theme) {
        List<String> imagePaths = loadThemeImages(theme);

        // Ensure there are enough images for the board
        if (imagePaths.size() < (rows * cols) / 2 - 1) {
            throw new IllegalArgumentException("Not enough images for the selected theme.");
        }

        // Create pairs of cards
        for (int i = 0; i < (rows * cols) / 2 - 1; i++) {
            String imagePath = imagePaths.get(i);
            cards.add(new ImageCard(i, imagePath));
            cards.add(new ImageCard(i, imagePath));
        }

        // Add joker cards
        cards.add(new JokerCard(999, imagePaths.get(0)));
        cards.add(new JokerCard(1000, imagePaths.get(1)));

        // Shuffle the cards
        Collections.shuffle(cards);
    }


    private List<String> loadThemeImages(String theme) {
        List<String> imagePaths = new ArrayList<>();
        switch (theme) {
            case "Animals":
                imagePaths.add("/resources/images/animal_fish.png");
                imagePaths.add("/resources/images/animal_owl.png");
                imagePaths.add("/resources/images/animal_cat.png");
                imagePaths.add("/resources/images/animal_dog.png");
                imagePaths.add("/resources/images/animal_lion.png");
                imagePaths.add("/resources/images/animal_elephant.png");
                imagePaths.add("/resources/images/animal_tiger.png");
                imagePaths.add("/resources/images/animal_bear.png");
                break;
            case "Numbers":
                imagePaths.add("images/number_1.png");
                imagePaths.add("images/number_2.png");
                imagePaths.add("images/number_3.png");
                imagePaths.add("images/number_4.png");
                imagePaths.add("images/number_5.png");
                imagePaths.add("images/number_6.png");
                imagePaths.add("images/number_7.png");
                imagePaths.add("images/number_8.png");
                break;
            case "Letters":
                imagePaths.add("images/letter_A.png");
                imagePaths.add("images/letter_B.png");
                imagePaths.add("images/letter_C.png");
                imagePaths.add("images/letter_D.png");
                imagePaths.add("images/letter_E.png");
                imagePaths.add("images/letter_F.png");
                imagePaths.add("images/letter_G.png");
                imagePaths.add("images/letter_H.png");
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