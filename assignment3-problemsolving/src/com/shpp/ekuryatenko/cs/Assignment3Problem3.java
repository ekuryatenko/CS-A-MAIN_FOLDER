package com.shpp.ekuryatenko.cs;

/*
 * File: Assignment3Problem3.java
 *
 * Pension savings counting
 *
 */

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Problem3 extends TextProgram {

    private static final int ROUND_PRECISION = 1;
    private static final int CURRENT_YEAR = 2015;
    private static final double SAVING_PROCENT = 7.5;

    public void run() {
        pensionCounting();
    }

    /* Precondition:
     *  */
    private void pensionCounting() {
        int retireYear = enterNiceInt("What year do you plan to retire? ");
        int firstSavingsYear = enterNiceInt("What year do you plan to start saving? ");
        double oneYearPayment = enterNiceDouble("How much $ per year do you plan to save? ");
        double currentYearSaving = 0;

        int yearsOfSaving = retireYear - firstSavingsYear;
        for(int i = 0; i < yearsOfSaving; i++){
            currentYearSaving += oneYearPayment;
            currentYearSaving *= (1 + (SAVING_PROCENT/100));
        }

        println("In " + retireYear + ", you'd have around $" + roundDouble(currentYearSaving));
    }

    private double enterNiceDouble(String frase) {
        boolean i = true;
        double someDouble = 0;
        while (i) {
            try {

                someDouble = readDouble(frase);

                if(someDouble < 0){
                    println("You can't save such money! Repeat, please");
                    i = true;
                }
                else {
                    i = false;
                }
            } catch (Exception e) {
                println("You entered wrong value!");
                i = true;
                readLine();
            }
        }
        return someDouble;
    }

    private int enterNiceInt(String frase) {
        boolean i = true;
        int someInt = 0;
        while (i) {
            try {
                someInt = readInt(frase);

                if(someInt < CURRENT_YEAR){
                    println("You entered wrong year! Repeat, please");
                    i = true;
                }
                else {
                    i = false;
                }

            } catch (Exception e) {
                println("You entered wrong value!");
                i = true;
                readLine();
            }
        }
        return someInt;
    }


    /* Double round method   */
    private int roundDouble(double myDouble) {
        double buf = myDouble * ROUND_PRECISION;
        double result100 = Math.round(buf);
        return (int) result100 / ROUND_PRECISION;
    }
}
