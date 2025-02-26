// 209522382 Yehonatan Hadar
package gameInterfaces;
import biuoop.DrawSurface;

/**
 * The Sprite interface represents a game object that can be drawn on a DrawSurface
 * and notified when time has passed in the game.
 */
public interface Sprite {

    /**
     * Draws the sprite on the provided DrawSurface.
     *
     * @param d the DrawSurface on which to draw the sprite.
     */
    //Draw the sprite to the screen
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that a unit of time has passed in the game.
     * This method is typically used to update the sprite's state or position.
     */
    //Notify the sprite that time has passed
    void timePassed();
}
