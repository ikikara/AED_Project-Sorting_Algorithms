package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        int n = in.nextInt();
        int[] array = new int[n];

        /*
        [To use manually]
        for(int i=0; i<n;i++){
            array[i]=in.nextInt();
        }*/

        //[Random array]
        for(int i=0; i<n;i++) {
            array[i] = (new Random()).nextInt(10000000);
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
        bSS(array);
        long time = System.currentTimeMillis() - start;
        System.out.printf("bSS\t\t%.5f seconds\n", time / 1000.0);

        /*[To see sorted Array]
        bSS(array);
        printArray(array);
        */

    }

    public static void printArray(int [] array){
        for (int j : array) {
            System.out.print(j+"\n");
        }
    }

    public static void iS(int [] array){
        int j;
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];

            for(j = i-1; j > -1 && array[j] > temp; j--) {
                array[j+1] = array[j];
            }
            array[j+1] = temp;
        }
    }

    public static void bSS(int[] array) {
        int g, n = array.length;
        int grps;
        ArrayList<Integer> gaps = sequence(array);

        for (int k = gaps.size()-1; k >=0; k--) {
            g=gaps.get(k);
            if (g < n){
                grps = (n/g);
                for(int i=0; i<g;i++){
                    int temp=i;
                    int [] temps=new int[grps];
                    for(int j=0; j<grps; j++){
                        temps[j]=array[temp];
                        temp+=g;
                    }
                    iS(temps);
                    temp=i;
                    for(int j=0; j<grps; j++){
                        array[temp]=temps[j];
                        temp+=g;
                    }
                }
            }
        }
    }

    public static ArrayList<Integer> sequence(int[] array) {
        ArrayList<Integer> lista= new ArrayList<>();
        lista.add(1);
        int val=0;
        int k = 1;
        while(val<(float)array.length/2){
            if(k % 2==0){
                val=9*((int)Math.pow(2,k)-(int)Math.pow(2,k/2))+1;
            }
            else if (k % 2==1){
                val=8*(int)Math.pow(2,k)-6*(int)Math.pow(2,(k+1)/2)+1;
            }
            if(val<(float)array.length/2){
                lista.add(val);
            }
            k++;
        }

        return lista;
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
            } 
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }
}