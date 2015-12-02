package com.shpp.ekuryatenko.cs;
/*
 * File: Assignment5Part2.java
 * ======================================================================
 * Add numeric strings method
 */

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part2 extends TextProgram {


    public void run() {
        AlgorismAlgorithms();
    }

    private void AlgorismAlgorithms() {
        /** Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number:  ");
            String n2 = readLine("Enter second number: ");

            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the numbers represented by those strings and
     * returns the result.
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        /** Initiate stores for kinds of numbers */
        char[] n1Char;
        char[] n2Char;
        int[] n1Int;
        int[] n2Int;

        int resultSize;

        /** Choose what number is longer to send to next methods */
        if (n1.length() <= n2.length()) {
            n1Char = n1.toCharArray();
            n2Char = n2.toCharArray();
            n1Int = convertCharToIntArray(n1Char);
            n2Int = convertCharToIntArray(n2Char);
            resultSize = n2.length() + 1;
        } else {
            n1Char = n2.toCharArray();
            n2Char = n1.toCharArray();
            n1Int = convertCharToIntArray(n1Char);
            n2Int = convertCharToIntArray(n2Char);
            resultSize = n1.length() + 1;
        }
        int[] resultInt = addIntArrays(n1Int, n2Int, resultSize);
        char[] resultChar = convertIntToCharArray(resultInt);

        return String.valueOf(resultChar);
    }

    private int[] convertCharToIntArray(char[] charArray) {
        int[] intArray = new int[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            intArray[i] = charArray[i] - '0';
        }
        return intArray;
    }

    private char[] convertIntToCharArray(int[] intArray) {
        char[] charArray = new char[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            charArray[i] = (char) (intArray[i] + '0');
        }
        return charArray;
    }
    /** @param n1Int it has to have smaller length then n2Int
     *  @param n2Int
     *  @param resultSize length of result integer array */
    private int[] addIntArrays(int[] n1Int, int[] n2Int, int resultSize) {
        int[] resultArray = new int[resultSize];

        int previousRest = 0;
        int nextRest = 0;
        int lastN2IntIndex = 0;
        int lastResultIndex = 0;

        /** Summing of arrays
         *  Precondition:
         *  - At first summarize digits in arrays
         *  - begins from end of n1Int array and move to n1Int start
         *  - then, if rest of sum remain it adds in 2-nd part to rest of n2Int
         *  - after ending of n2Int array - the rest adds to begin of result
         */

        /**  1-st part - runs by smallest, n1Int, array - adds one digit to another */
        for (int i = (n1Int.length - 1); i >= 0; i--) {
            /** next result digit's index */
            int resultIndex = (resultSize - 1) - (n1Int.length - 1 - i);
            lastResultIndex = resultIndex;
            /** next n2Int digit's index */
            int n2IntIndex = (n2Int.length - 1) - (n1Int.length - 1 - i);
            lastN2IntIndex = n2IntIndex;

            previousRest = nextRest;
            resultArray[resultIndex] = simpleAdditionResult((n1Int[i] + previousRest), n2Int[n2IntIndex]);
            nextRest = restOfAddition((n1Int[i] + previousRest), n2Int[n2IntIndex]);
        }
        /** 2-nd part - finalize to run along biggest, n2Int, array by adding rest */
        for (int i = (lastN2IntIndex - 1); i >= 0; i--) {
            int resultIndex = (lastResultIndex - 1) - (lastN2IntIndex - 1 - i);
            lastResultIndex = resultIndex;
            int n2IntIndex = (lastN2IntIndex - 1) - (lastN2IntIndex - 1 - i);
            lastN2IntIndex = n2IntIndex;

            previousRest = nextRest;
            resultArray[resultIndex] = simpleAdditionResult((0 + previousRest), n2Int[n2IntIndex]);
            nextRest = restOfAddition((0 + previousRest), n2Int[n2IntIndex]);
        }
        /** 3-nd part - finalize result array by adding rest to start */
        resultArray[lastResultIndex - 1] = nextRest;

        if (resultArray[0] == 0) {
            resultArray = shiftLeft(resultArray, 1);
            int[] result1 = cutCellsFromRight(resultArray, 1);
            return result1;
        } else {
            return resultArray;
        }
    }

    /** Gives simple sum of 2 single digits, without decade part */
    private int simpleAdditionResult(int n1, int n2) {
        int sumRest = n1 + n2;

        if (sumRest < 10) {
            return sumRest;
        } else {
            return sumRest - 10;
        }
    }

    /** Gives rest of sum */
    private int restOfAddition(int n1, int n2) {
        int sumRest = n1 + n2;

        if (sumRest < 10) {
            return 0;
        } else {
            return 1;
        }
    }

    /** Shift cells of array to start */
    public int[] shiftLeft(int[] array, int shiftValue) {
        int arraySize = array.length;
        for (int i = 0; i < shiftValue; i++) {
            int buf = array[0];
            for (int j = 0; j < (arraySize - 1); j++) {
                array[j] = array[j + 1];
            }
            array[arraySize - 1] = buf;
        }
        return array;
    }

    /** Removes cells from the end of array */
    public int[] cutCellsFromRight(int[] array, int cutCells) {
        int[] newArray = new int[array.length - cutCells];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

}