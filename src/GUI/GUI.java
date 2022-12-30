package GUI;

import javax.swing.*;

import Cell.Cell;
import Game.*;
import Player.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class GUI {
    private JLayeredPane gamePanel;
    private String player1Name;
    private String player2Name;
    private Color player1Color;
    private String player1ColorStr;
    private Color player2Color;
    private String player2ColorStr;
    private JLabel player1CellNum;
    private JLabel player2CellNum;
    private JLabel generationNum;
    private String[] colorOption = {"black", "white", "red", "blue", "yellow"};
    private TurnType playerNextTurn = TurnType.PLAYER1_KILL_TURN;
    int[] clickPosition;
    Game game = Game.getInstance();
    TurnResult turnResult = TurnResult.getInstance();

    public String setPlayerName(PlayerId player) {
        String playerName = "";
        while (Objects.equals(playerName, "")) {
            JPanel panel = new JPanel();
            playerName = JOptionPane.showInputDialog(panel, player.toString() + "enter your username:", "Enter Username", 1);
            System.out.println(playerName);
            if (playerName == null) {
                System.exit(0);
            }
        }

        return playerName;
    }

    public String getCellColor(PlayerId player, String[] colorOption) {
        JPanel panel = new JPanel();
        String playerColorStr = (String) JOptionPane.showInputDialog(panel, player.toString() + "select cell's color", "Select Cell's Color", 1, null, colorOption, colorOption[0]);
        if (playerColorStr == null) {
            System.exit(0);
        }
        return playerColorStr;
    }

    public Color setCellColor(String cellColor) {
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

    public String getPlayerName(PlayerId player) {
        if (player == PlayerId.PLAYER_A) {
            return player1Name;
        } else {
            return player2Name;
        }
    }

    public void setUpGameWindow() {
//        System.out.println(player1Name);
        System.out.println("Start setup");
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
        gamePanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    clickPosition = new int[2];
                    clickPosition[1] = e.getX() / 20;
                    clickPosition[0] = e.getY() / 20;
                    System.out.println(e.getX() / 20);
                    System.out.println(e.getY() / 20);

                    while (!game.judgeWinner()){

                        while (clickPosition==null){
                            clickPosition=getClickPosition();
                        }
                        System.out.println("Start Turn");
                        killCell(clickPosition, TurnType.PLAYER1_KILL_TURN, TurnType.PLAYER1_RELIVE_TURN, PlayerId.PLAYER_A);
                        System.out.println("Execute");
                        game.execute();
                        refreshGamePanel(game.getAllCells());
                        refreshInfoPanel();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

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

//        container.add(gamePanel);

//        for (int i = 0; i < 900; i++) {
//            JPanel gridPanel1 = new JPanel();
//            gridPanel1.setSize(20, 20);
//            gridPanel1.setBackground(Color.GRAY);
//            gamePanel.add(gridPanel1);
//        }
        Cell[][] initCells = game.setUp(player1Name,player2Name);
        initGamePanel(initCells);

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
        player1CellNum = new JLabel("", JLabel.CENTER);
        player1CellNum.setSize(150, 60);
        JLabel player2 = new JLabel("Player2:", JLabel.LEFT);
        player2.setSize(150, 60);
        JLabel player2Name = new JLabel(n2, JLabel.CENTER);
        player2Name.setSize(150, 60);
        JLabel player2Cell = new JLabel("Play2 Cell:", JLabel.LEFT);
        player2Cell.setSize(150, 60);
        player2CellNum = new JLabel("", JLabel.CENTER);
        player2CellNum.setSize(150, 60);
        JLabel generation = new JLabel("Generation:", JLabel.LEFT);
        generation.setSize(150, 60);
        generationNum = new JLabel("", JLabel.CENTER);
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

//        Component c = gamePanel.getComponentAt(40, 65);
//        System.out.println(c);
//        if (c instanceof JPanel) {
//            c.setBackground(Color.BLACK);
//        }

        gameWindow.add(gamePanel);
        gameWindow.add(infoPanel);
        gameWindow.add(headerLabel);
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void killCell(int[] position, TurnType thisStep, TurnType nextStep, PlayerId player) {
        System.out.println("Have fun kill");
        while (!game.getKill(player, position)) {
            System.out.println("oops");
            playerNextTurn = thisStep;
            clickPosition = new int[2];
        }
        System.out.println("Ready to kill cell");
        game.doKill(player, position);
        System.out.println("Do to kill cell");
        clickPosition = new int[2];
        Component c = gamePanel.getComponentAt(position[0] * 20, position[1] * 20);
        System.out.println(c);
        if (c instanceof JPanel) {
            c.setBackground(Color.GRAY);
        }
        playerNextTurn = nextStep;
    }

    public void reliveCell(int[] position, TurnType thisStep, TurnType nextStep, PlayerId player) {
        while (!game.getRelive(player, position)) {
            playerNextTurn = thisStep;
            clickPosition = new int[2];
        }
        game.doRelive(player, position);
        clickPosition = new int[2];
        Component c = gamePanel.getComponentAt(position[0] * 20, position[1] * 20);
        System.out.println(c);
        if (c instanceof JPanel) {
            if (player == PlayerId.PLAYER_A) {
                c.setBackground(player1Color);
            } else {
                c.setBackground(player2Color);
            }
        }
        playerNextTurn = nextStep;
    }

    public void initGamePanel(Cell[][] cells){
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].getOwner() == PlayerId.PLAYER_A) {
                    JPanel gridPanel1 = new JPanel();
                    gridPanel1.setSize(20, 20);
                    gridPanel1.setBackground(player1Color);
                    gamePanel.add(gridPanel1);

                } else if (cells[i][j].getOwner() == PlayerId.PLAYER_B){
                    JPanel gridPanel1 = new JPanel();
                    gridPanel1.setSize(20, 20);
                    gridPanel1.setBackground(player2Color);
                    gamePanel.add(gridPanel1);
                }else {
                    JPanel gridPanel1 = new JPanel();
                    gridPanel1.setSize(20, 20);
                    gridPanel1.setBackground(Color.GRAY);
                    gamePanel.add(gridPanel1);
                }
            }
        }
    }

    public void refreshGamePanel(Cell[][] allCells) {
        for (int i = 0; i < allCells.length; i++) {
            for (int j = 0; j < allCells[i].length; j++) {
                Component c = gamePanel.getComponentAt(i * 20, j * 20);
                if (allCells[i][j].getOwner() == PlayerId.PLAYER_A) {
                    c.setBackground(player1Color);
                } else if (allCells[i][j].getOwner() == PlayerId.PLAYER_B){
                    c.setBackground(player2Color);
                }else {
                    c.setBackground(Color.GRAY);
                }
            }
        }
    }

    public void refreshInfoPanel(){
        int turnNum = turnResult.getTurnNum();
        int player1Cells = turnResult.getLiveNumOfPlayer(PlayerId.PLAYER_A);
        int player2Cells = turnResult.getLiveNumOfPlayer(PlayerId.PLAYER_B);

        player1CellNum.setText(String.valueOf(player1Cells));
        player2CellNum.setText(String.valueOf(player2Cells));
        generationNum.setText(String.valueOf(turnNum));
    }

