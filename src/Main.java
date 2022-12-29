import GUI.GUI;
import Game.Game;

public class Main {
    public static void main(String[] args) {
        GUI gui = new GUI();
        Game game = new Game();
        gui.setUpGameWindow();
        game.setUp();
        game.play();
    }
}
