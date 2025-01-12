package model;

public class Player {
    private final String name;  // Όνομα του παίκτη
    private int failedAttempts;  // Αριθμός αποτυχημένων προσπαθειών του παίκτη

    // Κατασκευαστής της κλάσης Player
    public Player(String name) {
        this.name = name;  // Αναθέτουμε το όνομα του παίκτη που περνάει στον κατασκευαστή
        this.failedAttempts = 0;  // Αρχικοποιούμε τον αριθμό των αποτυχημένων προσπαθειών σε 0
    }

    // Μέθοδος για να επιστρέφουμε το όνομα του παίκτη
    public String getName() {
        return name;  // Επιστρέφουμε το όνομα του παίκτη
    }

    // Μέθοδος για να επιστρέφουμε τον αριθμό των αποτυχημένων προσπαθειών
    public int getFailedAttempts() {
        return failedAttempts;  // Επιστρέφουμε τον αριθμό των αποτυχημένων προσπαθειών
    }

    // Μέθοδος για να αυξήσουμε τον αριθμό των αποτυχημένων προσπαθειών κατά 1
    public void incrementFailed() {
        failedAttempts++;  // Αυξάνουμε τον αριθμό των αποτυχημένων προσπαθειών κατά 1
    }

    // Μέθοδος για να θέσουμε τον αριθμό των αποτυχημένων προσπαθειών σε μια συγκεκριμένη τιμή
    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;  // Ενημερώνουμε τον αριθμό των αποτυχημένων προσπαθειών με την νέα τιμή
    }
}
