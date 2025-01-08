package model;

public abstract class Card {
    private final int id;  // Καθορίζουμε το id ως τελικό
    protected boolean isFlipped;

    // Κατασκευαστής για το id
    public Card(int id) {
        this.id = id;
    }

    // Η μέθοδος isMatch πρέπει να υλοποιείται από τις υποκλάσεις
    public boolean isMatch(Card card) {
        return false;
    }

    // Μέθοδος για να αναστρέψουμε την κατάσταση flip
    public void flip() {
        this.isFlipped = !this.isFlipped;
    }

    // Επιστρέφει την κατάσταση flipped
    public boolean isFlipped() {
        return isFlipped;
    }

    // Επιστρέφει το id
    public int getId() {
        return id;
    }
}