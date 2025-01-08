package model;

public class Player {
    private String name;
    private int failedAttempts;

    public Player(String name) {
        this.name = name;
        this.failedAttempts = 0;
    }

    public String getName() {
        return name;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void incrementFailed() {
        failedAttempts++;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }
}
