package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

/*
QS
QS+IS1 -> gap 10
QS+IS2 -> gap 50
QS+IS3 -> gap 100
QS+IS4 -> gap 500
 */


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
        qS(array, 0, n-1);
        long time = System.currentTimeMillis() - start;
        System.out.printf("qS\t\t%.5f seconds\n", time / 1000.0);

        /*[To see sorted Array]
        qS(array, 0, n - 1, 10);
        printArray(array);*/
    }

    public static void printArray(int[] array) {
        for (int j : array) {
            System.out.print(j + "\n");
        }
    }

    static void qS(int[] array, int low, int high) {
        if (low < high) {
            int meio = partition(array, low, high);

            qS(array,low,meio-1);
            qS(array,meio+1, high);
        }

    }

    public static void iS(int [] array, int low, int high){
        int j;
        for (int i = low; i < high; i++) {
            int temp = array[i];

            for(j = i-1; j > -1 && array[j] > temp; j--) {
                array[j+1] = array[j];
            }
            array[j+1] = temp;
        }
    }

    static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    static int partition(int[] array, int low, int high){
        int index=(high+low)/2;
        swap(array, index, high);

        int pivot=array[high];

        int i = (low - 1);

        for(int j = low; j <= high - 1; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return (i + 1);
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

