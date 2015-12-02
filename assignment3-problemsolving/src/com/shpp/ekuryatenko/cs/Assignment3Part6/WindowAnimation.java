package com.shpp.ekuryatenko.cs.Assignment3Part6;
/*
 * File: WindowAnimation.java
 *
 * Runs WindowAnimation programm
 *
 */
import acm.graphics.GObject;
import com.shpp.cs.a.graphics.WindowProgram;

import java.util.Iterator;

public class WindowAnimation extends WindowProgram {
    /* The number of milliseconds to pause on each cycle */
    private static final int PAUSE_TIME = 20;
    private static final int WHOLE_PROGRAM_TIME = PAUSE_TIME * 500;
    private int programTimeCounter;

    /* The default width and height of the window.  */
    public static final int APPLICATION_WIDTH = 300;
    public static final int APPLICATION_HEIGHT = 300;

    private GShip myShip;
    private final double SHIP_Y = 0.4 * APPLICATION_HEIGHT;

    private GWave wave1;
    public GWave wave2;
    private final double WAVE1_MOVE_dX = -0.3;
    private final double WAVE2_MOVE_dY = -0.1;
    private final double WAVES_Y = SHIP_Y + 18;

    public GBird bird1;
    public GBird bird2;
    public GBird bird3;
    private final double BIRDS_GROUP_Y = 0.2 * APPLICATION_HEIGHT;

    public GFish fish1;
    public GFish fish2;
    public GFish fish3;
    private final double FISHS_GROUP_Y = 0.7 * APPLICATION_HEIGHT;
    private final double FISH_MOVE_dX = -0.5;
    private final double FISH_MOVE_dY = 0;

    public void init(){
        myShip = new GShip(0, SHIP_Y);

        wave1 = new GWave(-20,WAVES_Y, getWidth(),getHeight());
        wave1.currentDelta_dX = WAVE1_MOVE_dX;

        wave2 = new GWave(-10,WAVES_Y, getWidth(),getHeight());
        wave2.currentDelta_dY = WAVE2_MOVE_dY;

        bird1 = new GBird();
        bird2 = new GBird();
        bird3 = new GBird();
        fish1 = new GFish();
        fish2 = new GFish();
        fish3 = new GFish();

        add(myShip.getShip());
        add(wave1.getMyWave());
        add(wave2.getMyWave());
        add(bird1,getWidth(), BIRDS_GROUP_Y);
        add(bird2,(getWidth() - 100), BIRDS_GROUP_Y);
        add(bird3,(getWidth() + 100), BIRDS_GROUP_Y);
        add(fish1, getWidth(), FISHS_GROUP_Y);
        add(fish2, (getWidth() + 10), (FISHS_GROUP_Y - 20));
        add(fish3, (getWidth() + 20),(FISHS_GROUP_Y + 20));

        programTimeCounter = 0;
    }

    public void run() {
        while(programTimeCounter < WHOLE_PROGRAM_TIME){
            myShip.shipMovement();


            wave1.waveMovement();
            wave2.waveMovement();
            bird1.birdMovement();
            bird2.birdMovement();
            bird3.birdMovement();
            bird1.birdWingsTurns();
            bird2.birdWingsTurns();
            bird3.birdWingsTurns();
            fish1.fishMovement();
            fish2.fishMovement();
            fish3.fishMovement();
            fish1.fishHailTurns();
            fish2.fishHailTurns();
            fish3.fishHailTurns();

            if(myShip.getSmoke()){
                add(new GSmoke((myShip.getShip_X()+5), (myShip.getShip_Y() - 4)));
            }

            cleanRedundantSmokes();

            programTimeCounter += PAUSE_TIME;
            pause(PAUSE_TIME);
        }
    }

    /* Uses canvas iterator to find smoke ovals wich 
     * has to be removed from window (they have too long life) */
    public void cleanRedundantSmokes(){
        Iterator<GObject> i = iterator();
        while(i.hasNext()){
            GObject someObject = i.next();
            if(someObject instanceof GSmoke){
                GSmoke someSmoke = (GSmoke)someObject;
                if(someSmoke.getOffFlag()){
                   someSmoke.setOff();
                }
            }
        }
    }
}
