//Yehonatan Hadar
import gameSetup.Game;

import java.util.Stack;

/**
 * The Ass3Game class is the entry point of the game.
 * It initializes and starts the game.
 */
public class Ass5Game {
    /**
     * The main method initializes and runs the game.
     *
     * @param args command-line arguments (not used)
     */
    //Main class of the game
    public static void main(String[] args) {
        Game theGame = new Game();
        theGame.initialize();
        theGame.run();
    }
}
