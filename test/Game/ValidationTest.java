package Game;

import Board.Board;
import Cell.Cell;
import Game.Generation.Validation;
import Player.Player;
import Player.PlayerId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidationTest {
    Board board = Board.getInstance();
    Validation validation = new Validation();
    Cell diedCell = new Cell(board);
    Cell player1Cell = new Cell(board);
    Cell player2Cell = new Cell(board);
    Player player1 = new Player(PlayerId.PLAYER_A,"");
    Player player2 = new Player(PlayerId.PLAYER_B,"");

    @BeforeEach
    void init(){
        player1Cell.setOwner(PlayerId.PLAYER_A);
        player2Cell.setOwner(PlayerId.PLAYER_B);
    }

    @Test
    void validatePlacementTest(){
        assertFalse(validation.validatePlacement(player1Cell));
        assertFalse(validation.validatePlacement(player2Cell));
        assertTrue(validation.validatePlacement(diedCell));
    }

    @Test
    void validateKillTest(){
        assertTrue(validation.validateKill(player1,player2Cell));
        assertTrue(validation.validateKill(player2,player1Cell));
        assertFalse(validation.validateKill(player2,diedCell));
        assertFalse(validation.validateKill(player1,player1Cell));
        assertFalse(validation.validateKill(player2,player2Cell));
    }
}
