package com.shpp.ekuryatenko.cs;

/*
 * File: Assignment1Part3.java
 *
 * In this program Karel finds middle of the south side of some (almost) square area
 * This area is empty
 *
 */

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part3 extends KarelTheRobot {

    /*
     * Precondition:  Karel stands in east-south point, looks east
     * Result: Karel stands in the middle of the south side
     */
    public void run() throws Exception {
        /* Karel build diagonal line of area by beepers */
        makeBigDiagonal();

        /* Karel goes down to south side */
        moveToBottom();

        /* Karel "makes" another diagonal of area to the point of connection with first diagonal */
        moveToBigDiagonal();

        /* From connection of diagonals Karel moves down to the south side -
         * to the middle of south side and mark it */
        makeMiddleMarker();

        /* Karel picks all extra beepers */
        cleanArea();

        moveToBottom();

        /* Karel takes place in the middle cell of the south side */
        moveToMarker();
    }

    /*
     * Precondition:  Karel stands in east-south point, looks east
     * Result: Karel stands at the end of big diagonal, which he has made -
     * this place is in front of east edge of area, he looks east
     */
    private void makeBigDiagonal() throws Exception {
        putBeeper();

        /* Karel makes diagonal by beepers */
        while (frontIsClear()) {
            makeDiagonalStep();
            putBeeper();
        }
    }

    /*
     * Precondition:  Karel stands in some point, looks east
     * Result: Karel makes one step in diagonal direction (1 move east, 1 move up), looks east
     */
    private void makeDiagonalStep() throws Exception {

        if (frontIsClear()) {
            move();
            turnLeft();
        }
        if (frontIsClear()) {
            move();
            turnRight();
        }
        else {
            turnRight();
        }
    }

    /*
     * Precondition:  Karel looks in some direction
     * Result: Karel makes made turn on 90 degrees clockwise
     */
    private void turnRight() throws Exception {
        turnLeft();
        turnLeft();
        turnLeft();
    }

    /*
     * Precondition:  Karel stands at the end of big diagonal, in front of east edge, looks east
     * Result: Karel stands in south-east corner, looks south
     */
    private void moveToBottom() throws Exception {
        /* Turns to south */
        turnRight();

        while (frontIsClear()) {
            move();
        }
    }

    /*
     * Precondition: Karel stands in south - east corner, looks south
     * Result: Karel stands at the diagonals crossing point, looks west
     */
    private void moveToBigDiagonal() throws Exception {
        turnRight();

        /*
        * Makes diagonal movements while there is no beepers
        */
        while (noBeepersPresent()) {
            turnRight();
            move();
            turnLeft();
            if (noBeepersPresent()) {
                move();
            }
        }
    }
    /*
     * Precondition: Karel stands in the diagonals crossing point
     * Result: Karel moved down, on the south line and made marker of the middle of the line, looks south
     */
    private void makeMiddleMarker() throws Exception {
        turnToSouth();

        /* Moves to the south line */
        while (frontIsClear()) {
            move();
        }

        /* Makes marker of the middle on the south line, just below point of diagonals crossing */
        putBeeper();
    }

    /*
     * Precondition: Karel looks in some direction
     * Result: Karel looks directly to south
     */
    private void turnToSouth() throws Exception {
        while (notFacingSouth()) {
            turnLeft();
        }
    }

    /*
     * Precondition: Karel stands in the middle of the south line, looks south
     * Result: Karel picked all trash, stands at the end of "big diagonal", looks east
     */
    private void cleanArea() throws Exception {
        moveToSouthWestCorner();

        /* Turns east */
        turnRight();
        turnRight();

        /* Takes first beeper of the diagonal */
        pickBeeper();

        /* Moves along "big diagonal", picks beepers */
        while (frontIsClear()) {
            makeDiagonalStep();
            pickBeeper();
        }
    }

    /*
     * Precondition: Karel stands in the middle of the south line, looks south
     * Result: Karel stands in the south-west corner, looks west
     */
    private void moveToSouthWestCorner() throws Exception {
        turnRight();
        while (frontIsClear()) {
            move();
        }
    }

    /*
     * Precondition: Karel stands in south - est corner, looks south
     * Result: Karel stands at marker point - in the middle of the south line
     */
    private void moveToMarker() throws Exception {
        turnRight();

        while (noBeepersPresent()) {
            move();
        }
        pickBeeper();

    }


}