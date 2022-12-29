package Player;


import java.awt.*;

/**
 * Represent a player in the game
 */
public class Player {
    private final PlayerId id;
    private int livingCellNum;
    private String name;
    //private String playerName;

    public Player (PlayerId id,String name) {
        this.id = id;
        this.name = name;
//        this.livingCellNum = 0;
        this.livingCellNum = 10;
    }

    public PlayerId getPlayerId() {
        return id;
    }

    public int getLivingCellNum() {
        return livingCellNum;
    }

    public void modifyCellNum(int num){
        livingCellNum = num;
    }

}
