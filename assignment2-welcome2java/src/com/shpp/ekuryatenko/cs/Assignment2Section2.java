package com.shpp.ekuryatenko.cs;

/*
 * File: Assignment2Section2.java
 *
 * Bazz Buzz Game
 *
 */
import com.shpp.cs.a.console.TextProgram;

public class Assignment2Section2 extends TextProgram {

    public void run() {
        bazzBuzzGame();
    }

    private void bazzBuzzGame() {
        int myNumber = enterNumber();

        fizzBuzzCountFor(myNumber);
    }

    /* Input of number */
    private int enterNumber() {
        int inputNumber = enterRightInt("Please enter your number:  ");
        return inputNumber;
    }

    private int enterRightInt(String frase) {
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

    private void fizzBuzzCountFor(int myNumber) {
        for (int i = 0; i < myNumber; i++) {


                if ((i % 3 == 0)&&(i % 5 == 0)){
                    println("Buzz");
                }else if(i % 3 == 0){
                    println("Fizz");
                }else if(i % 5 == 0){
                    println("Bazz");
                }else{
                    println(i);
                }


        }
    }

}