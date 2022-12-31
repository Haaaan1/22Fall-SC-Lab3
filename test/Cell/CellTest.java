package Cell;
import Board.*;
import Player.PlayerId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CellTest {
    Board board;
    Neighbour neighbour;
    Cell[][] cells;
    @BeforeEach
    void init(){
        this.board = Board.getInstance();
        cells = board.initializeBoard();
    }

    @Test
    void howManyAliveTest() {
        Cell testCell = cells[10][10];
        testCell.setNeighbour();
        assertEquals(3,testCell.howManyAlive());
    }

    @Test
    void areYouAliveTest(){
        Cell cell = new Cell(board);
        cell.setNeighbour();
        assertEquals(false,cell.areYouAlive());
    }

    @Test
    void reliveTest(){
        Cell cell = new Cell(board);
        cell.setNeighbour();
        cell.relive(PlayerId.PLAYER_A);
        assertEquals(true,cell.areYouAlive());
        assertEquals(PlayerId.PLAYER_A,cell.getOwner());
    }

    @Test
    void suicideTest(){
        Cell cell = new Cell(board);
        cell.setNeighbour();
        cell.setStatus(Status.ALIVE);
        cell.suicide();
        assertEquals(false,cell.areYouAlive());
    }
    @Test
    void prepareSuicideTest(){
        Cell cell = new Cell(board);
        cell.setNeighbour();
        cell.relive(PlayerId.PLAYER_A);
        cell.prepareSuicide();
        cell.execute();
        assertEquals(false,cell.areYouAlive());
    }

    @Test
    void prepareReliveTest(){
        Cell cell = new Cell(board);
        cell.setNeighbour();
        cell.prepareRelive(PlayerId.PLAYER_A);
        cell.execute();
        assertEquals(true,cell.areYouAlive());
        assertEquals(PlayerId.PLAYER_A,cell.getOwner());
    }

    @Test
    void prepareStayTest(){
        Cell cell = new Cell(board);
        cell.setNeighbour();
        cell.prepareStay();
        cell.execute();
        assertEquals(false,cell.areYouAlive());
    }

    @Test
    void setTest(){
        Cell cell = new Cell(board);
        cell.setNeighbour();
        cell.setOwner(PlayerId.PLAYER_A);
        cell.setStatus(Status.ALIVE);
        assertEquals(true,cell.areYouAlive());
        assertEquals(PlayerId.PLAYER_A,cell.getOwner());
    }

    @Test
    void getMaxOwnerTest(){
        Cell cell = cells[10][10];
        cell.setNeighbour();
        assertEquals(PlayerId.PLAYER_A,cell.getMaxOwner());
        Cell cell2 = cells[10][20];
        cell2.setNeighbour();
        assertEquals(PlayerId.PLAYER_B,cell2.getMaxOwner());
    }

}
