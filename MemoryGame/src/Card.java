public abstract class Card {
    protected String image; // Η εικόνα της κάρτας
    protected boolean isFaceUp; // Αν η κάρτα είναι ανοιχτή ή κλειστή

    public Card(String image) {
        this.image = image;
        this.isFaceUp = false; // Η κάρτα ξεκινάει κλειστή
    }

    public String getImage() {
        return image;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public void flip() {
        isFaceUp = !isFaceUp; // Αντιστρέφει την κατάσταση της κάρτας
    }

    public abstract void display(); // Μέθοδος για την εμφάνιση της κάρτας
}
