package gui;

import controller.GameController;
import model.Card;
import model.GameBoard;
import model.ImageCard;

import javax.swing.*;
import java.awt.*;

public class GameGUI implements GUIInterface {
    private final GameController controller;  // Made final
    private JFrame frame;
    private JPanel boardPanel;
    private JLabel scoreLabel;
    private JLabel attemptsLabel;

    public GameGUI(GameController controller) {
        this.controller = controller;
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Memory Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem themeItem = new JMenuItem("Select Theme");
        themeItem.addActionListener(e -> selectTheme());
        menu.add(themeItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        JPanel statusPanel = new JPanel(new GridLayout(1, 2));
        scoreLabel = new JLabel("Score: 0");
        attemptsLabel = new JLabel("Failed Attempts: 0");
        statusPanel.add(scoreLabel);
        statusPanel.add(attemptsLabel);

        frame.add(statusPanel, BorderLayout.NORTH);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(4, 4));
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
        boardPanel.removeAll();  // Clear previous board
        for (Card card : board.getCards()) {
            JButton cardButton = new JButton();

            // If the card is flipped, load its image
            if (card.isFlipped()) {
                ImageCard imageCard = (ImageCard) card;  // Ensure the card is ImageCard
                String imagePath = imageCard.getImagePath();
                java.net.URL imageUrl = getClass().getResource(imagePath);  // Get image URL

                if (imageUrl != null) {
                    ImageIcon icon = new ImageIcon(imageUrl);
                    cardButton.setIcon(icon);  // Set the image on the card
                } else {
                    // Handle the case where the image path is invalid
                    System.err.println("Image not found: " + imagePath);
                    cardButton.setText("Image not found");  // Display an error message on the button
                }
            } else {
                cardButton.setText("");  // No text or image when card is not flipped
                cardButton.setBackground(Color.YELLOW);  // Set background for hidden cards
            }

            // Action when the card is selected
            cardButton.addActionListener(e -> {
                controller.cardSelected(card);
                updateBoard(board);  // Update the board
                scoreLabel.setText("Score: " + controller.getScore());
                attemptsLabel.setText("Failed Attempts: " + board.getCards().size());
                if (controller.isGameWon()) {
                    showMessage("Congratulations, you won!");
                }
            });

            boardPanel.add(cardButton);  // Add the button to the panel
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
