package com.shpp.ekuryatenko.cs;

/*
 * File: Assignment1Part1.java
 *
 * In this program Karel takes newspaper from street
 *
 */

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part1 extends KarelTheRobot {
    /*
     * Precondition:  Karel stand in start point, looks east
     * Result: Karel goes out from home, takes newspaper, and returns home
     */
    public void run() throws Exception {
        moveToDoor();
        manageWithNewspaper();
        moveToStartPoint();
    }

    /*
     * Precondition:  Karel stand in start point, looks east
     * Result: Karel found door, she is from left of him
     */
    private void moveToDoor() throws Exception {
        moveToEastWall();

        /* Stops Karel near the door */
        findDoor();
    }

    /*
     * Precondition:  Karel stand in start point, looks east
     * Result: Karel stand in front of east wall, looks east
     */
    private void moveToEastWall() throws Exception {
        while (frontIsClear()) {
            move();
        }
    }

    /*
     * Precondition:  Karel stand in front of east wall, looks east
     * Result: Karel stands near the door, she is on his left side, Karel looks south
     */
    private void findDoor() throws Exception {
        /* Turns to south*/
        turnRight();

        /* Moves Karel along wall to the door */
        while (leftIsBlocked()) {
            move();
        }
    }

    /*
     * Precondition: Karel needs to turn right
     * Result: Karel turned clockwise on 90 degrees
     */
    private void turnRight() throws Exception {
        turnLeft();
        turnLeft();
        turnLeft();
    }

    /*
     * Precondition: Karel looks south, the door left from him
     * Result: Karel took newspaper and come into home for 1 step, looks west
     */
    private void manageWithNewspaper() throws Exception {
        takeNewspaper();
        returnToHome();
    }

    /*
     * Precondition: Karel looks south, the door left from him
     * Result: Karel took newspaper, looks east
     */
    private void takeNewspaper() throws Exception {
        /* Moves out to street*/
        turnLeft();
        move();
        move();

        /* Takes newspaper*/
        pickBeeper();
    }

    /*
     * Precondition: Karel took newspaper, looks east
     * Result: Karel come into home for 1 step, looks west
     */
    private void returnToHome() throws Exception {
        turnRight();
        turnRight();
        move();
        move();
    }

    /*
     * Precondition: Karel came into home for 1 step, looks west
     * Result: Karel came to start point
     */
    private void moveToStartPoint() throws Exception {
        moveToNorthWall();
        moveToWestWall();
    }

    /*
     * Precondition: Karel came into home for 1 step, looks west
     * Result: Karel came to north wall, looks north
     */
    private void moveToNorthWall() throws Exception {
        turnRight();
        while (frontIsClear()) {
            move();
        }
    }

    /*
     * Precondition: Karel came to north wall, looks north
     * Result: Karel came to west wall - to west-north corner - to start point
     */
    private void moveToWestWall() throws Exception {
        turnLeft();
        while (frontIsClear()) {
            move();
        }
    }

}