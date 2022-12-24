package Game;

import Board.Board;
import Player.*;
import GUI.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Player> players = new ArrayList<>();
    private final Board board;
    private final GUI gui;

    public Game(){
        board = new Board();
        gui = new GUI();
    }

    public void setUp(){
        // initialize player
        players.add(new Player(PlayerId.PLAYER_A));
        players.add(new Player(PlayerId.PLAYER_B));
    }

    public void play(){

    }


}
