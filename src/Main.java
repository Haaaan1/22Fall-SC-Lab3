import GUI.GUI;
import Game.Game;

public class Main {
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setUpGameWindow();
        System.out.println("Continue to play");
        gui.play();
    }
}
