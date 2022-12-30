package GUI;

import javax.swing.*;

import Cell.Cell;
import Game.*;
import Player.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

import static javax.swing.JOptionPane.showMessageDialog;

import java.util.concurrent.TimeUnit;

public class GUI {
    private JLayeredPane gamePanel;
    private String player1Name;
    private String player2Name;
    private Color player1Color;
    private Color player2Color;
    private JLabel player1CellNum;
    private JLabel player2CellNum;
    private JLabel generationNum;
    private JLabel info;
    private String[] colorOption = {"black", "white", "red", "blue", "yellow"};
    private TurnType playerNextTurn;
    private TurnType startTurn;
    private TurnType endTurn;
    int[] clickPosition;
    Game game = Game.getInstance();
    TurnResult turnResult = TurnResult.getInstance();

    public String setPlayerName(PlayerId player) {
        String playerName = "";
        while (Objects.equals(playerName, "")) {
            JPanel panel = new JPanel();
            playerName = JOptionPane.showInputDialog(panel, player.toString() + "enter your username:", "Enter Username", 1);
            if (playerName == null) {
                System.exit(0);
            } else if (playerName.equals("")) {
                JPanel p = new JPanel();
                JOptionPane.showMessageDialog(p, "Player name can't be empty!", "Warn", 2);
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

    public void setUp() {
        gameIntro();
        setAllPlayersInfo();
        setUpGameWindow();
    }

    public void gameIntro() {
        JPanel p = new JPanel();
        String s = "Welcome to Conway’s Game of Life! Here are the rules:\n Each player enters their name and chooses their cell color, and each player\n" +
                " needs to kill one opponent's cell and generate one cell each turn. After a turn,\n" +
                " player must click the Next button. The player with no cells loses and the other\n" +
                " player wins.";
        JOptionPane.showMessageDialog(p, s, "Welcome!", 1);
    }

    public void setAllPlayersInfo() {
        player1Name = setPlayerName(PlayerId.PLAYER_A);
        String player1ColorStr = getCellColor(PlayerId.PLAYER_A, colorOption);
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
        String player2ColorStr = getCellColor(PlayerId.PLAYER_B, newColorOption);
        player2Color = setCellColor(player2ColorStr);
        playerNextTurn = startTurn(player1Name, player2Name);
    }

    public TurnType startTurn(String player1Name, String player2Name) {
        if (player1Name.compareTo(player2Name) <= 0) {
            startTurn = TurnType.PLAYER1_KILL_TURN;
            endTurn = TurnType.PLAYER2_RELIVE_TURN;
            return TurnType.PLAYER1_KILL_TURN;
        } else {
            startTurn = TurnType.PLAYER2_KILL_TURN;
            endTurn = TurnType.PLAYER1_RELIVE_TURN;
            return TurnType.PLAYER2_KILL_TURN;
        }

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
        JLabel headerLabel = new JLabel("", JLabel.CENTER);
        headerLabel.setBounds(250, 5, 200, 20);

        JFrame gameWindow = new JFrame("Group 18-Conway’s Game of Life");
        gameWindow.setBounds(200, 300, 820, 680);
        gameWindow.setBackground(Color.LIGHT_GRAY);
//        Container gamePanel = new Container();
        gamePanel = new JLayeredPane();
        GridLayout gridLayout = new GridLayout(30, 30, 1, 1);
        gamePanel.setLayout(gridLayout);
        gamePanel.setBounds(25, 25, 600, 600);
        Cell[][] initCells = game.setUp(player1Name, player2Name);
        initGamePanel(initCells);
        gamePanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    clickPosition = new int[2];
                    clickPosition[1] = e.getX() / 20;
                    clickPosition[0] = e.getY() / 20;
                    play();
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

        String n1 = player1Name;
        String n2 = player2Name;

        Font f = new Font(Font.DIALOG, Font.BOLD, 13);

        JPanel infoPanel = new JPanel(new GridLayout(13, 1));
        infoPanel.setSize(170, 600);
        JLabel player1 = new JLabel("Player1:", JLabel.LEFT);
        player1.setSize(150, 60);
        player1.setFont(f);
        JLabel player1Name = new JLabel(n1, JLabel.CENTER);
        player1Name.setSize(150, 60);
        JLabel player1Cell = new JLabel("Player1 Cells:", JLabel.LEFT);
        player1Cell.setFont(f);
        player1Cell.setSize(150, 60);
        player1CellNum = new JLabel("", JLabel.CENTER);
        player1CellNum.setSize(150, 60);
        JLabel player2 = new JLabel("Player2:", JLabel.LEFT);
        player2.setFont(f);
        player2.setSize(150, 60);
        JLabel player2Name = new JLabel(n2, JLabel.CENTER);
        player2Name.setSize(150, 60);
        JLabel player2Cell = new JLabel("Play2 Cells:", JLabel.LEFT);
        player2Cell.setFont(f);
        player2Cell.setSize(150, 60);
        player2CellNum = new JLabel("", JLabel.CENTER);
        player2CellNum.setSize(150, 60);
        JLabel generation = new JLabel("Generation:", JLabel.LEFT);
        generation.setFont(f);
        generation.setSize(150, 60);
        generationNum = new JLabel("", JLabel.CENTER);
        generationNum.setSize(150, 60);
        JLabel infoTitle = new JLabel("Current operation:", JLabel.LEFT);
        infoTitle.setFont(f);
        info = new JLabel(playerNextTurn.toString(), JLabel.CENTER);
        JButton button = new JButton("Next");
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (playerNextTurn == TurnType.NEXT_GENERATION) {
                        game.execute();
                        refreshGamePanel(game.getAllCells());
                        refreshInfoPanel();
                        playerNextTurn = startTurn;
                        info.setText(startTurn.toString());
                    } else {
                        JPanel p = new JPanel();
                        JOptionPane.showMessageDialog(p, "It's " + playerNextTurn.toString() + "Please click at the end of the turn.", "Hint", 1);
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
        infoPanel.add(infoTitle);
        infoPanel.add(info);
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
        infoPanel.add(button);
        infoPanel.setBounds(650, 25, 120, 600);
        refreshInfoPanel();

        gameWindow.add(gamePanel);
        gameWindow.add(infoPanel);
        gameWindow.add(headerLabel);
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void killCell(TurnType nextStep, PlayerId player) {
        game.doKill(player, clickPosition);
        playerNextTurn = nextStep;
    }

    public void reliveCell(TurnType nextStep, PlayerId player) {
        game.doRelive(player, clickPosition);
        playerNextTurn = nextStep;
    }

    public void initGamePanel(Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].getOwner() == PlayerId.PLAYER_A) {
                    JPanel gridPanel1 = new JPanel();
                    gridPanel1.setSize(20, 20);
                    gridPanel1.setBackground(player1Color);
                    gamePanel.add(gridPanel1);

                } else if (cells[i][j].getOwner() == PlayerId.PLAYER_B) {
                    JPanel gridPanel1 = new JPanel();
                    gridPanel1.setSize(20, 20);
                    gridPanel1.setBackground(player2Color);
                    gamePanel.add(gridPanel1);
                } else {
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
                Component c = gamePanel.getComponentAt(j * 20, i * 20);
                if (allCells[i][j].getOwner() == PlayerId.PLAYER_A) {
                    c.setBackground(player1Color);
                } else if (allCells[i][j].getOwner() == PlayerId.PLAYER_B) {
                    c.setBackground(player2Color);
                } else {
                    c.setBackground(Color.GRAY);
                }
            }
        }
    }

    public void refreshInfoPanel() {
        int turnNum = turnResult.getTurnNum();
        int player1Cells = turnResult.getLiveNumOfPlayer(PlayerId.PLAYER_A);
        int player2Cells = turnResult.getLiveNumOfPlayer(PlayerId.PLAYER_B);

        player1CellNum.setText(String.valueOf(player1Cells));
        player2CellNum.setText(String.valueOf(player2Cells));
        generationNum.setText(String.valueOf(turnNum));
    }

    public void endWithWinner() {
        // display winner and end game
        PlayerId player = game.getWinner();
        JPanel p = new JPanel();
        JOptionPane.showConfirmDialog(p, player.toString() + "win!", "Winner", 0);
    }

    public void play() {
        JPanel p = new JPanel();
        if (playerNextTurn == TurnType.PLAYER1_KILL_TURN) {
            if (game.getKill(PlayerId.PLAYER_A, clickPosition)) {
                killCell(TurnType.PLAYER1_RELIVE_TURN, PlayerId.PLAYER_A);
                info.setText(TurnType.PLAYER1_RELIVE_TURN.toString());
            } else {
                JOptionPane.showMessageDialog(p, playerNextTurn.toString() + "Please click right cell!", "Warning", 2);
            }
        } else if (playerNextTurn == TurnType.PLAYER1_RELIVE_TURN) {
            if (game.getRelive(PlayerId.PLAYER_A, clickPosition)) {
                if (endTurn == TurnType.PLAYER1_RELIVE_TURN) {
                    reliveCell(TurnType.NEXT_GENERATION, PlayerId.PLAYER_A);
                    info.setText(TurnType.NEXT_GENERATION.toString());
                } else {
                    reliveCell(TurnType.PLAYER2_KILL_TURN, PlayerId.PLAYER_A);
                    info.setText(TurnType.PLAYER2_KILL_TURN.toString());
                }
            } else {
                JOptionPane.showMessageDialog(p, playerNextTurn.toString() + "Please click right cell!", "Warning", 2);
            }
        } else if (playerNextTurn == TurnType.PLAYER2_KILL_TURN) {
            if (game.getKill(PlayerId.PLAYER_B, clickPosition)) {
                killCell(TurnType.PLAYER2_RELIVE_TURN, PlayerId.PLAYER_B);
                info.setText(TurnType.PLAYER2_RELIVE_TURN.toString());
            } else {
                JOptionPane.showMessageDialog(p, playerNextTurn.toString() + "Please click right cell!", "Warning", 2);
            }
        } else if (playerNextTurn == TurnType.PLAYER2_RELIVE_TURN) {
            if (game.getRelive(PlayerId.PLAYER_B, clickPosition)) {
                if (endTurn == TurnType.PLAYER2_RELIVE_TURN) {
                    reliveCell(TurnType.NEXT_GENERATION, PlayerId.PLAYER_B);
                    info.setText(TurnType.NEXT_GENERATION.toString());
                } else {
                    reliveCell(TurnType.PLAYER1_KILL_TURN, PlayerId.PLAYER_B);
                    info.setText(TurnType.PLAYER1_KILL_TURN.toString());
                }
            } else {
                JOptionPane.showMessageDialog(p, playerNextTurn.toString() + "Please click right cell!", "Warning", 2);
            }
        } else {
            JPanel p1 = new JPanel();
            JOptionPane.showMessageDialog(p1, "Please click Next button!", "Hint", 1);
        }
        refreshGamePanel(game.getAllCells());
        refreshInfoPanel();

        if (game.judgeWinner()) {
            endWithWinner();
            System.exit(0);
        }
    }

}
