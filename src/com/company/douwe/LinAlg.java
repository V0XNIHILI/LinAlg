package com.company.douwe;

import com.company.douwe.Matrix;

import java.util.stream.IntStream;

public class LinAlg {
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

        double[] result = new double[b.width * a.height];
        double[] inputValuesA = a.getValues();
        double[] inputValuesB = b.getValues();

        IntStream.range(0, b.width).parallel().forEach(i -> {
            for (int j = 0; j < a.height; j++) {
                double total = 0;

                int locationInA = j * a.width;

                for (int k = 0; k < b.height; k++) {
                    total += inputValuesA[locationInA + k] * inputValuesB[k * b.width + i];
                }

                result[j * b.width + i] = total;
            }
        });


        return new Matrix(result, b.width, a.height);
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

        double[] result = new double[a.width * a.height];

        double[] inputValuesA = a.getValues();
        double[] inputValuesB = b.getValues();

        for (int i = 0; i < a.width * a.height; i++) {
            result[i] = inputValuesA[i] + inputValuesB[i];
        }

        return new Matrix(result, a.width, a.height);
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

        double[] result = new double[a.width * a.height];

        double[] inputValuesA = a.getValues();
        double[] inputValuesB = b.getValues();

        for (int i = 0; i < a.width * a.height; i++) {
            result[i] = inputValuesA[i] - inputValuesB[i];
        }

        return new Matrix(result, a.width, a.height);
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

        Matrix result = new Matrix(a.getValues(), a.width, a.height);

        for (int i = 1; i < power; i++) {
            result = LinAlg.multiply(result, a);
        }

        return result;
    }

//    public static Matrix flipOverDiagonal(Matrix a) {
//        double[][] result = new double[a.width][a.height];
//        double[][] inputValues = a.getValues();
//
//        for (int i = 0; i < a.width; i++) {
//            for (int j = 0; j < a.height; j++) {
//                result[i][j] = inputValues[j][i];
//            }
//        }
//
//        return new Matrix(result);
//    }

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

        double[] result = new double[a.width * a.height];

        double[] inputValuesA = a.getValues();
        double[] inputValuesB = b.getValues();

        for (int i = 0; i < a.width * a.height; i++) {
            result[i] = inputValuesB[i] * inputValuesA[i];
        }

        return new Matrix(result, a.width, a.height);
    }
}
