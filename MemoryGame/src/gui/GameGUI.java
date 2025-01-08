package gui;

import controller.GameController;
import model.Card;
import model.GameBoard;
import model.ImageCard;
import model.JokerCard;

import javax.swing.*;
import java.awt.*;

public class GameGUI implements GUIInterface {
    private final GameController controller;
    private JFrame frame;
    private JPanel boardPanel;
    private JLabel scoreLabel;
    private JLabel attemptsLabel;
    private JLabel remainingCardsLabel;

    public GameGUI(GameController controller) {
        this.controller = controller;
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Memory Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem themeItem = new JMenuItem("Select Theme");
        themeItem.addActionListener(e -> selectTheme());
        menu.add(themeItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        JPanel statusPanel = new JPanel(new GridLayout(1, 3));
        scoreLabel = new JLabel("Score: 0");
        attemptsLabel = new JLabel("Failed Attempts: 0");
        remainingCardsLabel = new JLabel("Remaining Cards: " + controller.getGameBoard().getCards().size());
        statusPanel.add(scoreLabel);
        statusPanel.add(attemptsLabel);
        statusPanel.add(remainingCardsLabel);

        frame.add(statusPanel, BorderLayout.NORTH);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(controller.getGameBoard().getRows(), controller.getGameBoard().getCols()));
        frame.add(boardPanel, BorderLayout.CENTER);

        frame.setVisible(true);
        updateBoard(controller.getGameBoard());
    }

    private void selectTheme() {
        String[] themes = {"Animals", "Numbers", "Letters"};
        String selectedTheme = (String) JOptionPane.showInputDialog(frame, "Select a theme:", "Theme Selection",
                JOptionPane.QUESTION_MESSAGE, null, themes, themes[0]);

        if (selectedTheme != null) {
            controller.resetGame();
            updateBoard(controller.getGameBoard());
        }
    }

    @Override
    public void updateBoard(GameBoard board) {
        boardPanel.removeAll();

        for (Card card : board.getCards()) {
            JButton cardButton = new JButton();

            if (card.isFlipped()) {
                if (card instanceof ImageCard imageCard) {
                    String imagePath = imageCard.getImagePath();
                    java.net.URL imageUrl = getClass().getResource(imagePath);
                    if (imageUrl != null) {
                        ImageIcon icon = new ImageIcon(imageUrl);
                        cardButton.setIcon(icon);
                    } else {
                        cardButton.setText("Image not found");
                    }
                } else if (card instanceof JokerCard) {
                    cardButton.setText("Joker");
                    cardButton.setBackground(Color.GRAY);
                }
            } else {
                cardButton.setText("");
                cardButton.setBackground(Color.YELLOW);
            }

            cardButton.addActionListener(e -> {
                controller.cardSelected(card);
                updateBoard(board);
                scoreLabel.setText("Score: " + controller.getScore());
                attemptsLabel.setText("Failed Attempts: " + controller.getPlayer().getFailedAttempts());
                remainingCardsLabel.setText("Remaining Cards: " +
                        (board.getCards().size() - controller.getGameBoard().getCards().stream().filter(Card::isFlipped).count()));

                if (controller.isGameWon()) {
                    showMessage("Congratulations, you won!");
                } else if (controller.getPlayer().getFailedAttempts() >= controller.getGameBoard().getRows() * controller.getGameBoard().getCols() / 4) {
                    showMessage("Game Over, you lost!");
                }
            });

            boardPanel.add(cardButton);
        }

        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    @Override
    public void saveGameRecord(String playerName, int score) {
        System.out.println("Game record saved for " + playerName + " with score " + score);
    }


}
