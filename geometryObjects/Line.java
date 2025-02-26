// 209522382 Yehonatan Hadar

package geometryObjects;
import gameObjects.ConstantVariables;
import java.util.List;

/**
 * Represents a line segment between two points in 2D space.
 */

public class Line {

    //Fields
    private Point start; // Starting point of the line
    private Point end; // Ending point of the line

    /**
     * Constructs a line segment with the specified starting and ending points.
     *
     * @param start the starting point of the line
     * @param end   the ending point of the line
     */

    // Constructor that accepts two Point objects
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a line segment with the specified coordinates of starting and ending points.
     *
     * @param x1 the x-coordinate of the starting point
     * @param y1 the y-coordinate of the starting point
     * @param x2 the x-coordinate of the ending point
     * @param y2 the y-coordinate of the ending point
     */

    // Constructor that accepts coordinates of two points
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Calculates and returns the length of the line segment.
     *
     * @return the length of the line segment
     */

    // Return the length of the line
    public double length() {
        return this.getStart().distance(this.getEnd());
    }

    /**
     * Returns the starting point of the line segment.
     *
     * @return the starting point of the line segment
     */

    // Returns the start point of the line
    public Point getStart() {
        return this.start;
    }

    /**
     * Returns the ending point of the line segment.
     *
     * @return the ending point of the line segment
     */

    // Returns the end point of the line
    public Point getEnd() {
        return this.end;
    }

    /**
     * Determines if three points are collinear.
     *
     * @param a the first point
     * @param b the second point
     * @param c the third point
     * @return 0 if the points are collinear, 1 if they are clockwise, 2 if they are counterclockwise
     */

    //Check if three points are collinear
    public static int isCollinear(Point a, Point b, Point c) {
        double orientationPoint = ((b.getY() - a.getY()) * (c.getX() - b.getX()))
                - ((b.getX() - a.getX()) * (c.getY() - b.getY()));

        if (ConstantVariables.compareDoubles(orientationPoint, 0.0) == 0) {
            return 0; // are collinear
        }

        if (ConstantVariables.compareDoubles(orientationPoint, 0.0) == 1) {
            return 1;
        }

        return 2; // Same orientation or not
    }

    /**
     * Checks if two line segments intersect by examining the orientations of the points.
     *
     * @param p1 the starting point of the first line segment
     * @param q1 the ending point of the first line segment
     * @param p2 the starting point of the second line segment
     * @param q2 the ending point of the second line segment
     * @return true if the line segments intersect, false otherwise
     */

    //This method check if two line segments intersect by examining the orientations of the points
    public boolean checkIntersect(Point p1, Point q1, Point p2, Point q2) {
        Line one = new Line(p1, q1);
        Line two = new Line(p1, q1);
        Line three = new Line(p2, q2);
        Line four = new Line(p2, q2);

        int orientation1 = isCollinear(p1, q1, p2);
        int orientation2 = isCollinear(p1, q1, q2);
        int orientation3 = isCollinear(p2, q2, p1);
        int orientation4 = isCollinear(p2, q2, q1);

        if ((orientation1 != orientation2) && (orientation3 != orientation4)) {
            return true;
        }

        // p1, q1, and p2 are collinear and p2 lies on segment p1q1
        if (orientation1 == 0 && one.isPointOnLine(p2)) {
            return true;
        }

        // p1, q1, and q2 are collinear and q2 lies on segment p1q1
        if (orientation2 == 0 && two.isPointOnLine(q2)) {
            return true;
        }

        // p2, q2, and p1 are collinear and p1 lies on segment p2q2
        if (orientation3 == 0 && three.isPointOnLine(p1)) {
            return true;
        }

        // p2, q2, and q1 are collinear and q1 lies on segment p2q2
        return orientation4 == 0 && four.isPointOnLine(q1);
    }

    /**
     * Returns the slope of the line.
     *
     * @return the slope of the line
     * @throws ArithmeticException if the slope is undefined (vertical line)
     */

