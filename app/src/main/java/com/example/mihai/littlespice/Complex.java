package com.example.mihai.littlespice;

/**
 * Created by mihai on 10.05.2017.
 */

public class Complex {
    double re, im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public Complex() {
        this(0, 0);
    }

    public void egal(Complex other) {
        this.re = other.re;
        this.im = other.im;
    }

    public Complex plus(Complex other) {
        return new Complex(this.re + other.re, this.im + other.im);
    }

    public Complex minus(Complex other) {
        return new Complex(this.re - other.re, this.im - other.im);
    }

    public Complex multiply(Complex other) {
        return new Complex(this.re * other.re - this.im * other.im,
                this.re * other.im + this.im * other.re);
    }

    public Complex divide(Complex other) {
        double numitor = Math.pow(other.re, 2) + Math.pow(other.im, 2);

        // Calcularea numaratorului si apoi impart la numitor
        Complex newNr = this.multiply(new Complex(other.re, (-1) * other.im));
        newNr.re /= numitor;
        newNr.im /= numitor;

        return newNr;
    }

    @Override // Pentru System.out.println
    public String toString() {
        String operator;

        if ( im < 0 ) {
            return String.valueOf(re) + " - " +
                    String.valueOf(im).substring(1) + "i";
        } else {
            return String.valueOf(re) + " + " + String.valueOf(im) + "i";
        }
    }
}
