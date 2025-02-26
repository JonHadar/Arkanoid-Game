//Yehonatan Hadar
package gameSetup;

import gameInterfaces.Collidable;
import gameInterfaces.Sprite;
import geometryObjects.Point;
import geometryObjects.Rectangle;
import gameObjects.Paddle;
import gameObjects.ScoreIndicator;
import gameObjects.Velocity;
import gameObjects.Ball;
import gameObjects.BlockRemover;
import gameObjects.ScoreTrackingListener;
import gameObjects.Counter;
import gameObjects.Block;
import gameObjects.BallRemover;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * The Game class represents the main game logic and setup for the Arkanoid game.
 * It manages the game environment, sprites, and the game loop.
 */
public class Game {

    //Fields
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private Counter counterBlocks;
    private Counter counterBalls;
    private Counter score;

    /**
     * Constructs a new Game instance.
     * Initializes the game environment, sprite collection, and sleeper.
     */
    //Constructor
    public Game() {
        this.sleeper = new Sleeper();
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.counterBlocks = new Counter(0);
        this.counterBalls = new Counter(0);
        this.score = new Counter(0);
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    //Adding new collidable to the game
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the sprite collection.
     *
     * @param s the sprite object to add
     */
    //Adding new sprite to the game
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Returns the game environment.
     *
     * @return the game environment
     */
    //Return the game's environment
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Initializes a new game: creates the blocks, balls, paddle, and adds them to the game.
     * Also sets up the game GUI and screen dimensions.
     */
    //Initialize the game
    public void initialize() {
        //Listeners creation
        BlockRemover blockRemover = new BlockRemover(this, this.counterBlocks);
        BallRemover ballRemover = new BallRemover(this, this.counterBalls);
        ScoreTrackingListener scoreTracking = new ScoreTrackingListener(this.score);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score, 800, 15);

        this.gui = new GUI("Arkanoid", 800, 600);
        Random rnd = new Random();

        //Screen Sizes (considering the blocks that make up the borders of the screen)
        int screenWidth = 750;
        int screenHeight = 550;

        // Initialize the balls of the game
        Ball firstBall = new Ball(new Point(350, 300), 5, Color.BLACK, this.environment);
        firstBall.setVelocity(Velocity.fromAngleAndSpeed(rnd.nextInt() * 360, 5));

        Ball secondBall = new Ball(new Point(300, 300), 5, Color.BLACK, this.environment);
        secondBall.setVelocity(Velocity.fromAngleAndSpeed(rnd.nextInt() * 360, 5));

        Ball thirdBall = new Ball(new Point(300, 300), 5, Color.BLACK, this.environment);
        thirdBall.setVelocity(Velocity.fromAngleAndSpeed(rnd.nextInt() * 360, 5));

        //Create the death region
        Block deathRegion = new Block(new Point(25, 575), 750, 25, Color.MAGENTA);
        deathRegion.addToGame(this);

        // Create the borders of the screen and add them to the game
        Block upRectangle = new Block(new Point(0, 0), 800, 25, Color.GRAY);
        upRectangle.addToGame(this);
        Block lowRectangle = new Block(new Point(25, 575), 750, 25, Color.GRAY);
        lowRectangle.addToGame(this);
        Block rightRectangle = new Block(new Point(775, 25), 25, 575, Color.GRAY);
        rightRectangle.addToGame(this);
        Block leftRectangle = new Block(new Point(0, 25), 25, 575, Color.GRAY);
        leftRectangle.addToGame(this);

        // Create the background of the screen
        Block frame = new Block(new Point(25, 25), screenWidth, screenHeight, Color.CYAN);

        //Adding the ball remover and as a lister of the death region and update the number of balls in the game
        deathRegion.addHitListener(ballRemover);
        this.counterBalls.increase(3);

        // Add the balls and the frame to the game
        frame.addToGame(this);
        scoreIndicator.addToGame(this);
        firstBall.addToGame(this);
        secondBall.addToGame(this);
        thirdBall.addToGame(this);

        // Initialize the paddle
        Rectangle paddleRectangle = new Rectangle(new Point(400, 565), 150, 10);
        Paddle paddle = new Paddle(this.gui.getKeyboardSensor(), paddleRectangle, 5, Color.YELLOW);
        paddle.setGameScreenWidth(775);

        // Add the paddle to the game
        paddle.addToGame(this);

        // Define the dimensions of the blocks in the game
        int blockWidth = 50;
        int blockHeight = 20;
        int blockStartX = screenWidth - 25;
        int blockStartY = 100;

        // Create the blocks of the game, each a different color, and add them to the game
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 12 - i; j++) {
                Color color;
                switch (i) {
                    case 0:
                        color = Color.ORANGE;
                        break;
                    case 1:
                        color = Color.PINK;
                        break;
                    case 2:
                        color = Color.BLUE;
                        break;
                    case 3:
                        color = Color.YELLOW;
                        break;
                    case 4:
                        color = Color.RED;
                        break;
                    default:
                        color = Color.GREEN;
                        break;
                }

                // Calculate the position for each block
                int x = blockStartX - j * blockWidth;
                int y = blockStartY + i * blockHeight;

                // Create and add the block to the game
                Block block = new Block(new Point(x, y), blockWidth, blockHeight, color);
                block.addToGame(this);

                //Adding the block remover and score tracking as a lister of the block
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTracking);
                this.counterBlocks.increase(1);
            }
        }
    }

    /**
     * Runs the game -- starts the animation loop.
     * The game loop runs indefinitely, updating and drawing the game state.
     */
    //Run the game
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        boolean runGame = true;
        while (runGame) {
            //Timing
            long startTime = System.currentTimeMillis();
            DrawSurface d = this.gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();
            //Timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
            //Checks if there are no blocks left in the game
            if (this.counterBlocks.getValue() == 0) {
                this.score.increase(100);
                runGame = false;
                gui.close();
            }
            //Check if there are no balls left in the game
            if (this.counterBalls.getValue() == 0) {
                runGame = false;
                gui.close();
            }
        }
    }

    /**
     * Removes a collidable object from the game's environment.
     *
     * @param c the Collidable object to be removed from the environment
     */
    //Removes a collidable object from the game's environment.
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Removes a sprite from the game's sprite collection.
     *
     * @param s the Sprite object to be removed from the game
     */
    //Removes a sprite from the game's sprite collection.
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
}

