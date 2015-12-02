package com.shpp.ekuryatenko.cs;

/*
 * File: Assignment3Part5.java
 *
 * S-Petersburg's game
 *
 */

import acm.util.RandomGenerator;
import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part5 extends TextProgram {

    /* Probability constant for random generator */
    private static double PROBABILITY = 0.8;

    private RandomGenerator rgen = new RandomGenerator();

    public void run() {
        sPetersburgGame();
    }

    /* Precondition:
     * 2 Players: first - put miney to the bank, second - drops coin and takes the prise
     * second player takes bank if it's tail on the coin
     * first - put money to the bank if it's head  */
    private void sPetersburgGame() {
        int bank = 1;//the first bet is $1
        int prise = 0;
        int tails = 0;
        boolean head;//the coin prototype - true if head
        boolean gameNotOver = true;

        while (gameNotOver) {
            head = randomBoolean();

            if (head) {
                bank += bank;
            } else {
                tails++;
                println("This game, you earned $" + bank);
                prise += bank;
                println("Your total is $" + prise);
                bank = 1;

                /* Game is over when the second player win more then $20 */
                if (prise >= 20) {
                    println("It took " + tails + " games to earn more than $20");
                    gameNotOver = false;
                }
            }
        }
    }

    private boolean randomBoolean() {
        return rgen.nextBoolean(PROBABILITY);
    }
}