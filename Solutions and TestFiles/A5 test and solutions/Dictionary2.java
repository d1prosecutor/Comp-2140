import java.io.*;
import java.util.*;

/**
 * Dictionary class
 *
 *  This is our table ADT (a 2-3 search tree)
 *  that will hold our dictionary words.
 */
class Dictionary
{
  
  /******************************************************************************
   Inner class
   *******************************************************************************/
  
  private class TwoThreeNode
  {
    public String[] value; // An array of two values.
    public int numValues; // The number of values in the node (1 or 2).
    public TwoThreeNode[] child; // An array of three children pointers
    public TwoThreeNode parent; // A pointer to the parent of this node.
    
    /******************************************************************
     * Constructor for a node with one value and two children         *
     *                                                                *
     * Note: The one value is stored in value[0] and its two          *
     *       children are stored in child[0] (the child containing    *
     *       values less than value[0]) and child[1] (the child       *
     *       containing values greater than value[0]).                *
     ****************************************************************/
    public TwoThreeNode( String newValue,
                        TwoThreeNode left,
                        TwoThreeNode right,
                        TwoThreeNode newParent )
    {
      value = new String[2];
      child = new TwoThreeNode[3];
      numValues = 1;
      value[0] = newValue;
      child[0] = left;
      child[1] = right;
      parent = newParent;
    }
    
    /******************************************************************
     * Constructor for a node with two values and three children      *
     *                                                                *
     * Note: The two values are stored in sorted order in value[0]    *
     *       and value[1].                                            *
     *       child[0] contains values less than value[0]              *
     *       child[1] contains values greater than value[0] and less  *
     *                than value[1]                                   *
     *       child[2] contains values greater than value[1]           *
     ******************************************************************/
    public TwoThreeNode( String val0, String val1,
                        TwoThreeNode left,
                        TwoThreeNode middle,
                        TwoThreeNode right,
                        TwoThreeNode newParent )
    {
      value = new String[2];
      child = new TwoThreeNode[3];
      numValues = 2;
      value[0] = val0;
      value[1] = val1;
      child[0] = left;
      child[1] = middle;
      child[2] = right;
      parent = newParent;
    }
    
  }
  
  
  /******************************************************************************
   Attributes
   *******************************************************************************/
  
  // Our table. It consists of a 2-3 search tree
  // (for which we need a pointer to the root).
  
  private TwoThreeNode root;
  
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
  
  /*********************************************************************
   * lookup                                                             *
   *                                                                    *
   * Purpose: check if a key is already in the 2-3 tree or not.         *
   * Returns: true if key is in the tree, false if key is not there.    *
   **********************************************************************/
  public boolean lookup( String key )
  {
    return null == findInsertLeaf( key.toLowerCase() );
  }
  
  /******************************************************************************
   Private Methods
   *******************************************************************************/
  
  
  /******************************************************************
   * Insert                                                          *
   *                                                                 *
   * Purpose: insert the given key into the 2-3 tree.                *
   *          - handles empty tree, key already in tree, and         *
   *            normal insert.                                       *
   *******************************************************************/
  private void insert( String key )
  {
    TwoThreeNode insertPoint; // The leaf in which we insert the new key.
    
    if (root == null)
    {
      // The tree is empty, so the new key will be the only key in the tree,
      // and the root will be the only node in the tree.
      
      root = new TwoThreeNode( key, null, null, null );
    }
    else
    {
      insertPoint = findInsertLeaf( key );
      if (insertPoint != null)
      {
        // insertPoint is the leaf into which we should insert key.
        insertAndFixUp( insertPoint, key );
      }      
    }
  }
  
  
  /************************************************************
   * Debugging methods that you did not have to write.        *
   ************************************************************/
  private void printNode( TwoThreeNode curr )
  {
    if (curr != null)
    {
      System.out.print( "( " + curr.value[0] );
      if (curr.numValues == 2)
        System.out.print( ", " + curr.value[1] );
      System.out.print( " )" );
    }
    else
    {
      System.out.print( "null node" );
    }
  }
  
  private void printTree( TwoThreeNode curr, String prefix )
  {
    if (curr != null)
    {
      if (curr.numValues == 2)
      {
        printTree( curr.child[2], prefix+"  " );
        System.out.println( prefix + curr.value[1] + "(2)" );
      }
      printTree( curr.child[1], prefix+"  ");
      System.out.println( prefix + curr.value[0] + "(1)" );
      printTree( curr.child[0], prefix+"  ");
    }
  }
  
