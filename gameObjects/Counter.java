//Yehonatan Hadar
package gameObjects;

/**
 * A simple counter class that keeps track of an integer count.
 */
public class Counter {
    //Fields
    private int count;

    /**
     * Constructs a counter with an initial count value.
     *
     * @param count the initial value of the counter
     */
    //Constructor
    public Counter(int count) {
        this.count = count;
    }

    /**
     * Increases the counter's current value by a specified amount.
     *
     * @param number the amount to increase the counter by
     */
    //Increases the counter's current value by a specified amount.
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Decreases the counter's current value by a specified amount.
     *
     * @param number the amount to decrease the counter by
     */
    //Decreases the counter's current value by a specified amount.
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Returns the current value of the counter.
     *
     * @return the current count value
     */
    //Returns the current value of the counter.
    public int getValue() {
        return this.count;
    }
}
