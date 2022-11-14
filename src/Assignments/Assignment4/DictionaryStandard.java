package Assignments.Assignment4;

import java.io.*;
import java.util.*;

// This is our table ADT that will hold our hashed lists of dictionary words.
public class DictionaryStandard
{
    /******************************************************************************
     Attributes
     *******************************************************************************/
    private Tree dictTree;

    /******************************************************************************
     Public Methods
     *******************************************************************************/

    // our constructor will read in the dictionary file so once the object
    // is created we're ready to start the spell check
    public DictionaryStandard(String fileName)
    {
        String read_word;

        // start by initializing the dictTree to a new Tree
        dictTree = new Tree();

        // read the words into the tree
        try
        {
            Scanner theFile = new Scanner(new File(fileName));

            System.out.print("Loading dictionary");

            // each line contains 1 word...
            while (theFile.hasNextLine())
            {
                // get the next word to process
                read_word = theFile.nextLine();

                System.out.print(".");

                // always work in lower case
                read_word = read_word.toLowerCase();


                // create a new node at the top of the appropriate list so this is our insert
                dictTree.insert(read_word);
            }

            System.out.println();

            theFile.close();
        } catch (FileNotFoundException ex)
        {
            System.out.println("Could not find " + fileName + ". Check the file name and try again.");
        }
    }

    // try to find the given word in the dictionary, returns true if present
    public boolean lookup(String the_word)
    {
        // search for the word in the tree in lower case to ensure reliability
        return dictTree.search(the_word.toLowerCase());
    }

    // test method to see how the hashing is working...
    public void print_tree()
    {
        dictTree.print();
    }

}