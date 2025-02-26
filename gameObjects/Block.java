// 209522382 Yehonatan Hadar
package gameObjects;
import gameInterfaces.Collidable;
import gameInterfaces.Sprite;
import gameInterfaces.HitNotifier;
import gameInterfaces.HitListener;
import geometryObjects.Rectangle;
import geometryObjects.Point;
import gameSetup.Game;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * The Block class represents a block object in the game.
 * It implements the Collidable and Sprite interfaces.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    //Fields
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructs a Block with the specified position, width, height, and color.
     *
     * @param p      the upper-left corner of the block
     * @param width  the width of the block
     * @param height the height of the block
     * @param color  the color of the block
     */
    //Constructor
    public Block(Point p, double width, double height, Color color) {
        this.color = color;
        this.rectangle = new Rectangle(p, width, height);
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Returns the "collision shape" of the object.
     *
     * @return the rectangle representing the block's collision shape
     */
    @Override
    //Return the block that the ball collide with
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Notifies the block that a collision occurred at the specified collision point,
     * and updates the velocity accordingly.
     *
     * @param collisionPoint  the point at which the collision occurred
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity of the ball after the collision
     */

    @Override
    //Return the velocity of the ball after hits the block
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double veclocityDx = currentVelocity.getDx();
        double veclocityDy = currentVelocity.getDy();

        //Check if the ball hits the block from below or above, So it will change the vertical direction
        if (ConstantVariables.compareDoubles(collisionPoint.getX(), this.rectangle.getUpperLeft().getX()) == 0
                || ConstantVariables.compareDoubles(collisionPoint.getX(),
                this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth()) == 0) {
            veclocityDx = -veclocityDx;
        }

        //Check if the ball hits the block from the sides, So it will change the horizontal direction
        if (ConstantVariables.compareDoubles(collisionPoint.getY(), this.rectangle.getUpperLeft().getY()) == 0
                || ConstantVariables.compareDoubles(collisionPoint.getY(),
                this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight()) == 0) {
            veclocityDy = -veclocityDy;
        }

        if (!this.ballColorMatch(hitter)) {
            if (this.color != Color.GRAY) {
                hitter.setColor(this.color);
            }
            this.notifyHit(hitter);
        }

        //Updating the ball velocity
        return new Velocity(veclocityDx, veclocityDy);
    }

    /**
     * Adds the block to the specified game as both a sprite and a collidable.
     *
     * @param g the game to which the block should be added
     */
    //Adding the block to the game
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Draws the block on the given DrawSurface.
     *
     * @param surface the surface on which to draw the block
     */
    //Draws the block on the game's screen
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());

        surface.setColor(Color.BLACK); // Line color
        surface.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
    }

    /**
     * Notifies the block that time has passed.
     * Currently, this method does not perform any actions.
     */
    //Will define later
    public void timePassed() {

    }

    /**
     * Checks if the color of the given ball matches the color of this object.
     *
     * @param ball the ball whose color is to be checked
     * @return true if the color of the ball matches the color of this object, false otherwise
     */
    //Checks if the color of the given ball matches the color of this object.
    public boolean ballColorMatch(Ball ball) {
        return ball.getColor() == this.color;
    }

    /**
     * Removes this object from the game.
     *
     * @param game the game from which this object should be removed
     */
    //Removes this object from the game.
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Notifies all registered HitListeners about a hit event.
     *
     * @param hitter the Ball that caused the hit
     */
    //Notifies all registered HitListeners about a hit event.
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    // Add hl as a listener to hit events.
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    // Remove hl from the list of listeners to hit events.
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}


