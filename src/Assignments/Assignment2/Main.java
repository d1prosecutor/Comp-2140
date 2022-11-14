package Assignments.Assignment2;
//-----------------------------------------
// NAME: Chukwunaza Chukwuocha
// STUDENT NUMBER: 007928676
// COURSE: COMP 2140, SECTION: A01
// INSTRUCTOR: Cuneyt Ackora
// ASSIGNMENT: Assignment 2
//
// REMARKS: Tests the Set class program
//
//-----------------------------------------

import java.io.File;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        //Variables to represent the keys for each command
        String insert = "I";
        String delete = "D";
        String intersection = "*";
        String union = "U";
        String difference = "\\";
        String print = "P";

        // Variables to represent the tokens read from each line
        int commandToken = 0;
        int firstToken = 1;
        int secondToken = 2;
        int thirdToken = 3;

        //Variables to read the input
        Scanner theFile = new Scanner(new File("a2data.txt"));
        String line;
        String[] tokens;

        //Initializing the Array of Sets
        int numberOfSets = Integer.parseInt(theFile.nextLine());
        Set2[] arrayOfSet2s = new Set2[numberOfSets];

        //Initializing all the Sets in the array
        for (int i = 0; i < numberOfSets; i++)
        {
            arrayOfSet2s[i] = new Set2();
        }

        while (theFile.hasNextLine())
        {
            line = theFile.nextLine();

            tokens = line.split("\\s+");

            if (tokens[commandToken].equals(insert))
            {
                arrayOfSet2s[Integer.parseInt(tokens[firstToken])].insert(Integer.parseInt(tokens[secondToken]));
            } else if (tokens[commandToken].equals(delete))
            {
                arrayOfSet2s[Integer.parseInt(tokens[firstToken])].delete(Integer.parseInt(tokens[secondToken]));
            } else
            {
                if (tokens[commandToken].equals(intersection))
                {
                    arrayOfSet2s[Integer.parseInt(tokens[thirdToken])].intersection
                            (arrayOfSet2s[Integer.parseInt(tokens[firstToken])],
                                    arrayOfSet2s[Integer.parseInt(tokens[secondToken])]);
                } else if (tokens[commandToken].equals(union))
                {
                    arrayOfSet2s[Integer.parseInt(tokens[thirdToken])].union
                            (arrayOfSet2s[Integer.parseInt(tokens[firstToken])],
                                    arrayOfSet2s[Integer.parseInt(tokens[secondToken])]);

                } else if (tokens[commandToken].equals(difference))
                {
                    arrayOfSet2s[Integer.parseInt(tokens[thirdToken])].
                            difference(arrayOfSet2s[Integer.parseInt(tokens[firstToken])],
                                    arrayOfSet2s[Integer.parseInt(tokens[secondToken])]);
                } else if (tokens[commandToken].equals(print))
                {
                    arrayOfSet2s[Integer.parseInt(tokens[firstToken])].print(Integer.parseInt(tokens[firstToken]));
                }
            }
        }

        System.out.println("End of Processing");
        theFile.close();
    }
}
