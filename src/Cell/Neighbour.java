package Cell;

import Board.Board;
import Player.PlayerId;

import java.util.ArrayList;

public class Neighbour {
    private ArrayList<Cell> neighbours;

    public Neighbour(ArrayList<Cell> neighbours){
        this.neighbours = neighbours;
    }

    public int getAliveNum(){
        int aliveNum = 0;
        for(int i = 0;i<neighbours.size();i++){
            if(neighbours.get(i).areYouAlive())
                aliveNum++;
        }
        return aliveNum;
    }

    public PlayerId getMaxOwner(){
        int numA=0;
        int numB=0;
        for(int i = 0;i<neighbours.size();i++){
            if(neighbours.get(i).areYouAlive())
                if(neighbours.get(i).getOwner()==PlayerId.PLAYER_A)
                    numA++;
                else
                    numB++;
        }
        if(numA>numB)
            return PlayerId.PLAYER_A;
        else
            return PlayerId.PLAYER_B;
    }


}
