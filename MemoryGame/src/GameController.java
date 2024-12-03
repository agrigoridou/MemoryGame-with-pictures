public class GameController {
    private Board board;
    private Player player;
    private int incorrectAttempts;
    private int maxAttempts;

    public GameController(Player player, int boardSize, int maxAttempts) {
        this.player = player;
        this.board = new Board(boardSize);
        this.incorrectAttempts = 0;
        this.maxAttempts = maxAttempts;
    }

    public void flipCard(int index) {
        board.flipCard(index);
    }

    public boolean checkMatch(int firstIndex, int secondIndex) {
        Card firstCard = board.getCards().get(firstIndex);
        Card secondCard = board.getCards().get(secondIndex);

        if (firstCard.getImage().equals(secondCard.getImage())) {
            player.increaseScore();
            return true;
        } else {
            incorrectAttempts++;
            if (incorrectAttempts >= maxAttempts) {
                return false; // Παίκτης έχασε το παιχνίδι
            }
            return false;
        }
    }

    public boolean isGameOver() {
        return player.getScore() == (board.getCards().size() / 2);
    }
}
