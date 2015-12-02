package com.shpp.cs.assignments.arrays.tm;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];
        /** Combine total array of sound samples for current column */
        double[][]currentResultSound = getResultSoundArray(toneMatrix, column, samples, result);
        /** Sumarrize all current samples sounds into result sound */
        double[]sumarrySound = getSumarrizedSound(currentResultSound);
        /** Normalize sum sound for playing conditions */
        result = getNormalizedSound(sumarrySound);

        return result;
    }

    /** Combine total array of sound samples for current column */
    private static double[][] getResultSoundArray(boolean[][] toneMatrix, int column,
                                                  double[][] samples, double[] result) {
        int matrixRows = toneMatrix.length;
        /** Array of all sounds for this column   */
        double[][]currentResultSound = new double[matrixRows][result.length];
        /** Run through certain toneMatrix column, and add samples to result array if need */
        for (int row = 0; row < matrixRows; row++){
            boolean markedMatrixCell = toneMatrix[row][column];
            if(markedMatrixCell){
                double [] note = samples[row];
                currentResultSound[row] = note;
            }
        }
        return currentResultSound;
    }

    /** Sumarrize all current samples sounds into result sound */
    private static double[] getSumarrizedSound(double[][] currentResultSound) {
        /** Recognize sizes of input array */
        int rows = currentResultSound.length;
        int columns = currentResultSound[0].length;
        /** Runs through columns of input array and sumarrize values into result array */
        double[] result = new double[columns];
        for (int col = 0; col < columns; col++) {
            double sum = 0;
            for (int row = 0; row < rows; row++) {
                sum += currentResultSound[row][col];
            }
            result[col] = sum;
        }
        return result;
    }

    /** Normalize sum sound for playing conditions:
     * - all sounds values have to be in [-1.0; 1.0] output range for current program */
    private static double[] getNormalizedSound(double[] resultSound) {
        /** Get abs maximum value from array */
        double max = getAbsMaximum(resultSound);
        /** Normalize input array values to obtained max value */
        for(int i = 0; i < resultSound.length; i++){
            if(max > 0) {
                resultSound[i] = resultSound[i] / max;
            }else{
                resultSound[i] = 0;
            }
        }
        return resultSound;
    }

    /** Get abs maximum value from array */
    private static double getAbsMaximum(double[] array) {
        /** Recognize sizes of input array */
        int cells = array.length;
        /** Process of max value searching */
        double max = array [0];
        for (int i = 1; i < cells; i++){
            if(Math.abs(max) < Math.abs(array[i])){
                max = array[i];
            }
        }
        return Math.abs(max);
    }
}
