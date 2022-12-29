package Game.Generation;

import Board.Board;
import Cell.Cell;
import Player.Player;

import java.util.ArrayList;

public class Generation {

    // Ask board to have an Iterator
    private Board board;
    public Generation(Board board){
        this.board=board;
    }

    // Ask all Cells: Are you alive? Return all cells
    public ArrayList<Cell> getAllCells(){
        return null;
    }

    // Ask all Cells: How many alive neighbours?

    // Tell Cells: What to do Next?

    // Ask all Cells to execute
    public void executeAll(){
        while(board.iterator().hasNext()){
            board.iterator().next().execute();
        }
    }

    public void doKill(Cell cell){
        cell.prepareSuicide();
    }

    public void doRelive(Player player, Cell cell){
        cell.prepareRelive(player.getPlayerId());
    }


}
