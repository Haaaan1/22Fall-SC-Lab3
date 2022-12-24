package Cell;

import Player.PlayerId;

public class Cell {
    private Status status;
    private PlayerId owner;
    private Status nextStatus;
    private PlayerId nextOwner;
    private Neighbour neighbours;

    public Cell(){
        // aggregate all 8 neighbours
        this.neighbours = new Neighbour(this);
        // initial to be DEAD
        status = Status.DEAD;
    }

    // Ask neighbours how many are alive
    public int howManyAlive(){
        int aliveNum = neighbours.getAliveNum();
        return aliveNum;
    }

    // Ask cell itself whether it is alive
    public boolean areYouAlive(){
        if(status==Status.ALIVE)
            return true;
        else
            return false;
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

}
