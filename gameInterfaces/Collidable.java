//Yehonatan Hadar
package gameInterfaces;
import geometryObjects.Rectangle;
import geometryObjects.Point;
import gameObjects.Velocity;
import gameObjects.Ball;


/**
 * The Collidable interface represents an object that can participate in collisions.
 * It provides methods to retrieve its collision shape and to handle collisions.
 */
public interface Collidable {

    /**
     * Returns the collision shape (usually a rectangle) of the object.
     *
     * @return the collision shape (rectangle) of the object.
     */

    // Return the "collision shape" of the object
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred at a specific collision point
     * with a given velocity. Calculates and returns the new velocity of the object
     * after the collision.
     *
     * @param collisionPoint  the point where the collision occurred.
     * @param currentVelocity the current velocity of the colliding object.
     * @param hitter          the ball that involve the hit
     * @return the new velocity expected after the hit, based on the force inflicted by the colliding object.
     */
    //Return the new velocity expected after the hit (based on the force the object inflicted on us)
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
