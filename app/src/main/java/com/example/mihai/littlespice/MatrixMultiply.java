package com.example.mihai.littlespice;

/**
 * Created by mihai on 11.05.2017.
 */

public class MatrixMultiply {
    public static double[] multiplyWithVector(double[][] A, double[] B, int maxim) {
        // Aflarea marimii si declararea matricii de returnat ca rezultat
        int size = maxim;

        double[] result = new double[size];

        for ( int i = 0; i < size; i++ ) {
            for ( int j = 0; j < size; j++ ) {
                result[i] += (A[i][j] * B[j]);
            }
        }
        return result;
    }
}