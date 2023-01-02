package Game;
import Board.Board;
import Cell.Cell;
import Game.Generation.Generation;
import Player.Player;
import Player.PlayerId;
import GUI.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class GameTest {
    Board board = Board.getInstance();
    Generation generation = new Generation(board);
    GUI ui = new GUI();
    Game game = Game.getInstance();

    @Test
    void gamesTest(){
        ui.setUp();
        ui.setUpGameWindow();
        //ui.play();
    }
    @Test
    void getPlayerTest(){
        game.setUp("A","B");
        assertEquals("A",game.getPlayer(PlayerId.PLAYER_A).getPlayerName());
        assertEquals("B",game.getPlayer(PlayerId.PLAYER_B).getPlayerName());
    }
}
