package com.shpp.ekuryatenko.cs;

/*
 * File: Assignment3Part1.java
 *
 * Cardiovaculyar
 *
 */

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part1 extends TextProgram {

    /* Adjusting constants */
    private static final int ANALYSE_PERIOD = 7;
    private static final int VACULAR_DEMAND = 5;
    private static final int PRESURE_DEMAND = 3;

    /* Programm parametrs */
    private int thisDayMinutes = 0;
    private int vacularCount = 0;
    private int presureCount = 0;

    public void run() {
        cardiovaculyarAnalysing();
    }

    /* Precondition:
     * - user enters time he have spent for training last 7 days
     * Result:
     * - if this time isn't enough - program print how many days he has to training more
     * - also program prints is it was enough days for blood preasure  */
    private void cardiovaculyarAnalysing() {
        inputCycle();

        println();

        cardioAnalysys();
        presureAnalysys();
    }

    private void inputCycle() {
        for (int i = 1; i <= ANALYSE_PERIOD; i++) {
            thisDayMinutes = enterNumber(i);

            if (thisDayMinutes >= 30) {
                vacularCount++;
                if (thisDayMinutes >= 40) {
                    presureCount++;
                }
            }
        }
    }

    /* User input */
    private int enterNumber(int day) {
        return enterRightInt("How many minutes did you do on day " + day + "? ");
    }

    private int enterRightInt(String frase) {
        boolean i = true;
        int someInt = 0;
        while (i) {
            try {
                someInt = readInt(frase);

                if (someInt < 0) {
                    println("You entered wrong value!");
                }else {
                    i = false;
                }
            } catch (Exception e) {
                println("You entered very wrong value!");
                i = true;
                readLine();
            }
        }
        return someInt;
    }

    private void presureAnalysys() {
        println("Blood pressure:");
        if (presureCount >= PRESURE_DEMAND) {
            println("  Great job! You've done enough exercise to keep a low blood pressure.");
        } else {
            int extraDays = PRESURE_DEMAND - presureCount;

            println("  You needed to train hard for at least " + extraDays + " more day(s) a week!");
        }
    }

    private void cardioAnalysys() {
        println("Cardiovacular health:");
        if (vacularCount >= VACULAR_DEMAND) {
            println("  Great job! You've done enough exercise for cardiovacular health.");
        } else {
            int extraDays = VACULAR_DEMAND - vacularCount;

            println("  You needed to train hard for at least " + extraDays + " more day(s) a week!");
        }
    }
}