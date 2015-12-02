package com.shpp.ekuryatenko.cs;
/*
 * File: Breakout.java -  20150.08.11
 * ======================================================================
 * Kind of arkanoid game. Version 2 - after RShmelev revision
 * Version 1 get several remarks:
 * - weak comments
 * - bricks separates fail
 * - ball's shakes at bottom falling
 * */

import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Breakout extends WindowProgram {
    /** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /** Dimensions of game board (usually the same) */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /** Dimensions of the paddle */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /** Offset of the paddle up from the bottom */
    private static final int PADDLE_Y_OFFSET = 30;

    /** Paddle initiate settings */
    private static final Color PADDLE_COLOR = new Color(247, 183, 34);

    /** Number of bricks per row */
    private static final int NBRICKS_PER_ROW = 10;

    /** Number of rows of bricks */
    private static final int NBRICK_ROWS = 1;

    /** Separation between bricks */
    private static final int BRICK_SEP = 4;

    /** Width of a brick */
    private static final double BRICK_WIDTH =
            (WIDTH - (double) (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /** Height of a brick */
    private static final int BRICK_HEIGHT = 8;

    /** Offset of the top brick row from the top */
    private static final int BRICK_Y_OFFSET = 70;

    /** Radius of the ball in pixels */
    private static final int BALL_RADIUS = 10;
    private static final int BALL_DIAM = BALL_RADIUS * 2;

    /** Ball initiate settings */
    private static final Color BALL_COLOR = new Color(77, 72, 75);

    /** Number of turns */
    private static final int NTURNS = 3;

    /** Ball speed equivalent - it's best range is less of BRICK_HEIGHT for nice bricks identification */
    private static final double BALL_SPEED = BRICK_HEIGHT - 5;

    /** Ball animation pause time - it's best range for vision without pause defects is 10...20
     * Smaller value gives higher difficulty */
    private static final double PAUSE_TIME = 15;

    /** Main paddle variable */
    private GRect paddle;

    /** Main ball variable */
    private GOval ball;

    /** Ball speed variables */
    private double vx;
    private double vy;

    /** Bricks counter for current score printing */
    private int bricksCounter = NBRICK_ROWS * NBRICKS_PER_ROW;

    public void init() {
        addMouseListeners();
    }

    public void run() {
        breakoutGame();
    }

    private void breakoutGame() {
        /** Process of kind of arkanoid game */
        buildItems();
        gameMainAnimation();
    }

    /** Fills game window by objects */
    private void buildItems() {
        /** Build lines of game window borders for better visualisation */
        buildGameWindowBorders();
        /** Calculation of paddle's start position */
        double startPadle_X = getWidth()/2 - PADDLE_WIDTH/2;
        double startPadle_Y = getSouthWindowEdge_Y() - PADDLE_HEIGHT - PADDLE_Y_OFFSET;
        /** Add paddle in canvas */
        paddle = buildPaddle(startPadle_X, startPadle_Y);
        add(paddle);
        /** Calculation of ball's start position  */
        double startBall_X = getWidth()/2 - BALL_RADIUS;
        double startBall_Y = getNorthWindowEdge_Y() + BRICK_Y_OFFSET +
                                                    (NBRICK_ROWS * (BRICK_HEIGHT+BRICK_SEP)) + BALL_DIAM;
        /** Build main game ball at start position in game window   */
        ball = buildBall(startBall_X, startBall_Y);
        add(ball);
        buildBricks();
    }

    /** Build game window borders in game window sizes WIDTH and HEIGHT */
    private void buildGameWindowBorders() {
        double windowBase_X = getWestWindowEdge_X();
        double windowBase_Y = getNorthWindowEdge_Y();
        double windowEdge_X = getEastWindowEdge_X();
        double windowEdge_Y = getSouthWindowEdge_Y();
        add(buildLine(windowBase_X, windowBase_Y, windowEdge_X, windowBase_Y));
        add(buildLine(windowEdge_X, windowBase_Y, windowEdge_X, windowEdge_Y));
        add(buildLine(windowEdge_X, windowEdge_Y, windowBase_X, windowEdge_Y));
        add(buildLine(windowBase_X, windowEdge_Y, windowBase_X, windowBase_Y));
    }

    /** Getters for game window edges */
    private double getWestWindowEdge_X() {
        return (getWidth() - WIDTH) / 2;
    }

    private double getEastWindowEdge_X() {
        return getWestWindowEdge_X() + WIDTH;
    }

    private double getNorthWindowEdge_Y() {
        return (getHeight() - HEIGHT) / 2;
    }

    private double getSouthWindowEdge_Y() {
        return getNorthWindowEdge_Y() + HEIGHT;
    }

    /** Build border line for game window */
    private GLine buildLine(double x0, double y0, double x1, double y1) {
        GLine line = new GLine(x0, y0, x1, y1);
        return line;
    }

    private GOval buildBall(double x, double y) {
        GOval ballOval = new GOval(x, y, 2 * BALL_RADIUS, 2 * BALL_RADIUS);
        ballOval.setFilled(true);
        ballOval.setColor(BALL_COLOR);
        return ballOval;
    }

    private GRect buildPaddle(double x, double y) {
        GRect rect = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        rect.setFilled(true);
        rect.setColor(PADDLE_COLOR);
        return rect;
    }

    /** Build all bricks on game window */
    private void buildBricks() {
        ArrayList<Color> bricksColors = colorsArray();
        /** Due to conditions of task start code - single row width = WIDTH */
        int bricksRowWidth = WIDTH;
        double firstRowStart_x = getWestWindowEdge_X() + (WIDTH - bricksRowWidth) / 2;
        double firstRowStart_y = getNorthWindowEdge_Y() + BRICK_Y_OFFSET;
        /** Main building loop */
        for (int i = 0; i < NBRICK_ROWS; i++)
            for (int u = 0; u < NBRICKS_PER_ROW; u++) {
                /** Calculation of next bricks base position */
                double x = firstRowStart_x + (BRICK_WIDTH + BRICK_SEP) * u;
                double y = firstRowStart_y + (BRICK_HEIGHT + BRICK_SEP) * i;
                /** Logic of bricks colors getting */
                int colorsNumber = bricksColors.size();
                int thisColor = (i / 2) % colorsNumber;
                Color brickColor = bricksColors.get(thisColor);
                /** To differ bricks from other game elements they are build as polygons */
                GPolygon singleBrick = buildSingleBrick(x, y);
                singleBrick.setColor(brickColor);
                add(singleBrick);
            }
    }

    private GPolygon buildSingleBrick(double x, double y) {
        GPolygon singleBrick = new GPolygon(x, y);
        singleBrick.addVertex(0, 0);
        singleBrick.addVertex(BRICK_WIDTH, 0);
        singleBrick.addVertex(BRICK_WIDTH, BRICK_HEIGHT);
        singleBrick.addVertex(0, BRICK_HEIGHT);
        singleBrick.setFilled(true);
        return singleBrick;
    }

    /** Defines set of possible bricks colors */
    private ArrayList<Color> colorsArray() {
        ArrayList<Color> colorsArray = new ArrayList<Color>();
        colorsArray.add(Color.RED);
        colorsArray.add(Color.ORANGE);
        colorsArray.add(Color.YELLOW);
        colorsArray.add(Color.GREEN);
        colorsArray.add(Color.CYAN);
        return colorsArray;
    }

    /** Main Breakout game activity */
    private void gameMainAnimation() {
        /** Initiates game attempts counter */
        int gamesCount = NTURNS;
        /** Wait for user ready signal to play */
        waitForClick();
        /** After click the ball is lunched in random direction */
        lunchBall();
        /** === MAIN GAME PROCESS ===  */
        while (gamesCount > 0) {/** The game is lost while user isn't waste all game attempts */

            if (reachedButton()) {/** If ball falls into the bottom area it is lost, and user failed attempt */
                /** The user's game attempts counters are decremented  */
                gamesCount--;
                if (gamesCount >= 1) {/** While user's attempts are not wasted the game is restarted after fail */
                    /** New ball is relocated in start position and wait for user click */
                    reBuildBall();
                    waitForClick();
                    /** After click ball is lunched in random direction */
                    lunchBall();
                } else {
                    /** If all attempts are failed - game finishes and user get fail announcement */
                    finishGame("YOU LOST ALL BALLS!!!");
                }
            } else if (allBricksRemoved()) {
                /** If all bricks are removed successfully - game finishes and user get congratulation announcement */
                finishGame("YOU WIN!!!");
            } else {
                /** Main game activity - ball's movements in window
                 * Ball rebounds of window edges, paddle and bricks
                 * His moving and directions depends of angle of collision with some element
                 * The movements are lost while ball is hold under the bottom area, or while bricks are present
                 * Ball has to remove all bricks in window to finish game */
                gameBallMoving(ball);
                pause(PAUSE_TIME);
            }
        }
    }

    /** Executes ball's changes of movement directions into the window
     * It monitors ball's positions flags continuously, choose movement directions, and executes ball's movements */
    private void gameBallMoving(GOval ball) {
        /** Positions flags monitoring */
        /** Horizontal directions monitoring */
        if (reachedWest() || reachedEast()) {
            vx = -vx;
        }
        /** Vertical directions monitoring */
        if (reachedTop() || reachedBrick()) {
            vy = -vy;
        } else if (reachedPaddle()) {
            /** Variant to solve ball shakes, when it get under the paddle */
            vy = -Math.abs(vy);
        }
        /** Ball's movement execution */
        ball.move(vx, vy);
    }

    /** Flag - true if ball collide with right side of the window */
    private boolean reachedEast() {
        boolean reachedEast = false;
        /** vx offset added to avoid window edges penetrations by ball */
        double offset = Math.abs(vx);
        double myBallEastEdge = (ball.getX() + BALL_DIAM);
        double myBallBottom_Y = ball.getY() + BALL_DIAM;
        /** Variant to solve problem of bounces with north game border is to look at InsideWindow flag  */
        boolean ballInsideWindow_Y = ((myBallBottom_Y < getSouthWindowEdge_Y())||
                (ball.getY() > getNorthWindowEdge_Y()));
        if (myBallEastEdge >= (getEastWindowEdge_X() - offset)) {
            if(ballInsideWindow_Y) {
                reachedEast = true;
            }
        } else {
            reachedEast = false;
        }
        return reachedEast;
    }

    /** Flag - true if ball collide with left side of the window */
    private boolean reachedWest() {
        boolean reachedWest = false;
        /** vx offset added to avoid window edges penetrations by ball */
        double offset = Math.abs(vx) / 2;
        double myBallWestEdge_X = ball.getX();
        double myBallBottom_Y = ball.getY() + BALL_DIAM;
        /** Variant to solve problem of bounces with south game border is to look at InsideWindow flag  */
        boolean ballInsideWindow_Y = ((myBallBottom_Y < getSouthWindowEdge_Y())||
                                        (ball.getY() > getNorthWindowEdge_Y()));
        if (myBallWestEdge_X <= (getWestWindowEdge_X() + offset)) {
            if(ballInsideWindow_Y) {
                reachedWest = true;
            }
        } else {
            reachedWest = false;
        }
        return reachedWest;
    }

    /** Flag - true if ball dive under the bottom of the window */
    private boolean reachedButton() {
        boolean reachedButtom = false;
        double myBallBottom_Y = (ball.getY() + BALL_DIAM);
        /** Rise up flag when whole ball disappeared, and a little bit more time = BALL_DIAM
         * It gives kind of pause between game levels */
        if (myBallBottom_Y > (getSouthWindowEdge_Y() + 2 * BALL_DIAM)) {
            reachedButtom = true;
        } else {
            reachedButtom = false;
        }
        return reachedButtom;
    }

    /** Flag - true if ball collided with top of the window */
    private boolean reachedTop() {
        boolean reachedTop = false;
        /** vy offset added to avoid window edges penetrations by ball */
        double offset = Math.abs(vy);
        double myBallTop_Y = ball.getY();
        if (myBallTop_Y <= (getNorthWindowEdge_Y() + offset)) {
            reachedTop = true;
        } else {
            reachedTop = false;
        }
        return reachedTop;
    }

    /**
     * Flag - true if ball collided with paddle
     */
    private boolean reachedPaddle() {
        boolean reachedPaddle;

        GObject collider = getCollidingObject();

        if (collider == paddle) {
            reachedPaddle = true;
        } else if (collider == null) {
            reachedPaddle = false;
        } else if (collider instanceof GLine) {
            reachedPaddle = false;
        } else {
            /** Reached brick */
            reachedPaddle = false;
        }
        return reachedPaddle;
    }

    /**
     * Flag - true if ball collided with brick It's also invocate brick's removement activity
     */
    private boolean reachedBrick() {
        boolean reachedBrick;

        GObject collider = getCollidingObject();

        if (collider == paddle) {
            reachedBrick = false;
        } else if (collider == null) {
            reachedBrick = false;
        } else if (collider instanceof GLine) {
            reachedBrick = false;
        } else {
            /** Reached brick */
            reachedBrick = true;
            /** Brick's removement activity */
            brickRemovement(collider);
        }
        return reachedBrick;
    }

    /**
     * Execute brick's removement activity after ball's collision
     */
    private void brickRemovement(GObject brick) {
        remove(brick);
        decrementBricksCounter();
    }

    /**
     * Returns object collided with ball - scan balls corners and recognize objects at this points
     */
    private GObject getCollidingObject() {
        /** vx, vy offsets added to avoid objects edges penetrations by ball */
        double offset_X = Math.abs(vx) * 0.6;
        double offset_Y = Math.abs(vy) * 0.6;
        /** Points of ball's corners */
        GPoint westTopCorner = new GPoint((ball.getX() - offset_X), (ball.getY() - offset_Y));
        GPoint westBottomCorner = new GPoint((ball.getX() - offset_X), (ball.getY() + BALL_DIAM + offset_Y));
        GPoint eastTopCorner = new GPoint((ball.getX() + BALL_DIAM + offset_X), (ball.getY() - offset_Y));
        GPoint eastBottomCorner = new GPoint((ball.getX() + BALL_DIAM + offset_X),
                ball.getY() + BALL_DIAM + offset_Y);

        GObject cornerObject;

        /** Catch object if it's appear on some corner  */
        if (getElementAt(westTopCorner) != null) {
            cornerObject = getElementAt(westTopCorner);
        } else if (getElementAt(westBottomCorner) != null) {
            cornerObject = getElementAt(westBottomCorner);
        } else if (getElementAt(eastTopCorner) != null) {
            cornerObject = getElementAt(eastTopCorner);
        } else if (getElementAt(eastBottomCorner) != null) {
            cornerObject = getElementAt(eastBottomCorner);
        } else {
            cornerObject = null;
        }
        return cornerObject;
    }

    /**
     * Decrements bricks score counter in case of brick removement activity
     */
    private void decrementBricksCounter() {
        bricksCounter--;
    }

    /**
     * Flag - all  bricks removed
     */
    private boolean allBricksRemoved() {
        boolean allBricksRemoved = false;
        if (bricksCounter < 1) {
            allBricksRemoved = true;
        }
        return allBricksRemoved;
    }

    /**
     * Restart ball for next game turn
     */
    private void reBuildBall() {
        double startBall_X = getWestWindowEdge_X() + WIDTH / 2;
        double startBall_Y =  getNorthWindowEdge_Y() + BRICK_Y_OFFSET +
                                (NBRICK_ROWS * (BRICK_HEIGHT+BRICK_SEP)) + BALL_DIAM;
        remove(ball);
        ball = buildBall(startBall_X, startBall_Y);
        add(ball);
    }

    /**
     * Activity after game's end
     */
    private void finishGame(String label) {
        remove(ball);
        /** Print reason of game's finishing */
        buildCenterLabel(label);
    }

    /**
     * MouseListener activity - executes paddle's movements
     */
    public void mouseMoved(MouseEvent e) {
        double myPaddleCenter = paddle.getX() + PADDLE_WIDTH / 2;
        /** Paddle centre to cursor offset - gives value of current paddle motion towards cursor  */
        double dx = e.getX() - myPaddleCenter;
        /** Paddle is moved by mouse while it's not get out of game window */
        if ((paddle.getX() + dx) > getWestWindowEdge_X()) {
            if ((paddle.getX() + dx) < (getEastWindowEdge_X() - PADDLE_WIDTH)) {
                paddle.move(dx, 0);
            }
        }
    }

    /**
     * Executes lunch of the ball - for game starting or restarting
     * Every time lunching executes toward paddle, but X-axis direction is taken randomly
     */
    private void lunchBall() {
        vy = BALL_SPEED;
        /** X direction of ball moving is taken randomly */
        RandomGenerator rgen = RandomGenerator.getInstance();
        vx = rgen.nextDouble(1.0, BALL_SPEED);
        if (rgen.nextBoolean(0.5))
            vx = -vx;
    }

    /**
     * Print pare of game over labels in window's centre when game is finished
     */
    private void buildCenterLabel(String label) {
        /** Print "Game over" label */
        GLabel bigCenterLabel1 = new GLabel("GAME OVER!");
        bigCenterLabel1.setFont("Serif-30");
        double label1Width = bigCenterLabel1.getWidth();
        double label1Height = bigCenterLabel1.getHeight();
        double label1_X = (WIDTH - label1Width) / 2;
        double label1_Y = (getHeight() / 2) - label1Height;
        add(bigCenterLabel1, label1_X, label1_Y);
        /** Print label with reason of game's finishing  */
        GLabel bigCenterLabel2 = new GLabel(label);
        bigCenterLabel2.setFont("Serif-30");
        double label2Width = bigCenterLabel2.getWidth();
        double label2Height = bigCenterLabel2.getHeight();
        double label2_X = (WIDTH - label2Width) / 2;
        double label2_Y = (getHeight() / 2) + label1Height;
        add(bigCenterLabel2, label2_X, label2_Y);
    }
}
