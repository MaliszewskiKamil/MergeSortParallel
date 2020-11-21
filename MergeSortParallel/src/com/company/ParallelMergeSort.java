package com.company;

import java.util.Arrays;

public class ParallelMergeSort {

    public void parallelMergeSort(Integer[] numbers, int numOfThreads) {

        if (numOfThreads <= 1) {
            MergeSort.sort(numbers); // SEQUENTIAL !!!!
            return;
        }

        int middleIndex = numbers.length / 2;

        Integer[] leftSubarray = Arrays.copyOfRange(numbers, 0, middleIndex);
        Integer[] rightSubarray = Arrays.copyOfRange(numbers, middleIndex, numbers.length);

        Thread leftSorter = mergeSortThread(leftSubarray, numOfThreads);
        Thread rightSorter = mergeSortThread(rightSubarray, numOfThreads);

        leftSorter.start();
        rightSorter.start();

        try {
            leftSorter.join();
            rightSorter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mergeArray(leftSubarray, rightSubarray, numbers);
    }

    private Thread mergeSortThread(Integer[] numbers, int numOfThreads) {

        return new Thread() {
            @Override
            public void run() {
                parallelMergeSort(numbers, numOfThreads / 2);
            }
        };
    }

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }

        // postcondition: a[lo .. hi] is sorted
        assert isSorted(a, lo, hi);
    }

    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    private static boolean    less(Comparable v, Comparable w)   {  return v.compareTo(w) < 0;  }

    private static Integer[] mergeArray(Integer[] arrayA, Integer[] arrayB, Integer[] mergedArray)
    {

        int i=0, j=0, k=0;

        while (i < arrayA.length && j < arrayB.length)
        {
            if (arrayA[i] < arrayB[j])
            {
                mergedArray[k] = arrayA[i];
                i++;
                k++;
            }
            else
            {
                mergedArray[k] = arrayB[j];
                j++;
                k++;
            }
        }

        while (i < arrayA.length)
        {
            mergedArray[k] = arrayA[i];
            i++;
            k++;
        }

        while (j < arrayB.length)
        {
            mergedArray[k] = arrayB[j];
            j++;
            k++;
        }

        return mergedArray;
    }
}
