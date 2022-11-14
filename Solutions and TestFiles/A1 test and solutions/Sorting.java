import java.util.*;

public class Sorting
{
  // In the modified merge sort or quick sort, the base case (sorted non-recursively) is any list of BASE_CASE_SIZE or less items.
  final static int BASE_CASE_SIZE = 10; 
  
  // Sorts A[start]..A[end] using a selection sort. Can be used by quick sort or merge sort for small arrays.
  private static void selectionSortPart(int[] A, int start, int end)
  {
    int i;        // find the correct item to put in A[i]
    int j;        // loop index used to go through the remaining items to find the i-th smallest item
    int temp;     // temporarily storage for swapping items
    int smallest; // index of the i-th smallest item.
    
    for (i = start; i < end; i++)
    {
      // A[start] to A[i-1] is a sorted list.
      // Find the i-th smallest item to put in A[i].     
      smallest = i;
      for( j = i+1; j <= end; j++)
      {
        if (A[j] < A[smallest])
          smallest = j;
      } 
      
      // Swap A[i] and the i-th smallest item, if it isn't already in A[i].      
      if (smallest != i)
      {
        temp = A[smallest];
        A[smallest] = A[i];
        A[i] = temp;
      }
      
    } 
  } 
  
  // Driver routine for a recursive merge sort method. The caller need only pass the array to be sorted to this method. 
  // This method sets up the correct arguments to pass to the recursive merge sort method.
  public static void plainMergeSort(int[] A)
  {
    int[] temp;
    
    temp = new int[A.length];
    plainMergeSortRec(A, temp, 0, A.length-1);
  }
  
  // Sorts A[start]...A[end] using a merge sort. In this "plain" version, the base case is any list of 0 or 1 item.
  private static void plainMergeSortRec(int[] A, int[] temp, int start, int end)
  {
    int mid; // index of the middle element in A[start]...A[end]
    
    // Make sure there are at least two elements to sort before recursing.
    if ((end-start+1) > 1)
    {      
      mid = (start+end)/2; 
      plainMergeSortRec(A, temp, start, mid); 
      plainMergeSortRec(A, temp, mid+1, end);
      merge(A, temp, start, mid, end);
    } 
  }  
  
 // Merges two sorted sublists (A[start]...A[mid] and A[mid+1]...A[end]) into one sorted list stored in A[start]...A[end].
  private static void merge(int[] A, int[] temp, int start, int mid, int end)
  {
    int left = start;  // index in left half of A of smallest unused element.
    int right = mid+1; // index in right half of A of smallest unused element.
    int merge = start; // index in temp of next unused position.
    
    while ((left <= mid) && (right <= end))
    {
      if (A[left] < A[right])
      {
        temp[merge] = A[left];
        left++;
      }
      else
      {
        temp[merge] = A[right];
        right++;
      }
      merge++;
    } 
    
    while (left <= mid)
    {
      // Right half is used up.
      // Copy rest of left half into merged list.
      
      temp[merge] = A[left];
      left++;
      merge++;
    } 
    
    while (right <= end)
    {
      // Left half is used up.
      // Copy rest of right half into merged list.
      
      temp[merge] = A[right];
      right++;
      merge++;
    } 
    
    // Copy merged list back into A.
    for (int i = start; i <= end; i++)
      A[i] = temp[i];
  } 
  
  // Driver routine for a recursive quick sort method. The caller need only pass the array to be sorted to this method.
  // This method sets up the correct arguments to pass to the recursive quick sort method.
  public static void plainQuickSort(int[] A)
  {
    plainQuickSortRec(A, 0, A.length-1);
  } 
 
  // Sorts A[start]...A[end] using a quick sort. In this plain version, we use the first element of the list as the pivot.
  // The base cases of the recursion are lists of 0 or 1 element.
  private static void plainQuickSortRec(int[] A, int start, int end)
  {
    int pivotPosn; // position of pivot after partitioning
    
    if (start < end)
    {
      // We have more than 1 item to sort, use quick sort.
      // We will always use A[start] as the pivot.
      
      pivotPosn = partition(A, start, end);
      plainQuickSortRec(A, start, pivotPosn-1);
      plainQuickSortRec(A, pivotPosn+1, end);
    }     
  } 
 
