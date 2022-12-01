package Labs.Lab5;

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
    public heap()
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

            tokens = inLine.split("\\s+");
            ;

            for (int i = 0; i < tokens.length; i++)
            {
                // Get the next element from the current line of input.

                heapArray[heapSize] = Integer.parseInt(tokens[i]);
                heapSize++;

            }
        }

        theFile.close();
    }

    public heap(int size)
    {
        heapArray = new int[size];
        heapSize = 0;
    }

    /**************************************************************
     * retrieveMax                                                 *
     *                                                             *
     * Purpose: Remove and return the maximum value from the heap. *
     *                                                             *
     * Returns: The maximum value stored in the heap.              *
     **************************************************************/
    public int retrieveMax()
    {
        //Keep a copy of the current max to return
        int prevMax = heapArray[0];

        //Remove the last item in the heap and replace the current root with it
        //then update (reduce) the size of heap
        int lastItem = heapArray[--heapSize];
        heapArray[0] = lastItem;

        //now sift down the new Root which was the last item in order to
        //restore heap order
        siftDown(0);

        return prevMax;
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

    /*******************************************************************
     * Sift Down Iterative                                                     *
     *******************************************************************/
    private void siftDown(int i)
    {
        int index = i;
        int leftPosition;
        int rightChildPos;

        //If the current node has 2 children and is less than at least one of them
        while (((rightChildPos = (2 * index) + 2) < heapSize) && (heapArray[index] < heapArray[(leftPosition = (2 * index) + 1)] ||
                heapArray[index] < heapArray[rightChildPos]))
        {
            if (heapArray[rightChildPos] > heapArray[leftPosition])
            {
                swap(heapArray, index, rightChildPos);
                index = rightChildPos;
            } else
            {
                swap(heapArray, index, leftPosition);
                index = leftPosition;
            }
        }
        //The while loop ends or doesn't run if the current node doesn't have 2 children or
        //the heap is sorted

        //If the current node has only 1 child, check if sorted and sort if not
        int last;
        if ((last = 2 * index + 1) < heapSize)
        {
            if (heapArray[index] < heapArray[last])
            {
                swap(heapArray, index, last);
            }
        }
    }

    /*******************************************************************
     * Sift Down recursive                                                     *
     *******************************************************************/
    private void siftDownR(int i)
    {
        siftDownR(i, (2 * i) + 1, (2 * i) + 2);
    }

    private void siftDownR(int start, int leftChildPos, int rightChildPos)
    {
        if (start < heapSize)
        {
            if (rightChildPos < heapSize)
            {
                if (heapArray[start] < heapArray[leftChildPos] || heapArray[start] < heapArray[rightChildPos])
                {
                    if (heapArray[rightChildPos] >= heapArray[leftChildPos])
                    {
                        swap(heapArray, start, rightChildPos);
                        siftDownR(rightChildPos, (rightChildPos * 2) + 1, (rightChildPos * 2) + 2);
                    } else
                    {
                        swap(heapArray, start, leftChildPos);
                        siftDownR(leftChildPos, (leftChildPos * 2) + 1, (leftChildPos * 2) + 2);
                    }
                }
            } else if (leftChildPos < heapSize)
            {
                if (heapArray[start] < heapArray[leftChildPos])
                {
                    swap(heapArray, start, leftChildPos);
                }
            }
        }
    }


    private void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /***************************************************************
     * printHeap                                                    *
     *                                                              *
     * Purpose: Print out the values stored in heapArray.           *
     ***************************************************************/
    public void printHeap()
    {
        int i;
        int numPrintedOnLine = 0;

        for (i = 0; i < heapSize; i++)
        {
            System.out.print(heapArray[i] + " ");
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

        for (i = heapSize / 2; i >= 0; i--)
        {
            siftDown(i);
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
     * Insert                                                             *
     *******************************************************************/
    public void insert(int value)
    {
        if (heapSize < heapArray.length)
        {
            heapArray[heapSize++] = value;

            siftUp(heapSize - 1);
        }
    }

    private void siftUp(int index)
    {
        siftUpR(index, (index - 1) / 2);
    }

    /*******************************************************************
     * Sift Up recursive                                                     *
     *******************************************************************/
    private void siftUpR(int index, int parentPosition)
    {
        if (index > 0)
        {
            if (heapArray[index] > heapArray[parentPosition])
            {
                swap(heapArray, index, parentPosition);
                siftUpR(parentPosition, (parentPosition - 1) / 2);
            }
        }
    }

    /*******************************************************************
     * Sift up Iterative                                                             *
     *******************************************************************/
    private void siftUpI(int index)
    {
        int i = index;
        while (i > 0 && heapArray[i] > heapArray[(i - 1) / 2])
        {
            swap(heapArray, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    /*******************************************************************
     * main                                                             *
     *******************************************************************/
    public static void main(String[] args)
    {
        heap H = new heap();

        // Print out unsorted array.

        System.out.println("Unsorted array:");
        H.printHeap();

        // Sort array.
        H.heapSort();

        // Print sorted array.

        System.out.println("Sorted array:");
        H.printHeap();
    }
}
