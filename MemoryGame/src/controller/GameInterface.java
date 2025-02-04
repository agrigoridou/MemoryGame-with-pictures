package controller;  // Η κλάση βρίσκεται στο πακέτο controller

import model.Card;  // Εισαγωγή της κλάσης Card, η οποία αναπαριστά την κάρτα στο παιχνίδι

// Δηλώνει ένα interface για τη διαχείριση των βασικών λειτουργιών του παιχνιδιού
public interface GameInterface {

    // Μέθοδος για την εκκίνηση του παιχνιδιού
    void startGame();

    // Μέθοδος για την επιλογή μιας κάρτας από τον παίκτη
    void cardSelected(Card card);

    // Μέθοδος για την ενημέρωση του σκορ του παιχνιδιού
    void updateScore();

    // Μέθοδος για την επαναφορά του παιχνιδιού (π.χ. μετά από μία ολοκλήρωση ή επανεκκίνηση)
    void resetGame();

    void startNewGame(String playerName, String selectedLevel);
}
