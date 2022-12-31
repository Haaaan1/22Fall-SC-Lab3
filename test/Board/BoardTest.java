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

    @Test
    void findNeighboursTest(){
        Cell[][] cells = board.getAllCells();
        Cell upperLeft = cells[0][0];
        Cell upperRight = cells[0][29];
        Cell lowerLeft = cells[29][0];
        Cell lowerRight = cells[29][29];
        Cell upperEdge = cells[0][15];
        Cell lowerEdge = cells[29][15];
        Cell leftEdge = cells[15][0];
        Cell rightEdge = cells[29][15];
        Cell center = cells[15][15];

        assertEquals(3, board.findNeighbours(upperLeft).getNeighborNum());
        assertEquals(3, board.findNeighbours(upperRight).getNeighborNum());
        assertEquals(3, board.findNeighbours(lowerLeft).getNeighborNum());
        assertEquals(3, board.findNeighbours(lowerRight).getNeighborNum());
        assertEquals(5, board.findNeighbours(upperEdge).getNeighborNum());
        assertEquals(5, board.findNeighbours(lowerEdge).getNeighborNum());
        assertEquals(5, board.findNeighbours(leftEdge).getNeighborNum());
        assertEquals(5, board.findNeighbours(rightEdge).getNeighborNum());
        assertEquals(8, board.findNeighbours(center).getNeighborNum());
    }
}
