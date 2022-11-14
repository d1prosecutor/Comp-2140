package Assignments.Assignment4;

/******************************************************************************************
 * NAME: Chukwunaza Chukwuocha
 * STUDENT NUMBER: 007928676
 * COURSE: COMP 2140, SECTION: A01
 * INSTRUCTOR: Cuneyt Ackora
 * ASSIGNMENT: Assignment 4
 *
 * REMARKS: Implements a Binary Search Tree ADT to store A dictionary used in SpellChecking
 ******************************************************************************************/

import java.io.*;
import java.util.*;

public class Dictionary
{
    /*******************************************************************************
     Attributes
     *******************************************************************************/
    private TreeNode root;

    /******************************************************************************
     Public Methods
     *******************************************************************************/

    /********************************************************
     * Dictionary constructor
     *
     * Purpose: reads in the dictionary file so once the object
     * is created we're ready to start the spell check
     * Input Parameters:
     * fileName: the name of the dictionary file
     ********************************************************/
    public Dictionary(String fileName)
    {
        String read_word;

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


                // Insert the new node to the Tree
                insert(read_word);
            }

            System.out.println();

            theFile.close();
        } catch (FileNotFoundException ex)
        {
            System.out.println("Could not find " + fileName + ". Check the file name and try again.");
        }
    }//end dictionary constructor

    /********************************************************
     * lookup
     *
     * Purpose: searches for the node with a specified data
     * the_word: the value to be searched for.
     ********************************************************/
    public boolean lookup(String the_word)
    {
        // search for the word in the tree in lower case to ensure reliability
        return searchRec(the_word.toLowerCase(), root);
    }// end lookup

    /********************************************************
     * insert
     *
     * Purpose: insert a node into the BST.
     * Input: data is content of the node to be inserted.
     ********************************************************/
    public void insert(String data)
    {
        //If the tree is empty just insert at the root
        if (null == root)
        {
            root = new TreeNode(data);
        } else
        {
            //Tree is not empty, look for the correct position and insert there
            insertRec(data, null, root);
        }
    }// end insert

    /************************************************************
     * print
     *
     * Purpose: public driver for the printInorder method.
     *          (If the tree is OK, this should give a sorted
     *          ordering of the values stored in the tree.)
     ************************************************************/
    public void print_table()
    {
        if (null != root)
        {
            printInorder(root);
        }
    }// end print_table


    /******************************************************
     Private Recursive Functions
     ******************************************************/

    /********************************************************
     * searchRec
     *
     * Purpose: private helper method to search for the node containing the specified data.
     * Input Parameters:
     * data: content of the node to be searched for.
     * curr: The current node being walked over.
     ********************************************************/
    private boolean searchRec(String data, TreeNode curr)
    {
        boolean dataFound = false;
        if (null != curr)
        {
            if (curr.word.equals(data))
            {
                dataFound = true;
            } else if (curr.word.compareTo(data) <= 0)
            {
                dataFound = searchRec(data, curr.right);
            } else
            {
                dataFound = searchRec(data, curr.left);
            }
        }
        return dataFound;
    }// end searchRec

    /********************************************************
     * insertRec
     *
     * Purpose: private helper method to insert a node into the BST recursively.
     * Input Parameters:
     * data: content of the node to be inserted.
     * prev: The parent of the current node being walked over.
     * curr: The current node being walked over.
     ********************************************************/
    private void insertRec(String data, TreeNode prev, TreeNode curr)
    {
        if (null == curr)
        {
            TreeNode newNode = new TreeNode(data);
            if (prev.word.compareTo(data) < 0)
            {
                prev.right = newNode;
            } else
            {
                prev.left = newNode;
            }
        } else
        {
            if (curr.word.compareTo(data) <= 0)
            {
                insertRec(data, curr, curr.right);
            } else
            {
                insertRec(data, curr, curr.left);

            }
        }
    }// end insertRec

    /************************************************************
     * printInorder
     *
     * Purpose: print out the tree using an inorder traversal.
     *          (If the tree is OK, this should give a sorted
     *          ordering of the values stored in the tree.)
     ************************************************************/
    private void printInorder(TreeNode curr)
    {
        if (null != curr)
        {
            printInorder(curr.left);
            System.out.println(curr.word);
            printInorder(curr.right);
        }
    } // end method printInorder

    /******************************************************************************
     Inner class for the nodes of the tree
     *******************************************************************************/
    private class TreeNode
    {
        public String word;
        public TreeNode right;
        public TreeNode left;

        /******************************************************
         * Constructor
         *
         * Purpose: creates a new node with a given value
         *          (a leaf node essentially)
         * Input: the new word to be contained by the new node
         ******************************************************/
        public TreeNode(String newWord)
        {
            word = newWord;
            right = left = null;
        }
    }// end class TreeNode

}// end class Dictionary