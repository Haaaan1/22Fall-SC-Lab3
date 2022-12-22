package Board;

import Cell.*;


import java.util.ArrayList;

public class Board {
    // 2-D array to keep all cells
    private Cell[][] cellList;
    private int length;
    private int width;

    // Use singleton design pattern
    public Board(){
        // Initial cellList

    }

    //
    public static ArrayList<Cell> findNeighbours(Cell cell){
        ArrayList<Cell> neighbours = new ArrayList<Cell>();
        // Find neighbours
        return neighbours;
    }

    // Return iterator
    public Iterable<Cell> getIterator(){
        return null;
    }

    // Take position([1,2]) from GUI, return reference of Cell
    public Cell getCell(int[] position){
        return null;
    }
}
