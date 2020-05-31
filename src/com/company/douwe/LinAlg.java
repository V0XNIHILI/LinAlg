//package com.company.douwe;
//
//import com.company.douwe.Matrix;
//
//public class LinAlg {
//    /**
//     * Multiply two matrices
//     *
//     * @param a First matrix
//     * @param b Second matrix
//     * @return
//     * @throws IllegalArgumentException
//     */
//    public static Matrix multiply(Matrix a, Matrix b) throws IllegalArgumentException {
//        if (a.height != b.width || a.width != b.height)
//            throw new IllegalArgumentException("The dimensions of the matrices to be multiplied do not match");
//
//        double[][] result = new double[a.height][b.width];
//        double[][] inputValuesA = a.getValues();
//        double[][] inputValuesB = b.getValues();
//
//        for (int i = 0; i < b.width; i++) {
//            for (int j = 0; j < a.height; j++) {
//                double total = 0;
//
//                for (int k = 0; k < b.height; k++) {
//                    total += inputValuesA[j][k] * inputValuesB[k][i];
//                }
//
//                result[j][i] = total;
//            }
//        }
//
//        return new Matrix(result);
//    }
//
//    // @todo
//    // Implement the divide function -> a * b^-1
//
//    /**
//     * Add two matrices
//     *
//     * @param a First matrix
//     * @param b Second matrix
//     * @return
//     * @throws IllegalArgumentException
//     */
//    public static Matrix add(Matrix a, Matrix b) throws IllegalArgumentException {
//        if (b.width != a.width || b.height != a.height)
//            throw new IllegalArgumentException("The dimensions of the matrices to be added do not match");
//
//        double[][] result = new double[a.height][a.width];
//
//        double[][] inputValuesA = a.getValues();
//        double[][] inputValuesB = b.getValues();
//
//        for (int i = 0; i < a.width; i++) {
//            for (int j = 0; j < a.height; j++) {
//                result[j][i] = inputValuesA[j][i] + inputValuesB[j][i];
//            }
//        }
//
//        return new Matrix(result);
//    }
//
//    /**
//     * Subtract two matrices
//     *
//     * @param a First matrix (matrix to be subtracted from)
//     * @param b Second matrix (matrix to subtract with)
//     * @return
//     * @throws IllegalArgumentException
//     */
//    public static Matrix subtract(Matrix a, Matrix b) throws IllegalArgumentException {
//        if (b.width != a.width || b.height != a.height)
//            throw new IllegalArgumentException("The dimensions of the matrices to be added do not match");
//
//        double[][] result = new double[a.height][a.width];
//
//        double[][] inputValuesA = a.getValues();
//        double[][] inputValuesB = b.getValues();
//
//        for (int i = 0; i < a.width; i++) {
//            for (int j = 0; j < a.height; j++) {
//                result[j][i] = inputValuesA[j][i] - inputValuesB[j][i];
//            }
//        }
//
//        return new Matrix(result);
//    }
//
//    /**
//     * Apply a 'to-the-power' operation to a matrix
//     *
//     * @param a     The matrix where the power is computed from
//     * @param power Power number
//     * @return
//     * @throws IllegalArgumentException
//     */
//    public static Matrix pow(Matrix a, int power) throws IllegalArgumentException {
//        if (a.width != a.height)
//            throw new IllegalArgumentException("The matrix is not square");
//
//        if (power < 0)
//            throw new IllegalArgumentException("Power should be larger than 0");
//
//        // @todo
//        // if power == 1, return the identity matrix
//
//        Matrix result = new Matrix(a.getValues());
//
//        for (int i = 1; i < power; i++) {
//            result = LinAlg.multiply(result, a);
//        }
//
//        return result;
//    }
//
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
//
//    /**
//     * Perform elementwise matrix multiplication. For a mathematical reference, please see:
//     * https://en.wikipedia.org/wiki/Hadamard_product_(matrices)
//     *
//     * @param b Matrix that you want to perform elementwise multiplication with
//     * @return Result of the calculation
//     * @throws IllegalArgumentException
//     */
//    public static Matrix hadamard(Matrix a, Matrix b) throws IllegalArgumentException {
//        if (b.width != a.width || b.height != a.height)
//            throw new IllegalArgumentException("The width and height of the matrices to be multiplied do not match");
//
//        double[][] result = new double[a.height][a.width];
//
//        double[][] inputValuesA = a.getValues();
//        double[][] inputValuesB = b.getValues();
//
//        for (int i = 0; i < a.width; i++) {
//            for (int j = 0; j < a.height; j++) {
//                result[j][i] = inputValuesB[j][i] * inputValuesA[j][i];
//            }
//        }
//
//        return new Matrix(result);
//    }
//}
