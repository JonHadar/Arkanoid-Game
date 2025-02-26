// 209522382 Yehonatan Hadar
package geometryObjects;
import java.util.ArrayList;
import java.util.List;

/**
 * The Rectangle class represents a rectangle in a 2D space defined by an upper-left point,
 * width, and height.
 */
public class Rectangle {

    // Fields
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructs a new rectangle with the specified upper-left point, width, and height.
     *
     * @param upperLeft the upper-left point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    //Constructor
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    //Return the width of the rectangle
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle.
     */
    //Return the height of the rectangle
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */
    //Return the upper left point of the rectangle
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the upper line of the rectangle.
     *
     * @return the upper line of the rectangle.
     */
    //Return the upper line of the rectangle
    public Line getLine1() {
        return new Line(this.upperLeft, new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY()));
    }

    /**
     * Returns the bottom line of the rectangle.
     *
     * @return the bottom line of the rectangle.
     */
    //Return the bottom line of the rectangle
    public Line getLine2() {
        return new Line(new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height),
                new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height));
    }

    /**
     * Returns the left line of the rectangle.
     *
     * @return the left line of the rectangle.
     */
    //Return the left line of the rectangle
    public Line getLine3() {
        return new Line(this.upperLeft, new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height));
    }

    /**
     * Returns the right line of the rectangle.
     *
     * @return the right line of the rectangle.
     */
    //Return the right line of the rectangle
    public Line getLine4() {
        return new Line(new Point(this.upperLeft.getX() + this.width, upperLeft.getY()),
                new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height));
    }

    /**
     * Returns a list of intersection points between this rectangle and the specified line.
     * If there are no intersection points, returns an empty list.
     *
     * @param line the line to check for intersection points with this rectangle.
     * @return a list of intersection points between this rectangle and the specified line.
     */
    //Return a (possibly empty) List of intersection points with the specified line.
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();

        //Checks if there is intersection with line 1 of the rectangle
        if (this.getLine1().isIntersecting(line) && this.getLine1().intersectionWith(line) != null) {
            intersectionPoints.add(this.getLine1().intersectionWith(line));
        }

        //Checks if there is intersection with line 2 of the rectangle
        if (this.getLine2().isIntersecting(line) && this.getLine2().intersectionWith(line) != null) {
            intersectionPoints.add(this.getLine2().intersectionWith(line));
        }

        //Checks if there is intersection with line 3 of the rectangle
        if (this.getLine3().isIntersecting(line) && this.getLine3().intersectionWith(line) != null) {
            intersectionPoints.add(this.getLine3().intersectionWith(line));
        }

        //Checks if there is intersection with line 4 of the rectangle
        if (this.getLine4().isIntersecting(line) && this.getLine4().intersectionWith(line) != null) {
            intersectionPoints.add(this.getLine4().intersectionWith(line));
        }

        //Return the list of the intersection points with the rectangle's lines
        return intersectionPoints;
    }
}