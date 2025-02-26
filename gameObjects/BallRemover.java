// 209522382 Yehonatan Hadar
package gameObjects;
import gameInterfaces.HitListener;
import gameSetup.Game;

/**
 * A BallRemover is in charge of removing balls from the game,
 * as well as keeping count of the number of balls that remain.
 * It implements the HitListener interface.
 */
public class BallRemover implements HitListener {
    //Fields
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructs a BallRemover object.
     *
     * @param game the game from which balls will be removed
     * @param remainingBalls the counter keeping track of the remaining balls
     */
    //Constructor
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    // Balls that are hit the "death region" should be removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Remove the ball from the game
        hitter.removeFromGame(this.game);
        // Decrease the counter for the remaining blocks
        this.remainingBalls.decrease(1);
        // Remove this listener from the ball that is being removed
        if (this.remainingBalls.getValue() == 0) {
            beingHit.removeHitListener(this);
        }
    }
}
