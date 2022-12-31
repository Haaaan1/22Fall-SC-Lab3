package Game;

import Board.Board;
import Cell.Cell;
import Game.Generation.Generation;
import Player.*;
import Board.*;

import java.util.ArrayList;

public class TurnResult {
    private static TurnResult INSTANCE;
    private final Board board;
    private Generation generation;
    private int turnNum = 1;
    private TurnResult(){
        board = Board.getInstance();
        generation = new Generation(board);
    }
    public static synchronized TurnResult getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TurnResult();
        }
        return INSTANCE;
    }

    // Calculate live cells of each player
    public int getLiveNumOfPlayer(PlayerId id){
        int num;
        num = generation.getLiveNumOfCells(id);
        return num;
    }
    // Display turn numbers
    public int getTurnNum(){
        //turnNum++;
        return turnNum;
    }

    public void turnPlus(){
        turnNum++;
    }

}
