package Assignments.Assignment1;

//-----------------------------------------
// NAME: Chukwunaza Chukwuocha
// STUDENT NUMBER: 007928676
// COURSE: COMP 2140, SECTION: A01
// INSTRUCTOR: Cuneyt Akcora
// ASSIGNMENT: assignment 1, QUESTION: question 1
//
// REMARKS: This is a java program to compute the heights of the tick marks on a ruler recursively.
//-----------------------------------------

import java.util.*;

public class ChukwunazaChukwuochaA1Q1
{
    public static void main(String[] args)
    {
        int length = 0;
        Scanner inputNum = new Scanner(System.in);

        //Get the user input and check if it is an integer as well as a power of 2
        do
        {
            System.out.println("Enter the length of the ruler (Integer)");

            try
            {
                length = inputNum.nextInt();
            } catch (InputMismatchException e)
            {
                System.out.println("Not a valid integer");
            }
        } while (length < 2 || ((Math.log(length) / Math.log(2)) != Math.round(Math.log(length) / Math.log(2))));

        inputNum.close();

        heights(length);
    }// end main

    //------------------------------------------------------
// heights()
//
// PURPOSE: It acts as a driver for the private recursive function
// It creates the array of the position of the points and the heights of the ruler and
// calculates the heights using the private recursive function then prints out the two arrays at the end
//
// INPUT PARAMETERS:
// length: the length of the ruler (Integer) sent in by the user
//------------------------------------------------------
    public static void heights(int length)
    {
        int[] positionArray = new int[length + 1];

        //get only the last digits of the positions
        for (int i = 0; i < positionArray.length; i++)
        {
            positionArray[i] = i % 10;
        }

        int[] heightsArray = new int[length + 1];

        heightsArray = heightsRec(0, length + 1, length, heightsArray);

        for (int i = 0; i < heightsArray.length; i++)
        {
            System.out.print(heightsArray[i]);
        }

        System.out.println();

        for (int i = 0; i < positionArray.length; i++)
        {
            System.out.print(positionArray[i]);
        }
        System.out.println();
        System.out.println("End of Processing");

    }// end heights

    //------------------------------------------------------
// heightsRec()
//
// PURPOSE: Calculates the heights of the tick marks on a ruler given the length of the ruler
// INPUT PARAMETERS:
// Start: This stores the starting position of the ruler
// End: This stores the end position of the ruler
// length: the length of the ruler (Integer) sent in by the user
// rulerHeights: This accepts the array which stores the heights of the tick marks on the ruler
//
// OUTPUT PARAMETERS:
// rulerHeights: It returns the array back after filling it with the
// required heights of the tick marks of the ruler
//------------------------------------------------------
    private static int[] heightsRec(int start, int end, int length, int[] rulerHeights)
    {
        int baseTwo = 2;

        if (length > 2)
        {
            // calculates the heights of the middle point of the ruler and converts it to base 2
            int midHeight = (int) (Math.log(length) / Math.log(baseTwo));

            int midRuler = start + (end - start) / 2;

            rulerHeights[midRuler] = midHeight;

            heightsRec(start, midRuler, length / 2, rulerHeights);
            heightsRec(midRuler, end, length / 2, rulerHeights);
        }
        return rulerHeights;
    }
}// end heightsRec
