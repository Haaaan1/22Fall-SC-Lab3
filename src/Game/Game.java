package Game;

import Board.Board;
import Game.Generation.Generation;
import Player.*;
import GUI.*;
import Cell.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Player> players = new ArrayList<>();
    private final Board board;
    private final Generation generation;
    private static Game INSTANCE;


    private Game(){
        board = Board.getInstance();
        generation = new Generation(board);
    }

    public static synchronized Game getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Game();
        }
        return INSTANCE;
    }
    public Cell[][] setUp(String nameA,String nameB){
        // initialize player
        //ui.setUpGameWindow();
        players.add(new Player(PlayerId.PLAYER_A,nameA));
        players.add(new Player(PlayerId.PLAYER_B,nameB));
        return board.initializeBoard();
        // initialize GUI

    }

    public void execute(){
        generation.executeAll();
    }

    // Judge whether there is a winner.
    // If someone wins, return true.
    public boolean judgeWinner() {
        for (Player player : players) {
            if (generation.getLiveNumOfCells(player.getPlayerId()) == 0)
                return true;// we have a winner
        }
        return false;//no winner
    }

    public PlayerId getWinner() {
        final var winners = players.stream().filter(p -> generation.getLiveNumOfCells(p.getPlayerId()) != 0).toList();
        return winners.get(0).getPlayerId();
    }

    public boolean getKill(PlayerId playerId, int[] position){
        
        return board.ifKill(getPlayer(playerId),position);
    }

    public boolean getRelive(PlayerId playerId, int[] position){
        
        return board.ifRelive(position);
    }

    public void doKill(PlayerId playerId, int[] position){

        board.doKill(position);
    }

    public void doRelive(PlayerId playerId, int[] position){

        board.doRelive(getPlayer(playerId),position);
    }

    public Cell[][] getAllCells(){
        return board.getAllCells();
    }
    
    public Player getPlayer(PlayerId playerId){
        if(playerId==PlayerId.PLAYER_A)
            return players.get(0);
        else
            return players.get(1);
    }

}
