package com.company;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    static ArrayList<ArrayList<List<Integer>>> ReturnRandomMatrix ()
    {
        ArrayList<List<Integer>> matrix2D = new ArrayList<List<Integer>>();
        ArrayList<List<Integer>> matrix2D1 = new ArrayList<List<Integer>>();
        ArrayList<Integer> matrix1D = new ArrayList<Integer>();
        ArrayList<Integer> matrix1D1 = new ArrayList<Integer>();
        ArrayList<ArrayList<List<Integer>>> matrix3D = new ArrayList<ArrayList<List<Integer>>>();
        Random rand = new Random();
        int size = rand.nextInt(10)+2;
        int depth = rand.nextInt(10)+2;
        for (int i=0; i<size; i++)
        {
            int rand1 = i+1;
            matrix1D.add(rand1);
            matrix1D1.add(rand1*2);
        }
        for (int i=0; i<size; i++)
        {
            matrix2D.add(matrix1D);
            matrix2D1.add(matrix1D1);
        }
        for (int i=0; i <depth-1; i++)
        {
            matrix3D.add(matrix2D);
        }
        matrix3D.add(matrix2D1);
        return matrix3D;
    }

    static int moveValue(List<Integer> permutation, ArrayList<ArrayList<List<Integer>>> matrix, ArrayList<Integer> timeVector) {
        int FunctionValue = 0;
        int inputSize = matrix.get(0).size();
        int timeSize = timeVector.size();

        for(int i = 0 ; i <inputSize; i++)
        {
            int TempDepth = 0; // temporary 2D matrix index
            int TempValue = 0; // temporary move value
            if(FunctionValue>timeVector.get(timeSize-1)) // check if given time exceeds time vector
            {
                TempDepth = timeVector.get(timeVector.size()-1);
            }
            else
            {
                while(FunctionValue>timeVector.get(TempDepth))
                {
                    TempDepth++;
                }
            }
            if(i != inputSize-1)
            {
                TempValue = matrix.get(TempDepth).get(permutation.get(i)).get(permutation.get(i+1));
            }
            else
            {
                TempValue = matrix.get(TempDepth).get(permutation.get(i)).get(permutation.get(0));
            }
            FunctionValue=FunctionValue+TempValue;
            System.out.print(FunctionValue+ " ");
        }

        return FunctionValue;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<List<Integer>>> matrix = new ArrayList<ArrayList<List<Integer>>>();
        ArrayList<Integer> Time = new ArrayList<Integer>();
        ArrayList<Integer> input = new ArrayList<Integer>();

        matrix = ReturnRandomMatrix();
        int size = matrix.get(0).size();
        for (int i =0; i<size; i++)
        {
            input.add(i);
        }
        int depth = matrix.size();
        Time.add(0);                         // pierwszą wartością wektora czasu musi być 0
        for (int i =1; i<depth;i++)
        {
            Time.add(i);					// długość wektora taka jak głębokość tablicy
        }



        System.out.println(moveValue(input, matrix, Time));
        System.out.println(Time);
        System.out.println(input.size());
    }
}
/*
 * Matrix - tablica 3d wejścia
 * input - permutacja wejściowa
 * time - wektor wejściowy czasu
 * functionValue - funkcja celu
 *
 *
 *
 *
 *
 *
 *
 */

