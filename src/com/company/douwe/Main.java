package com.company.douwe;

import com.company.douwe.Matrix;

public class Main {
    public static void main(String[] args) {
        Matrix a = new Matrix(2, 2);
        Matrix b = new Matrix(2, 2);

        // Write Matrix constructors like this so they are easy to read
        Matrix c = new Matrix(new double[][]{
                {1, 2},
                {4, 5}
        });

        Matrix d = new Matrix(new double[][]{
                {7, 8},
                {9, 10},
        });

        // Show functionality of converting 1D double[] to matrix with given dimensions
        Matrix h = new Matrix(new double[]{1, 2, 3, 4}, 2, 2);

        System.out.println(h);

        int size = 1;

        // Run matrix multiplication operations for matrix with size 1, 2, 4, 8 ... 512, 1024 and time them
        while (size < 2048) {
            Matrix k = new Matrix(size, size, -1, 1);

            long startTime = System.currentTimeMillis();

            Matrix l = Matrix.pow(k, 2);

            long endTime = System.currentTimeMillis();

            System.out.println((endTime - startTime));

            size *= 2;
        }

        // Show replacement functionality
        d.replaceWhereBigger(8, -100);

        System.out.println(d);
    }
}
