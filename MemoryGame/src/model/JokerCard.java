package model;

public class JokerCard extends Card {
    private final String imagePath;  // Χρησιμοποιούμε την εικόνα του μπαλαντέρ (Joker)

    // Κατασκευαστής για την κλάση JokerCard
    public JokerCard(int id, String imagePath) {
        super(id);  // Καλούμε τον κατασκευαστή της υπερκλάσης (Card) για να αναθέσουμε το id στην κάρτα
        this.imagePath = imagePath;  // Αναθέτουμε τη διαδρομή της εικόνας (εικόνα του μπαλαντέρ)
        this.isFlipped = false;  // Η κάρτα ξεκινά κλειστή, οπότε το isFlipped είναι false
    }

    // Υλοποίηση της μεθόδου isMatch για να ελέγξουμε αν δύο κάρτες είναι ίδιες
    @Override
    public boolean isMatch(Card card) {
        // Αν η κάρτα είναι τύπου JokerCard, τότε οι δύο κάρτες ταιριάζουν πάντα
        if (card instanceof JokerCard) {
            return true;  // Οι κάρτες μπαλαντέρ (Joker) πάντα ταιριάζουν μεταξύ τους
        }
        // Αν δεν είναι JokerCard, καλούμε την isMatch της υπερκλάσης για να ελέγξουμε αν είναι ImageCard
        return super.isMatch(card);  // Επιστρέφουμε το αποτέλεσμα της μεθόδου isMatch της ImageCard
    }

    // Μέθοδος για να επιστρέφουμε τη διαδρομή της εικόνας του μπαλαντέρ
    public String getImagePath() {
        return imagePath;  // Επιστρέφουμε την διαδρομή της εικόνας του μπαλαντέρ
    }
}
