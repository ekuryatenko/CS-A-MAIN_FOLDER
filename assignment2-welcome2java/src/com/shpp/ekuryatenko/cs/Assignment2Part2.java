package com.shpp.ekuryatenko.cs;/*
 * File: Assignment2Part2.java
 *
 * Illusion with black circles
 *
 */

import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/* Result:
 * - Four circles have to be positioned right on corners of graphic window
 * - All circles are cut by white rectangle
 * - The size of graphic window could be changed
 * - For convenience the main dimensions are given in constatnts
 */
public class Assignment2Part2 extends WindowProgram {

    public static final int CIRCLE_DIAM = 50;

    /* Constants controling size of graphic window */
    public static final int APPLICATION_WIDTH = 700;
    public static final int APPLICATION_HEIGHT = 770;

    /* Service constants controling colors of figures */
    public static final Color CIRCLES_COLOR = Color.BLACK;
    public static final Color RECT_COLOR = Color.WHITE;

    public void run() {
        /* At first it is checking if main constants are correct */
        if(areConstatntsCorrect()) {
            /* Find sizes of canvas in fact */
            double realCanvasWidth = getWidth();
            double realCanvasHeight= getHeight();

            /* Find base positions for circles due to real graphic window dimensions
             * Circles numbers begins in base of window, and counts clockwise */
            GPoint CIRCLE1_POSITION = new GPoint(0,0);
            GPoint CIRCLE2_POSITION = new GPoint((realCanvasWidth - CIRCLE_DIAM),0);
            GPoint CIRCLE3_POSITION = new GPoint((realCanvasWidth - CIRCLE_DIAM),(realCanvasHeight - CIRCLE_DIAM));
            GPoint CIRCLE4_POSITION = new GPoint(0,(realCanvasHeight - CIRCLE_DIAM));

            buildNewCircle(CIRCLE1_POSITION);
            buildNewCircle(CIRCLE2_POSITION);
            buildNewCircle(CIRCLE3_POSITION);
            buildNewCircle(CIRCLE4_POSITION);

            buildNewRect(realCanvasWidth, realCanvasHeight);

        }else
        {
            /* Prints some frase in case of false constants dimensions */
            buildNewLabel("False circles dimensions");
        }
    }

    /* Checks if all circles could be put in graphic window
     * Sorry, I haven't managed to write it better */
    public boolean areConstatntsCorrect(){
        if((APPLICATION_WIDTH > (2*CIRCLE_DIAM))||(APPLICATION_HEIGHT > (2*CIRCLE_DIAM))) {
            return true;
        }
        else {
            return false;
        }
    }

    /* Build rectangle */
    public void buildNewRect(double canvasWidth, double canvasHeight){
        /* - Base position of rectangle strictly is taken from centre of Circle 1
        *  - Dimensions of rectangle are taken from real canvas dimensions, to hit into centre of Circle 3
        */
        double rectWidth = canvasWidth - CIRCLE_DIAM;
        double rectHeight = canvasHeight - CIRCLE_DIAM;

        GRect myRect = new GRect(CIRCLE_DIAM/2,CIRCLE_DIAM/2,rectWidth,rectHeight);
        myRect.setFilled(true);
        myRect.setColor(RECT_COLOR);
        add(myRect);
    }

    /* Build single circle */
    public void buildNewCircle(GPoint myPoint) {
        GOval oval = new GOval(CIRCLE_DIAM, CIRCLE_DIAM);
        oval.setFillColor(CIRCLES_COLOR);
        oval.setFilled(true);
        oval.setColor(CIRCLES_COLOR);
        oval.setLocation(myPoint);
        add(oval);
    }

    /* Build single label */
    public void buildNewLabel(String myFrase) {
        GLabel myLabel = new GLabel(myFrase);
        myLabel.setLocation(0,100);
        myLabel.setFont("Verdana-50");
        myLabel.setColor(Color.BLACK);
        add(myLabel);
    }
}