    //Return the slope of the line
    public double lineSlope() {
        if (ConstantVariables.compareDoubles(this.getStart().getX(), this.getEnd().getX()) == 0) {
            return Double.POSITIVE_INFINITY;
        } else {
            return ((this.getStart().getY() - this.getEnd().getY()) / (this.getStart().getX() - this.getEnd().getX()));
        }
    }

    /**
     * Checks if a point lies on this line segment.
     *
     * @param p the point to check
     * @return true if the point lies on the line segment, false otherwise
     */

    //Checks if point p is on this line
    public boolean isPointOnLine(Point p) {
        Line current = new Line(this.start, this.end);
        Point cp1 = current.getStart();
        Point cp2 = current.getEnd();

        if (ConstantVariables.compareDoubles(p.distance(cp1) + p.distance(cp2), current.length()) == 0) {
            return ((ConstantVariables.compareDoubles(cp1.getX(), p.getX()) == -1
                    && ConstantVariables.compareDoubles(p.getX(), cp2.getX()) == -1)
                    && (ConstantVariables.compareDoubles(cp1.getY(), p.getY()) == -1
                    && ConstantVariables.compareDoubles(p.getY(), cp2.getY()) == -1)
                    || (ConstantVariables.compareDoubles(cp2.getX(), p.getX()) == -1
                    && ConstantVariables.compareDoubles(p.getX(), cp1.getX()) == -1)
                    && (ConstantVariables.compareDoubles(cp2.getY(), p.getY()) == -1
                    && ConstantVariables.compareDoubles(p.getY(), cp1.getY()) == -1)
                    || (ConstantVariables.compareDoubles(cp1.getX(), p.getX()) == 0
                    && (ConstantVariables.compareDoubles(cp1.getY(), p.getY()) == -1
                    && ConstantVariables.compareDoubles(p.getY(), cp2.getY()) == -1)
                    || (ConstantVariables.compareDoubles(cp1.getY(), p.getY()) == 0)
                    && ConstantVariables.compareDoubles(cp1.getX(), p.getX()) == -1
                    && ConstantVariables.compareDoubles(p.getX(), cp2.getX()) == -1));
        }
        return false;
    }

    /**
     * Checks if any point of another line segment lies on this line segment.
     *
     * @param other the other line segment
     * @return true if any point of the other line segment lies on this line segment, false otherwise
     */

    //Checks if line other is located on this line
    public boolean isOnOtherLine(Line other) {
        if (other == null) {
            return false;
        }

        Line current = new Line(this.start, this.end);

        return ConstantVariables.compareDoubles(current.lineSlope(), other.lineSlope()) == 0
                && (current.isPointOnLine(other.getStart()) || current.isPointOnLine(other.getEnd()));
    }

    /**
     * Checks if both points of another line segment lie on this line segment.
     *
     * @param other the other line segment
     * @return true if both points of the other line segment lie on this line segment, false otherwise
     */

    //Checks if line other is located in this line
    public boolean isInOtherLine(Line other) {
        if (other == null) {
            return false;
        }

        Line current = new Line(this.start, this.end);

        return (current.isPointOnLine(other.getStart()) && current.isPointOnLine(other.getEnd()))
                || ((current.getStart().equals(other.getStart())) && (current.getEnd().equals(other.getEnd())))
                || ((current.getStart().equals(other.getEnd())) && (current.getEnd().equals(other.getStart())))
                || (other.isPointOnLine(current.getStart()) && other.isPointOnLine(current.getEnd()));
    }

    /**
     * Checks if this line segment intersects with another line segment.
     *
     * @param other the other line segment
     * @return true if this line segment intersects with the other line segment, false otherwise
     */

    //Checks if line other is intersecting with this line
    public boolean isIntersecting(Line other) {
        Line current = new Line(this.start, this.end);

        if (checkIntersect(current.getStart(), current.getEnd(), other.getStart(), other.getEnd())) {
            return true;
        }

        return current.getStart().equals(other.getStart()) || current.getStart().equals(other.getEnd())
                || current.getEnd().equals(other.getStart())
                || current.getEnd().equals(other.getEnd());
    }

    /**
     * Calculates the intersection point of this line segment with another line segment.
     *
     * @param other the other line segment
     * @return the intersection point if the line segments intersect, null otherwise
     */

