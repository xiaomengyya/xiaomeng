package upei.project.GUI;

import upei.project.Card.Card;
import upei.project.Controller.GameController;
import upei.project.People.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private GameController game;
    private JTextArea gameLog;
    private JTextField inputField;
    private JLabel currentPlayerLabel;
    private JLabel roundLabel;

    public GameWindow(GameController game) {
        this.game = game;
        setTitle("三国杀游戏 - 游戏界面");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 主面板
        JPanel mainPanel = new JPanel(new BorderLayout());

        // 顶部状态信息
        JPanel statusPanel = new JPanel(new GridLayout(2, 1));
        roundLabel = new JLabel("Round: 1", SwingConstants.CENTER);
        currentPlayerLabel = new JLabel("当前玩家: " + game.getCurrentPlayer().getName(), SwingConstants.CENTER);
        statusPanel.add(roundLabel);
        statusPanel.add(currentPlayerLabel);
        mainPanel.add(statusPanel, BorderLayout.NORTH);

        // 中间的游戏日志
        gameLog = new JTextArea();
        gameLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameLog);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // 底部的输入与按钮
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        JButton playCardButton = new JButton("Play Card");
        playCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePlayCard();
            }
        });

        JButton nextTurnButton = new JButton("Next Turn");
        nextTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleNextTurn();
            }
        });

        inputPanel.add(new JLabel("Enter card index to play:"), BorderLayout.WEST);
        inputPanel.add(inputField, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(playCardButton);
        buttonPanel.add(nextTurnButton);
        inputPanel.add(buttonPanel, BorderLayout.EAST);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        add(mainPanel);

        updateUI();
    }

    private void handlePlayCard() {
        String input = inputField.getText();
        try {
            int index = Integer.parseInt(input);
            Person currentPlayer = game.getCurrentPlayer();
            if (index < 0 || index >= currentPlayer.getHandCards().size()) {
                gameLog.append("无效的索引！请重新输入。\n");
                return;
            }
            Card card = currentPlayer.getHandCards().get(index);
            //Person opponent = game.getOpponent(currentPlayer);
            game.playCard(card, currentPlayer, game.getOpponent(currentPlayer));
            gameLog.append(currentPlayer.getName() + " 使用了卡牌: " + card.getName() + "\n");
            inputField.setText("");
        } catch (NumberFormatException e) {
            gameLog.append("请输入有效的索引数字！\n");
        }
    }

    private void handleNextTurn() {
        game.nextTurn();
        updateUI();
    }

    private void updateUI() {
        roundLabel.setText("Round: " + game.getRound());
        currentPlayerLabel.setText("当前玩家: " + game.getCurrentPlayer().getName());
        Person p1 = game.getP1();
        Person p2 = game.getP2();

        gameLog.append("\n--- 状态更新 ---\n");
        gameLog.append("玩家 1: " + p1.getName() + ", Legend: " + p1.getLegend().getName() + ", HP: " + p1.getHP() + "\n");
        gameLog.append("玩家 2: " + p2.getName() + ", Legend: " + p2.getLegend().getName() + ", HP: " + p2.getHP() + "\n");
        gameLog.append("当前玩家手牌: \n");
        int i = 0;
        for (Card card : game.getCurrentPlayer().getHandCards()) {
            gameLog.append(i + ": " + card.getName() + " (Skill: " + card.getDescription() + ")\n");
            i++;
        }
    }
}
