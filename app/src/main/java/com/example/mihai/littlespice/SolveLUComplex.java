package com.example.mihai.littlespice;

/**
 * Created by mihai on 10.05.2017.
 */

public class SolveLUComplex {
    public int sizeOfMatrix;
    public Complex[][] L;
    public Complex[][] U;
    public Complex[] x;

    public SolveLUComplex(int sizeOfMatrix) {
        this.sizeOfMatrix = sizeOfMatrix;
        L = new Complex[sizeOfMatrix][sizeOfMatrix];
        U = new Complex[sizeOfMatrix][sizeOfMatrix];
        x = new Complex[sizeOfMatrix];

        for(int i = 0; i < sizeOfMatrix; i++) {
            for(int j = 0; j < sizeOfMatrix; j++) {
                L[i][j] = new Complex();
                U[i][j] = new Complex();
            }
            x[i] = new Complex();
        }
    }

    public void solve(Complex[][] A, Complex[] b) {
        //!! Apare problema cand pe diagonala lui U exista vreun 0

        // Vectorul intermediar de rezolvare a sistemului triunghiular
        Complex[] y = new Complex[sizeOfMatrix];
        for(int i = 0; i < sizeOfMatrix; i++) {
            y[i] = new Complex();
        }

        // Initializarea matricilor pentru rezolvarea sistemului
        for ( int i = 1; i < sizeOfMatrix; i++ ) {
            for ( int j = 0; j < sizeOfMatrix; j++ ) {
                if ( i == j ) {
                    L[i][j].egal(new Complex(1, 1));
                } else {
                    L[i][j].egal(new Complex(0, 0));
                }

                U[i][j].egal(new Complex(0, 0));
            }
        }

        // Rezolvarea efectiva a sistemului
        for ( int p = 1; p < sizeOfMatrix; p++ ) {
            // Calcurea termenilor matricei U
            for ( int j = p; j < sizeOfMatrix; j++ ) {
                Complex elementsSum = new Complex();

                for ( int k = 1; k < p; k++) {
                    elementsSum.egal( elementsSum.plus( L[p][k].multiply(U[k][j]) ));
                }

                U[p][j].egal( A[p][j].minus(elementsSum) );
            }

            // Calcularea termenilor matricei L
            for ( int i = p + 1; i < sizeOfMatrix; i++ ) {
                Complex elementsSum = new Complex();

                for ( int k = 1; k < p; k++ ) {
                    elementsSum.egal( elementsSum.plus( L[i][k].multiply(U[k][p]) ));
                }

                L[i][p].egal( A[i][p].minus(elementsSum) );
                L[i][p].egal( L[i][p].divide(U[p][p]) );
            }
        }

        // Sistemul inferior triunghiular L*y = b
        for ( int i = 1; i < sizeOfMatrix; i++ ) {
            Complex elementsSum = new Complex();

            for ( int j = 1; j < i; j++) {
                elementsSum.egal( elementsSum.plus( L[i][j].multiply(y[j]) ));
            }

            y[i].egal( b[i].minus(elementsSum) );
            y[i].egal( y[i].divide(L[i][i]) );
        }

        // Sistemul superior triunghiular U*x = y
        for ( int i = sizeOfMatrix - 1; i >= 1; i-- ) {
            Complex elementsSum = new Complex();

            for ( int j = i + 1; j < sizeOfMatrix; j++ ) {
                elementsSum.egal( elementsSum.plus( U[i][j].multiply(x[j]) ));
            }

            x[i].egal( y[i].minus(elementsSum) );
            x[i].egal( y[i].divide(U[i][i]) );
        }
    }
    public Complex[] returnX() {
        return x;
    }
}
