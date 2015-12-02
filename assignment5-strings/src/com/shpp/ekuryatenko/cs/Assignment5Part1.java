package com.shpp.ekuryatenko.cs;
/*
 * File: Assignment5Part1.java
 * ======================================================================
 * Syllables in English word counting
 */

import com.shpp.cs.a.console.TextProgram;
import java.util.*;

public class Assignment5Part1 extends TextProgram {

    public void run() {
        syllableCounting();
    }

    private void syllableCounting() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesIn(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the heuristic specified in the
     * handout.
     *
     * @param word A string containing a single word.
     *
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesIn(String word) {
        ArrayList<Character> vowelsList = new ArrayList<Character>();
        vowelsList.add('a');
        vowelsList.add('e');
        vowelsList.add('i');
        vowelsList.add('o');
        vowelsList.add('u');
        vowelsList.add('y');

        word = word.toLowerCase();
        char[] charArray = word.toCharArray();

        /** Flag - rise if word has some vowels except "e" at end */
        boolean wordWithoutVowels = true;
        /** Main syllables counter */
        int syllQty = 0;

        for (int i = 0; i < charArray.length; i++) {

            /** Conditions flags processing */
            /** Flag - rise if this letter is vowel */
            boolean thisIsVowel = vowelsList.contains(charArray[i]);
            /** Flag - rise if next letter exist in this word */
            boolean nextExist = ((i + 1) <= (charArray.length - 1));
            /** Flag - rise if this is "e" */
            boolean thisIs_E = (charArray[i] == 'e');
            /** Flag - rise if  at end of word */
            boolean next_E_AtEnd = false;
            /** Flag - rise if  next word isn't vowel */
            boolean nextIsNotVowel = false;

            /** Service flags processing */
            if (nextExist) {
                /** Finds if next is not vowel */
                if (!(vowelsList.contains(charArray[i + 1]))) {
                    nextIsNotVowel = true;
                }
                /** Next is "e" */
                if(charArray[i + 1] == 'e'){
                    /** Next is end letter */
                    if(((i+1) == (charArray.length - 1))) {
                        next_E_AtEnd = true;
                    }
                }
            }

            /** Main syllables counting */
            if (thisIsVowel) {
                /** If next letter exist in this word */
                if (nextExist) {
                    /** This word has some letters except "e" at end */
                    wordWithoutVowels = false;
                    /** If next letter is not vowel too or next is "e" */
                    if (nextIsNotVowel || next_E_AtEnd) {
                        syllQty++;
                    }
                /** If this is end letter */
                } else {
                    /** "E" at the end but word has no other syllables */
                    if (thisIs_E ) {
                        if(wordWithoutVowels) {
                            syllQty++;
                        }
                        /** Some other syllable at end */
                    }else {
                        syllQty++;
                    }
                }
            }
        }

        return syllQty;
    }

}