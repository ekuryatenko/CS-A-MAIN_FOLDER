package com.shpp.ekuryatenko.cs.Assignment3Part6;
/*
 * File: GSmoke.java
 *
 * Ship's smoke class for window animation
 *
 */
import acm.graphics.GOval;

import java.awt.*;

public class GSmoke extends GOval {
    /* Settings of smoke's deminisions */
    private static final double SMOKE_WIDTH = 6;
    private static final double SMOKE_HEIGHT = 2;
    private static final Color FIRST_SMOKE_COLOR = new Color(77, 72, 75);

    /* Settings for removing smokes */
    private static int ALL_SMOKES_COUNTER = 0;
    private int THIS_SMOKE_COUNTER = 0;
    private static final int SMOKES_LIFE = 6;

    /* Constructor for single smoke cloud */
    public GSmoke(double x, double y) {
        super(x, y, SMOKE_WIDTH, SMOKE_HEIGHT);
        super.setFilled(true);
        super.setColor(FIRST_SMOKE_COLOR);

        /* Static counter */
        ALL_SMOKES_COUNTER = ALL_SMOKES_COUNTER + 1;
        THIS_SMOKE_COUNTER = ALL_SMOKES_COUNTER;
    }

    /* Gives flag for this cloud destroying */
    public boolean getOffFlag() {
        if(ALL_SMOKES_COUNTER - THIS_SMOKE_COUNTER > SMOKES_LIFE){
            return true;
        }else{
            return false;
        }
    }

    /* Kind of cloud destroying */
    public void setOff(){
        super.setVisible(false);
    }
}