//    public static void main(String[] args) {
//        GUI gui = new GUI();
//        gui.setAllPlayersInfo();
//        gui.setUpGameWindow();
//    }


    public void endWithWinner() {
        // display winner and end game
        PlayerId player = game.getWinner();
        JPanel p = new JPanel();
        JOptionPane.showConfirmDialog(p,player.toString()+"win!","Winner",0);
    }


    public int[] getClickPosition(){
        return clickPosition;
    }

    public void play() {
        System.out.println("Start play");
         //Start player's turn and let player pick one cell to kill and one cell to place

        System.out.println("go!");
        while (!game.judgeWinner()){

            while (clickPosition==null){
                clickPosition=getClickPosition();
            }
            System.out.println("Start Turn");
            killCell(clickPosition, TurnType.PLAYER1_KILL_TURN, TurnType.PLAYER1_RELIVE_TURN, PlayerId.PLAYER_A);
            reliveCell(clickPosition, TurnType.PLAYER1_RELIVE_TURN, TurnType.PLAYER2_KILL_TURN, PlayerId.PLAYER_A);
            killCell(clickPosition, TurnType.PLAYER2_KILL_TURN, TurnType.PLAYER2_RELIVE_TURN, PlayerId.PLAYER_B);
            reliveCell(clickPosition, TurnType.PLAYER2_RELIVE_TURN, TurnType.PLAYER1_KILL_TURN, PlayerId.PLAYER_B);
            System.out.println("Execute");
            game.execute();
            refreshGamePanel(game.getAllCells());
            refreshInfoPanel();
        }
        System.out.println("end!");
        endWithWinner();
    }

}
