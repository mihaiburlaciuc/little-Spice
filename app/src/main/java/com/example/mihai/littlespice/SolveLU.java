package com.example.mihai.littlespice;

/**
 * Created by mihai on 03.05.2017.
 */

public class SolveLU {
    public int sizeOfMatrix;
    public double[][] L;
    public double[][] U;
    public double[] x;

    public SolveLU(int sizeOfMatrix) {
        this.sizeOfMatrix = sizeOfMatrix;
        L = new double[sizeOfMatrix][sizeOfMatrix];
        U = new double[sizeOfMatrix][sizeOfMatrix];
        x = new double[sizeOfMatrix];
    }

    public void solve(double[][] A, double[] b) {

    }
    public double[] returnX() {
        return x;
    }
}
