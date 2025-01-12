package model;

public class ImageCard extends Card {
    private final String imagePath; // Διαδρομή της εικόνας που αντιπροσωπεύει η κάρτα

    // Κατασκευαστής για την κλάση ImageCard
    public ImageCard(int id, String imagePath) {
        super(id);  // Καλούμε τον κατασκευαστή της υπερκλάσης (Card) για να αναθέσουμε το id στην κάρτα
        this.imagePath = imagePath;  // Αναθέτουμε τη διαδρομή της εικόνας
        this.isFlipped = false;  // Η κάρτα ξεκινά κλειστή, οπότε το isFlipped είναι false
    }

    // Υλοποίηση της μεθόδου isMatch για να ελέγξουμε αν δύο κάρτες είναι ίδιες
    @Override
    public boolean isMatch(Card card) {
        // Αν η κάρτα είναι του τύπου ImageCard
        if (card instanceof ImageCard) {
            // Ελέγχουμε αν οι διαδρομές εικόνας της τρέχουσας κάρτας και της άλλης κάρτας είναι ίδιες
            return this.imagePath.equals(((ImageCard) card).imagePath);
        }
        return false; // Αν η κάρτα δεν είναι τύπου ImageCard, επιστρέφουμε false
    }

    // Μέθοδος για να επιστρέφουμε τη διαδρομή της εικόνας αυτής της κάρτας
    public String getImagePath() {
        return imagePath; // Επιστρέφουμε την διαδρομή της εικόνας
    }
}
