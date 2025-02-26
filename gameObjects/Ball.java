//Yehonatan Hadar
package gameObjects;
import geometryObjects.Point;
import geometryObjects.Line;
import gameInterfaces.Sprite;
import gameSetup.GameEnvironment;
import gameInterfaces.Collidable;
import gameSetup.Game;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The Ball class represents a ball with a center point, radius, color, velocity, and screen boundaries.
 */

public class Ball implements Sprite {

    //Fields
    private Point center;
    private int radius;
    private Color color;
    private Velocity v;
    private GameEnvironment game;


    /**
     * Constructor to initialize the ball with center point, radius, and color.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     * @param game   the environment of the game
     */
    // Constructor
    public Ball(Point center, int r, Color color, GameEnvironment game) {
        this.center = new Point(center.getX(), center.getY());
        this.radius = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.game = game;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */

    //Return the color of the ball
    public Color getColor() {
        return color;
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface to draw the ball on
     */

    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        Ball ball = new Ball(this.center, this.radius, this.color, this.game);
        surface.setColor(ball.getColor());
        surface.fillCircle((int) ball.center.getX(), (int) ball.center.getY(), ball.radius);
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the new velocity
     */

    //Sets the velocity to the ball
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Moves the ball one step, considering collisions.
     * Computes the ball's trajectory and checks if it will hit any collidable objects.
     * If a collision is detected, the ball's position is adjusted and its velocity is updated.
     */
    public void newMoveOneStep() {
        //Compute the ball trajectory
        Point endP = this.v.applyToPoint(this.center);
        Line trajectory = new Line(this.center, endP);

        CollisionInfo collisionInfo = this.game.getClosestCollision(trajectory);
        // Check if moving on this trajectory will hit anything
        if (collisionInfo == null) {
            this.center = endP;
        } else {
            Point collisionP = collisionInfo.collisionPoint();
            Collidable collidable = collisionInfo.collisionObject();

            double moveBack = 0.2;
            double newCenterX = collisionP.getX() - this.v.getDx() * moveBack;
            double newCenterY = collisionP.getY() - this.v.getDy() * moveBack;

            //Change the ball center position according to the collision location
            if (collisionP.getX() == collidable.getCollisionRectangle().getUpperLeft().getX()) {
                newCenterX -= 1;
            }

            if (collisionP.getX() == collidable.getCollisionRectangle().getUpperLeft().getX()
                    + collidable.getCollisionRectangle().getWidth()) {
                newCenterX += 1;
            }

            if (collisionP.getY() == collidable.getCollisionRectangle().getUpperLeft().getY()) {
                newCenterY -= 1;
            }

            if (collisionP.getY() == collidable.getCollisionRectangle().getUpperLeft().getY()
                    + collidable.getCollisionRectangle().getHeight()) {
                newCenterY += 1;
            }

            //Updating the ball's center location
            this.center = new Point(newCenterX, newCenterY);
            this.v = collidable.hit(this, collisionP, this.v);
        }
    }

    /**
     * Updates the ball's position by moving it one step.
     * This method is called to notify the ball that time has passed.
     */
    //Notify the ball that time has passed and activate the move one-step method
    public void timePassed() {
        this.newMoveOneStep();
    }

    /**
     * Adds the ball to the given game.
     * The ball is added to the game's environment and sprite collection.
     *
     * @param g the game to add the ball to
     */
    //Adding the ball to the game
    public void addToGame(Game g) {
        this.game = g.getEnvironment();
        g.addSprite(this);
    }

    /**
     * Removes this object from the specified game.
     *
     * @param game the Game object from which this object will be removed
     */
    //Removes this object from the specified game.
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }

    /**
     * Sets the color of the object.
     *
     * @param color the new color to set
     */
    //Sets the color of the object.
    public void setColor(Color color) {
        this.color = color;
    }
}




