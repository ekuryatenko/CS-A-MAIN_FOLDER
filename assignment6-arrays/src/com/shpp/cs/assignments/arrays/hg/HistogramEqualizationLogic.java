package com.shpp.cs.assignments.arrays.hg;

public class HistogramEqualizationLogic {
    private static final int MAX_LUMINANCE = 255;

    /**
     * Given the luminances of the pixels in an image, returns a histogram of the frequencies of
     * those luminances.
     * <p/>
     * You can assume that pixel luminances range from 0 to MAX_LUMINANCE, inclusive.
     *
     * @param luminances The luminances in the picture.
     * @return A histogram of those luminances.
     */
    public static int[] histogramFor(int[][] luminances) {
        /** Recognize sizes of input array */
        int rows = luminances.length;
        int columns = luminances[0].length;
        /** Output array size corresponds max able value of luminance */
        int[] outputHistogram = new int[256];
        /** Main process loop - runs through every input array pixel-luminance
         *  - summarize count of pixels for current luminance into output frequences histogram  */
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                int thisPixelLuminance = luminances[row][col];
                outputHistogram[thisPixelLuminance]++;
            }
        }
        return outputHistogram;
    }

    /**
     * Given a histogram of the luminances in an image, returns an array of the cumulative
     * frequencies of that image.  Each entry of this array should be equal to the sum of all
     * the array entries up to and including its index in the input histogram array.
     * <p/>
     * For example, given the array [1, 2, 3, 4, 5], the result should be [1, 3, 6, 10, 15].
     *
     * @param histogram The input histogram.
     * @return The cumulative frequency array.
     */
    public static int[] cumulativeSumFor(int[] histogram) {
        /** Recognize sizes of input array */
        int size = histogram.length;
        int[]cumulativeOutput = new int [size];
        /** Main process - cumulative histogram obtaining */
        /** Start cell filling */
        cumulativeOutput[0] = histogram[0];
        /** Loop - sumarizes all last input array values into next output cell */
        for(int i = 1; i < size; i++){
            cumulativeOutput[i] = cumulativeOutput[i-1] + histogram[i];
        }
        return cumulativeOutput;
    }

    /**
     * Returns the total number of pixels in the given image.
     *
     * @param luminances A matrix of the luminances within an image.
     * @return The total number of pixels in that image.
     */
    public static int totalPixelsIn(int[][] luminances) {
        /** Recognize sizes of input array */
        int rows = luminances.length;
        int columns = luminances[0].length;
        /** Suppose that each cell of input array corresponds to single pixel */
        int totalPixelsIn = rows * columns;
        return totalPixelsIn;
    }

    /**
     * Applies the histogram equalization algorithm to the given image, represented by a matrix
     * of its luminances.
     * <p/>
     * You are strongly encouraged to use the three methods you have implemented above in order to
     * implement this method.
     *
     * @param luminances The luminances of the input image.
     * @return The luminances of the image formed by applying histogram equalization.
     */
    public static int[][] equalize(int[][] luminances) {
        /** Recognize sizes of input array */
        int rows = luminances.length;
        int columns = luminances[0].length;
        int[][] outputArray = new int[rows][columns];
        /** Get histogram for imgage luminances */
        int[]simpleHistogram = histogramFor(luminances);
        /** Get cumulativeHistogram for this simpleHistogram */
        int[]cumulativeHistogram = cumulativeSumFor(simpleHistogram);
        /** Get total pixels quantity */
        int totalPixelsQty = totalPixelsIn(luminances);

        /** Main process loop - runs through every input array pixel
         *  and refilled it by new luminance value */
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                /** For this current pixel  */
                int thisPixelLumino = luminances[row][col];
                /** Find modifying value for each pixel lumino - it gives share of pixels which have smaller brightnes
                 * This share value gets due to value of cumulative histogram due to this pixel luminance */
                double fractionSmaller = (double)cumulativeHistogram[thisPixelLumino] / totalPixelsQty;
                /** Mofified luminance for current pixel  */
                int newPixelLumino = (int)(MAX_LUMINANCE * fractionSmaller);
                /** Filling output array*/
                outputArray[row][col] = newPixelLumino;
            }
        }
        return outputArray;
    }
}