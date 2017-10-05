package com.example.mihai.littlespice;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Double2;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class answerActivity extends AppCompatActivity {
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Intent intent = getIntent();
        String input = intent.getStringExtra("raspuns");

        TextView answer = (TextView) findViewById(R.id.answer);

        String[] linii;
        String delimiter = "\n";
        linii = input.split(delimiter);


        int maxim = 0;

        if (linii[0].equals(".dc")) {
            int realMaxim;
            char litera[] = new char[linii.length];
            int prima[] = new int[linii.length];
            int doua[] = new int[linii.length];
            double treia[] = new double[linii.length];
            CircuitType circuit = new CircuitType(100); // circuitul alocat cat de mare vrem
            for(int i = 1; i < linii.length; i++)
            {
                delimiter = " ";
                String unele[] = linii[i].split(delimiter);
                litera[i] = unele[0].charAt(0);
                prima[i] = Integer.parseInt(unele[1]);
                doua[i] = Integer.parseInt(unele[2]);
                treia[i] = Double.parseDouble(unele[3]);
                if (maxim < prima[i]) {
                    maxim = prima[i];
                }
                if (maxim < doua[i]) {
                    maxim = doua[i];
                }
                if (litera[i] == 'i' || litera[i] == 'I') {
                    circuit.addIntensity(prima[i], doua[i], treia[i]);
                } else if (litera[i] == 'r' || litera[i] == 'R') {
                    circuit.addRezistence(prima[i], doua[i], treia[i]);
                } else {
                    // TODO: eroare
                }
            }
            realMaxim = maxim;
            // Pentru tensiuni
            int nrSIT = 0;
            for (int i = 1; i < linii.length; i++) {
                if (litera[i] == 'v' || litera[i] == 'V') {
                    maxim++;
                    nrSIT++;
                    circuit.addV(prima[i], doua[i], treia[i], maxim);
                }

            }

            // Rezolvare
            double[][] G = circuit.returnG();
            double[] B = circuit.returnB();
            double[][] Gi = Inverse.invert(G, maxim);
            double[] vectorRezultat = MatrixMultiply.multiplyWithVector(Gi, B, maxim);
            Log.e("matrice", "Matricea este:");
            for (int i = 0; i <= maxim; i++) {
                String linie = " ";
                for (int j = 0; j <= maxim; j++) {
                    linie +=Double.toString(G[i][j]);
                    linie += " ";
                }
                Log.e("matrice", linie);
            }
            String output = new String();
            String linie2 = " ";
            Log.e("matrice", "Vectorul termenilor liberi e:");
            for (int i = 0; i <= maxim; i++) {
                linie2 += Double.toString(B[i]);
                linie2 += " ";
            }
            Log.e("matrice", linie2);
            int eOK = 0;
            for (int i = 0; i < vectorRezultat.length; i++) {
                if (vectorRezultat[i] != 0) {
                    eOK = 1;
                }
            }
            for (int i = 0; i < vectorRezultat.length; i++) {
                if (i < realMaxim) {
                    output += "V";
                    output += Integer.toString(i+1);
                    output += ": ";
                    output += Double.toString(round(vectorRezultat[i], 2));
                    output += "\n";
                } else {
                    output += "I(v";
                    output += Integer.toString(i - realMaxim + 1);
                    output += "): ";
                    output += Double.toString(round(vectorRezultat[i], 2));
                    output += "\n";
                }

            }
            Log.e("matrice", Integer.toString(maxim));
            Log.e("matrice", output);
            Log.e("matrice", "\n");
            if (eOK == 0) {
                output = "Eroare";
            }
            answer.setText(output);
        } else if (linii[0].equals(".ac")) {

            CircuitTypeComplex circuit = new CircuitTypeComplex(100); // circuitul alocat cat de mare vrem
            int frequency = Integer.parseInt(linii[1]);
            double w = 2 * 3.14 * frequency;
            char litera[] = new char[linii.length];
            int prima[] = new int[linii.length];
            int doua[] = new int[linii.length];
            double treia[] = new double[linii.length];
            double patru[] = new double[linii.length];
            int eroare = 0;
            for(int i = 2; i < linii.length; i++)
            {
                delimiter = " ";
                String unele[] = linii[i].split(delimiter);
                litera[i] = unele[0].charAt(0);
                prima[i] = Integer.parseInt(unele[1]);
                doua[i] = Integer.parseInt(unele[2]);
                treia[i] = Double.parseDouble(unele[3]);
                patru[i] = Double.parseDouble(unele[4]);
                Complex temp = new Complex(treia[i], patru[i]);
                if (maxim < prima[i]) {
                    maxim = prima[i];
                }
                if (maxim < doua[i]) {
                    maxim = doua[i];
                }
                if (litera[i] == 'i' || litera[i] == 'I') {
                    circuit.addIntensity(prima[i], doua[i], temp);
                } else if (litera[i] == 'r' || litera[i] == 'R') {
                    circuit.addRezistence(prima[i], doua[i], temp);
                } else if (litera[i] == 'l' || litera[i] == 'L') {
                    circuit.addL(prima[i], doua[i], temp, w);
                } else if (litera[i] == 'c' || litera[i] == 'C') {
                    circuit.addC(prima[i], doua[i], temp, w);
                } else {
                    eroare = 1;
                }
            }
            // Pentru tensiuni:

            // Loguri
            Log.e("matrice", "a intrat in ac"); // LOG
            String output = new String();
            Complex[][] G = circuit.returnG();
            Complex[] B = circuit.returnB();
            Complex[] vectorRezultat = InvComplex2.invert(G, B);
            for (int i = 0; i < vectorRezultat.length; i++) {
                output += "V";
                output += Integer.toString(i+1);
                output += ": ";
                output += Double.toString(round(vectorRezultat[i].re, 5));
                output += " ";
                output += Double.toString(round(vectorRezultat[i].im, 5));
                output += "i";
                output += "\n";
            }
            // Log-uri
            for (int i = 1; i < linii.length; i++) {
                String linie = " ";
                for (int j = 1; j < G[i].length; j++) {
                    linie +=Double.toString(G[i][j].re);
                    linie += " ";
                    linie += Double.toString(G[i][j].im);
                    linie += "; ";
                }
                Log.e("matrice", linie);
            }
            if (eroare == 1) {
                output = "Syntax error";
            }
            answer.setText(output);
        } else {
            String output = new String();
            output = "Error";
            answer.setText(output);
        }

    }


}
