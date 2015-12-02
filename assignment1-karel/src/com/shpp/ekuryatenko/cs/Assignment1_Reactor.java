package com.shpp.ekuryatenko.cs;

/*
 * File: Assignment1_Reactor.java
 *
 * In this program Karel cleans Reactor
 *
 */

import com.shpp.karel.KarelTheRobot;

public class Assignment1_Reactor extends KarelTheRobot {
    /*
     * Precondition:  Karel stands near west wall of the reactor
     *                He is on the middle of the first element, looks east
     *                Condition to clean element is empty middle cel
     * Result: Karel cleaned reactor and stands in near east wall
     */
    public void run() throws Exception {
        /* Cleans all other elements of reactor */
        while(frontIsClear()){
            cleanElement();
            moveToNextReactorElement();
        }
        /* Cleans edge element of reactor */
            cleanElement();
    }

    /*
    * Precondition:  Karel on the middle of the first element, looks east
    * Result: Karel cleaned single element, stands in corridor, looks east
    */
    public void cleanElement() throws Exception {
        /* At first he checks if he founds element correctly
         * Right element hac no beepers on the middle
         * Also he checks is he found position of element right */
        if (noBeepersPresent()) {
            if (leftIsClear()) {
                moveToTop();
                cleanCell();
                moveToBottom();
                cleanCell();
                moveToCorridor();
            }
        }
    }

    /*
    * Precondition:  Karel into the element column, looks east
    * Result: Karel stands in top cell of single element, looks north
    */
    public void moveToTop() throws Exception {
        turnToNorth();
        moveToWall();
    }

    /*
    * Precondition:  Karel into the element cell
    * Result: Karel cleaned the element cell
    */
    public void cleanCell() throws Exception {
        while(beepersPresent()){
            pickBeeper();
        }
    }

    /*
    * Precondition:  Karel in the top of the element line
    * Result: Karel stands in bottom cell of single element
    */
    public void moveToBottom() throws Exception {
        turnToSouth();
        moveToWall();
    }

    /*
    * Precondition:  Karel stands in bottom cell of single element
    * Result: Karel stands in corridor cell of single element, looks east
    */
    public void moveToCorridor() throws Exception {
        turnToNorth();
        move();
        turnToEast();

    }

    /*
    * Precondition:  Karel stands in corridor cell of single element, looks east
    * Result: Karel moved to corridor cell of next single element, looks east
    */
    public void moveToNextReactorElement() throws Exception {
        if (frontIsClear()) {
            move();
            if (frontIsClear()) {
                move();
            }
        }
    }

    /* Result: Karel made some turns, looks east     */
    public void turnToEast() throws Exception {
        while (notFacingEast()) {
            turnLeft();
        }
    }

    /* Result: Karel made some turns, looks south   */
    public void turnToSouth() throws Exception {
        while (notFacingSouth()) {
            turnLeft();
        }
    }

    /* Result: Karel made some turns, looks north  */
    public void turnToNorth() throws Exception {
        while (notFacingNorth()) {
            turnLeft();
        }
    }

    /* Result: Karel made some moves to achieve nearest wall  */
    public void moveToWall() throws Exception {
        while (frontIsClear()) {
            move();
        }
    }

}