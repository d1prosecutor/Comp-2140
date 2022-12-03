import java.io.*;
import java.util.*;

/**
 * Dictionary class
 *
 *  This is our table ADT (a binary search tree)
 *  that will hold our dictionary words.
 */
class Dictionary
{

  /******************************************************************************
  Inner class
  *******************************************************************************/

  private class TreeNode
  {
    public String word;
    public TreeNode left;
    public TreeNode right;

    // tree node constructor
    TreeNode( String newWord, TreeNode  newLeft, TreeNode newRight )
    {
      word = newWord;
      left = newLeft;
      right = newRight;
    }

  }

  /******************************************************************************
  Attributes
  *******************************************************************************/

  // Our table. It consists of a binary search tree
  // (for which we need a pointer to the root).
  
  private TreeNode root;

  /******************************************************************************
  Public Methods
  *******************************************************************************/

  /**
   * Dictionary constructor
   *
   * PURPOSE: Our constructor will read in and store the dictionary file in
   * the binary search tree.  As soon as the dictionary has been
   * created, we're ready to start the spell check.
   */
  
  Dictionary( String fileName )
  {
    String read_word;

    // Start by initializing the tree to an empty tree.
    root = null;

    // Read the words into the tree.
    try
    {
      Scanner theFile = new Scanner(new File(fileName));

      System.out.print( "Loading dictionary" );
      
      // each line contains 1 word...
      while ( theFile.hasNextLine() )
      {
        // get the next word to process
        read_word = theFile.nextLine();

        System.out.print( "." );
        
        // Always work in lower case.
        read_word = read_word.toLowerCase();

        // Insert the lower-case word into the tree.
        insert( read_word );
      }

      System.out.println();

      theFile.close();
    }

    catch( FileNotFoundException ex )
    {
      System.out.println( "Could not find " + fileName + ". Check the file name and try again." );
    }
  } 


  /**
  * lookup method
  *
  * PURPOSE: Try to find the given word in the dictionary.
  * PARAMETER: the_word: the given word to look up in the dictionary.
  *            (an input parameter)
  * RETURNS: true if the given word is present and false otherwise.
  */
  
  public boolean lookup( String the_word )
  {
    boolean found = false;
    TreeNode curr = root;  // Start the search at the root.
    
    // Make sure we only work in lower case.
    String  hash_word = the_word.toLowerCase();

    // Search for the word in the list.
    while ( !found && curr != null )
    {
      if ( hash_word.equals( curr.word ) )
        found = true;
      else if (hash_word.compareTo( curr.word ) < 0)
      {
        curr = curr.left;
      }
      else
      {
        curr = curr.right;
      }
    } 

    return found;
  } 

  /**
   * method print_table
   *
   * PURPOSE: Test method to see how the tree is working...
   */
   
  public void print_table()
  {
    System.out.println( "The Tree (Sideways)" );
    printTree( root, "" );
    System.out.println();
    System.out.println( "Inorder Listing" );
    inorder( root );
  } 

  /******************************************************************************
  Private Methods
  *******************************************************************************/

  /**
   * method insert
   *
   * PURPOSE: Private binary search tree insertion method.
   *          Inserts the new word into the binary search tree,
   *          if it is not already in the tree.
   * PARAMETER: newWord --- the word to insert into the BST.
   *            (input parameter)
   */
  
  private void insert( String newWord )
  {
    TreeNode prev = null; // parent of curr
    TreeNode curr = root; // current tree node being examined
    TreeNode newNode;
    boolean found = false; // Have we found the word in the tree?

    while((curr != null) && !found)
    {
      if (newWord.compareTo(curr.word) == 0)
      {
        found = true;
      }
      else
      {
        prev = curr;
        if (newWord.compareTo(curr.word) < 0)
          curr = curr.left;
        else
          curr = curr.right;
      }
    } 

    if (!found)
    {
      newNode = new TreeNode( newWord, null, null );
      if (prev == null)
      {
        // The tree is currently empty.
        // The new word will be the root of the tree.
        root = newNode;
      }
      else
      {
        // The tree is not empty and prev should be
        // the parent of the new node.
        if (newWord.compareTo(prev.word) < 0)
          prev.left = newNode;
        else
          prev.right = newNode;
      }
    } 
  
  } 

  /**
   * method printTree (optional)
   *
   * PURPOSE: Prints tree sideways (root at left, leaves at right, all nodes
   *          on a given level are printed at the same distance from the left
   *          margin --- in the same column) using a "reverse" inorder traversal:
   *          - Print out entire right child recursively
   *          - Print word in current node
   *          - Print out entire left child recursively
   * PARAMETER: Prefix is a string of blanks that tells us how far
   *            to indent the current node (all nodes on a given
   *            level are indented the same amount).
   *            (input parameter)
   * PARAMETER: curr is the current node to print out.
   *            (input parameter)
   */
   
  private static void printTree( TreeNode curr, String prefix )
  {
    if (curr != null)
    {
      printTree( curr.right, prefix + "  " );
      System.out.println( prefix + curr.word );
      printTree( curr.left, prefix + "  " );
    } 
  } 

  /**
   * method inorder (optional)
   *
   * PURPOSE: Print out the tree in inorder (should give
   *          tree data in sorted order), for debugging purposes.
   * PARAMETER: curr --- the current node to visit in inorder.
   *            (input parameter).
   */
   
  private static void inorder( TreeNode curr )
  {
    if (curr != null)
    {
      inorder( curr.left );
      System.out.println( curr.word );
      inorder( curr.right );
    }
  } 
  
} 
