package Cell;

import Player.PlayerId;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Cell {
    private Status status;
    private PlayerId owner;
    private Status nextStatus;
    private PlayerId nextOwner;
    private Neighbour neighbours;

    public Cell(){
        // aggregate all 8 neighbours
        this.neighbours = new Neighbour(this);
    }

    // Ask neighbours how many are alive
    public int howManyAlive(){

        return 0;
    }

    // Ask cell itself whether it is alive
    public boolean areYouAlive(){

        return false;
    }

    // Suicide at the end of Turn
    public void prepareSuicide(){
        nextStatus = Status.DEAD;
    }

    // Relive at the end of Turn
    public void prepareRelive(){

    }

    // Execute relive or suicide
    public void execute(){
        status = nextStatus;
        owner = nextOwner;
    }

}
