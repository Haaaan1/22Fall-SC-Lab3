package Game;

import Board.Board;
import Game.Generation.Generation;
import Player.*;
import GUI.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Player> players = new ArrayList<>();
    private final Board board;
    private final GUI ui;
    private final TurnResult turnResult;
    private final Generation generation;

    public Game(){
        board = new Board();
        ui = new GUI();
        turnResult = new TurnResult();
        generation = new Generation();
    }

    public void setUp(){
        // initialize player
        players.add(new Player(PlayerId.PLAYER_A));
        players.add(new Player(PlayerId.PLAYER_B));
        
        // initialize GUI
        ui.setUpGameWindow();
    }

    public void play(){
        while (!judgeWinner()){
            for(Player player : players){
                ui.startTurnOf(player);
            }
            // After each player's turn, ask generation to execute all cells' move
            generation.executeAll();
        }
        // Game ends with winner
        getAndDeclareWinner();
    }

    // Judge whether there is a winner.
    // If someone wins, return true.
    private boolean judgeWinner() {
        for (Player player : players) {
            if (player.getLivingCellNum() == 0) return true;
        }
        return false;
    }

    private void getAndDeclareWinner() {
        final var winners = players.stream().filter(p -> p.getLivingCellNum() != 0).toList();
        ui.endWithWinner(winners.get(0));
    }

}
