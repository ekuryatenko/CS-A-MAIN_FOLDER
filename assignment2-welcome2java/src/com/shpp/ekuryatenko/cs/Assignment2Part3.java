package com.shpp.ekuryatenko.cs;/*
 * File: Assignment2Part2.java
 *
 * Prints pawprints
 *
 */

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/* Result:
 * - Two pawprints are built in window in dimension of constants table
 */
public class Assignment2Part3 extends WindowProgram {
    /* Constants controlling the relative positions of the
     * three toes to the upper-left corner of the pawprint.  
     */
    private static final double FIRST_TOE_OFFSET_X = 0;
    private static final double FIRST_TOE_OFFSET_Y = 20;
    private static final double SECOND_TOE_OFFSET_X = 30;
    private static final double SECOND_TOE_OFFSET_Y = 0;
    private static final double THIRD_TOE_OFFSET_X = 60;
    private static final double THIRD_TOE_OFFSET_Y = 20;

    /* The position of the heel relative to the upper-left
     * corner of the pawprint.  
     */
    private static final double HEEL_OFFSET_X = 20;
    private static final double HEEL_OFFSET_Y = 40;

    /* Each toe is an oval with this width and height. */
    private static final double TOE_WIDTH = 20;
    private static final double TOE_HEIGHT = 30;

    /* The heel is an oval with this width and height. */
    private static final double HEEL_WIDTH = 40;
    private static final double HEEL_HEIGHT = 60;

    /* The colors of the print */
    public static final Color TOE_COLOR = Color.BLACK;
    public static final Color HEEL_COLOR = Color.BLACK;

    /* The default width and height of the window.
     */
    public static final int APPLICATION_WIDTH = 470;
    public static final int APPLICATION_HEIGHT = 420;



    public void run() {
        drawPawprint(20, 20);
        drawPawprint(180, 70);
    }

    /*
     * Draws a pawprint. The parameters should specify the upper-left corner of the
     * bounding box containing that pawprint.
     *
     * @param x The x coordinate of the upper-left corner of the bounding box for the pawprint.
     * @param y The y coordinate of the upper-left corner of the bounding box for the pawprint.
     */
    private void drawPawprint(double x, double y) {
        drawToe((x+FIRST_TOE_OFFSET_X),(y+FIRST_TOE_OFFSET_Y));
        drawToe((x+SECOND_TOE_OFFSET_X),(y+SECOND_TOE_OFFSET_Y));
        drawToe((x+THIRD_TOE_OFFSET_X),(y+THIRD_TOE_OFFSET_Y));
        drawHeel((x+HEEL_OFFSET_X),(y+HEEL_OFFSET_Y));
    }

    /* Draws single toe in window.*/
    private void drawToe(double x, double y) {
        GOval singleToe = new GOval(x,y,TOE_WIDTH,TOE_HEIGHT);
        singleToe.setFilled(true);
        singleToe.setFillColor(TOE_COLOR);
        add(singleToe);
    }

    /* Draws single heel in window.*/
    private void drawHeel(double x, double y) {
        GOval singleHeel = new GOval(x,y,HEEL_WIDTH,HEEL_HEIGHT);
        singleHeel.setFilled(true);
        singleHeel.setFillColor(HEEL_COLOR);
        add(singleHeel);
    }
}