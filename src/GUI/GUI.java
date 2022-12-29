package GUI;

import javax.swing.*;

import Game.Generation.Generation;
import Player.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class GUI implements Observer {
    private JLayeredPane gamePanel;
    private String player1Name;
    private String player2Name;
    private Color player1Color;
    private String player1ColorStr;
    private Color player2Color;
    private String player2ColorStr;
    private String[] colorOption = {"black", "white", "red", "blue", "yellow"};
    Generation generation = new Generation();

    public String setPlayerName(PlayerId player) {
        String playerName = "";
        while (Objects.equals(playerName, "")) {
            JPanel panel = new JPanel();
            playerName = JOptionPane.showInputDialog(panel, player + "enter your username:", "Enter Username", 1);
            System.out.println(playerName);
            if (playerName == null) {
                System.exit(0);
            }
        }

        return playerName;
    }

    public String getCellColor(PlayerId player, String[] colorOption) {
        JPanel panel = new JPanel();
        String playerColorStr = (String) JOptionPane.showInputDialog(panel, player + "select cell's color", "Select Cell's Color", 1, null, colorOption, colorOption[0]);
        if (playerColorStr == null) {
            System.exit(0);
        }
        return playerColorStr;
    }

    public Color setCellColor(String cellColor){
        return switch (cellColor) {
            case "black" -> Color.BLACK;
            case "white" -> Color.WHITE;
            case "red" -> Color.RED;
            case "blue" -> Color.BLUE;
            case "yellow" -> Color.YELLOW;
            default -> null;
        };
    }

    public void setAllPlayersInfo() {
        player1Name = setPlayerName(PlayerId.PLAYER_A);
        player1ColorStr = getCellColor(PlayerId.PLAYER_A, colorOption);
        player1Color = setCellColor(player1ColorStr);

        String[] newColorOption = new String[colorOption.length - 1];
        int flag = 0;
        for (String s : colorOption) {
            if (!Objects.equals(s, player1ColorStr)) {
                newColorOption[flag] = s;
                flag += 1;
            }
        }
        player2Name = setPlayerName(PlayerId.PLAYER_B);
        player2ColorStr = getCellColor(PlayerId.PLAYER_B, newColorOption);
        player2Color = setCellColor(player2ColorStr);
    }

    public String getPlayerName(Player player){
        if(player.getPlayerId() == PlayerId.PLAYER_A){
            return player1Name;
        }else {
            return player2Name;
        }
    }

    public void setUpGameWindow() {
//        System.out.println(player1Name);
        setAllPlayersInfo();
        JLabel headerLabel = new JLabel("Conway’s Game of Life", JLabel.CENTER);
        headerLabel.setBounds(250, 5, 200, 20);

        JFrame gameWindow = new JFrame("Group 18-Conway’s Game of Life");
        gameWindow.setBounds(200, 300, 800, 680);
        gameWindow.setBackground(Color.LIGHT_GRAY);
//        Container gamePanel = new Container();
        gamePanel = new JLayeredPane();
        GridLayout gridLayout = new GridLayout(30, 30, 1, 1);
        gamePanel.setLayout(gridLayout);
        gamePanel.setBounds(25, 25, 600, 600);

//        container.add(gamePanel);

        for (int i = 0; i < 900; i++) {
            JPanel gridPanel1 = new JPanel();
            gridPanel1.setSize(20, 20);
            gridPanel1.setBackground(Color.GRAY);
            gamePanel.add(gridPanel1);
        }

        String n1 = player1Name;
        String n2 = player2Name;

        JPanel infoPanel = new JPanel(new GridLayout(10, 1));
        infoPanel.setSize(150, 600);
        JLabel player1 = new JLabel("Player1:", JLabel.LEFT);
        player1.setSize(150, 60);
//        JLabel player1Name = new JLabel(player1Name, JLabel.CENTER);
        JLabel player1Name = new JLabel(n1, JLabel.CENTER);
        player1Name.setSize(150, 60);
        JLabel player1Cell = new JLabel("Player1 Cell:", JLabel.LEFT);
        player1Cell.setSize(150, 60);
        JLabel player1CellNum = new JLabel("", JLabel.CENTER);
        player1CellNum.setSize(150, 60);
        JLabel player2 = new JLabel("Player2:", JLabel.LEFT);
        player2.setSize(150, 60);
        JLabel player2Name = new JLabel(n2, JLabel.CENTER);
        player2Name.setSize(150, 60);
        JLabel player2Cell = new JLabel("Play2 Cell:", JLabel.LEFT);
        player2Cell.setSize(150, 60);
        JLabel player2CellNum = new JLabel("", JLabel.CENTER);
        player2CellNum.setSize(150, 60);
        JLabel generation = new JLabel("Generation:", JLabel.LEFT);
        generation.setSize(150, 60);
        JLabel generationNum = new JLabel("", JLabel.CENTER);
        generationNum.setSize(150, 60);
        infoPanel.add(player1);
        infoPanel.add(player1Name);
        infoPanel.add(player1Cell);
        infoPanel.add(player1CellNum);
        infoPanel.add(player2);
        infoPanel.add(player2Name);
        infoPanel.add(player2Cell);
        infoPanel.add(player2CellNum);
        infoPanel.add(generation);
        infoPanel.add(generationNum);
        infoPanel.setBounds(650, 25, 100, 600);

        Component c = gamePanel.getComponentAt(40, 65);
        System.out.println(c);
        if (c instanceof JPanel) {
            c.setBackground(Color.BLACK);
        }

        gameWindow.add(gamePanel);
        gameWindow.add(infoPanel);
        gameWindow.add(headerLabel);
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

//    public int[] getClickPosition() {
//        return clickPosition;
//    }

    public void killCell(int[] position) {
        Component c = gamePanel.getComponentAt(position[0] * 20, position[1] * 20);
        System.out.println(c);
        if (c instanceof JPanel) {
            c.setBackground(Color.GRAY);
        }
    }

    public void refreshGamePanel() {

    }

//    public static void main(String[] args) {
//        GUI gui = new GUI();
//        gui.setAllPlayersInfo();
//        gui.setUpGameWindow();
//    }


    public void endWithWinner(Player winner) {
        // display winner and end game
    }

    public void startTurnOf(Player player) {
        // Start player's turn and let player pick one cell to kill and one cell to place
        gamePanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    int[] clickPosition= new int[2];
                    clickPosition[0] = e.getX() / 20;
                    clickPosition[1] = e.getY() / 20;
                    System.out.println(e.getX() / 20);
                    System.out.println(e.getY() / 20);
                    if(generation.getKill(player, clickPosition)){
                        // change color

                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
