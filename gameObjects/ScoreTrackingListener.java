// 209522382 Yehonatan Hadar
package gameObjects;
import gameInterfaces.HitListener;

/**
 * The ScoreTrackingListener class implements the HitListener interface to track score changes
 * when a block is hit by a ball.
 */
public class ScoreTrackingListener implements HitListener {
    //Fields
    private Counter currentScore;

    /**
     * Constructor for ScoreTrackingListener.
     *
     * @param scoreCounter the Counter object representing the current game score
     */
    //Constructor
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    //Should increase the score in 5 points when block is hit
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        this.currentScore.increase(5);
    }
}
