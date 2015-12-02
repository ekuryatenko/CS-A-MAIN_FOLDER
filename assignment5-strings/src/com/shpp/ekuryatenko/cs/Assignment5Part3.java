package com.shpp.ekuryatenko.cs;
/*
 * File: Assignment5Part3.java
 * ======================================================================
 * American roads
 */

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part3 extends TextProgram {


    public void run() {
        americanRoadsGame();
    }

    /**
     * Precondition:
     * - User enters 3-letters sequence of words for american roads game
     * - Method sequentWordFromFile() has to discover words in file which match do american roads conditions
     */
    private void americanRoadsGame() {
        /** Sit in a loop, reading letters and adding them. */
        ArrayList<String> validWordsArray;
        while (true) {
            String letters = readLine("Enter 3 letter:  ");
            println("For your combination " + letters + " suitable words are: ");

            /** Prepare letters to process */
            letters = processInput(letters);
            /** Get valid array of input sequence words */
            validWordsArray = sequentWordFromFile("dictionary.txt", letters);
            /** Output for user */
            printArray(validWordsArray);
            println();
        }
    }

    /** Gives array of words from file which match to this letters sequence */
    private ArrayList<String> sequentWordFromFile(String file, String letters) {
        ArrayList<String> fileArray = readArrayFromFile(file);
        ArrayList<String> validWordsArray = sequentWordsArrayFromArray(fileArray, letters);

        return validWordsArray;
    }

    /** @return words array from file */
    private ArrayList<String> readArrayFromFile(String filename) {
        ArrayList<String> fileArray = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            while (true) {
                /** Read this entry, stopping if no more data remains. */
                String line = br.readLine();

                if (line == null)
                    break;

                /** Create array from file  */
                fileArray.add(line);
            }

            br.close();
        } catch (IOException e) {
            println(":-(");
        }
        return fileArray;
    }

    /** Gives array of valid words from array which match to this letters sequence */
    private ArrayList<String> sequentWordsArrayFromArray(ArrayList<String> testArray, String letters) {
        String validWord;
        ArrayList<String> validSequentWords = new ArrayList<String>();

        for (int i = 0; i < testArray.size(); i++) {
            String testArrayWord = testArray.get(i);
            validWord = singleWordSequentChecking(testArrayWord, letters);
            boolean valid = (!validWord.equals("-1"));

            if (valid) {
                validSequentWords.add(validWord);
            }
        }
        return validSequentWords;
    }

    /**
     * @return valid word if it matches to simpleLetters condition
     * Else - returns string "-1"
     */
    private String singleWordSequentChecking(String arrayWord, String simpleLetters) {

        String validWord = "-1";
        char[] simpleLettersChar = simpleLetters.toCharArray();
        boolean containsAllLetters = ifContainsAllChecking(arrayWord, simpleLettersChar);

        /** Main arrayWord checking */
        if (containsAllLetters) {/** There is sense to check this word */
        /** Process of letters in sequence:
         * - searchig letters appearance in word */
            int indexOf_0 = arrayWord.indexOf(simpleLettersChar[0]);
            int indexOf_1 = (arrayWord.indexOf(simpleLettersChar[1], (indexOf_0 + 1)));
            int indexOf_2 = (arrayWord.indexOf(simpleLettersChar[2], (indexOf_1 + 1)));

            if (indexOf_0 >= 0) {
                if (indexOf_1 >= 0) {
                    if(indexOf_2 >= 0) {
                        validWord = arrayWord;
                    }
                }
            }
       } else {
            validWord = "-1";
        }

        return validWord;
    }

    /** If all letters are present in word */
    private boolean ifContainsAllChecking(String arrayWord, char[] simpleLettersChar) {
        boolean containsAll = false;
        int indexOf_0 = arrayWord.indexOf(simpleLettersChar[0]);
        int indexOf_1 = arrayWord.indexOf(simpleLettersChar[1]);
        int indexOf_2 = arrayWord.indexOf(simpleLettersChar[2]);

        if (indexOf_0 >= 0) {
            if (indexOf_1 >= 0) {
                if (indexOf_2 >= 0) {
                    containsAll = true;
                }
            }
        } else {
                containsAll = false;
            }
            return containsAll;
        }

        private void printArray(ArrayList<String> array) {
            for (String i : array) {
                println(i);
            }
        }

        /** Prepares user input to process */
        private String processInput(String letters) {
            letters = letters.toLowerCase();
            return letters;
        }

    }