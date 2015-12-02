package com.shpp.ekuryatenko.cs.Assignment3Part6;
/*
 * File: GBird.java
 *
 * Bird class for window animation
 *
 */
import acm.graphics.GCompound;
import acm.graphics.GLine;
import acm.graphics.GPoint;

public class GBird extends GCompound {
    /* Bird's dimenisions */
    private static final double WING_SIZE_X = 5;
    private static final double WING_SIZE_Y = 10;

    /* The number of pixels to shift the bird in canvas on each cycle */
    public double birdDelta_dX = -1;
    public double birdDelta_dY = 0;

    /* Settings for wings swinging */
    private double swingDirection = 1;
    private static final double SWING_RANGE = 20;
    private double currentSwingCounter = 20;

    /* Wings of bird */
    private GLine leftWing;
    private GLine rightWing;

    /* Constructor for bird body */
    public GBird() {
        leftWing = new GLine(-WING_SIZE_X, -WING_SIZE_Y, 0, 0);
        rightWing = new GLine(WING_SIZE_X, -WING_SIZE_Y, 0, 0);

        add(leftWing);
        add(rightWing);
    }

    /* Wing's movements at method invocation - up-down */
    public void birdWingsTurns() {
        if (currentSwingCounter < 0) {
            swingDirection = swingDirection * -1;
            currentSwingCounter = SWING_RANGE;
        } else {
            GPoint leftEnd = leftWing.getStartPoint();
            GPoint rightEnd = rightWing.getStartPoint();

            double newEnd_left_x = leftEnd.getX();
            double newEnd_left_y = leftEnd.getY() + swingDirection;
            double newEnd_right_x = rightEnd.getX();
            double newEnd_right_y = rightEnd.getY() + swingDirection;

            leftWing.setStartPoint(newEnd_left_x, newEnd_left_y);
            rightWing.setStartPoint(newEnd_right_x, newEnd_right_y);
            currentSwingCounter = currentSwingCounter - 1;
        }
    }

    /* movement into the canvas */
    public void birdMovement() {
        leftWing.move(birdDelta_dX, birdDelta_dY);
        rightWing.move(birdDelta_dX, birdDelta_dY);
    }
}
