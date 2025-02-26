//Yehonatan Hadar
package gameObjects;
import gameInterfaces.Collidable;
import gameInterfaces.Sprite;
import geometryObjects.Rectangle;
import geometryObjects.Point;
import gameSetup.Game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * The Paddle class represents the paddle in the game.
 * It implements the Sprite and Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {

    //Fields
    private KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;
    private int speed;
    private int gameScreenWidth;
    private static final int NUM_OF_REGIONS = 5;
    private static final int FRAME_BLOCKS_WIDTH = 25;

    /**
     * Constructs a Paddle with the specified keyboard sensor, rectangle, speed, and color.
     *
     * @param keyboard    the keyboard sensor
     * @param rectangle   the rectangle representing the paddle's shape and position
     * @param paddleSpeed the speed of the paddle
     * @param color       the color of the paddle
     */
    //Constructor
    public Paddle(KeyboardSensor keyboard, Rectangle rectangle, int paddleSpeed, Color color) {
        this.keyboard = keyboard;
        this.rectangle = rectangle;
        this.color = color;
        this.speed = paddleSpeed;
    }

    /**
     * Sets the width of the game screen.
     *
     * @param gameScreenWidth the width of the game screen
     */
    //Sets the width of the game screen
    public void setGameScreenWidth(int gameScreenWidth) {
        this.gameScreenWidth = gameScreenWidth;
    }

    /**
     * Moves the paddle to the left by its speed.
     * If the paddle reaches the left edge of the screen, it wraps around to the right edge.
     */
    //Move the paddle left
    public void moveLeft() {
        double newX = this.rectangle.getUpperLeft().getX() - this.speed;

        if (newX < FRAME_BLOCKS_WIDTH) {
            newX = this.gameScreenWidth - this.rectangle.getWidth();
        }

        this.rectangle = new Rectangle(new Point(newX, this.rectangle.getUpperLeft().getY()),
                this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    /**
     * Moves the paddle to the right by its speed.
     * If the paddle reaches the right edge of the screen, it wraps around to the left edge.
     */
    //Move the paddle right
    public void moveRight() {
        double newX = this.rectangle.getUpperLeft().getX() + this.speed;

        if (newX + this.rectangle.getWidth() > this.gameScreenWidth) {
            newX = FRAME_BLOCKS_WIDTH;
        }

        this.rectangle = new Rectangle(new Point(newX, this.rectangle.getUpperLeft().getY()),
                this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    /**
     * Updates the paddle's position based on the keyboard input.
     * This method is called to notify the paddle that time has passed.
     */
    //Notify the paddle that the time has passed and moves it according to the user's keyboard key pressing
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }

        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param d the DrawSurface to draw the paddle on
     */
    @Override
    //Draw the paddle in the game
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());

        d.setColor(Color.BLACK); // Line color
        d.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
    }

    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return the collision rectangle of the paddle
     */
    @Override
    //Return the shape of the paddle - rectangle
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }


    /**
     * Handles the ball hitting the paddle.
     * Determines the new velocity of the ball based on the collision point.
     *
     * @param collisionPoint  the point where the collision occurred
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity of the ball after the collision
     */

    @Override
    //Return the velocity of the ball after hits the paddle
    public Velocity hit(Ball check, Point collisionPoint, Velocity currentVelocity) {
        // Calculate the region where the collision occurred
        double regionWidth = this.rectangle.getWidth() / NUM_OF_REGIONS;
        double regionCollisionPointX = collisionPoint.getX() - this.rectangle.getUpperLeft().getX();
        // Determine which region the collision occurred in
        int region = (int) (regionCollisionPointX / regionWidth) + 1;

        //Checks if the ball hits the sides of the paddle and update the velocity
        if ((collisionPoint.getY() >= this.rectangle.getUpperLeft().getY())
                && (collisionPoint.getY() <= this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight())) {
            if (Math.abs(this.rectangle.getUpperLeft().getX() - collisionPoint.getX())
                    < ConstantVariables.EPSILON) { // Left
                return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            } else if (Math.abs(collisionPoint.getX() - (this.rectangle.getUpperLeft().getX()
                    + this.rectangle.getWidth())) < ConstantVariables.EPSILON) { // Right
                return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            }
        }

        //Change the angle of the ball's direction according to hit region on the paddle
        double angle;
        switch (region) {
            case 1:
                angle = 300;
                break;
            case 2:
                angle = 330;
                break;
            case 3:
                angle = currentVelocity.getAngleOfVelocity();
                if (currentVelocity.getDy() > 0) {
                    currentVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                }
                break;
            case 4:
                angle = 30;
                break;
            case 5:
                angle = 60;
                break;
            default:
                angle = currentVelocity.getAngleOfVelocity();
                break;
        }

        //Updating the velocity
        return Velocity.fromAngleAndSpeed(angle, currentVelocity.getSpeedOfVelocity());
    }

    /**
     * Adds this paddle to the game.
     *
     * @param g the game to add the paddle to
     */
    //Adding the paddle to the game
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
