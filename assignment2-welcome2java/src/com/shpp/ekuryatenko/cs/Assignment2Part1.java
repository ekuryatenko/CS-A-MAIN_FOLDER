package com.shpp.ekuryatenko.cs;
/*
 * File: Assignment2Part1.java
 *
 * Solving of quadratic equation from console
 *
 */

import com.shpp.cs.a.console.TextProgram;

/* - Programm controls how correct koeficiens are entered
 * - Output double result could be round by roundDouble method  */
public class Assignment2Part1 extends TextProgram {

    /* Initiate main koeficients variables*/
    private static double koef_A = 0;
    private static double koef_B = 0;
    private static double koef_C = 0;

    public void run() {
        enterKoeficients();

        if (equationIsQuadratic()) {
            solveQuadraticEquation();
        } else {
            println("This equation is not quadratic. Program is finished! ***");
        }
    }

    /* Input of main coeficients of equation */
    private void enterKoeficients() {
        koef_A = enterRightDouble("Please enter a:  ");
        koef_B = enterRightDouble("Please enter b:  ");
        koef_C = enterRightDouble("Please enter c:  ");
    }

    /* Input of single koeficient.
     * Precondition: user has to enter right symbol, else method will ask input symbol again */
    private double enterRightDouble(String frase) {
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

    /* Equation is quadratic if koef_A is not equal zero */
    private boolean equationIsQuadratic() {
        if (koef_A == 0) {
            return false;
        } else {
            return true;
        }
    }

    /* Main equations method   */
    private void solveQuadraticEquation() {
        /* Find discriminant */
        double discriminant = ((koef_B * koef_B) - (4 * koef_A * koef_C));

        /* For service calculations find square root of discriminant*/
        double discriminantSqrt = Math.sqrt(discriminant);

        /* Main equation */
        if (discriminant > 0) {
            double root_X1 = ((((-1) * koef_B) + discriminantSqrt) / (2 * koef_A));
            double root_X2 = ((((-1) * koef_B) - discriminantSqrt) / (2 * koef_A));
            println("There is two roots: " + roundDouble(root_X1) + " and " + roundDouble(root_X2));
        }
        if (discriminant == 0) {
            double root_X1 = (((-1) * koef_B) / (2 * koef_A));
            println("There is one root: " + roundDouble(root_X1));
        }
        if (discriminant < 0) {
            println("There are no real roots");
        }
    }

    /* Double round method   */
    private double roundDouble(double myDouble) {
        double buf = myDouble * 100;
        double result100 = Math.round(buf);
        return result100 / 100;
    }
}
