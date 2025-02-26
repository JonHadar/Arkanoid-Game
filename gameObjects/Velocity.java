// 209522382 Yehonatan Hadar
package gameObjects;
import geometryObjects.Point;

/**
 * The Velocity class specifies the change in position on the `x` and the `y` axis.
 */

public class Velocity {

    //Fields
    private double dx; //Direction X-axis
    private double dy; //Direction Y-axis

    /**
     * Constructor to initialize the velocity with given changes in x and y directions.
     *
     * @param dx the change in position along the x-axis
     * @param dy the change in position along the y-axis
     */

    //Constructor
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets the change in position along the x-axis.
     *
     * @return the change in position along the x-axis
     */

    public double getDx() {
        return this.dx;
    }

    /**
     * Gets the change in position along the y-axis.
     *
     * @return the change in position along the y-axis
     */

    public double getDy() {
        return this.dy;
    }

    /**
     * Takes a point with position (x,y) and returns a new point with position (x+dx, y+dy).
     *
     * @param p the original point
     * @return a new point with updated position
     */

    // Take a point with position (x,y) and return a new point with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.getDx(), p.getY() + this.getDy());
    }

    /**
     * Creates a velocity object from an angle and speed.
     *
     * @param angle the direction angle in degrees
     * @param speed the speed
     * @return a new Velocity object
     */

    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = speed * Math.sin(radians);  // X direction
        double dy = -speed * Math.cos(radians); // Y direction, negative for up
        return new Velocity(dx, dy);
    }

    /**
     * Calculates and returns the angle of the velocity.
     * The angle is measured in degrees.
     *
     * @return the angle of the velocity in degrees.
     */
    //Returns the angle that helps to calculate the velocity
    public double getAngleOfVelocity() {
        return Math.toDegrees(Math.atan2(this.dy, this.dx));
    }

    /**
     * Calculates and returns the speed of the velocity.
     * The speed is the magnitude of the velocity vector.
     *
     * @return the speed of the velocity.
     */
    //Returns the velocity's speed
    public double getSpeedOfVelocity() {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }
}