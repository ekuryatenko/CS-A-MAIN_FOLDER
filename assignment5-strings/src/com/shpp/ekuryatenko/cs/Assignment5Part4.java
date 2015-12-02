package com.shpp.ekuryatenko.cs;
/*
 * File: Assignment5Part4.java - ver.2
 * ======================================================================
 * CSV parsing
 */

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part4 extends TextProgram {
    private static final int COLUMNS_IN_FILE = 3;
    private static final String CSV_FILE = "testCSV.csv";


    public void run() {
        cSVParsing();
    }

    /**
     * Precondition:
     * - Some table is coded in CSV File
     * - File created due to all CSV rules
     * Result:
     * - Method extractColumn() is created - it get certain column from CSV file
     * - If file haven't found method extractColumn() returns null
     */
    private void cSVParsing() {
        String filename = CSV_FILE;
        while (true) {
            int columnIndex = readInt("Choose column - from 0 to " + (COLUMNS_IN_FILE - 1) + " :");

            ArrayList<String> columnArray = extractColumn(filename, columnIndex);
            for (int i = 0; i < columnArray.size(); i++) {
                println(columnArray.get(i));
            }
            println();
        }
    }

    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        /** Create  service array of lines from file */
        ArrayList<String> stringsArray = readArrayFromFile(filename);
        /** Create  create main 2D table array  */
        ArrayList<ArrayList<String>> tableArray = createTableArray(stringsArray);
        return extractColumnFromTableArray(tableArray, columnIndex);
    }

    /**
     * Create  create 2D table array of cells
     * @param stringsArray array of strings - cells are not discovered yet
     */
    private ArrayList<ArrayList<String>> createTableArray(ArrayList<String> stringsArray) {
        /** Initiate result array */
        ArrayList<ArrayList<String>> resultArray = new ArrayList<ArrayList<String>>();
        for (String i : stringsArray) {
            ArrayList<String> cellsArray = fieldsIn(i);
            resultArray.add(cellsArray);
        }
        return resultArray;
    }

    /** @return or array of string from file or null if there are problems with file */
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
            println(null);
        }
        return fileArray;
    }

    /**
     * @param tableArray 2D array of discovered CSV cells
     * @param index      index of column we want to extract
     * @return array of strings - each string is single cell content
     */
    private ArrayList<String> extractColumnFromTableArray(ArrayList<ArrayList<String>> tableArray, int index) {
        ArrayList<String> resultColumnArray = new ArrayList<String>();
        for (int i = 0; i < tableArray.size(); i++) {
            ArrayList<String> rowArray = tableArray.get(i);
            resultColumnArray.add(rowArray.get(index));
        }
        return resultColumnArray;
    }

    /**
     * Gets string line and discovers pure CSV elements in it
     * Goes though line from param and search commas and quotes
     * Adds new cell to arrayList
     * The most terrible part of this program
     */
    private ArrayList<String> fieldsIn(String line) {
        ArrayList<String> resultArray = new ArrayList<String>();

        /** Variables to store edge indexes of current cell */
        int startOfCellIndex;
        int endOfCellIndex;

        /** Kind of search service element
         * Content of pare is analyzed in main loop */
        char[] pare = new char[2];
        /** Flag - rise if new cell is started by comma, is put down if current cell is finished */
        boolean waitFinishByComma = false;
        /** Flag - rise if new cell is started by quotes, is put down if current cell is finished */
        boolean waitFinishByQuotes = false;

        /** Start of line CSV condition - start of first cell */
        pare[0] = line.charAt(0);
        if ((pare[0] == '\"')) {
            startOfCellIndex = 0;
            waitFinishByQuotes = true;
        } else {
            startOfCellIndex = 0;
            waitFinishByComma = true;
        }

        /** Main logic loop - cuts substrings due to CSV conditions  */
        for (int i = 1; i < line.length(); i++) {
            pare[0] = pare[1];
            pare[1] = line.charAt(i);

            /** Kinds of flags to simplify code reading */
            boolean endLine = (i == (line.length() - 1));
            boolean quote_0 = (pare[0] == '\"');
            boolean quote_1 = (pare[1] == '\"');
            boolean comma_0 = (pare[0] == ',');
            boolean comma_1 = (pare[1] == ',');

            /*=========== Main CSV conditions part =============================================*/

            /** End of line with quote  */
            if (waitFinishByQuotes && endLine) {
                endOfCellIndex = i + 1;
                waitFinishByQuotes = false;
                String pieceOfLine = line.substring(startOfCellIndex, endOfCellIndex);
                resultArray.add(pieceOfLine);
            }

            /** End of line with comma  */
            else if (waitFinishByComma && endLine) {
                endOfCellIndex = i + 1;
                waitFinishByComma = false;
                String pieceOfLine = line.substring(startOfCellIndex, endOfCellIndex);
                resultArray.add(pieceOfLine);
            }

            /** Start of cell with quotes  */
            else if (comma_0 && quote_1 && (!waitFinishByComma) && (!waitFinishByQuotes)) {
                startOfCellIndex = i;
                waitFinishByQuotes = true;
            }

            /** End of cell with quotes   */
            else if (quote_0 && comma_1 && waitFinishByQuotes) {
                endOfCellIndex = i;
                waitFinishByQuotes = false;
                String pieceOfLine = line.substring(startOfCellIndex, endOfCellIndex);
                resultArray.add(pieceOfLine);
            }

            /** Start of cell with comma */
            else if (comma_0 && (!waitFinishByComma) && (!waitFinishByQuotes)) {
                startOfCellIndex = i;
                waitFinishByComma = true;
            }

            /** End of cell with comma*/
            else if (comma_1 && waitFinishByComma) {
                endOfCellIndex = i;
                waitFinishByComma = false;
                String pieceOfLine = line.substring(startOfCellIndex, endOfCellIndex);
                resultArray.add(pieceOfLine);
            }
        }

        resultArray = removeQuotesFromArray(resultArray);
        return resultArray;
    }

    /** @return - elements of result array don't have service CSV quotes */
    private ArrayList<String> removeQuotesFromArray(ArrayList<String> array) {
        for (int i = 0; i < array.size(); i++) {
            String element = array.get(i);
            element = removeQuotesFromElement(element);
            array.set(i, element);
        }
        return array;
    }

    /**
     * Extract inside part of word, and cut quotes on edges
     * @return - word param without service CSV quotes
     */
    private String removeQuotesFromElement(String word) {
        int startIndex = 0;
        int endIndex = word.length();

        if (word.charAt(0) == '\"') {
            startIndex = 1;
        }

        if (word.charAt(word.length() - 1) == '\"') {
            endIndex = word.length() - 1;
        }
        return word.substring(startIndex, endIndex);
    }
}