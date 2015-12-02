package com.shpp.ekuryatenko.cs.Assignment3Part6;
/*
 * File: GWave.java
 *
 * Class for ship object for window animation
 *
 */

import acm.graphics.GPolygon;

import java.awt.*;

public class GShip extends GPolygon {
    /* The number of pixels to shift the ship on each cycle */
    private static final double DELTA_X = 1;
    private static final double DELTA_Y = -0.3;

    /* the ship different changes */
    private static final double SHIP_RISE_TIME = 100;
    public double rotateDirection;
    private double risingCounter;
    private double rotateAngle;


    private static final int SMOKES_DISTANCE = 10;
    private int currentDistanceToNextSmoke = SMOKES_DISTANCE;

    private GPolygon ship;

    public GShip(double x, double y) {
        this.ship = buildShip(x, y);
        risingCounter = SHIP_RISE_TIME/2;
        rotateDirection = 1;
        rotateAngle = 0;
    }

    public GPolygon getShip() {
        return ship;
    }

    public double getShip_X() {
        return ship.getX();
    }

    public double getShip_Y() {
        return ship.getY();
    }

    public boolean getSmoke() {
        if (currentDistanceToNextSmoke < 0) {
            currentDistanceToNextSmoke = SMOKES_DISTANCE;
            return true;//made smoke!
        } else {
            currentDistanceToNextSmoke--;
            return false;
        }
    }

    public void shipMovement() {
        ship.rotate(currentRotateAngle());
        ship.move(DELTA_X, (DELTA_Y * currentRotateAngle()));
    }

    private GPolygon buildShip(double x, double y) {
        GPolygon ship = new GPolygon(x, y);
        ship.addVertex(3, 35);//1
        ship.addVertex(60, 35);//2
        ship.addVertex(70, 10);//3
        ship.addVertex(10, 15);//4
        ship.addVertex(10, 5);//5
        ship.addVertex(3, 5);//6
        ship.addVertex(3, 15);//7
        ship.addVertex(0, 15);//8
        ship.addVertex(3, 35);//1

        ship.setFilled(true);
        ship.setFillColor(Color.GRAY);

        return ship;
    }

    private double currentRotateAngle() {

        if (risingCounter > 0) {

            if ((risingCounter < 0.2 * SHIP_RISE_TIME)&&(risingCounter > 0.15 * SHIP_RISE_TIME)) {
                rotateAngle = 1;
            } else if ((risingCounter < 0.4 * SHIP_RISE_TIME)&&(risingCounter > 0.35 * SHIP_RISE_TIME))  {
                rotateAngle = 1;
            } else if ((risingCounter < 0.6 * SHIP_RISE_TIME)&&(risingCounter > 0.55 * SHIP_RISE_TIME)) {
                rotateAngle = 2;
            } else if ((risingCounter < 0.8 * SHIP_RISE_TIME)&&(risingCounter > 0.75 * SHIP_RISE_TIME)) {
                rotateAngle = 1;
            } else {
                rotateAngle = 0;
            }
        }

        if (risingCounter < 0) {
            risingCounter = SHIP_RISE_TIME;
            rotateDirection *= -1;
        }
        else {
            risingCounter--;
        }

        return rotateAngle * rotateDirection;
    }
}
