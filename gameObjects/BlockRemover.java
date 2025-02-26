//Yehonatan Hadar
package gameObjects;
import gameSetup.Game;
import gameInterfaces.HitListener;
/**
 * A BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    //Fields
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructs a BlockRemover.
     *
     * @param game the game from which blocks will be removed
     * @param remainingBlocks a Counter for tracking the number of remaining blocks
     */
    //Constructor
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    // Blocks that are hit should be removed from the game
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Remove the block from the game
        beingHit.removeFromGame(this.game);
        // Decrease the counter for the remaining blocks
        this.remainingBlocks.decrease(1);
        // Remove this listener from the block that is being removed
        beingHit.removeHitListener(this);
    }
}
