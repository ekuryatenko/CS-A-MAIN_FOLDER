package com.shpp.ekuryatenko.cs;
/*
 * File: BreakoutExt.java
 * ======================================================================
 * Kind of arkanoid game. Extended version
 */

import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BreakoutExt extends WindowProgram {
    /** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /** Dimensions of game board (usually the same) */
    private static final int WIDTH = APPLICATION_WIDTH - 20;
    /** I haven't enough time to arrange dimenisions with BorderLayout dimenisions */
    private static final int HEIGHT = APPLICATION_HEIGHT - 60;

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
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

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

    /**
     * Ball animation pause time - it's best range for vision without pause defects is 10...20 Smaller value gives
     * higher difficulty
     */
    private static final double PAUSE_TIME = 10;
    /** Time of breakout message showing */
    private static int BREAKOUT_MESSAGE_DISPLAY = 3;
    /** Sound of tuckets for game winner */
    private static String tadaFile = "tada.wav";
    /** Main paddle variable */
    private GRect paddle;
    /** Main ball variable */
    private GOval ball;
    /** Ball speed variables */
    private double vx;

    /**
     * Extended functions variables. Unfortunately I had no time to make them localy (( To decide how to make them
     * globally was more easier Another reason is to remain gameMainAnimation() method more simple, to hide such service
     * functions
     */
    private double vy;
    /** Breakout event variable. Bricks counter for current score printing */
    private int bricksCounter = NBRICK_ROWS * NBRICKS_PER_ROW;
    /** Breakout event variable. Storage for breakout statemachine */
    private int breakoutState = 0;
    /** Breakout event variable. Timer of breakout message showing */
    private int breakoutLabelCounter = BREAKOUT_MESSAGE_DISPLAY;
    /** Breakout event variable. Main label showing due to breakout even */
    private GLabel breakoutLabel;
    /** JLabel of remain bricks quantity */
    private JLabel bricksJLabel;
    /** JLabel of remain turns */
    private JLabel turnsJLabel;

    public void init() {
        /** Windows to show current game counters */
        bricksJLabel = new JLabel("Bricks   " + bricksCounter);
        turnsJLabel = new JLabel("Turns   " + NTURNS);
        add(bricksJLabel, SOUTH);
        add(turnsJLabel, SOUTH);

        addMouseListeners();
    }

    public void run() {
        breakoutGame();
    }

    private void breakoutGame() {
        buildItems();
        gameMainAnimation();
    }

    /** Fills game window by objects */
    private void buildItems() {
        buildGameWindowBorders();
        double startPadle_X = getWestWindowEdge_X() + WIDTH / 2;
        double startPadle_Y = getSouthWindowEdge_Y() - PADDLE_HEIGHT - PADDLE_Y_OFFSET;
        paddle = buildPaddle(startPadle_X, startPadle_Y);
        add(paddle);
        double startBall_X = startPadle_X + PADDLE_WIDTH / 2;
        double startBall_Y = getNorthWindowEdge_Y() + BRICK_Y_OFFSET + (NBRICK_ROWS * BRICK_HEIGHT) + BALL_DIAM;
        ball = buildBall(startBall_X, startBall_Y);
        add(ball);
        buildBricks();
        breakoutLabel = waitMeassege("!!BREAKOUT!!");
    }

    /** Build game window borders in sizes WIDTH and HEIGHT */
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

    /** Build main ball of game */
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

        for (int i = 0; i < NBRICK_ROWS; i++)
            for (int u = 0; u < NBRICKS_PER_ROW; u++) {
                double x = firstRowStart_x + (BRICK_WIDTH + BRICK_SEP) * u;
                double y = firstRowStart_y + (BRICK_HEIGHT) * i;
                int colorsNumber = bricksColors.size();
                int thisColor = (i / 2) % colorsNumber;
                Color brickColor = bricksColors.get(thisColor);
                GPolygon singleBrick = buildSingleBrick(x, y);
                singleBrick.setColor(brickColor);
                add(singleBrick);
            }
    }

    /** To define brick in getCollidingObject method - bricks are created as polygons */
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
        /** Tucket for game  winners */
        double[] tucket = StdAudio.read(tadaFile);

        reStartBreakoutMonitor();
        reStartGame(gamesCount);

        while (gamesCount > 0) {
            bricksJLabel.setText("Bricks   " + bricksCounter + "  ");

            if (allBricksRemoved()) {
                /** Finishes game if all bricks are removed */
                StdAudio.play(tucket);
                StdAudio.close();
                finishGame("YOU WIN!!!");

            } else if (reachedButton()) {
                gamesCount--;
                turnsJLabel.setText("Turns   " + gamesCount + "  ");
                if (gamesCount >= 1) {
                    /** Rebuild game after ball missing */
                    reBuildBall();
                    reStartGame(gamesCount);
                    reStartBreakoutMonitor();
                } else {
                    /** Finishes game if all attempts are failed */
                    finishGame("YOU LOST ALL BALLS!!!");
                }
            } else {
                /** Main game activity - ball's movements in window with breakout monitoring */
                gameBallMoving(ball);

                pause(PAUSE_TIME);
            }
        }
    }

    public void reStartGame(int game) {
        GLabel centerMessage = waitMeassege("You have " + game + " turns! Click to run!");
        waitForClick();
        remove(centerMessage);
        /** lunch ball in random direction */
        lunchBall();
    }

    /** Executes ball's changes of movement directions into the window Executes breakout monitoring */
    private void gameBallMoving(GOval ball) {

        if (reachedWest() || reachedEast()) {
            vx = -vx;
            /** Booleans here correspond with ball event flags
             * Here true values correspond with reachedWest() and reachedEast() flag-methods  */
            breakoutMonitor(false, false, false, true, true);
        }
        if (reachedTop()) {
            vy = -vy;
            breakoutMonitor(false, false, true, false, false);
        }
        if (reachedBrick()) {
            vy = -vy;
            breakoutMonitor(true, false, false, false, false);
        }
        if (reachedPaddle()) {
            /** Variant to solve ball shakes, when it get under the paddle */
            vy = -Math.abs(vy);
            breakoutMonitor(false, true, false, false, false);
        }

        ball.move(vx, vy);
    }

    /**
     * Monitors breakout event during the game At breakout event shows message for some time
     *
     * @param brickflag  rise due to reachBrick() method
     * @param paddleFlag rise due to reachPaddle() method
     * @param topFlag    rise due to reachTop() method
     * @param westFlag   rise due to reachPWest() method
     * @param eastFlag   rise due to reachEst() method
     */
    private void breakoutMonitor(boolean brickflag, boolean paddleFlag, boolean topFlag,
                                 boolean westFlag, boolean eastFlag) {
        breakoutState = stateControl(breakoutState, brickflag, paddleFlag, topFlag, westFlag, eastFlag);
        breakoutLabelCounter = counterControl(breakoutLabelCounter, breakoutState);
        labelControl(breakoutLabelCounter, breakoutState);
    }

    /** Initiate breakoutMonitor for next round */
    private void reStartBreakoutMonitor() {
        breakoutState = 0;
        breakoutLabel.setVisible(false);
        breakoutLabelCounter = 0;
    }

    /** State machine for breakout event capturing */
    private int stateControl(int currentState, boolean brickflag, boolean paddleFlag, boolean topFlag,
                             boolean westFlag, boolean eastFlag) {
        /** The main purpose is to capture moment when ball destroys bricks automatically, without paddle colliding
         * Initially - it's waiting for first brick touching */
        if (currentState == 0) {
            if (brickflag) {
                currentState = 1;/** First brick */
            }
        } else if (currentState == 1) {
            if (westFlag || eastFlag || topFlag) {
                currentState = 2;/** Wait for next brick */
            } else if (paddleFlag || brickflag) {
                currentState = 0;
            }
        } else if (currentState == 2) {
            if (brickflag) {
                currentState = 3;/** Breakout state */
            } else if (paddleFlag || westFlag || eastFlag || topFlag) {
                currentState = 0;
            }
        } else if (currentState == 3) {
            if (westFlag || eastFlag || topFlag) {
                currentState = 2;/** Wait for next brick */
            } else if (paddleFlag || brickflag) {
                currentState = 0;
            }
        }
        return currentState;
    }

    /** Timer for breakout message display */
    private int counterControl(int counter, int state) {
        /** Waits for breakout state = 3, and lunch counting
         * Stops when counts to zero */
        if (state == 3) {
            counter = BREAKOUT_MESSAGE_DISPLAY;
        } else if (counter == 0) {
            counter = 0;
        } else {
            counter--;
        }
        return counter;
    }

    /** Breakout message management */
    private void labelControl(int counter, int state) {
        /** Start to show message if stateControl defines breakout event
         *  Hide message if timer counted to zero */
        if (state == 3) {
            breakoutLabel.setVisible(true);
        } else if (counter == 0) {
            breakoutLabel.setVisible(false);
        }
    }

    /** Flag - true if ball collide with right side of the window */
    private boolean reachedEast() {
        boolean reachedEast = false;
        /** vx offset added to avoid window edges penetrations by ball */
        double offset = Math.abs(vx);
        double myBallEastEdge = (ball.getX() + BALL_DIAM);
        if (myBallEastEdge >= (getEastWindowEdge_X() - offset)) {
            reachedEast = true;
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
        if (myBallWestEdge_X <= (getWestWindowEdge_X() + offset)) {

            reachedWest = true;
        } else {
            reachedWest = false;
        }
        return reachedWest;
    }

    /** Flag - true if ball dive under the bottom of the window */
    private boolean reachedButton() {
        boolean reachedButtom = false;
        double myBallBottom_Y = (ball.getY() + BALL_DIAM);
        /** Rise up flag when whole ball disappeared, and a little bit more time = BALL_DIAM */
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

    /** Flag - true if ball collided with paddle */
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

    /** Flag - true if ball collided with brick It's also invocate brick's removement activity */
    private boolean reachedBrick() {
        boolean reachedBrick;

        GObject collider = getCollidingObject();

        if (collider == paddle) {
            reachedBrick = false;
        } else if (collider == null) {
            reachedBrick = false;
        } else if (collider instanceof GLine) {
            reachedBrick = false;
        } else if (collider instanceof GLabel) {
            reachedBrick = false;
        } else {
            /** Reached brick */
            reachedBrick = true;
            /** Brick's removement activity */
            brickRemovement(collider);
        }
        return reachedBrick;
    }

    /** Execute brick's removement */
    private void brickRemovement(GObject brick) {
        remove(brick);
        decrementBricksCounter();
    }

    /** Returns object collided with ball */
    private GObject getCollidingObject() {
        /** vx, vy offsets added to avoid objects edges penetrations by ball */
        double offset_X = Math.abs(vx) * 0.6;
        double offset_Y = Math.abs(vy) * 0.6;
        /** Points of ball's angles */
        GPoint westTopAngle = new GPoint((ball.getX() - offset_X), (ball.getY() - offset_Y));
        GPoint westBottomAngle = new GPoint((ball.getX() - offset_X), (ball.getY() + BALL_DIAM + offset_Y));
        GPoint eastTopAngle = new GPoint((ball.getX() + BALL_DIAM + offset_X), (ball.getY() - offset_Y));
        GPoint eastBottomAngle = new GPoint((ball.getX() + BALL_DIAM + offset_X),
                ball.getY() + BALL_DIAM + offset_Y);

        GObject angleObject;

        /** Catch object if it's appear on some angle  */
        if (getElementAt(westTopAngle) != null) {
            angleObject = getElementAt(westTopAngle);
        } else if (getElementAt(westBottomAngle) != null) {
            angleObject = getElementAt(westBottomAngle);
        } else if (getElementAt(eastTopAngle) != null) {
            angleObject = getElementAt(eastTopAngle);
        } else if (getElementAt(eastBottomAngle) != null) {
            angleObject = getElementAt(eastBottomAngle);
        } else {
            angleObject = null;
        }
        return angleObject;
    }

    /** Decrements bricks score counter */
    private void decrementBricksCounter() {
        bricksCounter--;
    }

    /** Flag - all  bricks removed */
    private boolean allBricksRemoved() {
        boolean allBricksRemoved = false;
        if (bricksCounter < 1) {
            allBricksRemoved = true;
        }
        return allBricksRemoved;
    }

    /** Restart ball for next game turn */
    private void reBuildBall() {
        double startBall_X = getWestWindowEdge_X() + WIDTH / 2;
        double startBall_Y = getNorthWindowEdge_Y() + BRICK_Y_OFFSET + (NBRICK_ROWS * BRICK_HEIGHT) + 2 * BALL_DIAM;
        remove(ball);
        ball = buildBall(startBall_X, startBall_Y);
        add(ball);
    }

    private GLabel waitMeassege(String meassege) {
        GLabel centerLabel = new GLabel(meassege);
        centerLabel.setFont("Serif-30");
        double label_X = getEastWindowEdge_X() - WIDTH / 2 - centerLabel.getWidth() / 2;
        double label_Y = getNorthWindowEdge_Y() + (HEIGHT - centerLabel.getHeight()) / 2;
        add(centerLabel, label_X, label_Y);
        return centerLabel;
    }

    /** Activity after game's end */
    private void finishGame(String label) {
        ball.setColor(Color.RED);
        remove(breakoutLabel);
        /** Print reason of game's finishing */
        buildCenterLabel(label);

        vx = 0;
        vy = Math.abs(vy);
        while (!(reachedWest() || reachedEast() || reachedButton())) {
            ball.move(vx, vy);
            pause(PAUSE_TIME);
        }

        remove(ball);
    }

    /** MouseListener activity - executes paddle's movements */
    public void mouseMoved(MouseEvent e) {
        double myPaddleCenter = paddle.getX() + PADDLE_WIDTH / 2;
        /** Paddle centre to cursor offset  */
        double dx = e.getX() - myPaddleCenter;
        /** Paddle is moved by mouse while it's not get out of game window */
        if ((paddle.getX() + dx) > getWestWindowEdge_X()) {
            if ((paddle.getX() + dx) < (getEastWindowEdge_X() - PADDLE_WIDTH)) {
                paddle.move(dx, 0);
            }
        }
        /** Shows if cursor moved out of game window */
        if ((e.getX() > getEastWindowEdge_X()) || (e.getX() < getWestWindowEdge_X())) {
            ball.setColor(Color.RED);
            paddle.setColor(Color.GREEN);
        } else {
            ball.setColor(BALL_COLOR);
            paddle.setColor(PADDLE_COLOR);
        }
    }

    /** Shows if cursor moved out of canvas window */
    public void mouseExited(MouseEvent e) {
        ball.setColor(Color.GRAY);
        paddle.setColor(Color.GRAY);
    }

    /** Executes lunch of the ball - for game starting or restarting */
    private void lunchBall() {
        vy = BALL_SPEED;
        /** X direction of ball moving is taken randomly */
        RandomGenerator rgen = RandomGenerator.getInstance();
        vx = rgen.nextDouble(1.0, BALL_SPEED);
        if (rgen.nextBoolean(0.5))
            vx = -vx;
    }

    /** Print pare of game over labels in window's centre */
    private void buildCenterLabel(String label) {
        /** Print "Game over" label */
        GLabel bigCenterLabel1 = new GLabel("GAME OVER!");
        bigCenterLabel1.setFont("Serif-30");
        double label1Width = bigCenterLabel1.getWidth();
        double label1Height = bigCenterLabel1.getHeight();
        double label1_X = getWestWindowEdge_X() + WIDTH / 2 - label1Width / 2;
        double label1_Y = (getSouthWindowEdge_Y() / 2) - label1Height;
        add(bigCenterLabel1, label1_X, label1_Y);
        /** Print label with reason of game's finishing  */
        GLabel bigCenterLabel2 = new GLabel(label);
        bigCenterLabel2.setFont("Serif-30");
        double label2Width = bigCenterLabel2.getWidth();
        double label2Height = bigCenterLabel2.getHeight();
        double label2_X = getWestWindowEdge_X() + WIDTH / 2 - label2Width / 2;
        double label2_Y = (getSouthWindowEdge_Y() / 2) + label1Height;
        add(bigCenterLabel2, label2_X, label2_Y);
    }

}