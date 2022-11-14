//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Date;
//import java.util.Scanner;
//
//public class Assignment1Abstract
//{
//    ////////// Q1
//    ////-----------------------------------------
////// NAME: your name
////// STUDENT NUMBER: your student number
////// COURSE: COMP 2140, SECTION: Axx
////// INSTRUCTOR: name of your instructor
////// ASSIGNMENT: assignment #, QUESTION: question #
//////
////// REMARKS: What is the purpose of this code?
//////
//////-----------------------------------------
////
////import java.util.*;
////import java.lang.*;
////
////public class Assignment1Q1
////{
////    public static void main(String[] args)
////    {
////        int length = 0;
////        Scanner inputNum = new Scanner(System.in);
////
////        do
////        {
////            System.out.println("Enter the length of the ruler (Integer)");
////
////            try
////            {
////                length = inputNum.nextInt();
////            } catch (InputMismatchException e)
////            {
////                System.out.println("Not a valid integer");
////            }
////        } while (length < 2 || ((Math.log(length) / Math.log(2)) != Math.round(Math.log(length) / Math.log(2))));
////        inputNum.close();
////
////        heights(length);
////    }
////
////    public static void heights(int length)
////    {
////        int[] positionArray = new int[length + 1];
////
////        for (int i = 0; i < positionArray.length; i++)
////        {
////            positionArray[i] = i % 10;
////        }
////
////        int[] heightsArray = new int[length + 1];
////
////        heightsArray = heightsRec(0, length + 1, length, heightsArray);
////
////        for (int i = 0; i < heightsArray.length; i++)
////        {
////            System.out.print(heightsArray[i]);
////        }
////
////        System.out.println();
////
////        for (int i = 0; i < positionArray.length; i++)
////        {
////            System.out.print(positionArray[i]);
////        }
////
////    }
////
////    private static int[] heightsRec(int start, int end, int length, int[] rulerHeights)
////    {
////        if (length < 2)
////        {
////            return rulerHeights;
////        }
////
////        int midHeight = (int) (Math.log(length) / Math.log(2));
////        int midRuler = start + (end - start) / 2;
////
////        rulerHeights[midRuler] = midHeight;
////
////        heightsRec(start, midRuler, length / 2, rulerHeights);
////        heightsRec(midRuler, end, length / 2, rulerHeights);
////
////        return rulerHeights;
////    }
////}
//
//
//
//
//
//    //////////Q2
////    //-----------------------------------------
////// NAME: your name
////// STUDENT NUMBER: your student number
////// COURSE: COMP 2140, SECTION: Axx
////// INSTRUCTOR: name of your instructor
////// ASSIGNMENT: assignment #, QUESTION: question #
//////
////// REMARKS: What is the purpose of this code?
//////
//////-----------------------------------------
////
////import java.io.File;
////import java.io.FileNotFoundException;
////import java.util.*;
////
////    public class Assignment1Q2
////    {
////        public static void main(String[] args) throws FileNotFoundException
////        {
////            String fileName = "A1Data.txt";
////            Scanner theFile = new Scanner(new File(fileName));
////            String line;
////            String[] tokens;
////
////            int numItems = Integer.parseInt((theFile.nextLine()).trim());
////
////            int[] intArray = new int[numItems];
////
////            while (theFile.hasNextLine())
////            {
////                line = theFile.nextLine();
////
////                tokens = line.split("\\s+");
////
////                for (int i = 0; i < tokens.length; i++)
////                {
////                    intArray[i] = Integer.parseInt(tokens[i]);
////                }
////            }
////            theFile.close();
////
////            System.out.println("Sorting with merge sort and quick sort.\nStudent number[007928676]");
////
//////        testMergeSort(intArray);
//////        testQuickSortFirst(intArray);
////            testQuickSortMed(intArray);
////
////        }//end main
////
////        public static void plainMergeSort(int[] array)
////        {
////            int[] temp = new int[array.length];
////
////            plainMergeSort(array, 0, array.length, temp);
////        }//end sort
////
////        private static void plainMergeSort(int[] array, int start, int end, int[] temp)
////        {
////            int mid;
////
////            if (end - start > 1)
////            {
////                mid = start + (end - start) / 2;
////                plainMergeSort(array, start, mid, temp);
////                plainMergeSort(array, mid, end, temp);
////                merge(array, start, mid, end, temp);
////            }// end if
////
////        }//end mergeSort
////
////        private static void merge(int[] array, int start, int mid, int end, int[] temp)
////        {
////            int currL = start;
////            int currR = mid;
////            int currT;
////
////            for (currT = start; currT < end; currT++)
////            {
////                if (currL < mid && (currR >= end || array[currL] < array[currR]))
////                {
////                    temp[currT] = array[currL];
////                    currL++;
////                } else
////                {
////                    temp[currT] = array[currR];
////                    currR++;
////                }
////            }//end for
////
////            for (currT = start; currT < end; currT++)
////            {
////                array[currT] = temp[currT];
////            }//end for
////
////        }//end merge
////
////        public static void quickSortFirst(int[] array)
////        {
////            quickSortFirst(array, 0, array.length);
////        }
////
////        private static void quickSortFirst(int[] arr, int start, int end)
////        {
////            if (end - start == 2)
////            {
////                if (arr[start] < arr[end])
////                {
////                    swap(arr, start, end);
////                }
////            } else if (end - start > 2)
////            {
////                int pivot = partition(arr, start, end);
////
////                quickSortFirst(arr, start, pivot - 1);
////                quickSortFirst(arr, pivot + 1, end);
////            }

