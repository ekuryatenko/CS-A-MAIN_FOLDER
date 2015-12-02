package com.shpp.ekuryatenko.cs;

/*
 * File: Assignment3Part4.java
 *
 * Pyramide
 *
 */

import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;

import java.lang.*;
import java.awt.*;

public class Assignment3Part4 extends WindowProgram {

    /* Adjusting constants for pyramide size */
    private final int BRICKS_IN_BASE = 1;

    /* As for me it is nice always to see whole pyramide in the window */
    private final double BRICK_WIDTH = (APPLICATION_WIDTH - 50) / BRICKS_IN_BASE;
    private final double BRICK_HEIGHT = (APPLICATION_HEIGHT - 50) / BRICKS_IN_BASE;

    /* Adjusting constants for colors */
    private static final Color BRICK_COLOR = Color.ORANGE;

    /*Adjusting constants for graphic window size*/
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 400;

    public void run() {
        buildNewPyramide();
    }

    private void buildNewPyramide() {
        double centreOfCanvas_X = getWidth() / 2;
        double bottomOfCanvas_Y = getHeight();
        double startOfNewBricksLevel_X;
        double startOfNewBricksLevel_Y;

        for (int level = BRICKS_IN_BASE; level > 0; level--) {
            double lengthOfNewBricksLevel = BRICK_WIDTH * level;
            startOfNewBricksLevel_X = centreOfCanvas_X - lengthOfNewBricksLevel / 2;
            startOfNewBricksLevel_Y = bottomOfCanvas_Y - BRICK_HEIGHT * (1 + BRICKS_IN_BASE - level);
            buildThisBricksLevel(startOfNewBricksLevel_X, startOfNewBricksLevel_Y, level);
        }
    }

    private void buildThisBricksLevel(double x, double y, int bricksQty) {
        for (int brick = 0; brick < bricksQty; brick++) {
            double singleBrick_x = x + BRICK_WIDTH * brick;
            double singleBrick_y = y;

            buildSingleBrick(singleBrick_x, singleBrick_y);
        }
    }

    private void buildSingleBrick(double x, double y) {
        GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setFillColor(BRICK_COLOR);
        add(brick, x, y);
    }
}