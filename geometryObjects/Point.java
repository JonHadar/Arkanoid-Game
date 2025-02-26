//Yehonatan Hadar
package geometryObjects;
import gameObjects.ConstantVariables;
/**
 * Represents a point in 2D space with x and y coordinates.
 */
public class Point {

    //Fields
    private double x; // X-coordinate of the point
    private double y; // Y-coordinate of the point

    /**
     * Constructs a point with the specified x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    // Constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates and returns the distance between this point and another point.
     *
     * @param other the other point
     * @return the distance between this point and the other point
     */

    //Return the distance of this point to the other point
    public double distance(Point other) {
        if (other == null) {
            return Double.MAX_VALUE;
        }

        return Math.sqrt((other.getX() - this.getX()) * (other.getX() - this.getX())
                + ((other.getY() - this.getY()) * (other.getY() - this.getY())));
    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param other the other point
     * @return true if the points are equal (i.e., have the same x and y coordinates), false otherwise
     */
    //Return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        return ConstantVariables.compareDoubles(this.getX(), other.getX()) == 0
                && ConstantVariables.compareDoubles(this.getY(), other.getY()) == 0;
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    // Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }
}
