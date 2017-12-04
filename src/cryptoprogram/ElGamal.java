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
        
        //ArrayList<Pair> pairs = new ArrayList<>();
        String line = "";
             
        while(inFile.hasNextLine()){
            line = inFile.nextLine();
            System.out.println(line);
        }
    }

    // Function for Discrete Logarithm Problem
    public static int discreteLogProb(int p, int alpha){
        int beta = 0;
        for(int i = 1; i < p; i++){
                beta = alpha % p;
        }
        return beta;
    }
    
    // Function to do Modular Exponentation via Repeated Squaring
    public static int modExp() {
        return 0;
    }

    // Function to Convert Blocks of Text to Text
    public static String toText(int r, int t) {
        
        return "blank";
    }

    // Extended Euclidean Algorithm to find GCD
    public static int[] extendedEuclid(int p, int q) {
        if (q == 0)
            return new int[] {p, 1, 0};
        
        int[] temp = extendedEuclid(q, p % q);
        int a = temp[0];
        int b = temp[2];
        int c = temp[1] - (p/q) * temp[2];
        
        return new int[] {a, b, c};
    }

    // Function to Find M
    public static int calcM() {
        return 0;
    }

    // Find the Inverse of r
    public static int inverseR(int r) {
        return 0;
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
    
    static class Pair{
        
        int r;
        int t;
        
        public Pair(int r, int t){
            this.r = r;
            this.t = t;
        }
        
        public int getR(){
            return this.r;
        }
        
        public int getT(){
            return this.t;
        }
    }
}
