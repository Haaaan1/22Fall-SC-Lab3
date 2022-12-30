package Game.Generation;

import Board.Board;
import Cell.Cell;
import Player.*;

import java.util.ArrayList;

public class Generation {

    // Ask board to have an Iterator
    private Board board;
    public Generation(Board board){
        this.board=board;
    }

    // Ask all Cells: Are you alive? Return all cells
    public int getLiveNumOfCells(PlayerId id){
        int num = 0;
        // System.out.println("live num cells"+num);
        while(board.iterator().hasNext()){
            if(board.iterator().next().getOwner()==id)
                num++;
        }


        return num;
    }

    // Ask all Cells: How many alive neighbours?

    // Tell Cells: What to do Next?
    public void selfCheck(){
        while(board.iterator().hasNext()){
            Cell currentCell = board.iterator().next();
            if(currentCell.howManyAlive()!=2){
                if(currentCell.howManyAlive()==3){
                    currentCell.prepareRelive(currentCell.getMaxOwner());
                }else{
                    currentCell.prepareSuicide();
                }
            }
        }
    }

    // Ask all Cells to execute
    public void executeAll(){
        selfCheck();
        while(board.iterator().hasNext()){
            board.iterator().next().execute();
        }
    }

    public void doKill(Cell cell){
        cell.suicide();
    }

    public void doRelive(Player player, Cell cell){
        cell.relive(player.getPlayerId());
    }


}
