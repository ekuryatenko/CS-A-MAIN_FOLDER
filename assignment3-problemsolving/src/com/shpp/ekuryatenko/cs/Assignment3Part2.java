package com.shpp.ekuryatenko.cs;

/*
 * File: Assignment3Part2.java
 *
 * Hail-numbers
 *
 */

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part2 extends TextProgram {

    public void run() {
        hailNumberTask();
    }

    /* Precondition:
     * - user enters single number
     * Result:
     * - this number counted in some conditions  */
    private void hailNumberTask() {
        int mainNumber = enterNumber();

        hailNumberCounting(mainNumber);
    }

    /* User input */
    private int enterNumber() {
        return enterRightInt("Enter a number: ");
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

    private void hailNumberCounting(int mainNumber) {
        while (mainNumber != 1) {
            int newNumber = 0;

            if(mainNumber%2 == 0) {
                newNumber = mainNumber / 2;
                println(mainNumber + " is even so I take half: " + newNumber);
                mainNumber = newNumber;
            }else{
                newNumber = 3 * mainNumber + 1;
                println(mainNumber + " is odd so I make 3n + 1: " + newNumber);
                mainNumber = newNumber;
            }
        }

        if(mainNumber == 1){
            println("End");
        }
    }

}