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
    private ArrayList<Cell> neighbourCellList;

    public Cell(Board board){
        // aggregate all 8 neighbours
        //this.neighbour = neighbour;
        neighbour = board.findNeighbours(this);
        // initial to be DEAD
        status = Status.DEAD;
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

    // Get color according to the playerId
    public Color getColor(){
        if(owner == PlayerId.PLAYER_A)
            return Color.BLUE;
        else if (owner == PlayerId.PLAYER_B)
            return Color.RED;
        else
            return Color.GRAY;
    }

    public PlayerId getOwner(){
        return owner;
    }

}