  public void checkParentPointers( TwoThreeNode curr )
  {
    int i;
    
    if (curr != null)
    {
      if (curr.child[0] != null)
      {
        for (i = 0; i <= curr.numValues; i++)
        {
          if (curr.child[i].parent != curr)
          {
            printNode( curr.child[i] );
            System.out.print( " does not point at its parent " );
            printNode( curr );
            System.out.println();
          }
          checkParentPointers( curr.child[i] );
        }
      }
    }
  }
  
  
  /***********************************************************
   * findInsertLeaf                                           *
   *                                                          *
   * Purpose: return a pointer to the leaf into which we      *
   *          should insert key.                              *
   *          If the key is already in the tree, return null. *
   ************************************************************/
  private TwoThreeNode findInsertLeaf( String key )
  {
    TwoThreeNode curr = root;  // Node pointer to do search from root.
    int moveResult; // Index in child array, telling us which child
                    // to move to from the current node.
    boolean found = false; // Have we found the key?
    
    while (!found && (curr.child[0] != null)) // while not at a leaf yet
    {                                         // and haven't found the key
                                              // Figure out which child to move to.
      
      moveResult = moveToChild( curr, key );
      
      if (moveResult < 0)
      {
        // The key is already in the tree!
        
        curr = null;
        found = true;
      }
      else
      {
        // Move to the appropriate child.
        
        curr = curr.child[moveResult];
      }
    }
    
    if (!found)
    {
      // We made it to a leaf.  Is the key already in the leaf?
      
      if (moveToChild( curr, key ) < 0)
      {
        // The key is in the leaf.
        
        curr = null;
      }
    }
    
    return curr;
  }
  
  
  /******************************************************************
   * moveToChild                                                     *
   *                                                                 *
   * Purpose: We are searching for key in the 2-3 tree and we're     *
   *          currently at node curr.  Figure out which child of     *
   *          curr we should move to next.                           *
   *          If key is in node curr, then                           *
   *            - return -1 if key = curr.value[0]                   *
   *            - return -2 if key = curr.value[1]                   *
   *              (of course, must have curr.numValues == 2)         *
   *          Otherwise, return the index of the child to move to:   *
   *            - return 0 if key < curr.value[0]                    *
   *              that is, move to curr.child[0].                    *
   *            - return 1 if curr.value[0] < key < curr.value[1]    *
   *              that is, move to curr.child[1]                     *
   *              (of course, must have curr.numValues == 2)         *
   *            - return 2 if curr.value[1] < key                    *
   *              that is, move to curr.child[2]                     *
   *              (again, must have curr.numValues == 2)             *
   *******************************************************************/
  private int moveToChild( TwoThreeNode curr, String key )
  {
    int returnValue = curr.numValues;
    // Assume that we should move to curr.child[curr.numValues].
    int i;
    
    // Because values and children pointers are in arrays, we can
    // use a for-loop to search within node curr for key.
    
    for (i = 0; (i < curr.numValues) && (returnValue == curr.numValues); i++)
    {
      if (key.compareTo(curr.value[i]) < 0)
        returnValue = i;
      else if (key.equals(curr.value[i]))
        returnValue = -i-1;
    }
    
    return returnValue;
  }

  
  /*******************************************************************
   * insertAndFixUp                                                   *
   *                                                                  *
   * Purpose: Insert key into the leaf insertPoint,                   *
   *          then do any splitting of nodes and checking of parents  *
   *          that is required to fix up the 2-3 tree after the       *
   *          insertion.                                              *
   ********************************************************************/
  private void insertAndFixUp( TwoThreeNode insertPoint, String key )
  {
    TwoThreeNode curr = insertPoint; // Current node being inserted into.
    String newKey = key; // Key to be inserted into curr.
    TwoThreeNode newRightChild = null; // New node from previous split
                                       // at child of curr.
                                       // Contains values > newKey.
    TwoThreeNode newLeftChild = null; // Old node from previous split at
                                      // child of curr (i.e. original child
                                      // of curr before split at child)
                                      // Contains values < newKey.
    String splitKey; // used when splitting the node.
    TwoThreeNode splitNode; // used when splitting the node.
    
    // while we haven't reached the level above the root and
    //       the node we're trying to insert into is already full
    //  -- we have to split curr!
    
    while ((curr != null) && (curr.numValues == 2))
    {
      // Add to a full node:
      //   Add newKey with child newRightChild to node curr.
      //   We must split curr into two nodes and pass a key
      //   and a new child up to curr's parent.
      
      // Find out which of newKey, curr.value[0] and
      // curr.value[1] must be passed up to curr's parent.
      
      if ((curr.value[0].compareTo(newKey) < 0) && (newKey.compareTo(curr.value[1])) < 0)
      { // newKey gets passed up to curr's parent.
        // Sorted order of keys: curr.value[0], newKey, curr.value[1]
        // Order of children:
        //      curr.child[0], curr.child[1], newRightChild, curr.child[2]
        // Therfore:
        // original node curr will contain (after split):
        //      value: curr.value[0]
        //      children (in order): curr.child[0], curr.child[1]
        // splitNode (newly created node) will contain (after split):
        //      value: curr.value[1]
        //      children (in order): newRightChild, curr.child[2]
        
        splitKey = newKey;
        splitNode = new TwoThreeNode( curr.value[1],
                                     newRightChild,
                                     curr.child[2],
                                     curr.parent );
        
        // Set the parent pointers of the children.
        if (newRightChild != null)
          newRightChild.parent = splitNode;
        if (curr.child[2] != null)
          curr.child[2].parent = splitNode;
      }
      else if (newKey.compareTo(curr.value[0]) < 0)
      { // curr.value[0] gets passed up to curr's parent.
        // Sorted order of keys: newKey, curr.value[0], curr.value[1]
        // Order of children:
        //      curr.child[0], newRightChild, curr.child[1], curr.child[2]
        // Therfore:
        // original node curr will contain (after split):
        //      value: newKey
        //      children (in order): curr.child[0], newRightChild
        // splitNode (newly created node) will contain (after split):
        //      value: curr.value[0]
        //      children (in order): curr.child[1], curr.child[2]
        
        splitKey = curr.value[0];
        splitNode = new TwoThreeNode( curr.value[1],
                                     curr.child[1],
                                     curr.child[2],
                                     curr.parent );
        
        // Set the parent pointers of the children.
        if (newRightChild != null)
          newRightChild.parent = curr;
        if (curr.child[1] != null)
          curr.child[1].parent = splitNode;
        if (curr.child[2] != null)
          curr.child[2].parent = splitNode;
        
        // Set up the old node correctly.
        curr.value[0] = newKey;
        curr.child[1] = newRightChild;
      }
      else
      { // curr.value[1] gets passed up to curr's parent.
        // Sorted order of keys: curr.value[0], curr.value[1], newKey
        // Order of children:
        //      curr.child[0], curr.child[1], curr.child[2], newRightChild
        // Therfore:
        // original node curr will contain (after split):
        //      value: curr.value[0]
        //      children (in order): curr.child[0], curr.child[1]
        // splitNode (newly created node) will contain (after split):
        //      value: newKey
        //      children (in order): curr.child[2], newRightChild
        
        splitKey = curr.value[1];
        splitNode = new TwoThreeNode( newKey,
                                     curr.child[2],
                                     newRightChild,
                                     curr.parent );
        
        // Set the parent pointers of the children.
        if (curr.child[2] != null)
          curr.child[2].parent = splitNode;
        if (newRightChild != null)
          newRightChild.parent = splitNode;
      }
      
      // Set up the next iteration of the while-loop
      // (we will move up to the parent).
      curr.numValues = 1; // After split, original node contains 1 value.
      newKey = splitKey;  // Key to be passed up to parent of curr.
      newLeftChild = curr; // Original node contains values < splitKey
      newRightChild = splitNode; // New node contains values > splitKey
      curr = curr.parent; // Now move up to parent.
    }
    
    if (curr == null)
    {
      // We split the old root in the while-loop.  Make a new root.
      
      root = new TwoThreeNode( newKey,
                              newLeftChild,
                              newRightChild,
                              null );
      newLeftChild.parent = root;
      newRightChild.parent = root;
    }
    else
    {
      // The while-loop stopped when we reached a node that is not full.
      // Curr is not full.  Simply insert the key.
      
      if (newKey.compareTo(curr.value[0]) < 0)
      {
        // newKey belongs in curr.value[0]
        // Order of children: curr.child[0], newRightChild, curr.child[1]
        
        curr.value[1] = curr.value[0];
        curr.value[0] = newKey;
        curr.child[2] = curr.child[1];
        curr.child[1] = newRightChild;
        curr.numValues = 2;
      }
      else
      {
        // newKey belongs in curr.value[1]
        // Order of children: curr.child[0], curr.child[1], newRightChild
        
        curr.value[1] = newKey;
        curr.child[2] = newRightChild;
        curr.numValues = 2;
      }
      if (newRightChild != null)
        newRightChild.parent = curr;
    }
  }
} 
