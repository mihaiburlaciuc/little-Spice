package com.example.mihai.littlespice;

/**
 * Created by mihai on 11.05.2017.
 */

public class InvComplex2 {

    public static Complex[] invert(Complex[][] A, Complex[] b) {
        Complex[][] invA = new Complex[2][2];

        invA[0][0] = new Complex();
        invA[0][1] = new Complex();
        invA[1][0] = new Complex();
        invA[1][1] = new Complex();

        Complex detA = new Complex();
        detA.egal( A[0][0].multiply(A[1][1]).minus(
                A[1][0].multiply(A[0][1]) ) );

        invA[0][0].egal( A[1][1].divide(detA) );

        invA[0][1].egal( A[0][1].divide(detA) );
        invA[0][1].egal(invA[0][1].multiply(new Complex(-1, 0)));

        invA[1][0].egal( A[1][0].divide(detA) );
        invA[1][0].egal(invA[1][0].multiply(new Complex(-1, 0)));

        invA[1][1].egal( A[0][0].divide(detA) );

        // Calculare x
        Complex[] x = new Complex[2];
        x[0] = new Complex();
        x[1] = new Complex();

        x[0].egal( invA[0][0].multiply(b[0]).plus(invA[0][1].multiply(b[1])) );
        x[1].egal( invA[1][0].multiply(b[0]).plus(invA[1][1].multiply(b[1])) );

        return x;
    }
}
