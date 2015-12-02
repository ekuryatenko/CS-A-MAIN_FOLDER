package com.shpp.ekuryatenko.cs;

/*
 * File: Assignment3Part3.java
 *
 * Rising to a power
 *
 */

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part3 extends TextProgram {

    private static final int ROUND_PRECISION = 1000;

    public void run() {
        raisingToPowerTask();
    }

    /* User enters values for "raise to power equation", and the programm returns result of equation  */
    private void raisingToPowerTask() {
        double baseValue = enterNiceDouble("Enter your base ");
        int exponentValue = enterNiceInt("Enter your exponent ");
        double result = raiseToPower(baseValue, exponentValue);
        println("Answer is " + roundDouble(result));
    }

    private double raiseToPower(double base, int exponent) {
        double result = 0;

        if (exponent == 0) {
            result = 1;
        } else if (exponent > 0) {
            result = base;
            for (int i = 1; i < exponent; i++) {
                result *= base;
            }
        } else if (exponent < 0) {
            double result1 = base;
            for (int i = exponent + 1; i < 0; i++) {
                result1 *= base;
            }
            result = 1 / result1;
        }
        return result;
    }

    private int enterNiceInt(String frase) {
        boolean i = true;
        int someInt = 0;
        while (i) {
            try {
                someInt = readInt(frase);
                i = false;

            } catch (Exception e) {
                println("You entered wrong value!");
                i = true;
                readLine();
            }
        }
        return someInt;
    }

    private double enterNiceDouble(String frase) {
        boolean i = true;
        double someDouble = 0;
        while (i) {
            try {
                someDouble = readDouble(frase);
                i = false;
            } catch (Exception e) {
                println("You entered wrong value!");
                i = true;
                readLine();
            }
        }
        return someDouble;
    }

    /* Double round method   */
    private double roundDouble(double myDouble) {
        double buf = myDouble * ROUND_PRECISION;
        double result100 = Math.round(buf);
        return result100 / ROUND_PRECISION;
    }
}