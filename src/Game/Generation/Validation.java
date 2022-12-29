package Game.Generation;

import Player.Player;
import Cell.Cell;

public class Validation {
    // Placement validation
    public boolean validatePlacememt(Cell cell){
        if(cell.getOwner()==null){
            return true;
        }

        return false;
    }

    // Kill validation
    public boolean validateKill(Player player, Cell cell){
        if(player.getPlayerId()==cell.getOwner()){
            return false;
        }

        if(cell.getOwner()==null){
            return false;
        }

        return true;
    }

}
