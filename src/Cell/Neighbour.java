package Cell;

import Board.Board;

import java.util.ArrayList;

public class Neighbour {
    private ArrayList<Cell> neighbour;

    public Neighbour(Cell cell){
        // Get all neighbours, ask board
        this.neighbour = Board.findNeighbours(cell);
    }

}
