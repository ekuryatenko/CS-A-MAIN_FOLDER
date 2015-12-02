package com.shpp.ekuryatenko.cs;

/*
 * File: Assignment2ChessBoard.java
 *
 * BuildChessBoard
 *
 */

import acm.graphics.GPoint;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;
import java.awt.*;

public class Assignment2ChessBoard extends WindowProgram {

    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 8;
    private static final int NUM_COLS = 8;

    /* The width and height of each cell. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the cells. */
    private static final double BOX_SPACING = 5;

    /*Adjusting constants for Colors */
    private static final Color BOXES_COLOR = new Color(215, 83, 22);
    private static final Color BORDERS_COLOR = Color.YELLOW;

    /*Adjusting constants for size of graphic window */
    public static final int APPLICATION_WIDTH = (int)((BOX_SIZE + BOX_SPACING) * NUM_COLS * 1.2);
    public static final int APPLICATION_HEIGHT = (int)((BOX_SIZE + BOX_SPACING) * NUM_ROWS * 1.2);

    public void run() {
        buildChessBoard();
    }

    private void buildChessBoard() {
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
        boolean isFilled = true;

        for (int i = 0; i < NUM_ROWS; i++) {
            for (int u = 0; u < NUM_COLS; u++) {
                double singleCell_X = matrixBase_X + (u * (BOX_SIZE));
                double singleCell_Y = matrixBase_Y + (i * (BOX_SIZE));

                if(i%2==0){
                    if(u%2==0){
                        isFilled = true;
                    }
                    else{
                        isFilled = false;
                    }

                }else{
                    if(u%2==0){
                        isFilled = false;
                    }
                    else{
                        isFilled = true;
                    }
                }

                buildSingleCell(singleCell_X, singleCell_Y, isFilled);
            }
        }
    }

    private void buildSingleCell(double x, double y, boolean isFilled) {
        GRect myBox = new GRect(x, y, BOX_SIZE, BOX_SIZE);
        myBox.setFilled(true);
        myBox.setFillColor(BORDERS_COLOR);
        add(myBox);

        GRect inBox = new GRect((x+BOX_SPACING), (y+BOX_SPACING), (BOX_SIZE - 2*BOX_SPACING), (BOX_SIZE - 2*BOX_SPACING));
        inBox.setFilled(isFilled);
        inBox.setColor(BOXES_COLOR);
        add(inBox);
    }


}
