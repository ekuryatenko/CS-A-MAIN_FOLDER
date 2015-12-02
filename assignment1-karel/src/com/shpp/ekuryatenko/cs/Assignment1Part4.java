package com.shpp.ekuryatenko.cs;

/*
 * File: Assignment1Part4.java - ver.2
 *
 *=============================================
 * Was improved after Roman Shmelev review
 *
 * In this program Karel makes ñhess board
 * At start area is not cleaned
 *
 */

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part4 extends KarelTheRobot {
    /*
     * Precondition:  Karel stands in south-west cell of the area
     * Result: Karel made rectangle chess board
     */
    public void run() throws Exception {
        /* Clean area from all beepers */
        cleanWholeArea();

        /* Karel makes board in condition:
        * - the area is rectangle
        * - the first filled cell is south-west */
        findFirstCell();
        buildChessBoard();
    }

    /*
     * Precondition:  Karel stands in south-west cell of the area. Area is dirty
     * Result: Karel cleaned whole area
     */
    public void cleanWholeArea() throws Exception {
        /* Cleans first line  */
        cleanSingleLine();
        returnBackToWest();

        /* Cleans other lines  */
        while (frontIsClear()) {
            moveToUpperLine();
            cleanSingleLine();
            returnBackToWest();
        }
    }

    /*
     * Precondition:  Karel stands near west wall, looks east
     * Result: Karel cleaned single line, stands in front of east wall, looks east
     */
    public void cleanSingleLine() throws Exception {
        while (frontIsClear()) {
            cleanSingleCell();
            move();
        }

        /* End cell checking */
        if (frontIsBlocked()) {
            cleanSingleCell();
        }
    }

    /*
  * Precondition:  Karel stands in some cell, looks east
  * Result: Karel cleaned single cell, looks east
  */
    public void cleanSingleCell() throws Exception {
        while (beepersPresent()) {
            pickBeeper();
        }
    }

    /*
     * Precondition:  Karel stands in front of east wall, looks east
     * Result: Karel return to west wall, looks north
     */
    public void returnBackToWest() throws Exception {
        turnToWest();
        moveToWall();
        turnToNorth();
    }

    /*
     * Precondition:  Karel stands in front of west wall, looks north.
     * Result: Karel moved into upper line, looks east
     */
    public void moveToUpperLine() throws Exception {
        if (frontIsClear()) {
            move();
        }
        turnToEast();
    }

    /*
     * Precondition:  Karel stands somewhere in the area.
     * Result: Karel found south-west cell, prepared to building of first line, looks east
     */
    public void findFirstCell() throws Exception {
        /* Moves to south-west cell */
        movesToWestWall();
        movesToSouthWall();
    }

    /*
     * Precondition:  Karel stands somewhere in the area
     * Result: Karel stands in front of west wall, looks west
     */
    public void movesToWestWall() throws Exception {
        turnToWest();
        moveToWall();
    }

    /*
     * Precondition:  Karel stands somewhere in the area
     * Result: Karel stands in front of south wall, looks south
     */
    public void movesToSouthWall() throws Exception {
        turnToSouth();
        moveToWall();
    }

    /*
     * Precondition:  Karel ready to build board, looks east
     * Result: Karel made chess board
     */
    public void buildChessBoard() throws Exception {
        turnToEast();
        putBeeper();
        makeLine();
        returnBackToWest();

        /* While north not blocked make another lines */
        while (frontIsClear()) {
            findNextLine();
            makeLine();
            returnBackToWest();
        }
    }

    /*
    * Precondition:  Karel at beginning of single line, ready to build this line, looks east
    * Result: Karel built single line, stands in front of east wall, looks east
    */
    public void makeLine() throws Exception {
        /* Build line */
        /* Every line begins from full cell */
        if (frontIsClear()) {
            while (frontIsClear()) {
                makeSinglePieceOfLine();
            }
        }
    }

    /*
     * Precondition:  Karel in empty cell, looks east
     * Result: Karel built single piece of line, looks east
     */
    public void makeSinglePieceOfLine() throws Exception {
        /* For building piece he makes steps - put beeper, makes 2 moves */
        if(frontIsClear()) {
            move();

        }
        if (frontIsClear()) {
            move();
            putBeeper();
        }
    }

    /*
    * Precondition:  Karel stands in front of west wall, at start cell of full line, looks north
    * Result: Karel moved into next line, prepares to put first beeper, looks east
    */
    public void findNextLine() throws Exception {
        /* Decides how to start next line:
         * - if down line has beepers in start - he makes 2-nd cell  as start cell  */
        if (beepersPresent()) {
            if(frontIsClear()) {//If up is clear - 1-st step
                moveToUpperLine();
                if (frontIsClear()) {//2-nd step - Move up to 2-nd cell of line
                    move();
                    putBeeper();//and begin line then
                }else{//it's column field
                    returnBackToWest();
                    if(frontIsClear()){
                        moveToUpperLine();
                        putBeeper();
                    }
                }
            }
        }
        else {// - if down line has no beepers in start - he makes 1-st cell of top line as start cell */
            if(frontIsClear()) {
                moveToUpperLine();//and start to build line then
                putBeeper();
            }
        }
    }

    /*==================================================
    *
    * Service methods
    *
    /*==================================================
    /* Result: Karel made some turns, looks east     */
    public void turnToEast() throws Exception {
        while (notFacingEast()) {
            turnLeft();
        }
    }

    /* Result: Karel made some turns, looks west     */
    public void turnToWest() throws Exception {
        while (notFacingWest()) {
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