package com.shpp.ekuryatenko.cs;
/*
 * File: Assignment2Part6.java
 *
 * Caterpillar
 *
 */

import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;
import java.awt.*;

public class Assignment2Part6 extends WindowProgram {

    /* Adjusting quantity of ovals in caterpillar */
    private static final int OVALS_QTY = 10;

    /* Adjusting constants for oval's dimensions */
    private static final double WHOLE_OVAL_DIAM = 200;
    private static final double OVAL_BORDER_WIDTH = 0.02* WHOLE_OVAL_DIAM;

    /*Adjusting constants for ovals moving
    * - OFFSET 1 - distance between ovals in pair. Pair is two ovals - buttom and top
    * - OFFSET 2 - distance between pairs of ovals.*/
    private static final double OFFSET_1_dX = WHOLE_OVAL_DIAM * 0.65;
    private static final double OFFSET_1_dY = WHOLE_OVAL_DIAM * 0.35;
    private static final double OFFSET_2_dX = WHOLE_OVAL_DIAM * 0.5;
    private static final double OFFSET_2_dY = -1 * OFFSET_1_dY;

    /* Adjusting position of first oval in caterpillar*/
    private static final double START_X = 0;
    private static final double START_Y = OFFSET_1_dY;

    /*Adjusting constants for Colors */
    private static final Color IN_OVAL_GREEN = new Color(20, 196, 81);
    private static final Color BORDER_BRAUN = new Color(196, 99, 20);

    public void run() {
        setTitle("Caterpillar");

        buildCaterpillar();
    }

    /*
     * Build Caterpillar for such conditions:
     * - Ovals have to be superpositioned one to another
     * - Color of the oval centre and color of the oval border must be different
     * - It could be possible to change quantity of the caterpillar segments
     */
    private void buildCaterpillar(){
        /* Counter of current ovals coordinates */
        double currentPosition_X = START_X;
        double currentPosition_Y = START_Y;

        for(int i=1; i <= OVALS_QTY; i++) {
            if(i == OVALS_QTY){
                buildFace(currentPosition_X, currentPosition_Y);
            }else {
                buildSingleOval(currentPosition_X, currentPosition_Y);
                currentPosition_X = getNew_X(i, currentPosition_X);
                currentPosition_Y = getNew_Y(i, currentPosition_Y);
            }
        }
    }

    /* Build single oval with border. The border gets by superposition outOval by inOval */
    private void buildSingleOval(double x,double y){
        /* Out oval building */
        GOval outOval = new GOval(x,y, WHOLE_OVAL_DIAM, WHOLE_OVAL_DIAM);
        outOval.setFilled(true);
        outOval.setFillColor(BORDER_BRAUN);
        add(outOval);

        /* Inner oval building */
        double inOvalDiam = WHOLE_OVAL_DIAM - 2 * OVAL_BORDER_WIDTH;
        double inOvalPosition_X = x + OVAL_BORDER_WIDTH;
        double inOvalPosition_Y = y + OVAL_BORDER_WIDTH;

        GOval inOval = new GOval(inOvalPosition_X,inOvalPosition_Y,inOvalDiam,inOvalDiam);
        inOval.setFilled(true);
        inOval.setFillColor(IN_OVAL_GREEN);
        add(inOval);
    }

    /* Build smiling face of Caterpillar - it's two eyes and smile
     * It looks much better with face, as for me */
    private void buildFace(double x,double y){
        buildSingleOval(x, y);

        double smile_X = x + WHOLE_OVAL_DIAM /4;
        double smile_Y = y + WHOLE_OVAL_DIAM /2;
        GArc smile = new GArc(smile_X, smile_Y, WHOLE_OVAL_DIAM /2, WHOLE_OVAL_DIAM /3, 180, 180);
        add(smile);

        double leftEye_X = x + (WHOLE_OVAL_DIAM /2 - WHOLE_OVAL_DIAM /4);
        double rightEye_X = x + (WHOLE_OVAL_DIAM /2 + WHOLE_OVAL_DIAM /4);
        double eyes_Y = y + WHOLE_OVAL_DIAM /4;
        GOval leftEye = new GOval(leftEye_X, eyes_Y,5,5);
        GOval rightEye = new GOval(rightEye_X, eyes_Y,5,5);
        add(leftEye);
        add(rightEye);
    }

    /* Gives next position of oval in x direction */
    private double getNew_X(int iteration, double lastPosition_X){
        if(iteration%2 == 0){
            return lastPosition_X + OFFSET_2_dX;
        }else{
            return lastPosition_X + OFFSET_1_dX;
        }
    }

    /* Gives next position of oval in y direction */
    private double getNew_Y(int iteration, double lastPosition_Y){
        if(iteration%2 == 0){
            return lastPosition_Y - OFFSET_2_dY;
        }else{
            return lastPosition_Y - OFFSET_1_dY;
        }
    }
}