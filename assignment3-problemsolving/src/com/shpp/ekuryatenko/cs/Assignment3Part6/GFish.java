package com.shpp.ekuryatenko.cs.Assignment3Part6;
/*
 * File: GFish.java
 *
 * Fish class for window animation
 *
 */
import acm.graphics.GArc;
import acm.graphics.GCompound;
import acm.graphics.GPolygon;

public class GFish extends GCompound {
    /* The number of pixels to shift the fish in canvas on each cycle */
    private double fishDelta_dX = -0.5;
    private double fishDelta_dY = 0;

    /* Settings for fish's tail swinging */
    private double tailRotateDirection = 1;
    private static final double TAIL_ROTATE_RANGE = 20;
    private double currentRotateCounter = 20;

    /* My class main object */
    private GArc bodyTop;
    private GArc bodyBottom;
    private GPolygon tail;

    /* Constructor for fish body */
    public GFish() {
        /* Manual fish's body adjustment */
        bodyTop = new GArc(40,40,45,90);
        bodyBottom = new GArc(0,-29,40,40,225,90);
        tail = new GPolygon();
        tail.addVertex(0,0);
        tail.addVertex(10, -5);
        tail.addVertex(10,5);
        tail.addVertex(0, 0);
        tail.movePolar(33,-9.5);

        add(bodyTop);
        add(bodyBottom);
        add(tail);
    }

    /* Tail movements at method invocation - up-down */
    public void fishHailTurns() {
        tail.rotate(tailRotateDirection);

        if(currentRotateCounter < 0) {
             tailRotateDirection = tailRotateDirection * -1;
            currentRotateCounter = TAIL_ROTATE_RANGE;
        }else{
            currentRotateCounter = currentRotateCounter - 1;
        }
    }

    /* Fish movements in canvas */
    public void fishMovement() {
        bodyTop.move(fishDelta_dX, fishDelta_dY);
        bodyBottom.move(fishDelta_dX, fishDelta_dY);
        tail.move(fishDelta_dX, fishDelta_dY);
    }
}

