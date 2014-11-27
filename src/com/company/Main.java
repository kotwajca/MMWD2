package com.company;


import java.util.*;

public class Main {
    static int counter = 0;

    static List<ArrayList<List<Integer>>> ReturnRandomMatrix ()
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

    static int moveValue(List<Integer> permutation, List<List<List<Integer>>> matrix, List<Integer> timeVector) {
        counter++;
        int FunctionValue = 0;
        int inputSize = matrix.get(0).size();
        int timeSize = timeVector.size();

        if (permutation.size() == 0) {
            ;
        }

        for(int i = 0 ; i < inputSize; i++)
        {
            int TempDepth = 0; // temporary 2D matrix index
            int TempValue = 0; // temporary move value
            if(FunctionValue > timeVector.get(timeSize-1)) // check if given time exceeds time vector
            {
                TempDepth = timeVector.get(timeVector.size()-1);
            }
            else
            {
                while (FunctionValue > timeVector.get(TempDepth))
                {
                    TempDepth++;
                    ;
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

    static List<List<Integer>> getAcceptableNeighbourWithIndexes (List<Integer> startPermutation, List<List<Integer>> tabuMatrix) {
        Random rand = new Random();

        // generate two not equal permutation elements
        int element1Index = rand.nextInt(startPermutation.size());
        int element2Index;

        do {
            do {
                element2Index = rand.nextInt(startPermutation.size());
            } while (element1Index == element2Index);
        } while (tabuMatrix.get(element1Index).get(element2Index) != 0);

        List<Integer> tempPerm = new ArrayList<Integer>(startPermutation);

        tempPerm.set(element1Index, startPermutation.get(element2Index));
        tempPerm.set(element2Index, startPermutation.get(element1Index));

        List<List<Integer>> returnList = new ArrayList<List<Integer>>();
        returnList.add(tempPerm);

        List<Integer> indexRow = new ArrayList<Integer>();
        indexRow.add(element1Index);
        indexRow.add(element2Index);

        returnList.add(indexRow);

        return returnList;
    }



//    static void moveValueTest() {
//        List<List<List<Integer>>> matrix = new ArrayList<List<List<Integer>>>();
//        ArrayList<Integer> timeVector = new ArrayList<Integer>();
//        ArrayList<Integer> permutation = new ArrayList<Integer>();
//
//        matrix = ReturnRandomMatrix();
//        int size = matrix.get(0).size();
//        for (int i =0; i<size; i++)
//        {
//            permutation.add(i);
//        }
//        int depth = matrix.size();
//        permutation.add(0);                         // pierwszą wartością wektora czasu musi być 0
//        for (int i =1; i<depth;i++)
//        {
//            permutation.add(i);					// długość wektora taka jak głębokość tablicy
//        }
//
//
//
//        System.out.println(moveValue(permutation, matrix, timeVector));
//        System.out.println(timeVector);
//        System.out.println(permutation.size());
//    }


    static List<List<Integer>> getReducedIncidenceMatrix(List<List<Integer>> incidenceMatrix) {
        int verticesNumber = incidenceMatrix.size(); // assuming input matrix is square

        List<List<Integer>> reducedIncidenceMatrix = new ArrayList<List<Integer>>();

        for (int i = 0; i < verticesNumber; ++i) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j < verticesNumber; j++) {
                if (incidenceMatrix.get(i).get(j) != -1) {   // i, j switched to maintain array literals transposition
                    row.add(j);
                }
            }
            reducedIncidenceMatrix.add(row);
        }

        return reducedIncidenceMatrix;
    }

    static List<Integer> getPath(Node<Integer> currentRoot, List<List<Integer>> incidenceMatrix,
                                 Integer globalRootValue) {
        Integer verticesNumber = incidenceMatrix.size();

        List<Integer> parentsList = currentRoot.getPathToRoot(null);

        List<Integer> childCandidatesValues = incidenceMatrix.get(currentRoot.value);
        List<Integer> path;

        for (Integer childValue : childCandidatesValues) {
            if (childValue.equals(globalRootValue) && parentsList.size() == verticesNumber) {
                return parentsList;
            }

            if (!parentsList.contains(childValue)) {
                Node<Integer> child = new Node<Integer>(childValue);
                currentRoot.addChild(child);
                path = getPath(child, incidenceMatrix, globalRootValue);
                if (path != null) // and if length = N
                    return path;
            }
        }
        return null;
    }

//    static List<List<Integer>> readIncidenceMatrix(String path) throws IOException {
//        List<String> rows = Files.readAllLines(Paths.get(path));
//        List<List<Integer>> matrix = new ArrayList<List<Integer>>();
//        for (String row : rows) {
//            String[] stringValues = row.split(inputFileDelimiter);
//            List<Integer> intRow = new ArrayList<Integer>();
//            for (String stringValue : stringValues) {
//                intRow.add(Integer.parseInt(stringValue));
//            }
//            matrix.add(intRow);
//        }
//        return matrix;
//    }
//
//    static void printPathToFile(List<Integer> path, String filePath) throws FileNotFoundException {
//        PrintWriter zapis = new PrintWriter(filePath);
//        zapis.println(path);
//        zapis.close();
//    }

//    static void getAcceptableNeighbourTest() {
//        List<Integer> permutation = new ArrayList<Integer>();
//        for (int i = 0; i < 5; i++) {
//            permutation.add(i);
//        }
//
//        System.out.println(permutation);
//        System.out.println(getAcceptableNeighbour(permutation));
//        System.out.println();
//    }

    static List<Integer> getBestPath(List<List<List<Integer>>> matrix, List<Integer> timeVector, List<Integer> startPath) {
        List<Integer> currentPath = startPath;
        List<Integer> bestPath = startPath;


        int tabuLifetime = 2;


        for (int n = 0; n < 10; n++) {
            // 1
            int currentValue = moveValue(currentPath, matrix, timeVector);
            int bestValue = moveValue(bestPath, matrix, timeVector);

            ArrayList<List<Integer>> tabuMatrix = new ArrayList<List<Integer>>();
            int tabuSize = startPath.size(); // startPath has the same length as the vertices number

            for (int i = 0; i < tabuSize; i++) {
                List<Integer> tabuRow = new ArrayList<Integer>();
                tabuMatrix.add(tabuRow);
                for (int j = 0; j < tabuSize; j++) {
                    tabuRow.add(0);
                }
            }

            // 2
            List<List<Integer>> acceptablePathWithIndexes = getAcceptableNeighbourWithIndexes(currentPath, tabuMatrix);
            List<Integer> newPath = acceptablePathWithIndexes.get(0);
            List<Integer> newPathIndexes = acceptablePathWithIndexes.get(1);
            List<Integer> bestNewPathIndexes = newPathIndexes;

            int bestNewPathValue = moveValue(newPath, matrix, timeVector);

            List<Integer> currentPathFromAcceptable = getAcceptableNeighbourWithIndexes(currentPath, tabuMatrix).get(0);

            for (int i = 0; i < 10; i++) { // neighbourhood size

                acceptablePathWithIndexes = getAcceptableNeighbourWithIndexes(currentPath, tabuMatrix);
                newPath = acceptablePathWithIndexes.get(0);
                newPathIndexes = acceptablePathWithIndexes.get(1);

                int newValueCandidate = moveValue(newPath, matrix, timeVector);
                if (newValueCandidate < bestNewPathValue) {
                    bestNewPathValue = newValueCandidate;
                    currentPathFromAcceptable = newPath;
                    bestNewPathIndexes = newPathIndexes;
                }
            }


            // currentPathFromAcceptable - best path in the acceptable neighbourhood
            // bestNewPathIndexes - best path indexes in the acceptable neighbourhood

            int controlVal = 0;

            // 3

            int swappedIndex1 = bestNewPathIndexes.get(0);
            int swappedIndex2 = bestNewPathIndexes.get(1);

            for (int i = 0; i < tabuSize; i++) {
                List<Integer> tabuRow = new ArrayList<Integer>(tabuMatrix.get(i));
                for (int j = 0; j < tabuSize; j++) {
                    if (tabuRow.get(j) != 0) {
                        List<Integer> swappedCurrentPath = new ArrayList<Integer>(currentPath);
                        java.util.Collections.swap(swappedCurrentPath, i, j);

                        int swappedValue = moveValue(swappedCurrentPath, matrix, timeVector);
                        if (swappedValue < bestValue) {
                            bestValue = swappedValue;
                            currentValue = swappedValue;
                            currentPath = bestPath = swappedCurrentPath;

                            swappedIndex1 = i;
                            swappedIndex2 = j;

                            controlVal++;
                        }
                    }
                }
            }

            if (controlVal == 0) {
                currentPath = currentPathFromAcceptable;
                currentValue = moveValue(currentPathFromAcceptable, matrix, timeVector);
            }

            if (currentValue < bestValue) {
                bestValue = currentValue;
            }


            // 4

            for (int i = 0; i < tabuSize; i++) {
                List<Integer> tabuRow = tabuMatrix.get(i);
                for (int j = 0; j < tabuSize; j++) {
                    int tabuElement = tabuRow.get(j);
                    if (tabuElement > 0) {
                        tabuRow.set(j, tabuElement - 1);
                    }
                }
            }

            tabuMatrix.get(swappedIndex1).set(swappedIndex2, tabuLifetime);
            tabuMatrix.get(swappedIndex2).set(swappedIndex1, tabuLifetime);
        }

        return bestPath;
    }

    public static void main(String[] args) {
        // generate matrix
        List<List<List<Integer>>> matrix = new ArrayList<List<List<Integer>>>();
        for (int k = 0; k < 2; k++) {
            List<List<Integer>> slice = new ArrayList<List<Integer>>();
            matrix.add(slice);
            for (int i = 0; i < 4; i++) {
                List<Integer> row = new ArrayList<Integer>();
                slice.add(row);
                for (int j = 0; j < 4; j++) {
                    row.add(j+1);
                }
            }
        }


        // generate timeVector
        List<Integer> timeVector = new ArrayList<Integer>();
        timeVector.add(0);
        timeVector.add(1);

        // generate start path
        List<Integer> startPath = new ArrayList<Integer>();
        startPath.add(0);
        startPath.add(1);
        startPath.add(2);
        startPath.add(3);


        System.out.println(matrix);
        System.out.println(timeVector);
        System.out.println(startPath);

        List<Integer> bestPath = getBestPath(matrix, timeVector, startPath);

        System.out.println();
        System.out.println();
        System.out.println("response: ");


        System.out.println(bestPath);
        System.out.println(counter);

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

