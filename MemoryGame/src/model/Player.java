package model;

public class Player {
    private String name;
    private int failedAttempts;

    public Player(String name) {
        this.name = name;
        this.failedAttempts = 0;
    }

    public void incrementFailed() {
        this.failedAttempts++;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public String getName() {
        return name;
    }
}

