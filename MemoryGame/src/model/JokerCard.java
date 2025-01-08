package model;

public class JokerCard extends Card {
    private final String imagePath;  // Χρησιμοποιούμε την εικόνα του μπαλαντέρ

    public JokerCard(int id, String imagePath) {
        super(id);  // Αναθέτουμε το id μέσω του κατασκευαστή της υπερκλάσης
        this.imagePath = imagePath;
        this.isFlipped = false;  // Μπορείς να το αφήσεις έτσι ή να το ρυθμίσεις μέσω του κατασκευαστή αν χρειάζεται
    }

    @Override
    public boolean isMatch(Card card) {
        if (card instanceof JokerCard) {
            return true;  // Joker cards match each other
        }
        return super.isMatch(card);  // Fall back to the ImageCard matching logic
    }


    public String getImagePath() {
        return imagePath;
    }
}