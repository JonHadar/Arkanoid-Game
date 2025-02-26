//Yehonatan Hadar
package gameObjects;

/**
 * This class contains constant variables used in the application.
 */

public class ConstantVariables {

    /**
     * A small value used for comparison of double values.
     */
    public static final double EPSILON = 0.00001;

    /**
     * Utility method to compare two doubles with a small epsilon tolerance.
     * Returns 0 if the difference between num1 and num2 is within the epsilon tolerance,
     * -1 if num1 is less than num2, and 1 if num1 is greater than num2.
     *
     * @param num1 the first double to compare.
     * @param num2 the second double to compare.
     * @return 0 if num1 is approximately equal to num2 within the epsilon tolerance,
     *         -1 if num1 is less than num2,
     *         1 if num1 is greater than num2.
     */
    public static int compareDoubles(double num1, double num2) {
        if (Math.abs(num1 - num2) < EPSILON) {
            return 0; //Equals
        } else if (num1 - num2 < 0) {
            return -1; //num1 less than num 2
        } else {
            return 1; // num1 greater than num 2
        }
    }
}
