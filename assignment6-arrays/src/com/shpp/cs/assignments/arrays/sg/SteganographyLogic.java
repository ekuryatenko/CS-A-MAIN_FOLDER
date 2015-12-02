package com.shpp.cs.assignments.arrays.sg;

import acm.graphics.GImage;

public class SteganographyLogic {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p/>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {
        /** Get integer array from input image */
        int[][] inputArray = source.getPixelArray();
        /** Recover hidden message into output array */
        boolean[][] foundSecretMessage = findBoolArray(inputArray);
        return foundSecretMessage;
    }

    /** Recover hidden message into output array */
    private static boolean[][] findBoolArray(int[][] inputArray) {
        int red;
        boolean boolValue;
        /** Get sizes for output array */
        int rows = inputArray.length;
        int columns = inputArray[0].length;
        boolean[][] foundMessage = new boolean[rows][columns];
        /** Main process:
         * - runs through each pixel of input array and discover it's red color value */
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                red = GImage.getRed(inputArray[row][col]);
                /** Gets boolean value of secret image at this pixel from red color value due to steganographic rules */
                boolValue = findBoolFromColor(red);
                foundMessage[row][col] = boolValue;
            }
        }
        return foundMessage;
    }

    /** Gets boolean value of secret image at this pixel from some color value due to steganographic rules */
    private static boolean findBoolFromColor(int color) {
        boolean boolBlack = false;
        if (color % 2 != 0) { /** Odd color value - corresponds to black - true */
            boolBlack = true;
        } else {/** Even color value  - corresponds to white - false */
            boolBlack = false;
        }
        return boolBlack;
    }

    /**
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p/>
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {
        /** Get integer array from input source image */
        int[][] imageArray = source.getPixelArray();
        /** Hide message into source imageArray */
        int[][] outputArray = getSteganoModifiedArray(imageArray, message);
        /** Get output image */
        GImage outputImage = new GImage(outputArray);
        return outputImage;
    }

    /**
     * Changes pixels in background image due to secret image in steganographics rules
     * @param backgroundImage - array of image which will be carier of secret
     * @param boolImageArray  - array of secret image, sizes of each array have to be equal
     * @return - integer array with modified image, in size of input arrays
     */
    private static int[][] getSteganoModifiedArray(int[][] backgroundImage, boolean[][] boolImageArray) {
        int red;
        int green;
        int blue;
        boolean boolValue;
        /** Get output array sizes */
        int rows = backgroundImage.length;
        int columns = backgroundImage[0].length;
        int[][] modifiedPixels = new int[rows][columns];
        /** Main process
         * * - runs through each pixel of background image and modify it's colors values */
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                red = GImage.getRed(backgroundImage[row][col]);
                green = GImage.getGreen(backgroundImage[row][col]);
                blue = GImage.getBlue(backgroundImage[row][col]);
                /** Gets boolean value of secret image at this pixel */
                boolValue = boolImageArray[row][col];
                /** Modify current pixel due to steganographics rules */
                modifiedPixels[row][col] = getSteganoModifiedPixel(red, green, blue, boolValue);
            }
        }
        return modifiedPixels;
    }

    /** Gets params of background image colors, and modifies its red color due to secret image */
    private static int getSteganoModifiedPixel(int red, int green, int blue, boolean boolBlack) {
        /** Modifies chosen color due to steganographics rules */
        int newRed = getSteganoModifiedColor(red, boolBlack);
        int outputPixel = GImage.createRGBPixel(newRed, green, blue);
        return outputPixel;
    }

    /** Moifies taken color of background image in steganographic rules */
    private static int getSteganoModifiedColor(int colorValue, boolean boolBlack) {
        boolean colorIsOdd = (colorValue % 2 != 0);
        /** if secret message has black pixel there */
        if (boolBlack) {/** make odd value */
            if (colorIsOdd) {
            } else {
                colorValue = colorValue + 1;
            }
            /** Else, if secret message has not black pixel there */
        } else {/** make even value */
            if (colorIsOdd) {
                colorValue = colorValue - 1;
            } else {
            }
        }
        return colorValue;
    }

}
