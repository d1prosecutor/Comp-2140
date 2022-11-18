import java.io.*;
import java.util.*;

public class heap
{
  
  int[] heapArray; // The array containing the heap.
  int heapSize; // The number of elements currently stored in the heap.
  
  
  /*******************************************************************
   * CONSTRUCTOR                                                      *
   *                                                                  *
   * Purpose: Find out how many elements to put into the array        *
   *          (first number in the input file), create a heapArray    *
   *          of that size, and then read the elements into the       *
   *          heapArray.  The input file is "heapSortData.txt".       *
   *******************************************************************/
  public heap( )
  {
    Scanner theFile = new Scanner(System.in);
    String inLine;   // A line of input from BufferedReader in.
    String[] tokens; // the elements from a line of input.
    
    // Get the number of elements to read in and allocate
    // a heapArray of that size.
    
    inLine = theFile.nextLine();
    heapArray = new int[Integer.parseInt(inLine)];
    heapSize = 0;

    // Read the data to be inserted into the heap.
    while (theFile.hasNextLine())
    {
      inLine = theFile.nextLine();
      
      // Parse the current line of input.
      // Each line can contain more than one element.
      
      tokens = inLine.split( "\\s+" );;
      
      for (int i=0 ; i<tokens.length ; i++ )
      {
        // Get the next element from the current line of input.
        
        heapArray[ heapSize ] =  Integer.parseInt( tokens[i] );
        heapSize++;
        
      }
    }

    theFile.close();      
  }
  
  
  /**************************************************************
   * retrieveMax                                                 *
   *                                                             *
   * Purpose: Remove and return the maximum value from the heap. *
   *                                                             *
   * Returns: The maximum value stored in the heap.              *
   **************************************************************/
  public int retrieveMax( )
  {
    
  }
  
  
  
  /***************************************************************
   * siftDown                                                     *
   *                                                              *
   * Purpose: The value in heapArray[i] may no longer be heap-    *
   *          ordered --- it may be smaller than one or both of   *
   *          its children.  Heapify the heap --- restore heap-   *
   *          heap order.                                         *
   * Param i: Input parameter                                     *
   *          The index of the element that may have messed up    *
   *          the heap ordering.                                  *
   ***************************************************************/
  private void siftDown( int i )
  {
    
  }
  
  
  /***************************************************************
   * printHeap                                                    *
   *                                                              *
   * Purpose: Print out the values stored in heapArray.           *
   ***************************************************************/
  public void printHeap( )
  {
    int i;
    int numPrintedOnLine = 0;
    
    for (i = 0; i < heapSize; i++)
    {
      System.out.print( heapArray[i] + " " );
      numPrintedOnLine++;
      if (numPrintedOnLine == 20)
      {
        System.out.println();
        numPrintedOnLine = 0;
      }
    }
    System.out.println();
  }
  
  /*****************************************************************
   * heapSort                                                       *
   *                                                                *
   * Purpose: To sort the array heapArray using a heap sort.        *
   *****************************************************************/
  public void heapSort()
  {
    int i;
    int result;
    int tempSize = heapSize;
    
    // Heapify the unsorted array.
    
    for (i = heapSize/2; i >= 0; i--)
    {
      siftDown( i );
    }
    
    // Now sort the array by repeatedly doing retrieveMax.
    
    for (i = heapSize - 1; i > 0; i--)
    {
      result = retrieveMax();
      heapArray[i] = result;
    }
    heapSize = tempSize; // So printHeap works.
  }
  
  /*******************************************************************
   * main                                                             *
   *******************************************************************/
  public static void main( String[] args )
  {
    heap H = new heap();
    
    // Print out unsorted array.
    
    System.out.println( "Unsorted array:" );
    H.printHeap();
    
    // Sort array.
    
    H.heapSort();
    
    // Print sorted array.
    
    System.out.println( "Sorted array:" );
    H.printHeap();
  }
}
