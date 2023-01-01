package Game.Generation;

import Board.Board;
import Cell.*;
import Player.*;

import java.util.ArrayList;
import java.util.Iterator;

public class Generation {

    // Ask board to have an Iterator
    private Board board;

    public Generation(Board board) {
        this.board = board;
    }

    // Ask all Cells: Are you alive? Return all cells
    public int getLiveNumOfCells(PlayerId id) {
        int num = 0;
        Iterator<Cell> iterator = board.iterator();
        while(iterator.hasNext()){
            Cell currentCell = iterator.next();
            // System.out.println("generation: "+currentCell.getOwner());
            if(currentCell.getOwner()==id)
                num++;
        }

        return num;
    }

    // Ask all Cells: How many alive neighbours?

    // Tell Cells: What to do Next?
    public void selfCheck() {
        Iterator<Cell> iterator = board.iterator();
        while(iterator.hasNext()){
            Cell currentCell = iterator.next();
            if ((currentCell.howManyAlive() == 3) && (currentCell.getStatus() == Status.ALIVE)) {
                currentCell.prepareStay();
            } else if (currentCell.howManyAlive() == 3) {
                currentCell.prepareRelive(currentCell.getMaxOwner());
            } else if (currentCell.howManyAlive() == 2) {
                currentCell.prepareStay();
            } else {
                currentCell.prepareSuicide();
            }
        }

    }

    // Ask all Cells to execute
    public void executeAll() {
        selfCheck();
        Iterator<Cell> iterator = board.iterator();
        while(iterator.hasNext()){
            iterator.next().execute();
        }

    }

    public void doKill(Cell cell) {
        cell.suicide();
    }

    public void doRelive(Player player, Cell cell) {
        cell.relive(player.getPlayerId());
    }


}
