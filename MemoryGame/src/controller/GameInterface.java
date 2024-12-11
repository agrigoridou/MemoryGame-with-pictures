package controller;
import model.Card;


public interface GameInterface {
    void startGame();
    void cardSelected(Card card);
    void updateScore();
    void resetGame();
}

