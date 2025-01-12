package model;

public abstract class Card {
    private final int id;  // Μοναδικό αναγνωριστικό (ID) της κάρτας.
    protected boolean isFlipped; // Κατάσταση της κάρτας: true αν είναι γυρισμένη (ανοιχτή), false αν είναι κλειστή.

    // Κατασκευαστής της κλάσης Card
    public Card(int id) {
        this.id = id; // Αρχικοποίηση του μοναδικού ID της κάρτας.
    }

    /**
     * Η μέθοδος isMatch ελέγχει αν η κάρτα που συγκρίνεται είναι όμοια με την τρέχουσα κάρτα.
     * @param card η κάρτα με την οποία γίνεται η σύγκριση.
     * @return true αν οι κάρτες ταιριάζουν, false διαφορετικά.
     */
    public boolean isMatch(Card card) {
        return false; // Default συμπεριφορά: οι κάρτες δεν ταιριάζουν.
    }

    /**
     * Αναστρέφει την κατάσταση της κάρτας από flipped σε unflipped ή το αντίστροφο.
     */
    public void flip() {
        this.isFlipped = !this.isFlipped; // Αντιστροφή της τρέχουσας κατάστασης.
    }

    /**
     * Επιστρέφει την κατάσταση της κάρτας (αν είναι flipped ή όχι).
     * @return true αν η κάρτα είναι γυρισμένη (ανοιχτή), false αν είναι κλειστή.
     */
    public boolean isFlipped() {
        return isFlipped; // Επιστροφή της τρέχουσας κατάστασης flipped.
    }

    /**
     * Setter για να ορίσουμε την κατάσταση flipped.
     */
    public void setFlipped(boolean flipped) {
        this.isFlipped = flipped; // Σωστό setter για το πεδίο isFlipped
    }

    /**
     * Επιστρέφει το μοναδικό αναγνωριστικό (ID) της κάρτας.
     * @return το ID της κάρτας.
     */
    public int getId() {
        return id; // Επιστροφή του ID.
    }
}
