package Assignments.Assignment1;

//-----------------------------------------
// NAME: Chukwunaza Chukwuocha
// STUDENT NUMBER: 007928676
// COURSE: COMP 2140, SECTION: A01
// INSTRUCTOR: Cuneyt Akcora
// ASSIGNMENT: assignment 1, QUESTION: question 2
//
// REMARKS: Implements merge sort, plain version and the median-of-three version of quick sort
// on arrays of Integers and tests the time efficiency of these algorithms
//
//-----------------------------------------

import java.io.File;
import java.util.*;

public class ChukwunazaChukwuochaA1Q2
{
    public static void main(String[] args) throws Exception
    {
        int[] intArray;

        //Get the input file from standard input
        Scanner theFile = new Scanner(new File("A1data.txt"));

        int numItems = Integer.parseInt(theFile.nextLine());

        String line;
        String[] tokens;

        intArray = new int[numItems];
        int arrayIndex = 0;

        // Breaks the tokens into separate integers and store them in the array
        while (theFile.hasNextLine())
        {
            line = theFile.nextLine().trim();

            tokens = line.split("\\s+");

            for (int i = 0; i < tokens.length; i++)
            {
                intArray[arrayIndex++] = Integer.parseInt(tokens[i]);
            }
        }

//        test the sorting algorithms
        testMergeSort(intArray);
        System.out.println();

//        testQuickSort(intArray);
//        System.out.println();

//        testQuickSortMed(intArray);

        theFile.close();
    }//end main

    //Driver Code for the mergeSort recursive
    public static void mergeSort(int[] array)
    {
        if (array.length > 1)
        {
            int[] temp = new int[array.length];

            mergeSortRec(array, 0, array.length, temp);
        }
    }//end mergeSort

    private static void mergeSortRec(int[] arr, int start, int end, int[] temp)
    {
        if (end - start > 1)
        {
            int mid = start + (end - start) / 2;

            mergeSortRec(arr, start, mid, temp);
            mergeSortRec(arr, mid, end, temp);

            merge(arr, start, mid, end, temp);
        }
    }//end mergeSortRec

    //------------------------------------------------------
// merge()
//
// PURPOSE: merges the two sorted halves of sorted array received from the mergeSort method
// and copies the merged array back into the original array(arr[]) from the temp array(temp[])
// INPUT PARAMETERS:
// arr[]: this receives the original array which we want to sort
// start: this stores the start position of the arrays(temp[] and arr[])
// mid: this stores the middle position of the array(arr[])
// end: this stores the end position of the arrays(temp[] and arr[])
// temp[]: this stores the temporary array which will be used in sorting the original array(arr[])
//------------------------------------------------------
    private static void merge(int[] arr, int start, int mid, int end, int[] temp)
    {
        //Pointers for the Left and Right sub-arrays and Current Index of the temp array respectively
        int currL = start;
        int currR = mid;
        int currIndex;

        for (currIndex = start; currIndex < end; currIndex++)
        {
            if (currL < mid && (currR >= end || arr[currL] < arr[currR]))
            {
                temp[currIndex] = arr[currL];
                currL++;
            } else
            {
                temp[currIndex] = arr[currR];
                currR++;
            }
        }

        for (int i = start; i < end; i++)
            arr[i] = temp[i];
    }//end merge

    //Driver code for the quickSort recursive function
    public static void quickSortRec(int[] array)
    {
        if (array.length > 1)
            quickSortRec(array, 0, array.length);

    }//end quickSort

    //------------------------------------------------------
// partition()
//
// PURPOSE: Arranges the array such that the pivot is in a position where all items less than
// it are on its left while all ites greater than it are on its right.
// INPUT PARAMETERS:
// arr[]: this receives the original array which we want to partition
// start: this stores the start position of the array(arr[])
// end: this stores the end position of the arrays(temp[] and arr[])
// OUTPUT PARAMETERS:
// (bigIndex-1): This is the location where the pivot is finally located after the partition is done
//------------------------------------------------------
    private static int partition(int[] arr, int start, int end)
    {
        int pivot = arr[start];

        //Index of the starting position of all the elements larger than the pivot
        int bigIndex = (start + 1);

        for (int currIndex = start + 1; currIndex < end; currIndex++)
        {
            if (arr[currIndex] < pivot)
            {
                swap(arr, bigIndex, currIndex);
                bigIndex++;
            }
        }
        swap(arr, bigIndex - 1, start);
        return (bigIndex - 1);
    }// end partition

    private static void quickSortRec(int[] arr, int start, int end)
    {
        if ((end - start) > 1)
        {
            int pivot = partition(arr, start, end);

            quickSortRec(arr, start, pivot - 1);
            quickSortRec(arr, pivot + 1, end);
        }
    }// end quickSortRec

