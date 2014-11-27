package com.company;


import java.util.ArrayList;
import java.util.IntSummaryStatistics;
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

    static List<Integer> getNeighbour (List<Integer> startPermutation) {
        Random rand = new Random();


//        System.out.println(startPermutation);
//        int neighbourhoodSize = 10;
        // generate two not equal permutation elements
        int element1Index = rand.nextInt(startPermutation.size());
        int element2Index;
        do
        {
            element2Index = rand.nextInt(startPermutation.size());
        } while(element1Index == element2Index);


//            ArrayList<Integer> tempPerm = (ArrayList<Integer>)startPermutation.clone();
        List<Integer> tempPerm = new ArrayList<Integer>(startPermutation);
        tempPerm.set(element1Index, startPermutation.get(element2Index));
        tempPerm.set(element2Index, startPermutation.get(element1Index));
//        System.out.println(matrix);

        return tempPerm;
    }




    static void moveValueTest() {
        ArrayList<ArrayList<List<Integer>>> matrix = new ArrayList<ArrayList<List<Integer>>>();
        ArrayList<Integer> timeVector = new ArrayList<Integer>();
        ArrayList<Integer> permutation = new ArrayList<Integer>();

        matrix = ReturnRandomMatrix();
        int size = matrix.get(0).size();
        for (int i =0; i<size; i++)
        {
            permutation.add(i);
        }
        int depth = matrix.size();
        permutation.add(0);                         // pierwszą wartością wektora czasu musi być 0
        for (int i =1; i<depth;i++)
        {
            permutation.add(i);					// długość wektora taka jak głębokość tablicy
        }



        System.out.println(moveValue(permutation, matrix, timeVector));
        System.out.println(timeVector);
        System.out.println(permutation.size());
    }

    static void getNeighbourTest() {
        List<Integer> permutation = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            permutation.add(i);
        }

        System.out.println(permutation);
        System.out.println(getNeighbour(permutation));
        System.out.println();
    }

    public static void main(String[] args) {
//        moveValueTest();
//        for (int i = 0; i < 10; i++)
//            getNeighbourTest();
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

