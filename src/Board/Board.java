package Board;

import Cell.*;
import Game.Generation.Generation;
import Game.Generation.Validation;
import Player.Player;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.Random;

public class Board implements Iterable<Cell>{
    // 2-D array to keep all cells
    private Cell[][] cells;
    private Validation validation;
    private Generation generation;
    public final static int LENGTH=30;
    public final static int WIDTH=30;
    private static Board INSTANCE;  //Singleton design pattern

    // Use singleton design pattern
    private Board(){
        // Initial cells
        cells = new Cell[LENGTH][WIDTH];
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                cells[i][j] = new Cell(this);
            }
        }

        validation = new Validation();
        generation=new Generation();
    }


    /**
     * Implementation of singleton design pattern
     *
     * @return
     */
    public static synchronized Board getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Board();
        }
        return INSTANCE;
    }


    //
    public Neighbour findNeighbours(Cell cell){
        ArrayList<Cell> neighbours = new ArrayList<Cell>();
        for(int i=0; i<LENGTH; i++){
            for(int j=0; j<WIDTH; j++){
                if(cell==cells[i][j]){
                    // Four corners-----------------------------------------------------------
                    if((i==0)&&(j==0)){ //upper left corner
                        neighbours.add(cells[i][j+1]);
                        neighbours.add(cells[i+1][j]);
                        neighbours.add(cells[i+1][j+1]);
                    }else if((i==0)&&(j==WIDTH-1)) { //upper right corner
                        neighbours.add(cells[i+1][j]);
                        neighbours.add(cells[i][j-1]);
                        neighbours.add(cells[i+1][j-1]);
                    }else if((i==LENGTH-1)&&(j==0)){ //lower left corner
                        neighbours.add(cells[i][j+1]);
                        neighbours.add(cells[i-1][j]);
                        neighbours.add(cells[i-1][j+1]);
                    }else if((i==LENGTH-1)&&(j==WIDTH-1)){ //lower right corner
                        neighbours.add(cells[i][j-1]);
                        neighbours.add(cells[i-1][j]);
                        neighbours.add(cells[i-1][j-1]);
                    }
                    // Four edges -----------------------------------------------------------------
                    else if((i==0)&&(j!=0)&&(j!=WIDTH-1)){ //upper edge
                        neighbours.add(cells[i][j-1]);
                        neighbours.add(cells[i][j+1]);
                        neighbours.add(cells[i+1][j+1]);
                        neighbours.add(cells[i+1][j]);
                        neighbours.add(cells[i+1][j-1]);
                    }else if((i==LENGTH-1)&&(j!=0)&&(j!=WIDTH-1)){ //lower edge
                        neighbours.add(cells[i][j-1]);
                        neighbours.add(cells[i][j+1]);
                        neighbours.add(cells[i-1][j+1]);
                        neighbours.add(cells[i-1][j]);
                        neighbours.add(cells[i-1][j-1]);
                    }else if((j==0)&&(i!=0)&&(i!=LENGTH-1)){ //left edge
                        neighbours.add(cells[i+1][j]);
                        neighbours.add(cells[i-1][j]);
                        neighbours.add(cells[i+1][j+1]);
                        neighbours.add(cells[i][j+1]);
                        neighbours.add(cells[i-1][j+1]);
                    }else if((j==WIDTH-1)&&(i!=0)&&(i!=LENGTH-1)){ //right edge
                        neighbours.add(cells[i+1][j]);
                        neighbours.add(cells[i-1][j]);
                        neighbours.add(cells[i+1][j-1]);
                        neighbours.add(cells[i][j-1]);
                        neighbours.add(cells[i-1][j-1]);
                    }
                    // All others----------------------------------------------------------------
                    else{
                        neighbours.add(cells[i+1][j]);
                        neighbours.add(cells[i+1][j-1]);
                        neighbours.add(cells[i+1][j+1]);
                        neighbours.add(cells[i][j-1]);
                        neighbours.add(cells[i][j+1]);
                        neighbours.add(cells[i-1][j]);
                        neighbours.add(cells[i-1][j-1]);
                        neighbours.add(cells[i-1][j+1]);
                    }
                }
            }
        }

        Neighbour neighbour = new Neighbour(neighbours);
        // Find neighbours
        return neighbour;
    }


    /**
     * Iterator design pattern
     * @return
     */
    @Override
    public Iterator<Cell> iterator(){
        return new Iterator<Cell>() {
            private int i = 0;
            private int j=0;

            public boolean hasNext() {
                return i < cells.length && j < cells[i].length;
            }

            public Cell next() {
                Cell value = cells[i][j];
                j++;
                if (j >= cells[i].length) {
                    i++;
                    j = 0;
                }
                return value;
            }
        };
    }

    // Take position([1,2]) from GUI, return reference of Cell
    private Cell getCell(int[] position){
        return cells[position[0]][position[1]];
    }

    public boolean ifKill(Player player, int[] position){
        return validation.validateKill(player,getCell(position));
    }

    public boolean ifRelive(int[] position){
        return validation.validatePlacememt(getCell(position));
    }

    public void doKill(int[] position){
        generation.doKill(getCell(position));
    }

    public void doRelive(Player player, int[] position){
        generation.doRelive(player, getCell(position));
    }

    public Cell[][] getAllCells(){
        return cells;
    }

    public Cell[][] initializeBoard(){
        // Create a new Random object
        Random rand = new Random();

        // Set the size of the array
        int size = 10;

        // Set the range for the random numbers
        int min = 0;
        int max = 30;

        // Create an array to store the random numbers
        int[] randomArray = new int[size];

        // Generate the random numbers and store them in the array
        for (int i = 0; i < size; i++) {
            randomArray[i] = rand.nextInt((max - min) + 1) + min;
        }

        return null;
    }

}
