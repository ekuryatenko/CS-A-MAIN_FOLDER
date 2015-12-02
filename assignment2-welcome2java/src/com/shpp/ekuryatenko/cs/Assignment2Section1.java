package com.shpp.ekuryatenko.cs;
/*
 * File: Assignment2Section1.java
 *
 * Robot face
 *
 */

import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;
import java.awt.*;

public class Assignment2Section1 extends WindowProgram {

    /*Adjusting constants for robot face */
    private static final double HEAD_WIDTH = 600;
    private static final double HEAD_HEIGHT = 200;
    private static final double EYE_RADIUS = 30;
    private static final double APPLE_EYE_RADIUS = 10;
    private static final double MOUTH_WIDTH = 0.7 * HEAD_WIDTH;
    private static final double MOUTH_HEIGHT = 20;
    private static final double HEAD_BORDER_WIDTH = 0.05 * HEAD_WIDTH;

    /*Adjusting constants for Colors */
    private static final Color FACE_COLOR = Color.GRAY;
    private static final Color MOUTH_COLOR = Color.ORANGE;
    private static final Color FACE_BORDER_COLOR = Color.BLACK;
    private static final Color WHOLE_EYE_COLOR = Color.WHITE;
    private static final Color APPLE_EYE_COLOR = Color.GREEN;


    /*Adjusting constants for robot eyes position */
    private static final double EYE_TO_CENTER_dX = HEAD_WIDTH / 4;
    private static final double EYE_TO_TOP_dY = HEAD_HEIGHT / 4;

    /*Adjusting constants for robot mouth position */
    private static final double MOUTH_TO_BOTTOM_dY = HEAD_HEIGHT / 4;

    /* The default width and height of the window.
     */
    public static final int APPLICATION_WIDTH = 370;
    public static final int APPLICATION_HEIGHT = 320;

    public void run() {
        setTitle("Robot face");

        createRobotFace();
    }

    /*
     * Preconditions:
     * - Mouth is always centered
     * - Eyes are built symmetrically due to center axis
     * - Head has a border
     * - user input nice values of APPLICATION_WIDTH, APPLICATION_HEIGHT constants
     */
    private void createRobotFace() {
        double canvasWidth = getWidth();
        double canvasHeight = getHeight();

        double headBasePoint_X = (canvasWidth - HEAD_WIDTH) / 2;
        double headBasePoint_Y = (canvasHeight - HEAD_HEIGHT) / 2;

        double headCenterPoint_X = headBasePoint_X + HEAD_WIDTH/2;

        double leftEyeCenterPoint_X = headCenterPoint_X + EYE_TO_CENTER_dX;
        double rightEyeCenterPoint_X = headCenterPoint_X - EYE_TO_CENTER_dX;
        double eyesCenterPoint_Y = headBasePoint_Y +EYE_TO_TOP_dY;

        double mouthBasePoint_X = headBasePoint_X + (HEAD_WIDTH - MOUTH_WIDTH) / 2;
        double mouthBasePoint_Y = headBasePoint_Y + (HEAD_HEIGHT - MOUTH_TO_BOTTOM_dY - MOUTH_HEIGHT);


        buildRobotHead(headBasePoint_X, headBasePoint_Y);

        buildRobotEyes(leftEyeCenterPoint_X, eyesCenterPoint_Y);
        buildRobotEyes(rightEyeCenterPoint_X, eyesCenterPoint_Y);

        buildRobotMouth(mouthBasePoint_X, mouthBasePoint_Y);
    }

    private void buildRobotHead(double headBasePoint_x, double headBasePoint_y) {
        GRect wholeHeadOfRobot = new GRect(HEAD_WIDTH, HEAD_HEIGHT);
        wholeHeadOfRobot.setFilled(true);
        wholeHeadOfRobot.setColor(FACE_BORDER_COLOR);
        add(wholeHeadOfRobot, headBasePoint_x, headBasePoint_y);

        GRect inFaceOfRobot = new GRect((HEAD_WIDTH - 2*HEAD_BORDER_WIDTH), (HEAD_HEIGHT - 2*HEAD_BORDER_WIDTH));
        inFaceOfRobot.setFilled(true);
        inFaceOfRobot.setColor(FACE_COLOR);
        add(inFaceOfRobot, (headBasePoint_x + HEAD_BORDER_WIDTH), (headBasePoint_y  + HEAD_BORDER_WIDTH));
    }

    private void buildRobotEyes(double eyeCenterPoint_x, double eyeCenterPoint_y) {
        GOval wholeEye = new GOval (2*EYE_RADIUS,2*EYE_RADIUS);
        wholeEye.setFilled(true);
        wholeEye.setFillColor(WHOLE_EYE_COLOR);

        double eyeBase_X = eyeCenterPoint_x - EYE_RADIUS;
        double eyeBase_Y = eyeCenterPoint_y - EYE_RADIUS;

        add(wholeEye,eyeBase_X,eyeBase_Y);

        GOval appleEye = new GOval (2*APPLE_EYE_RADIUS,2*APPLE_EYE_RADIUS);
        appleEye.setFilled(true);
        appleEye.setColor(APPLE_EYE_COLOR);

        add(appleEye, (eyeBase_X + (EYE_RADIUS / 4)), (eyeBase_Y + (EYE_RADIUS/2)));
    }

    private void buildRobotMouth(double mouthBasePoint_x, double mouthBasePoint_y) {
        GRect mouth = new GRect(MOUTH_WIDTH, MOUTH_HEIGHT);
        mouth.setFilled(true);
        mouth.setFillColor(MOUTH_COLOR);

        add(mouth, mouthBasePoint_x, mouthBasePoint_y);
    }
}
