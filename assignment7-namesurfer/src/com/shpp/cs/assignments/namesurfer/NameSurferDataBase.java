package com.shpp.cs.assignments.namesurfer;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of 4513 names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class NameSurferDataBase implements NameSurferConstants {
    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) {
        /** Main array of data from current file */
        nameBase = new ArrayList<NameSurferEntry>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String currentLine = br.readLine();
            while (currentLine != null) {
                /** Current readed line data entry */
                NameSurferEntry entry = new NameSurferEntry(currentLine);
                /** Checks if name value of readed entry is correct */
                if (validName(entry.getName())){
                    nameBase.add(entry);
                }
                currentLine = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println("File problems");
        }
    }
    /** Main data base array from input file */
    private ArrayList<NameSurferEntry> nameBase;

    /**
     * Returns the first NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String inputName) {
        NameSurferEntry outEntry = null;
        if(validName(inputName)) {
            for (NameSurferEntry i : nameBase) {
                String name = i.getName();
                inputName = lowerCaseProcess(inputName);
                if (name.equals(inputName)) {
                    outEntry = i;
                    break;
                }
            }
        }
        return outEntry;
    }

    /** Enables to get names from user in any register
     * Gives input name with upper case first letter and lowercase other letters  */
    private String lowerCaseProcess(String name) {
        name = name.toLowerCase();
        String firstLetter = name.substring(0,1);
        firstLetter = firstLetter.toUpperCase();
        String out = firstLetter + name.substring(1);
        return out;
    }

    /** String validation part
     * Checks if entered name is correct
     */
    private boolean validName(String name) {
        boolean validFlag = false;

        if(name == null) {
            validFlag = false;
        }else if (name.equals("")) {
            validFlag = false;
        }else{
            name = name.toLowerCase();
            for (int i = 0; i < name.length(); i++) {
                char letter = name.charAt(i);
                /** If alphabet dosen't contain this letter - break */
                if (!alphabetContain(letter)) {
                    validFlag = false;
                    break;
                }else{
                    validFlag = true;
                }
            }
        }
        return validFlag;
    }

    /** Gives true if this letter belongs to English alphabet */
    private boolean alphabetContain(char letter) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char[] alphabetArray = alphabet.toCharArray();
        boolean containFlag = false;
        for (char i : alphabetArray) {
            if (i == letter) {
                containFlag = true;
                break;
            }
        }
        return containFlag;
    }
}