////second method
// if (start < end)
//        {
//
//            // pi is partitioning index, arr[p]
//            // is now at right place
//            int pi = partition(arr, start, end);
//
//            // Separately sort elements before
//            // partition and after partition
//            quickSort(arr, start, pi - 1);
//            quickSort(arr, pi + 1, end);
//        }
////        }
////
////
////        public static int partition(int[] arr, int start, int end)
////        {
////            //       1st partition method using two pointers from opposite ends
//////        int pivot = start;
//////
//////        int bigIndexStart = start + 1;
//////        int j = end-1;
//////
//////        while (bigIndexStart < j)
//////        {
//////            while (arr[bigIndexStart] <= arr[pivot] && bigIndexStart < j)
//////            {
//////                bigIndexStart++;
//////            }
//////
//////            while (arr[j] > arr[pivot] && bigIndexStart < j)
//////            {
//////                j--;
//////            }
//////
//////            if (bigIndexStart < j)
//////            {
//////                swap(arr, bigIndexStart, j);
//////            }
//////        }
//////        swap(arr, bigIndexStart - 1, pivot);
////
//////2nd partition method using two pointers from same side(left side/beginning)
////            int bigIndexStart = start + 1;
////            int pivot = arr[start];
////
////            for (int currentIndex = start + 1; currentIndex < end; currentIndex++)
////            {
////                if (arr[currentIndex] < pivot)
////                {
////                    swap(arr, bigIndexStart, currentIndex);
////                    bigIndexStart += 1;
////                }
////            }
////            swap(arr, start, bigIndexStart - 1);  //put the pivot element in its proper place.
////            return bigIndexStart - 1;
////
//////3rd partition method using two pointers from same side(right side/end)
//////        int pivot = arr[start];
//////
//////        int bigIndexStart = end;
//////
//////        for (int j = end-1; j > start; j--)
//////        {
//////
//////            if (arr[j] > pivot)
//////            {
//////                swap(arr, bigIndexStart, j);
//////                bigIndexStart--;
//////            }
//////        }
//////        swap(arr, bigIndexStart, start);
//////        return (bigIndexStart-1);
////        }
////
////        public static void quickSortMed(int[] array)
////        {
////            quickSortMed(array, 0, array.length);
////        }
////
////        private static void quickSortMed(int[] arr, int start, int end)
////        {
////            if (end - start > 10)
////            {
////                int median = medianOfThree(arr, start, (start + end) / 2, end);
////                swap(arr, start, median);
////
////                int pivot = partition(arr, start, end);
////
////                quickSortMed(arr, start, pivot - 1);
////                quickSortMed(arr, pivot + 1, end);
////            } else
////            {
////                selectionSort(arr);
////            }
////        }
////
////        public static void selectionSort(int[] arr)
////        {
////            for (int i = 0; i < arr.length - 1; i++)
////            {
////                int min = i;
////
////                for (int j = i + 1; j < arr.length; j++)
////                {
////                    if (arr[j] < arr[min])
////                    {
////                        min = j;
////                    }
////                }
////
////                swap(arr, i, min);
////            }
////        }
////
////        public static int medianOfThree(int[] arr, int a, int b, int c)
////        {
////            int median;
////
////            if ((arr[a] < arr[b] && arr[b] < arr[c]) || (arr[c] < arr[b] && arr[b] < arr[a]))
////            {
////                median = b;
////            } else if ((arr[b] < arr[a] && arr[a] < arr[c]) || (arr[c] < arr[a] && arr[a] < arr[b]))
////            {
////                median = a;
////            } else
////            {
////                median = c;
////            }
////
////            return median;
////        }
////
////        public static void swap(int[] arr, int i, int j)
////        {
////            int temp = arr[i];
////            arr[i] = arr[j];
////            arr[j] = temp;
////        }
////
////        public static boolean sortErrorCheck(int[] arr)
////        {
////            boolean sorted = true;
////
////            for (int i = 0; i < arr.length - 1; i++)
////            {
////                if (arr[i + 1] < arr[i])
////                {
////                    sorted = false;
////                }
////            }
////            return sorted;
////        }
////
////        public static void testMergeSort(int[] arr)
////        {
////            long timestamp;
////            timestamp = new Date().getTime();
////
////            plainMergeSort(arr);
////
////            timestamp = new Date().getTime() - timestamp;
////
////            System.out.println("Time for the plain merge sort: " + timestamp + " milliseconds.");
////
////            if (sortErrorCheck(arr))
////            {
////                System.out.println("Plain merge sort successfully sorted " + arr.length + " elements.\n");
////            } else
////            {
////                System.out.println("Plain merge sort was unsuccessful");
////            }
////        }
////
////        public static void testQuickSortFirst(int[] arr)
////        {
////            long timestamp;
////            timestamp = new Date().getTime();
////
////            quickSortFirst(arr);
////
////            timestamp = new Date().getTime() - timestamp;
////
////            System.out.println("Time for the quick sort (first): " + timestamp + " milliseconds.");
////
////            if (sortErrorCheck(arr))
////            {
////                System.out.println("quick sort (first ) successfully sorted " + arr.length + " elements.\n");
////            } else
////            {
////                System.out.println("quick sort (first) was unsuccessful");
////            }
////        }
////
////        public static void testQuickSortMed(int[] arr)
////        {
////            long timestamp;
////            timestamp = new Date().getTime();
////
////            quickSortMed(arr);
////
////            timestamp = new Date().getTime() - timestamp;
////
////            System.out.println("Time for the quick sort (mid): " + timestamp + " milliseconds.");
////
////            if (sortErrorCheck(arr))
////            {
////                System.out.println("quick sort (mid ) successfully sorted " + arr.length + " elements.\n");
////            } else
////            {
////                System.out.println("quick sort (mid) was unsuccessful");
////            }
////        }
////
//////    public static void timing()
//////    {
//////        long timestamp;
//////
//////        timestamp = new Date().getTime();
//////        timestamp = new Date().getTime() - timestamp;
//////    }
////    }//end class
//
//}