    //Returns the intersection point if the lines intersect, and null otherwise.
    public Point intersectionWith(Line other) {
        if (other == null) {
            return null;
        }

        Line current = new Line(this.getStart(), this.getEnd());

        if (current.isInOtherLine(other) || current.isOnOtherLine(other) || !current.isIntersecting(other)) {
            return null;
        } else {
            if (current.getStart().equals(other.getStart()) || current.getStart().equals(other.getEnd())) {
                return current.getStart();
            }

            //Checks if the current line's end point is equal to the start one of the other line
            if (current.getEnd().equals(other.getStart())) {
                return current.getEnd();
            }

            //Checks if the start point of the other line is on the current line
            if (current.isPointOnLine(other.getStart())) {
                return other.getStart();
            }

            //Checks if the end point of the other line is on the current line
            if (current.isPointOnLine(other.getEnd())) {
                return other.getEnd();
            }

            //Checks if the start point of the current line is on the other line
            if (other.isPointOnLine(current.getStart())) {
                return current.getStart();
            }

            //Checks if the end point of the current line is on the other line
            if (other.isPointOnLine(current.getEnd())) {
                return current.getEnd();
            }

            //Edge case and calculate the intersection point
            double pX1 = current.getStart().getX(), pY1 = current.getStart().getY(), pX2 = current.getEnd().getX(),
                    pY2 = current.getEnd().getY();
            double qX1 = other.getStart().getX(), qY1 = other.getStart().getY(), qX2 = other.getEnd().getX(),
                    qY2 = other.getEnd().getY();

            double denominator = (pX1 - pX2) * (qY1 - qY2) - (pY1 - pY2) * (qX1 - qX2);

            double intersectPx = ((pX1 * pY2 - pY1 * pX2) * (qX1 - qX2) - (pX1 - pX2) * (qX1 * qY2 - qY1 * qX2))
                    / denominator;
            double intersectPy = ((pX1 * pY2 - pY1 * pX2) * (qY1 - qY2) - (pY1 - pY2) * (qX1 * qY2 - qY1 * qX2))
                    / denominator;

            if ((ConstantVariables.compareDoubles(pX1, pX2) == 0 && ConstantVariables.compareDoubles(qY1, qY2) == 0)
                    || (ConstantVariables.compareDoubles(qX1, qX2) == 0
                    && ConstantVariables.compareDoubles(pY1, pY2) == 0)) {
                return new Point(intersectPx, intersectPy);
            }

            if (current.isIntersecting(other)) {
                return new Point(intersectPx, intersectPy);
            }

            //Calculate the slope of the lines
            double slope1 = current.lineSlope();
            double slope2 = other.lineSlope();

            //Calculate the intersection value of the line with the y-axis
            double b1 = (-slope1 * current.getStart().getX()) + current.getStart().getY();
            double b2 = (-slope2 * other.getStart().getX()) + other.getStart().getY();

            //Calculate the values of the intersection point of the two lines
            double px = (b2 - b1) / (slope1 - slope2);
            double py = (slope1 * px) + b1;

            return new Point(px, py);
        }
    }

    /**
     * Finds and returns the closest intersection point between the line and the given rectangle.
     * If the line does not intersect with the rectangle, returns null.
     *
     * @param rect the rectangle to check for intersection points with this line.
     * @return the closest intersection point to the start of the line, or null if there are no intersection points.
     */

    //If this line does not intersect with the rectangle, return null.
    //Otherwise, return the closest intersection point to the start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        if (rect.intersectionPoints(new Line(this.getStart(), this.getEnd())).isEmpty()) {
            return null;
        }

        List<Point> intersectionPoints = rect.intersectionPoints(new Line(this.getStart(), this.getEnd()));
        double minDistance = this.getStart().distance(intersectionPoints.get(0));
        Point closetPoint = intersectionPoints.get(0);

        for (Point intersectionPoint : intersectionPoints) {
            double currentDistance = this.getStart().distance(intersectionPoint);

            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                closetPoint = intersectionPoint;
            }
        }

        return closetPoint;
    }
}