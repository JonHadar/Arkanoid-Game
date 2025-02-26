//Yehonatan Hadar
package gameSetup;
import biuoop.DrawSurface;
import java.util.ArrayList;
import gameInterfaces.Sprite;
import java.util.List;

/**
 * The SpriteCollection class represents a collection of Sprite objects.
 * It provides methods to add sprites to the collection, notify all sprites that time has passed,
 * and draw all sprites on a given surface.
 */
public class SpriteCollection {

    //Fields
    private List<Sprite> spriteCollection;

    /**
     * Constructs an empty SpriteCollection.
     */
    //Constructor
    public SpriteCollection() {
        this.spriteCollection = new ArrayList<>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to add
     */
    //Adding new sprite to the collection
    public void addSprite(Sprite s) {
        this.spriteCollection.add(s);
    }

    /**
     * Removes a sprite from the game's sprite collection.
     *
     * @param s the Sprite object to be removed from the game
     */
    public void removeSprite(Sprite s) {
        this.spriteCollection.remove(s);
    }

    /**
     * Calls the timePassed() method on all sprites in the collection.
     * This method notifies all sprites that time has passed, allowing them to update their state.
     */
    //Notifies all sprites that time has passed and update all the sprite's position
    public void notifyAllTimePassed() {
        // Make a copy of the hitListeners before iterating over them.
        List<Sprite> spriteList = new ArrayList<>(this.spriteCollection);
        for (Sprite sprite : spriteList) {
            sprite.timePassed();
        }
    }

    /**
     * Calls the drawOn(DrawSurface d) method on all sprites in the collection.
     * This method draws all sprites on the provided DrawSurface.
     *
     * @param d the DrawSurface on which to draw all sprites
     */
    //Draw all the sprites on the game's screen
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : spriteCollection) {
            sprite.drawOn(d);
        }
    }
}
