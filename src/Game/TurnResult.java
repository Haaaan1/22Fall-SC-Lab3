package Game;

import Cell.Cell;
import Player.*;

import java.util.ArrayList;

public class TurnResult {
    private int turnNum = 0;

    // Calculate live cells of each player
    public int getLiveNumOfPlayer(PlayerId id, ArrayList<Cell> liveCellList){
        int num = 0;
        for(Cell cell : liveCellList){
             if(cell.getOwner()==id)
                 num++;
         }
        return num;
    }
    // Display turn numbers
    public int getTurnNum(){
        turnNum++;
        return turnNum;
    }

}