  // Driver routine for a recursive quick sort method. The caller need only pass the array to be sorted to this method.
  // This method sets up the correct arguments to pass to the recursive quick sort method.
  // In this version, small arrays (<= 10 elements) are sorted using selection sort, and the pivot is chosen using the median-of-three method.
  public static void modifiedQuickSort(int[] A)
  {
    modifiedQuickSortRec(A, 0, A.length-1);
  } 
  
  // Sorts A[start]...A[end] using a quick sort. 
  // The pivot is found using the median-of-three method. 
  // Arrays of 10 items or less are the base cases of the recursion (we sort them directly with selection sort).
  private static void modifiedQuickSortRec(int[] A, int start, int end)
  {
    int pivotPosn; // position of pivot after partitioning
    
    if (end-start+1 > BASE_CASE_SIZE)
    {
      // We have more than BASE_CASE_CASE items to sort, use quick sort.
      
       // put pivot in A[start]
      chooseMedianOfThree(A, start, end);
      pivotPosn = partition(A, start, end);

      modifiedQuickSortRec(A, start, pivotPosn-1);
      modifiedQuickSortRec(A, pivotPosn+1, end);
    }
    else
    {
      // We have <= BASE_CASE_SIZE items to sort, use selection sort.
      
      selectionSortPart(A, start, end);
    }
    
  } 
  
  
  // Partition unsorted[start]...unsorted[end] into
  // <---- smalls ----><pivot><---- BIGS ---->
  // where smalls = elements < pivot (in no particular order) = unsorted[start]...unsorted[pivotPosn-1]
  // BIGS = elements >= pivot (in no particular order) = unsorted[pivotPosn+1]...unsorted[end]
  // pivot is in its correct position in the sorted list = unsorted[pivotPosn].
  private static int partition(int[] unsorted, int start, int end)
  {
    int endSmalls = start;
    int pivot = unsorted[start]; // pivot is already in first position.
    int temp;
    
    // Now partition unsorted[start+1]..unsorted[end]
    // into <--- smalls --><--- BIGS --->
    // smalls = (items < pivot) (unsorted) and BIGS = (items >= pivot) (unsorted).
    
    for (int i = start+1; i <= end; i++)
    {
      // Invariant: smalls = unsorted[start+1]..unsorted[endSmalls]
      //            BIGS = unsorted[endSmalls+1]..unsorted[i-1].
      // Now see where unsorted[i] belongs.
      
      if (unsorted[i] < pivot)
      {
        // unsorted[i] belongs in the smalls
        
        endSmalls++;
        temp = unsorted[endSmalls];
        unsorted[endSmalls] = unsorted[i];
        unsorted[i] = temp;
      }
      
      // If unsorted[i] belongs in the BIGS, we don't need to move it: do nothing!      
    } 
    
    // Now move pivot in between smalls and BIGS, that is, into its final sorted position.
    // (Swap the pivot in unsorted[start] with unsorted[endSmalls], the last element in the smalls.)
    
    unsorted[start] = unsorted[endSmalls];
    unsorted[endSmalls] = pivot;
    
    return endSmalls;
  } 
  
  
  
  
  // Choose the median of A[start], A[mid] and A[end], where mid is the index of the middle element, to be the pivot. Put the pivot into A[start].
  // Algorithm:
  //          Treat A[mid], A[start], A[end] as an unsorted array (with the positions in that order) and perform a selection sort on them.  
  //          When we're done the selection sort, the pivot (the median of three) will be in A[start].
  public static void chooseMedianOfThree(int[] A, int start, int end)
  {
    int temp; 
    int mid = (start+end)/2; // Index of the middle element of A[start], ..., A[end].
    
    // Put the min of A[mid], A[start], A[end] into A[mid].
    
    if (A[start] < A[end])
    {
      if (A[start] < A[mid])
      {
        // A[start] is less than both A[end] and A[mid].
        // A[start] is the min.
        
        temp = A[mid];
        A[mid] = A[start];
        A[start] = temp;
      }
      else
      {
        // A[mid] < A[start] < A[end].
        // A[mid] is already the min.
      }
    }
    else
    {  // A[end] < A[start]
      if (A[end] < A[mid])
      {
        // A[end] is less than both A[start] and A[mid].
        // A[end] is the min.
        
        temp = A[mid];
        A[mid] = A[end];
        A[end] = temp;
      }
      else
      {
        // A[mid] < A[end] < A[start].
        // A[mid] is already the min.
      }
    }
    
    // Now put the min of A[start] and A[end] into A[start]
    
    if (A[end] < A[start])
    {
      temp = A[start];
      A[start] = A[end];
      A[end] = temp;
    }
    
    // Now A[start] contains the median of A[mid], A[start], A[end]
  } 
  
  
  // An error-checking method that simply checks if a sorting method works.
  // Checks that the array is now sorted (each element is >= the previous element)
  private static boolean inSortedOrder(int[] A)
  {
    boolean result = true;
    int i = 1;
    
    while ((i < A.length) && result)
    {
      result = A[i-1] <= A[i];
      if (!result)
        System.out.println( "A[" + (i-1) + "] = " + A[i-1] + " is greater than  A[" + i + "] = " + A[i] );
      i++;
    }

    return result;
  } 
  
  
  
