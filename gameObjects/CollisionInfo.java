//Yehonatan Hadar
package gameObjects;
import geometryObjects.Point;
import gameInterfaces.Collidable;

/**
 * The CollisionInfo class represents information about a collision between
 * a point and a collidable object.
 */
public class CollisionInfo {

    // Fields
    private Point collisionPoint;
    private Collidable collidableObject;

    /**
     * Constructs a new CollisionInfo object with the specified collision point
     * and collidable object.
     *
     * @param collisionPoint the point at which the collision occurs.
     * @param collidable the collidable object involved in the collision.
     */
    //Constructor
    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collisionPoint = collisionPoint;
        this.collidableObject = collidable;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point.
     */
    // Return the point at which the collision occurs
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object.
     */
    // Return the collidable object involved in the collision.
    public Collidable collisionObject() {
        return this.collidableObject;
    }
}
