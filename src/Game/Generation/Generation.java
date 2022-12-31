package Game.Generation;

import Board.Board;
import Cell.*;
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
//        int num = 0;
//        System.out.println("live num cells"+num);
//        while(board.iterator().hasNext()){
//            Cell currentCell = board.iterator().next();
//            System.out.println(currentCell.getOwner());
//            if(currentCell.getOwner()==id)
//                num++;
//        }
//
//
//        return num;
        int num = 0;
        Cell[][] c = board.getAllCells();
        for(int i=0; i<c[0].length; i++){
            for(int j=0; j<c[i].length; j++){
                if(c[i][j].getOwner()==id){
                    num++;
                }
            }
        }


        return num;
    }

    // Ask all Cells: How many alive neighbours?

    // Tell Cells: What to do Next?
    public void selfCheck(){
//        while(board.iterator().hasNext()){
//            Cell currentCell = board.iterator().next();
//            if(currentCell.howManyAlive()!=2){
//                if(currentCell.howManyAlive()==3){
//                    currentCell.prepareRelive(currentCell.getMaxOwner());
//                }else{
//                    currentCell.prepareSuicide();
//                }
//            }
//        }

        Cell[][] c = board.getAllCells();
        for(int i=0; i<c[0].length; i++){
            for(int j=0; j<c[i].length; j++){
                if((c[i][j].howManyAlive()==3)&&(c[i][j].getStatus()==Status.ALIVE)) {
                    c[i][j].prepareStay();
                }else if(c[i][j].howManyAlive()==3){
                    c[i][j].prepareRelive(c[i][j].getMaxOwner());
                }else if(c[i][j].howManyAlive()==2){
                    c[i][j].prepareStay();
                }
                else{
                    c[i][j].prepareSuicide();
                }

            }
        }

    }

    // Ask all Cells to execute
    public void executeAll(){
        selfCheck();
//        while(board.iterator().hasNext()){
//            board.iterator().next().execute();
//        }

        Cell[][] c = board.getAllCells();
        for(int i=0; i<c[0].length; i++){
            for(int j=0; j<c[i].length; j++){
                c[i][j].execute();
            }
        }
    }

    public void doKill(Cell cell){
        cell.suicide();
    }

    public void doRelive(Player player, Cell cell){
        cell.relive(player.getPlayerId());
    }


}
