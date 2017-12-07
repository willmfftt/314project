/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptoprogram;

import java.util.*;
import java.io.*;

/**
 *
 * @author bradycusack
 */
public class ElGamal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        //int q = discreteLogProb(19683, 3);
        //System.out.print("Back in Main: " + q);
        Scanner inFile = new Scanner(new FileReader("/Users/bradycusack/NetBeansProjects/CryptoProgram/src/cryptoprogram/elgamal.txt"));

        ArrayList<Pair> pairs = new ArrayList<>();
        String line = "";

        while (inFile.hasNextLine()) {
            line = inFile.nextLine();
            System.out.println(line);
        }
        
        
        while(!pairs.isEmpty()){
            System.out.println(calcM(pairs.get(0)));
            pairs.remove(0);
        }
    }

    // Function for Discrete Logarithm Problem
    public static int discreteLogProb(int p, int alpha) {
        int beta = 0;
        for (int i = 1; i < p; i++) {
            beta = alpha % p;
        }
        return beta;
    }

    // Function to do Modular Exponentation via Repeated Squaring
    public static int modExp(int x, int exp, int mod) {
        int result = 1;
        x = x % mod;

        while (exp > 0) {
            if (exp % 2 != 0) {
                result = (result * x) % mod;
            }

            exp = exp / 2;
            x = (x * x) % mod;
        }

        return result;
    }

    // Extended Euclidean Algorithm to find GCD
    public static int[] extendedEuclid(int p, int q) {
        if (q == 0) {
            return new int[]{p, 1, 0};
        }

        int[] temp = extendedEuclid(q, p % q);
        int a = temp[0];
        int b = temp[2];
        int c = temp[1] - (p / q) * temp[2];

        return new int[]{a, b, c};
    }

    // Function to Find M
    public static int calcM(Pair pair) {        
        return pair.getT()*inverseR(pair.getR());
    }

    // Find the Inverse of r
    public static int inverseR(int r) {
        return 0;
    }

    public static String convertToText(int num) {
        int a, b, c;
        c = num % 26;
        num = num - c;
        int div = num / 26;
        b = div % 26;
        num = num - (b * 26);
        a = num / (26 * 26);
        String A = toAlphabetic(a);
        String B = toAlphabetic(b);
        String C = toAlphabetic(c);
        String finalString = A + B + C;
        return finalString;
    }

    public static String toAlphabetic(int i) {
        if (i < 0) {
            return "-" + toAlphabetic(-i - 1);
        }

        int quot = i / 26;
        int rem = i % 26;
        char letter = (char) ((int) 'A' + rem);
        if (quot == 0) {
            return "" + letter;
        } else {
            return toAlphabetic(quot - 1) + letter;
        }
    }

    static class Key {

        int p;
        int alpha;
        int beta;

        public Key(int p, int alpha, int beta) {
            this.alpha = alpha;
            this.beta = beta;
            this.p = p;
        }

        public int getP() {
            return this.p;
        }

        public int getAlpha() {
            return this.alpha;
        }

        public int getBeta() {
            return this.beta;
        }

    }

    static class Pair {

        int r;
        int t;

        public Pair(int r, int t) {
            this.r = r;
            this.t = t;
        }

        public int getR() {
            return this.r;
        }

        public int getT() {
            return this.t;
        }
    }
}