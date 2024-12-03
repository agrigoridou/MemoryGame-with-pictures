import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private List<Card> cards;
    private int boardSize;

    public Board(int size) {
        this.boardSize = size;
        this.cards = new ArrayList<>();
        initializeBoard();
    }

    private void initializeBoard() {
        // Προσθήκη ζευγών εικόνας
        List<String> images = new ArrayList<>();
        for (int i = 0; i < (boardSize * boardSize) / 2; i++) {
            images.add("Image_" + i);
            images.add("Image_" + i); // Διπλασιάζουμε κάθε εικόνα για τα ζεύγη
        }

        // Ανακάτεμα των εικόνων
        Collections.shuffle(images);

        // Δημιουργία καρτών για το ταμπλό
        for (String image : images) {
            if (image.equals("Joker")) {
                cards.add(new JokerCard(image)); // Προσθήκη ειδικών καρτών μπαλαντέρ
            } else {
                cards.add(new Card(image) {
                    @Override
                    public void display() {
                        if (isFaceUp()) {
                            System.out.println("Displaying Card: " + getImage());
                        } else {
                            System.out.println("Card is face down.");
                        }
                    }
                });
            }
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public void flipCard(int index) {
        if (index >= 0 && index < cards.size()) {
            cards.get(index).flip();
        }
    }
}
