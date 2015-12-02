package com.shpp.ekuryatenko.cs;

/*
 * File: Assignment3Problem4.java
 *
 * Coin's game
 *
 */

import acm.util.RandomGenerator;
import com.shpp.cs.a.console.TextProgram;

public class Assignment3Problem4 extends TextProgram {

    /* Probability constant for random generator */
    private static double PLAYER_1_LUCKY = 0.0;
    private static double PLAYER_2_LUCKY = 0.9;

    private static final int NICE_COINS_INPUT = 0;

    private RandomGenerator rgen = new RandomGenerator();

    public void run() {
        coinsGame();
    }

    /* Precondition:  */
    private void coinsGame() {
        int player_1_bank = enterNiceInt("Player 1 coins = ");
        int player_2_bank = enterNiceInt("Player 2 coins = ");

        boolean player_1_win;
        boolean player_2_win;

        boolean gameNotOver = true;

        println("Game started!!!");

        while (gameNotOver) {
            println("Player 1 has " + player_1_bank + " coins. Player 2 has " + player_2_bank + " coins.");

            player_1_win = randomBoolean(PLAYER_1_LUCKY);
            player_2_win = randomBoolean(PLAYER_2_LUCKY);

            if(player_1_win) {
                if (!player_2_win) {
                    player_1_bank++;
                    player_2_bank--;
                }
            }else{
                if(player_2_win){
                    player_1_bank--;
                    player_2_bank++;
                }
            }

            if (player_1_bank == 0) {
                gameNotOver = false;
                println("Player 2 wins " + player_2_bank + " coins!!!");
            }
            if (player_2_bank == 0) {
                gameNotOver = false;
                println("Player 1 wins " + player_1_bank + " coins!!!");
            }
        }
    }


    private boolean randomBoolean(double probability) {
        return rgen.nextBoolean(probability);
    }

    private int enterNiceInt(String frase) {
        boolean i = true;
        int someInt = 0;
        while (i) {
            try {
                someInt = readInt(frase);

                if (someInt < NICE_COINS_INPUT) {
                    println("You entered too low number! Repeat, please");
                    i = true;
                } else {
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
}