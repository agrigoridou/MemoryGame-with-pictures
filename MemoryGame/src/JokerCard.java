public class JokerCard extends Card {

    public JokerCard(String image) {
        super(image);
    }

    @Override
    public void display() {
        if (isFaceUp()) {
            System.out.println("Displaying Joker Card: " + getImage());
        } else {
            System.out.println("Joker Card is face down.");
        }
    }
}
