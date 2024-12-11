package model;

public class JokerCard extends Card {
    private String jokerEffect;

    public JokerCard(int id, String jokerEffect) {
        super(id);  // Αναθέτουμε το id μέσω του κατασκευαστή της υπερκλάσης
        this.jokerEffect = jokerEffect;
        this.isFlipped = false;  // Μπορείς να το αφήσεις έτσι ή να το ρυθμίσεις μέσω του κατασκευαστή αν χρειάζεται
    }

    @Override
    public boolean isMatch(Card card) {
        return true; // Η κάρτα "Joker" ταιριάζει με όλες τις άλλες
    }

    public String getJokerEffect() {
        return jokerEffect;
    }
}
