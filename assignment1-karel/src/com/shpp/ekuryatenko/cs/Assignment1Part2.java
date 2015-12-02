package com.shpp.ekuryatenko.cs;

/*
 * File: Assignment1Part2.java
 *
 * In this program Karel makes columns according to rules
 *
 */

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part2 extends KarelTheRobot {
    /*
     * Precondition:  Karel stands in east-south point, looks east
     * Result: Karel made all possible columns
     */
    public void run() throws Exception {
        /* Karel builds columns if:
        * - it is east edge of area
        * - it's 1-st, 5-th, 9-th... column of area from west */
        while (frontIsClear()) {
            buildSingleColumn();
            moveToColumnBottom();
            findNextColumn();
        }

        /* Karel builds last column if he is in front of east edge of area */
        buildSingleColumn();
    }

    /*
    * Precondition:  Karel stands at bottom of column, looks east
    * Result: Karel made single column, stands in top of column, looks north
    */
    private void buildSingleColumn() throws Exception {
        /* Karel moves upward while front is clear
        * If he founds empty cell he puts beeper */
        if (leftIsClear()) {
            turnLeft();
        }

        if (noBeepersPresent()) {
            putBeeper();
        }

        while (frontIsClear()) {
            move();
            if (noBeepersPresent()) {
                putBeeper();
            }
        }
    }



    /*
    * Precondition:  Karel stands in top of single column, looks north
    * Result: Karel stands at bottom of single column, looks south
    */
    private void moveToColumnBottom() throws Exception {
        turnAround();
        while (frontIsClear()) {
            move();
        }
    }

    /*
    * Precondition:  Karel stands in bottom of single column, looks south
    * Result: Karel stands at bottom of next single column, looks east
    */
    private void findNextColumn() throws Exception {
        turnLeft();
        /* Karel moves to east. He stops if
         * - it is east edge of area
         * - he is on the next number of column
         * - if he founds beepers trash on his way he pick it */
        for (int i = 0; i < 3; i++) {
            if (frontIsClear()) {
                move();
                if (frontIsClear()) {
                    if (beepersPresent()) {
                        pickBeeper();
                    }
                }
            }
        }
        /* Last move to column */
        if (frontIsClear()) {
            move();
        }

    }

    /*
    * Precondition:  Karel looks in some direction
    * Result: Karel made turn on 180 degrees
    */
    private void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }

}