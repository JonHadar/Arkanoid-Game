//Yehonatan Hadar
package gameInterfaces;
/**
 * The HitNotifier interface should be implemented by any class that
 * wants to notify others of hit events.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the HitListener to be added
     */
    // Add hl as a listener to hit events.
    void addHitListener(HitListener hl);
    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the HitListener to be removed
     */
    // Remove hl from the list of listeners to hit events.
    void removeHitListener(HitListener hl);

}
