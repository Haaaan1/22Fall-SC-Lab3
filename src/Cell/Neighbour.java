package Cell;

import Board.Board;

import java.util.ArrayList;

public class Neighbour {
    private ArrayList<Cell> neighbours;

    public Neighbour(Cell cell){
        // Get all neighbours, ask board
        neighbours = Board.findNeighbours(cell);
    }

    public int getAliveNum(){
        int aliveNum = 0;
        for(int i = 0;i<neighbours.size();i++){
            if(neighbours.get(i).areYouAlive())
                aliveNum++;
        }
        return aliveNum;
    }

}
