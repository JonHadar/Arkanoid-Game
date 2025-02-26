//Yehonatan Hadar
package gameObjects;
import gameInterfaces.Sprite;
import gameSetup.Game;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The ScoreIndicator class implements the Sprite interface to display the game score on the screen.
 */
public class ScoreIndicator implements Sprite {
    //Fields
    private Counter score;
    private static final int FONT_SIZE = 15;
    private int widthScoreScreen;
    private int heightScoreScreen;

    /**
     * Constructor for ScoreIndicator.
     *
     * @param score             the Counter object holding the game score
     * @param widthScoreScreen  the width of the score indicator screen
     * @param heightScoreScreen the height of the score indicator screen
     */
    //Constructor
    public ScoreIndicator(Counter score, int widthScoreScreen, int heightScoreScreen) {
        this.score = score;
        this.widthScoreScreen = widthScoreScreen;
        this.heightScoreScreen = heightScoreScreen;
    }

    /**
     * Draws the score indicator on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    //Draws the score indicator on the given DrawSurface.
    public void drawOn(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, this.widthScoreScreen, this.heightScoreScreen);
        String scoreText = "Score: " + this.score.getValue();
        d.setColor(Color.BLACK);
        d.drawText((this.widthScoreScreen / 2) - 10, this.heightScoreScreen - 2, scoreText, FONT_SIZE);
    }

    /**
     * Implementation of the timePassed method from the Sprite interface.
     * No updates needed for the score indicator.
     */
    //No updates needed here
    public void timePassed() {
    }

    /**
     * Adds the score indicator to the game.
     *
     * @param g the Game object to add the score indicator to
     */
    //Adds the score indicator to the game.
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
