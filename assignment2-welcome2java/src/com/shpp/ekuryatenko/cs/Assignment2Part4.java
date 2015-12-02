package com.shpp.ekuryatenko.cs;

/*
 * File: Assignment2Part4.java
 *
 * Flag
 *
 */

import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;
import java.lang.*;
import java.awt.*;

public class Assignment2Part4 extends WindowProgram {
    /* Adjusting string of label */
    private static final String FLAG_LABEL = "Flag of Gabon";

    /* Adjusting constants for direction of flag lines
    * - type true if your flag has horizontal lines
    * - type false if your flag has vertical lines*/
    private static final boolean HORIZONTAL_DIRECTION = true;

    /* Adjusting constants for flags sizes */
    private final double FLAG_WIDTH = 0.6 * APPLICATION_WIDTH;
    private final double FLAG_HEIGHT = 0.66 * FLAG_WIDTH;

    /* Adjusting constants for direction of flag lines
     * The direction of lines numbers is:
     * - for horizontal lines - first line is top line
     * - for vertical lines - first line is left line in window */
    private static final Color FIRST_LINE_COLOR = new Color(7, 128, 23);
    private static final Color SECOND_LINE_COLOR = new Color(250, 219, 20);
    private static final Color THIRD_LINE_COLOR = Color.BLUE;

    /*Adjusting constants for size of graphic window */
    public static final int APPLICATION_WIDTH = 900;

    public void run() {
        buildYourFlag();
    }

    /* Builds flag in centre of window. Label is put in corner of window */
    private void buildYourFlag (){
        double centreOfCanvas_X = getWidth()/2;
        double centreOfCanvas_Y = getHeight()/2;

        double flagBase_X = centreOfCanvas_X - FLAG_WIDTH/2;
        double flagBase_Y = centreOfCanvas_Y - FLAG_HEIGHT/2;

        if(HORIZONTAL_DIRECTION) {
            buildNewHorizontalFlag(flagBase_X, flagBase_Y);
        }else {
            buildNewVerticalFlag(flagBase_X, flagBase_Y);
        }

        buildLabel();
    }

    /* BUILD HORIZONTAL FLAG
     * @param x - whole flag base point
     * @param y - whole flag base point */
    private void buildNewHorizontalFlag (double x, double y){
        double secondLineOffset = (FLAG_HEIGHT/3);
        double thirdLineOffset = (2*FLAG_HEIGHT/3);

        buildHorizontalLine(x, y, FIRST_LINE_COLOR);
        buildHorizontalLine(x, (y + secondLineOffset), SECOND_LINE_COLOR);
        buildHorizontalLine(x, (y + thirdLineOffset), THIRD_LINE_COLOR);
    }

    private void buildHorizontalLine(double x, double y, Color lineColor){
        double horizontalLineWidth = FLAG_WIDTH;
        double horizontalLineHeight = FLAG_HEIGHT/3;

        GRect myLine = new GRect(x,y,horizontalLineWidth,horizontalLineHeight);
        myLine.setColor(lineColor);
        myLine.setFillColor(lineColor);
        myLine.setFilled(true);
        add(myLine);
    }

    /*BUILD VERTICAL FLAG
     * @param x - whole flag base point
     * @param y - whole flag base point */
    private void buildNewVerticalFlag (double x, double y){
        double secondLineOffset = (FLAG_WIDTH/3);
        double thirdLineOffset = (2*FLAG_WIDTH/3);

        buildVerticalLine(x,y,FIRST_LINE_COLOR);
        buildVerticalLine((x + secondLineOffset),y,SECOND_LINE_COLOR);
        buildVerticalLine((x + thirdLineOffset),y,THIRD_LINE_COLOR);
    }

    private void buildVerticalLine(double x, double y, Color lineColor){
        double verticalLineWidth = FLAG_WIDTH/3;
        double verticalLineHeight = FLAG_HEIGHT;

        GRect myLine = new GRect(x,y,verticalLineWidth,verticalLineHeight);
        myLine.setColor(lineColor);
        myLine.setFillColor(lineColor);
        myLine.setFilled(true);
        add(myLine);
    }

    /*BUILD LABEL
     *Precondition: whole label has to be in right-down corner of window  */
    private void buildLabel(){
        GLabel myLabel = new GLabel(FLAG_LABEL);
        myLabel.setFont("Serif-30");

        double labelWidth = myLabel.getWidth();
        double labelDescent = myLabel.getDescent();

        double edgeOfCanvas_X = getWidth();
        double edgeOfCanvas_Y = getHeight();

        myLabel.setLocation((edgeOfCanvas_X - labelWidth),(edgeOfCanvas_Y - labelDescent));
        myLabel.setColor(Color.BLACK);
        add(myLabel);
    }
}