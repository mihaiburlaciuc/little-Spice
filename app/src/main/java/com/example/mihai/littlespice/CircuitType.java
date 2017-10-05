package com.example.mihai.littlespice;

import android.util.Log;

/**
 * Created by mihai on 03.05.2017.
 */

public class CircuitType {
    private final double[][] G;// matricea pentru rezistente
    private final double[][] V; // matricea pentru tensiuni
    private final double[] B; // matricea pentru intensitati

    public CircuitType(int nodes) {
        G = new double[nodes][nodes];
        B = new double[nodes];
        V = new double[nodes][nodes];
    }

    // Functiile care modifica matricile din clasa in functie de
    // datele citite din fisier
    public void addRezistence(int i, int j, double value) {
        String mesaj = new String();
        if ( i > 0 && j > 0 ) {
            i--;
            j--;
            G[i][i] += 1/value;
            G[i][j] -= 1/value;
            G[j][i] -= 1/value;
            G[j][j] += 1/value;
            i++;
            j++;

        } else if ( i > 0 && j == 0 ) {
            i--;
            G[i][i] += 1/value; // am modificat din + in -
            i++;

        } else if ( i == 0 && j > 0 ) {
            j--;
            G[j][j] += 1/value;
            j++;
        }
        mesaj += "Rezistente:";
        mesaj += Integer.toString(i);
        mesaj += " si ";
        mesaj += Integer.toString(j);
        Log.e("isij", mesaj);
    }

    public void addIntensity(int i, int j, double value) {
        if ( i > 0 && j > 0 ) {
            i--;
            j--;
            B[i] -= value;
            B[j] += value;
            i++;
            j++;

        } else if ( i > 0 && j == 0 ) {
            i--;
            B[i] -= value;
            i++;

        } else if ( i == 0 && j > 0 ) {
            j--;
            B[j] += value;
            j++;
        }
    String mesaj = new String();
        mesaj += "Intensitati:";
        mesaj += Integer.toString(i);
        mesaj += " si ";
        mesaj += Integer.toString(j);
        Log.e("isij", mesaj);
    }
    public void addV(int i, int j, double value, int maxim) {
        if (i > 0 && j > 0) {
            maxim--;
            i--;
            j--;
            G[maxim][i] = 1;
            G[maxim][j] = -1;
            G[i][maxim] = -1;
            G[j][maxim] = 1;
            B[maxim] += value;
            maxim++;
            i++;
            j++;
        } else if (i == 0 && j > 0) {
            maxim--;
            j--;
            G[maxim][j] = -1;
            G[j][maxim] = 1;
            B[maxim] +=value;
            maxim++;
            j++;
        } else if (i > 0 && j == 0) {
            maxim--;
            i--;
            G[maxim][i] = 1;
            G[i][maxim] = -1;
            B[maxim] += value;
            maxim++;
            i++;
        }

        String mesaj = new String();
        mesaj += "Tensiuni:";
        mesaj += Integer.toString(i);
        mesaj += " si ";
        mesaj += Integer.toString(j);
        Log.e("isij", mesaj);

    }
    public double[][] returnG() {
        return G;
    }
    public double[] returnB() {
        return B;
    }
}
