package Cell;

import Board.Board;
import Player.PlayerId;

import java.util.ArrayList;

public class Cell {
    private Status status;
    private PlayerId owner;
    private Status nextStatus;
    private PlayerId nextOwner;
    private Neighbour neighbour;
    private Board board;
    private ArrayList<Cell> neighbourCellList;

    public Cell(Board board){
        // aggregate all 8 neighbours
        //this.neighbour = neighbour;
        this.board = board;
        // initial to be DEAD
        status = Status.DEAD;
    }

    public void setNeighbour(){
        neighbour = board.findNeighbours(this);
    }

    // Ask neighbours how many are alive
    public int howManyAlive(){
        int aliveNum = neighbour.getAliveNum();
        return aliveNum;
    }

    // Ask cell itself whether it is alive
    public boolean areYouAlive(){
        return status==Status.ALIVE;
    }

    public void suicide(){
        status = Status.DEAD;
        owner = null;
    }
    public void relive(PlayerId ownerId){
        status = Status.ALIVE;
        owner = ownerId;
    }

    // Suicide at the end of Turn
    public void prepareSuicide(){
        nextStatus = Status.DEAD;
        nextOwner = null;
    }

    // Relive at the end of Turn
    public void prepareRelive(PlayerId ownerId){
        nextStatus = Status.ALIVE;
        nextOwner = ownerId;
    }

    // Execute relive or suicide
    public void execute(){
        status = nextStatus;
        owner = nextOwner;
    }

    public void setOwner(PlayerId ownerId){
        owner = ownerId;
    }

    public void setStatus(Status status){
        this.status = status;
    }

//    // Get color according to the playerId
//    public Color getColor(){
//        if(owner == PlayerId.PLAYER_A)
//            return Color.BLUE;
//        else if (owner == PlayerId.PLAYER_B)
//            return Color.RED;
//        else
//            return Color.GRAY;
//    }

    public PlayerId getOwner(){
        return owner;
    }

    public PlayerId getMaxOwner(){
        return neighbour.getMaxOwner();
    }

}
