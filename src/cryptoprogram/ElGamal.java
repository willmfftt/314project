/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptoprogram;

import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

/**
 *
 * @author bradycusack
 */
public class ElGamal {

    /**
     * @param args the command line arguments
     */
    
    // A Key object that we will be using for the purpose of storing the key we are processing
    static Key key = new Key(31847, 5, 18074);
    
    // The list of Pair objects.
    static ArrayList<Pair> pairs = new ArrayList<>();
    
    // List of M values that we will be converting.
    static ArrayList<Integer> mVals = new ArrayList<>();
    
    public static void main(String[] args) throws FileNotFoundException {


        // TODO code application logic here
        try {
            populateList();
        } catch (FileNotFoundException ex) {
            System.err.println("Unable to open file");
            System.exit(-1);
        }

        /* Iterate through the list of pairs that were added in, print out the value
        of M that is calculated, and then add the m value to a list of mValues that
        will later on be converted.*/
        pairs.stream().map((pairList) -> {
            System.out.println("Call to calcM for current pair: " + calcM(pairList));
            return pairList;
        }).forEachOrdered((pairList) -> {
            mVals.add(calcM(pairList));
        });

        /* Iterate through the list of mVals which is the message from each pair
        and then print out the text conversion of the mValues.*/
        mVals.forEach((mValues) -> {
            System.out.print(convertToText(mValues));
        });
    }

    public static void populateList() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("elgamal.txt")).useDelimiter("\\(");
        while (scan.hasNext())
        {
            String pair = scan.next();
            pair = pair.replace(")", "");
            String[] strPairs = pair.split(",");
            pairs.add(new Pair(Integer.valueOf(strPairs[0].trim()), Integer.valueOf(strPairs[1].trim())));
        }
    }
    
    // Function for Discrete Logarithm Problem
    public static int discreteLogProb() {
        int p = key.getP();
        int alpha = key.getAlpha();
        int a = 0;
        for (int i = 1; i < p; i++) {
            a = alpha % p;
        }
        return a;
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
        return modExp(r, key.alpha, discreteLogProb());
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

    /* Pair objects. We will create an ArrayList of every pair that we get as input 
    that will be utilized when calculated the m values for the output! */ 
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
