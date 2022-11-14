package Assignments.Assignment3.Q2;

//-----------------------------------------
// NAME: Chukwunaza Chukwuocha
// STUDENT NUMBER: 007928676
// COURSE: COMP 2140, SECTION: A01
// INSTRUCTOR: Cuneyt Ackora
// ASSIGNMENT: Assignment 3 Question 2
//
// REMARKS: Tests a spellchecker by providing a dictionary file and the file
// to be tested for incorrectly spelled words
//
//-----------------------------------------

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpellChecker
{
    public static void main(String[] args)
    {
        String newLine;// Variable used to read all new lines from the scanners

        Table dictTable = new Table();  //Dictionary table
        WordQueue incorrectWords = new WordQueue(); // incorrect words

        Scanner dictInput = null;
        Scanner fileInput = null;

//        the dictionary file is the first file to be passed as an argument when running the code
        String dictionary = args[0];
        String testFile = args[1];


        //Try to open and read the files
        try
        {
            dictInput = new Scanner(new File(dictionary));
            fileInput = new Scanner(new File(testFile));
        } catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }

        //Read each line from the dictionary file and add each word as the data to the dictionary table
        //!!!Note that in this program, the key and the data are the same
        if (null != dictInput)
        {
            while (dictInput.hasNextLine())
            {
                newLine = dictInput.nextLine();

                dictTable.insert(newLine, newLine);
            }
            dictInput.close();
        }


        /*Read each line from the test File and add it to the incorrect words queue if
         the word is not found in the dictionary Table*/
        if (null != fileInput)
        {
            int lineCounter = 0;
            while (fileInput.hasNextLine())
            {
                lineCounter++;

                newLine = fileInput.nextLine();

                if (newLine.length() > 0)
                {
                    String[] splitWords = splitSentence(newLine);

                    int index = 0;
                    while (index < splitWords.length)
                    {
                        if (splitWords[index].length() > 0 && !dictTable.search(splitWords[index]))
                        {
                            incorrectWords.enter(splitWords[index], lineCounter);
                        }
                        index++;
                    }
                }
            }
            fileInput.close();
        }
        incorrectWords.print();
    }

    //------------------------------------------------------
// splitSentence
//
// PURPOSE: Splits a string into words using punctuation marks as delimiters
// INPUT PARAMETERS:
// newLine: the string to be split
//------------------------------------------------------
    private static String[] splitSentence(String newLine)
    {
        //Store all the punctuation marks which will be used to split the testFile in one String
        // and in regex format
        String allPunctuationMarks = "[-.,!?:;_+*%@\\\\(){}\\[\\]\"/\\s]+";

        return newLine.split(allPunctuationMarks);
    }
}
