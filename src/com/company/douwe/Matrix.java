package com.company.douwe;

import java.lang.StringBuilder;
import java.text.DecimalFormat;
import java.util.Random;
import java.lang.Math;

public class Matrix {
    public final int width;
    public final int height;

    private final double[][] values;

    /**
     * Create a new Matrix instance
     *
     * @param width  Width of the matrix
     * @param height Height of the matrix
     * @throws IllegalArgumentException
     */
    public Matrix(int width, int height) throws IllegalArgumentException {
        this(width, height, false);
    }

    /**
     * Create a matrix optionally filled with ones
     *
     * @param width  Width of the matrix
     * @param height Height of the matrix
     * @param ones   Whether or not to fill the matrix with ones only; else fill it with zeroes
     * @throws IllegalArgumentException
     */
    public Matrix(int width, int height, boolean ones) throws IllegalArgumentException {
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Both width and height must be larger than 0");

        this.width = width;
        this.height = height;

        this.values = new double[height][width];

        if (ones == true) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    this.values[j][i] = 1;
                }
            }
        }
    }

    /**
     * Create a new matrix from a 1D array given a certain width and height. First fill the rows, then the columns
     *
     * @param input  Array containing data to fill the matrix with
     * @param width  Width of the matrix
     * @param height Height of the matrix
     */
    public Matrix(double[] input, int width, int height) throws IllegalArgumentException {
        this(width, height);

        if (input.length != width * height)
            throw new IllegalArgumentException("The input data does not fit into a matrix specified by the width and height");


        int inputIndexCounter = 0;

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                this.values[j][i] = input[inputIndexCounter];
                inputIndexCounter++;
            }
        }
    }

    /**
     * Create and fill a matrix with random variables bounded by min and max
     *
     * @param width  Width of the matrix
     * @param height Height of the matrix
     * @param min    Minimum random value to occur in the matrix
     * @param max    Maximum random value to occur in the matrix
     */
    public Matrix(int width, int height, double min, double max) {
        this(width, height);

        Random random = new Random();

        double factor = (max - min);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.values[j][i] = min + factor * random.nextDouble();
            }
        }
    }


    /**
     * Internal constructor used to return the result of calculations
     *
     * @param input 2D array from which a Matrix should be created
     * @throws IllegalArgumentException
     */
    public Matrix(double[][] input) throws IllegalArgumentException {
        if (input.length == 0)
            throw new IllegalArgumentException("Input array height should be larger than 0");

        if (input[0].length == 0)
            throw new IllegalArgumentException("Input array width should be larger than 0");

        this.width = input[0].length;
        this.height = input.length;

        this.values = input;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public double[][] getValues() {
        return this.values;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    /**
     * Multiply two matrices
     *
     * @param a First matrix
     * @param b Second matrix
     * @return
     * @throws IllegalArgumentException
     */
    public static Matrix multiply(Matrix a, Matrix b) throws IllegalArgumentException {
        if (a.height != b.width || a.width != b.height)
            throw new IllegalArgumentException("The dimensions of the matrices to be multiplied do not match");

        double[][] result = new double[a.height][b.width];
        double[][] inputValues = a.getValues();

        for (int i = 0; i < b.width; i++) {
            for (int j = 0; j < a.height; j++) {
                for (int k = 0; k < b.height; k++) {
                    result[j][i] += inputValues[j][k] * b.values[k][i];
                }
            }
        }

        return new Matrix(result);
    }

    // @todo
    // Implement the divide function -> a * b^-1

    /**
     * Add two matrices
     *
     * @param a First matrix
     * @param b Second matrix
     * @return
     * @throws IllegalArgumentException
     */
    public static Matrix add(Matrix a, Matrix b) throws IllegalArgumentException {
        if (b.width != a.width || b.height != a.height)
            throw new IllegalArgumentException("The dimensions of the matrices to be added do not match");

        double[][] result = new double[a.height][a.width];
        double[][] inputValues = b.getValues();

        for (int i = 0; i < a.width; i++) {
            for (int j = 0; j < a.height; j++) {
                result[j][i] = inputValues[j][i] + a.values[j][i];
            }
        }

        return new Matrix(result);
    }

    /**
     * Subtract two matrices
     *
     * @param a First matrix (matrix to be subtracted from)
     * @param b Second matrix (matrix to subtract with)
     * @return
     * @throws IllegalArgumentException
     */
    public static Matrix subtract(Matrix a, Matrix b) throws IllegalArgumentException {
        if (b.width != a.width || b.height != a.height)
            throw new IllegalArgumentException("The dimensions of the matrices to be added do not match");

        double[][] result = new double[a.height][a.width];
        double[][] inputValues = b.getValues();

        for (int i = 0; i < a.width; i++) {
            for (int j = 0; j < a.height; j++) {
                result[j][i] = a.values[j][i] - inputValues[j][i];
            }
        }

        return new Matrix(result);
    }

    /**
     * Apply a 'to-the-power' operation to a matrix
     *
     * @param a     The matrix where the power is computed from
     * @param power Power number
     * @return
     * @throws IllegalArgumentException
     */
    public static Matrix pow(Matrix a, int power) throws IllegalArgumentException {
        if (a.width != a.height)
            throw new IllegalArgumentException("The matrix is not square");

        if (power < 0)
            throw new IllegalArgumentException("Power should be larger than 0");

        // @todo
        // if power == 1, return the identity matrix

        Matrix result = new Matrix(a.getValues());

        for (int i = 1; i < power; i++) {
            result = Matrix.multiply(result, a);
        }

        return result;
    }

    public static Matrix flipOverDiagonal(Matrix a) {
        double[][] result = new double[a.width][a.height];
        double[][] inputValues = a.getValues();

        for (int i = 0; i < a.width; i++) {
            for (int j = 0; j < a.height; j++) {
                result[i][j] = inputValues[j][i];
            }
        }

        return new Matrix(result);
    }

    /**
     * Perform elementwise matrix multiplication. For a mathematical reference, please see:
     * https://en.wikipedia.org/wiki/Hadamard_product_(matrices)
     *
     * @param b Matrix that you want to perform elementwise multiplication with
     * @return Result of the calculation
     * @throws IllegalArgumentException
     */
    public static Matrix hadamard(Matrix a, Matrix b) throws IllegalArgumentException {
        if (b.width != a.width || b.height != a.height)
            throw new IllegalArgumentException("The width and height of the matrices to be multiplied do not match");

        double[][] result = new double[a.height][a.width];
        double[][] inputValues = b.getValues();

        for (int i = 0; i < a.width; i++) {
            for (int j = 0; j < a.height; j++) {
                result[j][i] = inputValues[j][i] * a.values[j][i];
            }
        }

        return new Matrix(result);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    /**
     * Add a number to each element in the matrix.
     *
     * @param value The number to add to all the values
     */
    public void addValue(double value) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.values[j][i] += value;
            }
        }
    }

    /**
     * Subtract a number from each element in the matrix
     *
     * @param value The number to subtract from all the values
     */
    public void subtractValue(double value) {
        addValue(-value);
    }

    /**
     * Multiply a number with each element in the matrix
     *
     * @param value The number to multiply all the values in the matrix width
     */
    public void multiplyWithValue(double value) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.values[j][i] *= value;
            }
        }
    }

    /**
     * Divide each element in the matrix with a number
     *
     * @param value The number to divide all the values in the matrix by
     */
    public void divideByValue(double value) {
        this.multiplyWithValue(1 / value);
    }

    /**
     * Apply a 'to the power' for every element in the matrix
     *
     * @param power
     */
    public void toThePower(double power) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.values[j][i] = Math.pow(this.values[j][i], power);
            }
        }
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public double getValueAt(int x, int y) throws IllegalArgumentException {
        if (x >= this.width || y >= this.height)
            throw new IllegalArgumentException("Provided value coordinates are larger than the dimesions of the matrix");

        return this.values[y][x];
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public void replaceWhereEquals(double comparisonValue, double valueIfTrue, double valueIfFalse) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.values[j][i] = this.values[j][i] == comparisonValue ? valueIfTrue : valueIfFalse;
            }
        }
    }

    public void replaceWhereEquals(double comparisonValue, double valueIfTrue) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.values[j][i] = this.values[j][i] == comparisonValue ? valueIfTrue : this.values[j][i];
            }
        }
    }

    public void replaceWhereNotEquals(double comparisonValue, double valueIfTrue, double valueIfFalse) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.values[j][i] = this.values[j][i] != comparisonValue ? valueIfTrue : valueIfFalse;
            }
        }
    }

    public void replaceWhereNotEquals(double comparisonValue, double valueIfTrue) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.values[j][i] = this.values[j][i] != comparisonValue ? valueIfTrue : this.values[j][i];
            }
        }
    }


    public void replaceWhereBigger(double comparisonValue, double valueIfTrue, double valueIfFalse) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.values[j][i] = this.values[j][i] > comparisonValue ? valueIfTrue : valueIfFalse;
            }
        }
    }

    public void replaceWhereBigger(double comparisonValue, double valueIfTrue) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.values[j][i] = this.values[j][i] > comparisonValue ? valueIfTrue : this.values[j][i];
            }
        }
    }

    public void replaceWhereBiggerOrEquals(double comparisonValue, double valueIfTrue, double valueIfFalse) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.values[j][i] = this.values[j][i] >= comparisonValue ? valueIfTrue : valueIfFalse;
            }
        }
    }

    public void replaceWhereBiggerOrEquals(double comparisonValue, double valueIfTrue) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.values[j][i] = this.values[j][i] >= comparisonValue ? valueIfTrue : this.values[j][i];
            }
        }
    }

    public void replaceWhereSmaller(double comparisonValue, double valueIfTrue, double valueIfFalse) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.values[j][i] = this.values[j][i] < comparisonValue ? valueIfTrue : valueIfFalse;
            }
        }
    }

    public void replaceWhereSmaller(double comparisonValue, double valueIfTrue) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.values[j][i] = this.values[j][i] < comparisonValue ? valueIfTrue : this.values[j][i];
            }
        }
    }

    public void replaceWhereSmallerOrEquals(double comparisonValue, double valueIfTrue, double valueIfFalse) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.values[j][i] = this.values[j][i] <= comparisonValue ? valueIfTrue : valueIfFalse;
            }
        }
    }

    public void replaceWhereSmallerOrEquals(double comparisonValue, double valueIfTrue) {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.values[j][i] = this.values[j][i] <= comparisonValue ? valueIfTrue : this.values[j][i];
            }
        }
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    /**
     * Converts matrix to a String. See example below:
     * <pre>{@code
     * ┌                                ┐
     * │ -1,00 -1,92 -1,80 -0,92  0,25  │
     * │  0,05  0,99  0,25 -0,50 -0,19  │
     * │  0,98 -1,94 -1,35  0,22 -0,88  │
     * │ -0,85 -1,63  0,07 -0,39 -0,24  │
     * │  0,45 -0,33 -1,14  0,07 -0,61  │
     * └                                ┘
     * }</pre>
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        DecimalFormat dfm = new DecimalFormat("0.00");

        for (int j = 0; j < this.height; j++) {
            builder.append("│ ");


            for (int i = 0; i < this.width; i++) {
                if (i != 0)
                    builder.append(" ");

                if (this.values[j][i] > 0)
                    builder.append(" ");

                builder.append(dfm.format(this.values[j][i]));
            }

            builder.append("  │");

            builder.append("\n");
        }

        final int lineLength = this.width * 6 + 4;

        final String prepend = "┌" + " ".repeat(lineLength - 2) + "┐\n";
        final String append = "└" + " ".repeat(lineLength - 2) + "┘\n";

        return prepend + builder.toString() + append;
    }
}
