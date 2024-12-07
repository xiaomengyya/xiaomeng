package upei.project.GUI;
import upei.project.Controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGame extends JFrame {
    public StartGame() {
        setTitle("Warring States");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 主界面布局
        JPanel panel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to the Three Kingdoms game", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(welcomeLabel, BorderLayout.CENTER);

        // Start button
        JButton startGameButton = new JButton("Start Game");
        startGameButton.setFont(new Font("Arial", Font.BOLD, 14));
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Assign two players' Legends and switch to the game interface
                GameController game = new GameController();
                game.initializeGame();
                new GameWindow(game).setVisible(true);
                dispose();
            }
        });
        panel.add(startGameButton, BorderLayout.SOUTH);

        add(panel);
        setVisible(true); // 确保界面可见
    }

    public static void main(String[] args) {
        try {
            SwingUtilities.invokeLater(() -> {
                new StartGame().setVisible(true);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
