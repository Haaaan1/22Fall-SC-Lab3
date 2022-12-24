package Player;

/**
 * Represent a player in the game
 */
public class Player {
    private final PlayerId id;
    private int livingCellNum;

    public Player (PlayerId id) {
        this.id = id;
        this.livingCellNum = 0;
    }

    public PlayerId getPlayerId() {
        return id;
    }

    public int geLivingCellNum() {
        return livingCellNum;
    }

    public void modifyCellNum(int num){
        livingCellNum = num;
    }
}
