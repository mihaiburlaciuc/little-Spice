package com.example.mihai.littlespice;

/**
 * Created by mihai on 10.05.2017.
 */

public class CircuitTypeComplex {
    private final Complex[][] G; // matricea pentru rezistente
    private final Complex[] B; // matricea pentru intensitati

    public CircuitTypeComplex(int nodes) {
        G = new Complex[nodes][nodes];
        B = new Complex[nodes];

        for(int i = 0; i < nodes; i++) {
            for(int j = 0; j < nodes; j++) {
                G[i][j] = new Complex();
            }
            B[i] = new Complex();
        }
    }

    // Functiile care modifica matricile din clasa in functie de
    // datele citite din fisier
    public void addRezistence(int i, int j, Complex value) {
        if ( i > 0 && j > 0 ) {
            i--;
            j--;
            G[i][i].egal( G[i][i].plus(new Complex(1, 0).divide(value)) );
            G[i][j].egal( G[i][j].minus(new Complex(1, 0).divide(value)) );
            G[j][i].egal( G[j][i].minus(new Complex(1, 0).divide(value)) );
            G[j][j].egal( G[j][j].plus(new Complex(1, 0).divide(value)) );
            i++;
            j++;

        } else if ( i > 0 && j == 0 ) {
            i--;
            G[i][j].egal( G[i][i].plus(new Complex(1, 0).divide(value)) );
            i++;
        } else if ( i == 0 && j > 0 ) {
            j--;
            G[j][j].egal( G[j][j].plus(new Complex(1, 0).divide(value)) );
            j++;
        }
    }

    public void addL(int i, int j, Complex value, double w) {
        if (i > 0 && j > 0) {
            i--;
            j--;
            Complex rezultat = new Complex();
            rezultat.egal(new Complex(0,w).multiply(value));
            G[i][i].egal( G[i][i].plus(new Complex(1, 0).divide(rezultat)) );
            G[i][j].egal( G[i][j].minus(new Complex(1, 0).divide(rezultat)) );
            G[j][i].egal( G[j][i].minus(new Complex(1, 0).divide(rezultat)) );
            G[j][j].egal( G[j][j].plus(new Complex(1, 0).divide(rezultat)) );
            i++;
            j++;

        } else if (i == 0 && j > 0) {
            Complex rezultat = new Complex();
            j--;
            rezultat.egal(new Complex(0,w).multiply(value));
            G[j][j].egal(G[j][j].plus(new Complex(1,0).divide(rezultat)));
            j++;
        } else if (i > 0 && j == 0) {
            Complex rezultat = new Complex();
            i--;
            rezultat.egal(new Complex(0,w).multiply(value));
            G[i][i].egal(G[i][i].plus(new Complex(1,0).divide(rezultat)));
            i++;
        }
    }

    public void addC(int i, int j, Complex value, double w) {
        if (i > 0 && j > 0) {
            i--;
            j--;
            Complex rezultat = new Complex();
            rezultat.egal(new Complex(0,w).multiply(value));
            G[i][i].egal( G[i][i].plus(rezultat));
            G[i][j].egal( G[i][j].minus(rezultat));
            G[j][i].egal( G[j][i].minus(rezultat));
            G[j][j].egal( G[j][j].plus(rezultat));
            i++;
            j++;

        } else if (i == 0 && j > 0) {
            Complex rezultat = new Complex();
            j--;
            rezultat.egal(new Complex(0,w).multiply(value));
            G[j][j].egal(G[j][j].plus(rezultat));
            j++;
        } else if (i > 0 && j == 0) {
            Complex rezultat = new Complex();
            i--;
            rezultat.egal(new Complex(0,w).multiply(value));
            G[i][i].egal(G[i][i].plus(rezultat));
            i++;
        }
    }

    public void addIntensity(int i, int j, Complex value) {
        if ( i > 0 && j > 0 ) {
            i--;
            j--;
            B[i].egal( B[i].minus(value) );
            B[j].egal( B[j].plus(value) );
            i++;
            j++;

        } else if ( i > 0 && j == 0 ) {
            i--;
            B[i].egal( B[i].minus(value) );
            i++;

        } else if ( i == 0 && j > 0 ) {
            j--;
            B[j].egal( B[j].minus(value) );
            j++;
        }
    }

    public Complex[][] returnG() {
        return G;
    }
    public Complex[] returnB() {
        return B;
    }
}
