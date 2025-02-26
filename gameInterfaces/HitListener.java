// 209522382 Yehonatan Hadar
package gameInterfaces;
import gameObjects.Block;
import gameObjects.Ball;
/**
 * The HitListener interface should be implemented by any class that
 * wants to be notified of hit events.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the Block that is being hit
     * @param hitter the Ball that is doing the hitting
     */
    //This method is called whenever the beingHit object is hit
    void hitEvent(Block beingHit, Ball hitter);
}
