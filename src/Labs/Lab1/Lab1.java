package Labs.Lab1;
//-----------------------------------------
// NAME: Chukwunaza Chukwuocha
// STUDENT NUMBER: 007928676
// COURSE: COMP 2140, SECTION: A01
// INSTRUCTOR: Cuneyt Akcora
// Lab: 1
//
// REMARKS: To implement Binary Search Iteratively and Recursively
//
//-----------------------------------------

import java.util.*;

public class Lab1
{
    public static void main(String[] args)
    {
        int[] testArray = new int[100000];
        long timestamp;

        int key = 0;

        for (int i = 0; i < testArray.length; i++)
        {
            testArray[i] = i;
        }

        timestamp = new Date().getTime();

        for (int i = 0; i < 200000; i++)
            key = binarySearchIte(testArray, 0);


        timestamp = new Date().getTime() - timestamp;

        System.out.println("The time it takes for The iterative version of Binary search " +
                "to complete is " + timestamp + " milliseconds");

        timestamp = new Date().getTime();

        for (int i = 0; i < 200000; i++)
            key = binarySearchRec(testArray, 1);


        timestamp = new Date().getTime() - timestamp;

        System.out.println("The time it takes for The recursive version of Binary search " +
                "to complete is " + timestamp + " milliseconds");

    }// end main

    //driver code for binarySearch Iterative version
    public static int binarySearchIte(int[] array, int value)
    {
        int position = -1;
        if (array.length > 1)
            return binarySearchIte(array, value, 0, array.length);
        else if (array.length == 1)
        {
            if (value == array[0])
            {
                position = 0;
            }
        }
        return position;
    }// end binarySearch Iterative

    //driver code for binarySearch Recursive version
    public static int binarySearchRec(int[] array, int value)
    {
        int position = -1;
        if (array.length > 1)
            return binarySearchRec(array, value, 0, array.length);
        else if (array.length == 1)
        {
            if (value == array[0])
            {
                position = 0;
            }
        }
        return position;
    }// end binarySearch Recursive


    private static int binarySearchRec(int[] arr, int value, int start, int end)
    {
        int position = -1; //returns -1 if the value being searched for is not found

        if (start <= end)
        {
            int mid = (start + end) / 2;

            if (value == arr[mid])
            {
                position = mid;
            } else if (value < arr[mid])
            {
                return binarySearchRec(arr, value, start, mid - 1);
            } else
            {
                return binarySearchRec(arr, value, mid + 1, end);
            }
        }

        return position;
    }// end binarySearchRec

    private static int binarySearchIte(int[] arr, int value, int start, int end)
    {
        int position = -1; //returns -1 if the value being searched for
        int mid;

        while (start <= end)
        {
            mid = (start + end) / 2;

            if (value == arr[mid])
            {
                return mid;
            } else if (value < arr[mid])
            {
                end = mid - 1;
            } else
            {
                start = mid + 1;
            }
        }
        return position;
    }// end binarySearchIte
}// end class
