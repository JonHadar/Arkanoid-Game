// 209522382 Yehonatan Hadar
package gameSetup;
import gameInterfaces.Collidable;
import gameObjects.CollisionInfo;
import gameObjects.ConstantVariables;
import geometryObjects.Point;
import geometryObjects.Rectangle;
import geometryObjects.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * The GameEnvironment class represents the environment in which the game objects interact.
 * It manages a collection of collidable objects and determines the closest collision that can occur.
 */
public class GameEnvironment {

    //Fields
    private List<Collidable> collidables;

    /**
     * Constructs a new GameEnvironment instance.
     * Initializes an empty list of collidable objects.
     */
    //Constructor
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds the given collidable object to the environment.
     *
     * @param c the collidable object to add
     */
    //Adding new collidable
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Removes a collidable object from the game.
     *
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Determines the closest collision that is going to occur given an object's trajectory.
     * Assumes an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, returns null.
     * Otherwise, returns the information about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory of the moving object
     * @return the information about the closest collision, or null if no collision occurs
     */
    //Return the collision info of the closet collision that occurs with the ball
    public CollisionInfo getClosestCollision(Line trajectory) {
        // Helps determine if there are collisions with objects
        int emptyFlag = 0;

        Point closestPoint = trajectory.closestIntersectionToStartOfLine(collidables.get(0).getCollisionRectangle());
        double minDistance = trajectory.getStart().distance(closestPoint);
        Collidable collidable = collidables.get(0);

        //Pass on all the collidables and find the closet collision
        for (Collidable theCollidable : collidables) {
            if (trajectory.closestIntersectionToStartOfLine(theCollidable.getCollisionRectangle()) == null) {
                continue;
            }
            Rectangle collisionRectangle = theCollidable.getCollisionRectangle();
            Point currentClosest = trajectory.closestIntersectionToStartOfLine(collisionRectangle);
            double currentDistance = trajectory.getStart().distance(currentClosest);
            //There is collision
            emptyFlag = 1;

            //Check if the current collision is closer than the existing one
            if (ConstantVariables.compareDoubles(currentDistance, minDistance) == -1) {
                minDistance = currentDistance;
                closestPoint = currentClosest;
                collidable = theCollidable;
            }
        }

        //Checks if there was collision
        if (emptyFlag == 0) {
            return null;
        }

        //Return the closet collision info
        return new CollisionInfo(closestPoint, collidable);
    }
}
