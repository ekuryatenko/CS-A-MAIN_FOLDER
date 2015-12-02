package com.shpp.ekuryatenko.cs;
/*
 * File: Assignment5Part3Ext.java
 * ======================================================================
 * Reverse of american roads game - get possible sequences of letters
 * for input dictionary
 *
 * Result of programm is 2009 kinds of sequences exist without matches in
 * American roads rules
 */

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part3Ext extends TextProgram {


    public void run() {
        reverseAmericanRoadsGame();
    }

    /** Preconditions:
     * - runs search of all unValid letters sequences for "dictionary.txt" file
     * - there are NO suitable words in file for such letters sequences
     *  */
    private void reverseAmericanRoadsGame() {

        ArrayList<String> noValidSequencesArray = new ArrayList<String> ();
        AlphabetCounter alphabetCounter = new AlphabetCounter();
        ArrayList<String> dictionary = readArrayFromFile("dictionary.txt");

            println("For your dictionary there are such sequences which have no suitable words: ");

            int i = 0;

            while(!alphabetCounter.getSecondTurnFlag()) {
                String currentSequence = alphabetCounter.getSequence();
                //String currentSequence = "agx";
                ArrayList<String> currentValidWordsArray = sequentWordsArrayFromArray(dictionary,currentSequence);

                if(currentValidWordsArray.isEmpty()){
                    i++;
                    noValidSequencesArray.add(currentSequence);
                    println(i + " - " + currentSequence);
                }
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



class AlphabetCounter {
    private static final String ALPHABET = "abcdefghijklmnopqrstuwxyz";
    private int index0;
    private int index1;
    private int index2;
    private boolean secondTurnFlag;

    public AlphabetCounter (){
        index0 = 0;
        index1 = 0;
        index2 = 0;
        secondTurnFlag = false;
    }
    /** Gives next turn alphabet sequence  */
    public String getSequence(){
        countersProcess();
        char[] sequence = extractCharSequence();
        return String.valueOf(sequence);
    }

    private char[] extractCharSequence() {
        char[] sequence = new char[3];
        sequence[0] = getLetterAtIndex(ALPHABET, index0);
        sequence[1] = getLetterAtIndex(ALPHABET, index1);
        sequence[2] = getLetterAtIndex(ALPHABET, index2);

        return sequence;
    }

    private void countersProcess() {

        if (index2 >= 24) {
            index2 = 0;
            if (index1 >= 24) {
                index1 = 0;
                if (index0 >= 24) {
                    index0 = 0;
                    secondTurnFlag = true;
                } else {
                    index0++;
                }
            } else {
                index1++;
            }
        } else {
            index2++;
        }

    }

    private char getLetterAtIndex(String alphabet, int index) {
        return alphabet.charAt(index);
    }

    /** Shows that all possble alphabet sequences were given and counting restarted */
    public boolean getSecondTurnFlag(){
        return secondTurnFlag;
    }
}