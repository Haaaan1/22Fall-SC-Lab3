package Board;
import Cell.*;
import Player.Player;
import Player.PlayerId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    Board board;
    Neighbour neighbour;
    @BeforeEach
    void init(){
        this.board = Board.getInstance();
    }

    @Test
    void initializeTest() {
        Cell[][] cells = board.initializeBoard();
        int[] pos= new int[]{10,10};
        Player player = new Player(PlayerId.PLAYER_B,"Test");
        //testCell.setNeighbour();
        assertEquals(true,board.ifKill(player,pos));
    }

}