  // Copy the input array A into output array copyA.
  private static void copyArray(int[] A, int[] copyA)
  {
    for( int i = 0; i < A.length; i++ )
      copyA[i] = A[i];
    
  }  
  
  // Try out each sorting method in turn.
  // First, reading in the array from a file, then sorting, checking that the sort worked correctly and then printing out the time the method took to sort.
  // The file to be read is taken from standard input.
  public static void main(String[] args)
  {
    long timestamp;  // Time how long each sorting method takes.
    Scanner theFile = new Scanner(System.in);
    String line;
    String[] tokens;    // The elements from a line of input.
    int size = 0;    // The number of elements to sort.
    int[] A = null;  // Array to be sorted.
    int[] copyA;     // A copy of A (so that all methods
                     // sort the SAME array).
    
    // The first line is the number of elements.
    line = theFile.nextLine();
    
    //  Allocate the array to sort.
    A = new int[Integer.parseInt(line)];
    
    // Read the elements into array A.    
    while (theFile.hasNextLine())
    {
      line = theFile.nextLine();
    
      // Parse the current line of input.
      // Each line can contain more than one element. 
      tokens = line.split("\\s+");
      
      for ( int i=0 ; i<tokens.length ; i++ )
      {
        // Get the next element from the current line of input.
        if (size < A.length)
        {
          A[size] = Integer.parseInt(tokens[i]);
          size++;
        }
      }
    }
    
    theFile.close();
        
    // Allocate copy array.
    copyA = new int[size];
    
    // try plain merge sort.   
    copyArray( A, copyA );
    timestamp = new Date().getTime();
    plainMergeSort( copyA );
    timestamp = new Date().getTime() - timestamp;
    System.out.println( "Time for plain merge sort: " + timestamp + " milliseconds." );
    if (inSortedOrder( copyA ))
      System.out.println( "    Plain merge sort successfully sorted " + size + " elements." );
    else
      System.out.println( "    Plain merge sort did not successfully" + " sort " + size + " elements." );
    System.out.println();
    
    
    // try plain quick sort.    
    copyArray( A, copyA );
    timestamp = new Date().getTime();
    plainQuickSort( copyA );
    timestamp = new Date().getTime() - timestamp;
    System.out.println( "Time for plain quick sort: " + timestamp + " milliseconds." );
    if (inSortedOrder( copyA ))
      System.out.println( "    Plain quick sort successfully sorted " + size +  " elements." );
    else
      System.out.println( "    Plain quick sort did not successfully" + " sort " + size + " elements." );
    System.out.println();
    
    
    // try modified quick sort.    
    copyArray( A, copyA );
    timestamp = new Date().getTime();
    modifiedQuickSort( copyA );
    timestamp = new Date().getTime() - timestamp;
    System.out.println( "Time for modified quick sort: " + timestamp + " milliseconds." );
    if (inSortedOrder( copyA ))
      System.out.println( "    Modified quick sort successfully sorted " + size +  " elements." );
    else
      System.out.println( "    Modified quick sort did not successfully" + " sort " + size + " elements." );
  }   
}