    //Driver code for the quickSortMed Recursive Function
    public static void quickSortMedRec(int[] array)
    {
        if (array.length > 1)
            quickSortMedRec(array, 0, array.length - 1);

    }// end quickSortMed

    private static void quickSortMedRec(int[] arr, int start, int end)
    {
        if ((end - start) > 10)
        {
            // Finds the median of the first, middle and last items in the array
            // and swaps that item with the first item in the array
            int median = medianOfThree(arr, start, start + (end - start) / 2, end);
            swap(arr, start, median);

            int pivot = partition(arr, start, end);

            quickSortMedRec(arr, start, pivot - 1);
            quickSortMedRec(arr, pivot + 1, end);
        } else
        {
            insertionSort(arr);
        }
    }// end quickSortMedRec

    public static void insertionSort(int[] array)
    {
        for (int currIndex = 1; currIndex < array.length; currIndex++)
        {
            int key = array[currIndex];
            int sortedIndex = currIndex - 1;

            while ((sortedIndex >= 0) && (array[sortedIndex] > key))
            {
                array[sortedIndex + 1] = array[sortedIndex];
                sortedIndex--;
            }
            array[sortedIndex + 1] = key;
        }
    }// end insertionSort

    //------------------------------------------------------
// merge
//
// PURPOSE: Finds the median of three integers
// INPUT PARAMETERS:
// a, b, c: The three integers
// OUTPUT PARAMETERS:
// median: The median of the three integers
//------------------------------------------------------
    public static int medianOfThree(int[] arr, int a, int b, int c)
    {
        int median;

        if ((arr[a] < arr[b] && arr[b] < arr[c]) || (arr[c] < arr[b] && arr[b] < arr[a]))
        {
            median = b;
        } else if ((arr[b] < arr[a] && arr[a] < arr[c]) || (arr[c] < arr[a] && arr[a] < arr[b]))
        {
            median = a;
        } else
        {
            median = c;
        }

        return median;
    }// end medianOfThree

    public static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }// end swap

    //------------------------------------------------------
// sortErrorCheck()
//
// PURPOSE: Checks if the array it receives is sorted by checking if each
// element is bigger than the one previous to it
// INPUT PARAMETERS:
// arr[]: this receives the array which we want to check
// OUTPUT PARAMETERS:
// sorted: return true if the array is sorted and false if it's not
//------------------------------------------------------
    public static boolean sortErrorCheck(int[] arr)
    {
        boolean sorted = true;

        for (int i = 0; i < arr.length - 1; i++)
        {
            if (arr[i + 1] < arr[i])
            {
                sorted = false;
            }
        }
        return sorted;
    }// sortErrorCheck

    //------------------------------------------------------
// test Methods (testMergeSort, testquickSort, and testQuickSortMed)
//
// PURPOSE: tests any one of the sorting algorithms written and prints out the
// time it takes for that algorithm to complete and also if the algorithm sorted the array successfully or not
// INPUT PARAMETERS:
// arr: The array to be tested
//------------------------------------------------------
    public static void testMergeSort(int[] arr)
    {
        int[] tempArr = new int[arr.length];
        System.arraycopy(arr, 0, tempArr, 0, arr.length);

        long timestamp;
        timestamp = new Date().getTime();

        System.out.println("Sorting with mergeSort");

        mergeSort(tempArr);

        timestamp = new Date().getTime() - timestamp;

        System.out.println("Time for mergesort to complete sorting: " + timestamp + " milliseconds.");

        System.out.println("mergeSort " + (sortErrorCheck(tempArr) ? "Successfully sorted " + arr.length + " elements" :
                "was unsuccessful"));
    }//end testSort

    public static void testQuickSort(int[] arr)
    {
        int[] tempArr = new int[arr.length];
        System.arraycopy(arr, 0, tempArr, 0, arr.length);

        System.out.println("Sorting with quickSort");

        long timestamp;
        timestamp = new Date().getTime();

        quickSortRec(tempArr);

        timestamp = new Date().getTime() - timestamp;

        System.out.println("Time for quickSort to complete sorting: " + timestamp + " milliseconds.");

        System.out.println("quickSort " + (sortErrorCheck(tempArr) ? "Successfully sorted " + arr.length + " elements" :
                "was unsuccessful"));
    }//end testSort

    public static void testQuickSortMed(int[] arr)
    {
        int[] tempArr = new int[arr.length];
        System.arraycopy(arr, 0, tempArr, 0, arr.length);

        System.out.println("Sorting with quickSortMed");

        long timestamp;
        timestamp = new Date().getTime();

        quickSortMedRec(tempArr);

        timestamp = new Date().getTime() - timestamp;

        System.out.println("Time for quickSortMed to complete sorting: " + timestamp + " milliseconds.");

        System.out.println("quickSortMed " + (sortErrorCheck(tempArr) ? "Successfully sorted " + arr.length + " elements" :
                "was unsuccessful"));
    }//end testSort

}//end class
