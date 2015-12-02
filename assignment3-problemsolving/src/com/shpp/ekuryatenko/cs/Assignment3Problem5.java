package com.shpp.ekuryatenko.cs;

/*
 * File: Assignment3Problem5.java
 *
 * Sunset animation
 *
 */

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment3Problem5 extends WindowProgram {
    /*Adjusting constants for graphic window size*/
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 900;

    private static final int PAUSE_TIME = 100;

    /* Adjusting constants for colors */
    private static final Color SUN_START_COLOR = new Color(253, 255, 43);
    private static final Color EARTH_START_COLOR = Color.GREEN;
    private static final Color SKY_START_COLOR = new Color(15, 227, 217);

    private static final double SKY_TO_EARTH = 0.7;
    private static final double SUN_DIAMETR= 0.1 * APPLICATION_HEIGHT;

    private static final int COLOR_COUNT_MAX = 10;
    private int colorCount;

    private GRect sky;
    private GRect earth;
    private GOval sun;

    private Color skyColor;
    private Color earthColor;
    private Color sunColor;

    public void run() {
        sunsetAnimation();
    }

    private void sunsetAnimation() {
        initiatePicture();

        while(sun.getY() < getHeight()){
            sunMoving();

            if(sun.getY() > 0.5 * getHeight()) {
                colorsChanging();
            }

            pause(PAUSE_TIME);
        }
    }

    private void initiatePicture(){
        double skyWidth = getWidth();
        double skyHeght = SKY_TO_EARTH * getHeight();
        sky = buildRect(0, 0, skyWidth, skyHeght, SKY_START_COLOR);
        add(sky);

        double sunBase_X = (getWidth() - SUN_DIAMETR)/2;
        double sunBase_Y = (getHeight() - SUN_DIAMETR)/2;
        sun = buildOval(sunBase_X, sunBase_Y, SUN_DIAMETR, SUN_DIAMETR, SUN_START_COLOR);
        add(sun);

        double earthWidth = getWidth();
        double earthHeight = (1 - SKY_TO_EARTH) * getHeight();
        double earthBase_X = 0;
        double earthBase_Y = SKY_TO_EARTH * getHeight();
        earth = buildRect(earthBase_X, earthBase_Y, earthWidth, earthHeight, EARTH_START_COLOR);
        add(earth);

        colorCount = COLOR_COUNT_MAX;
    }

    private GRect buildRect(double x, double y, double width, double height, Color rectColor){
        GRect myRect = new GRect(x,y,width,height);
        myRect.setFilled(true);
        myRect.setColor(rectColor);
        return myRect;
    }

    private GOval buildOval(double x, double y, double width, double height, Color rectColor) {
        GOval myOval = new GOval(x, y, width, height);
        myOval.setFilled(true);
        myOval.setColor(rectColor);
        return myOval;
    }

    private void sunMoving(){
        sun.move(1, 3);
    }

    private void colorsChanging(){
        if(colorCount < 0 ) {
            sky.setColor(sky.getColor().darker());
            earth.setColor(earth.getColor().darker());
            colorCount = COLOR_COUNT_MAX;
        }else{
            colorCount--;
        }
    }
}