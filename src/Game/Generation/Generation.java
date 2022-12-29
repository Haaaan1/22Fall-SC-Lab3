package Game.Generation;

import Cell.Cell;
import Player.Player;

import java.util.ArrayList;

public class Generation {

    // Ask board to have an Iterator

    // Ask all Cells: Are you alive? Return all cells
    public ArrayList<Cell> getAllCells(){
        return null;
    }

    // Ask all Cells: How many alive neighbours?

    // Tell Cells: What to do Next?

    // Ask all Cells to execute
    public void executeAll(){

    }

    public void doKill(Cell cell){
        cell.prepareSuicide();
    }

    public void doRelive(Player player, Cell cell){
        cell.prepareRelive(player.getPlayerId());
    }


}
