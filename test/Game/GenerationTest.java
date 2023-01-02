package Game;

import Board.Board;
import Cell.Cell;
import Game.Generation.Generation;
import Player.PlayerId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GenerationTest {
    Board board = Board.getInstance();
    Generation generation = new Generation(board);

    @Test
    void getLiveNumOdCellsTest(){
        Cell[][] cells = board.initializeBoard();
        generation.executeAll();
        assertEquals(4,generation.getLiveNumOfCells(PlayerId.PLAYER_A));
        assertEquals(4,generation.getLiveNumOfCells(PlayerId.PLAYER_B));
    }
}
