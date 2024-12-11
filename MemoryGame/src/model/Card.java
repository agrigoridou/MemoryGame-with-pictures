package model;

public abstract class Card {
    protected boolean isFlipped;
    protected int id;

    public abstract boolean isMatch(Card card);

    public void flip() {
        this.isFlipped = !this.isFlipped;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public int getId() {
        return id;
    }
}

