package com.shpp.ekuryatenko.cs.Assignment3Part6;
/*
 * File: GWave.java
 *
 * Prototype class for sea waves for window animation
 *
 */
import acm.graphics.GPolygon;

import java.awt.*;

public class GWave extends GPolygon {

    /* Wave shape's adjusting */
    private static final int ARCS_QTY = 5;
    private static final int ARC_WIDTH = 20;
    private static final int ARC_HEIGHT = 10;
    private static final int ARC_1_ANGLE = 0;
    private static final int ARC_2_ANGLE = -180;

    private static final Color WAVE_COLOR = new Color(103, 206, 215);

    /* wave move counter */
    public int currentMoveCounter = 0;
    /* The number of pixels to shift the wave on each cycle */
    public double currentDelta_dX = 0;//defined in run method of program
    public double currentDelta_dY = 0;//defined in run method of program
    private static final int REVERSE_PERIOD = 30;

    private GPolygon myWave;

    public GWave(double x, double y, double canvasWidth, double canvasHeight) {
        this.myWave = buildWave(x, y, canvasWidth, canvasHeight);
    }

    public GPolygon getMyWave() {
        return myWave;
    }

    public void waveMovement() {
        if (currentMoveCounter < 0) {
            currentDelta_dX *= -1;
            currentDelta_dY *= -1;
            currentMoveCounter = REVERSE_PERIOD;
        } else {
            currentMoveCounter--;
        }
        myWave.move(currentDelta_dX, currentDelta_dY);
    }

    /* Create shape of wave */
    private GPolygon buildWave(double x, double y, double canvasWidth, double canvasHeight) {
        /* Shape of wave tied to real canvas dimenisions */
        int arcsInWave =  ((int)canvasWidth / ARCS_QTY);

        /* Wave polygon creating */
        GPolygon wave = new GPolygon(x, y);
        wave.addVertex(0, 0);
        for (int i = 0; i < arcsInWave; i++) {
            wave.addArc(-ARC_WIDTH, ARC_HEIGHT, ARC_1_ANGLE, ARC_2_ANGLE);
        }
        wave.addArc(-ARC_WIDTH, ARC_HEIGHT, ARC_1_ANGLE, ARC_2_ANGLE);
        wave.addVertex((canvasWidth + ARC_WIDTH), (canvasHeight));
        wave.addVertex(0, canvasHeight);
        wave.setFilled(true);
        wave.setColor(WAVE_COLOR);

        return wave;
    }
}
