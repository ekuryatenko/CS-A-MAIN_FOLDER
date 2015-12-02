package com.shpp.ekuryatenko.cs;

/*
 * File: Assignment2Part5.java
 *
 * Black boxes matrix
 *
 */

import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part5 extends WindowProgram {

    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 11;
    private static final int NUM_COLS = 6;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;

    /*Adjusting constants for Colors */
    private static final Color BOXES_COLOR = Color.BLACK;

    /*Adjusting constants for size of graphic window */


    public static final int APPLICATION_WIDTH = (int)(((BOX_SIZE + BOX_SPACING) * NUM_COLS) * 1.5);
    public static final int APPLICATION_HEIGHT = (int)(((BOX_SIZE + BOX_SPACING) * NUM_ROWS) * 1.5);

    public void run() {
        buildNewIllusion();
    }

    private void buildNewIllusion() {
        GPoint matrixBasePoint = getMatrixBase();

        /* building new matrix */
        buildNewMarix(matrixBasePoint.getX(), matrixBasePoint.getY());
    }

    private GPoint getMatrixBase() {
        double matrixWidth = NUM_COLS * (BOX_SIZE + BOX_SPACING);
        double matrixHeight = NUM_ROWS * (BOX_SIZE + BOX_SPACING);

        double canvasWidth = getWidth();
        double canvasHeight = getHeight();

        double myBasePoint_X = (canvasWidth - matrixWidth) / 2;
        double myBasePoint_Y = (canvasHeight - matrixHeight) / 2;

        return new GPoint(myBasePoint_X, myBasePoint_Y);
    }

    private void buildNewMarix(double matrixBase_X, double matrixBase_Y) {
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int u = 0; u < NUM_COLS; u++) {
                double singleCell_X = matrixBase_X + (u * (BOX_SIZE + BOX_SPACING));
                double singleCell_Y = matrixBase_Y + (i * (BOX_SIZE + BOX_SPACING));

                buildSingleCell(singleCell_X, singleCell_Y);
            }
        }
    }

    private void buildSingleCell(double x, double y) {
        GRect myBox = new GRect(x, y, BOX_SIZE, BOX_SIZE);
        myBox.setFilled(true);
        myBox.setColor(BOXES_COLOR);
        add(myBox);
    }
}
