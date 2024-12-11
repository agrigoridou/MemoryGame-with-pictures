package model;

public class ImageCard extends Card {
    private String imagePath;

    public ImageCard(int id, String imagePath) {
        super(id);  // Αναθέτουμε το id μέσω του κατασκευαστή της υπερκλάσης
        this.imagePath = imagePath;
        this.isFlipped = false;  // Μπορείς να το αφήσεις έτσι ή να το ρυθμίσεις μέσω του κατασκευαστή αν χρειάζεται
    }

    @Override
    public boolean isMatch(Card card) {
        if (card instanceof ImageCard) {
            return this.imagePath.equals(((ImageCard) card).imagePath);
        }
        return false;
    }

    public String getImagePath() {
        return imagePath;
    }
}
