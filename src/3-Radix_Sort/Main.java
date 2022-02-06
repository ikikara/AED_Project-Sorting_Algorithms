package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        int n = in.nextInt();
        int[] array = new int[n];

        /*[To use manually]
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }*/

        //[Random array]
        for(int i=0; i<n;i++) {
            array[i] = ( new Random()).nextInt(10000000);
        }

        /*/[Descending sorted array]
        for(int i=0; i<n; i++){
            array[i]=n-i;
        }*/

        /*[1% sorted array]
        for(int i=0; i<n;i++){
            if(i<n*0.01){
                array[i]=i;
            }
            else{
                array[i]=(new Random()).nextInt(10000000);
            }
        }*/

        /*[5% sorted array]
        for(int i=0; i<n;i++){
            if(i<n*0.05){
                array[i]=i;
            }
            else{
                array[i]=(new Random()).nextInt(10000000);
            }
        }*/


        long start = System.currentTimeMillis();
        radixsort(array);
        long time = System.currentTimeMillis() - start;
        System.out.printf("rS\t\t%.5f seconds\n", time / 1000.0);

        /*[To see sorted Array]
        radixsort(array);
        printArray(array);
        */
    }

    public static void printArray(int[] array) {
        for (int j : array) {
            System.out.print(j + "\n");
        }
    }


    static void radixsort(int[] array){
        int limitexp=(int)Math.floor(Math.log10(getMax(array)))+1;

        for(int exp=0; exp<limitexp; exp++){
            countsort(array, exp);
        }

    }

    static void countsort(int[] array, int exp){
        int[] counts = new int[10];
        int index;
        int nbase=(int)Math.pow(10,exp);
        int[] output = new int[array.length];

        for(int i : array){
            index=(i/nbase)%10;
            counts[index]++;
        }

        for(int i=1; i<10; i++){
            counts[i]+=counts[i-1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            output[counts[(array[i] / nbase) % 10] - 1] = array[i];
            counts[(array[i] / nbase) % 10]--;
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = output[i];
        }

    }

    static int getMax(int[] array){
        int max=array[0];
        for(int i=1; i<array.length; i++){
            if(array[i]>max){
                max=array[i];
            }
        }

        return max;
    }

}

class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream));
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }
}