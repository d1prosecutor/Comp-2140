package Assignments.Assignment3.Q2;

//-----------------------------------------
// NAME: Chukwunaza Chukwuocha
// STUDENT NUMBER: 007928676
// COURSE: COMP 2140, SECTION: A01
// INSTRUCTOR: Cuneyt Ackora
// ASSIGNMENT: Assignment 3 Question 2
//
// REMARKS: Implements a Queue ADT for storing the incorrect words gotten from checking
// the user's file with the user's dictionary
//
//-----------------------------------------

public class WordQueue
{
    private QNode front;
    private QNode end;
    private int numIncorrectWords;


    //Constructor for the queue of incorrect words
    public WordQueue()
    {
        front = end = null;
        numIncorrectWords = 0;
    }

    //------------------------------------------------------
// enter
//
// PURPOSE:  adds a new word to the queue of incorrect words
// INPUT PARAMETERS:
// newItem: the new word to be added
// lineNum: The number of the line on the user's input file from which this word was read
// ------------------------------------------------------

    public void enter(String newItem, int lineNum)
    {
        QNode newEnd = new QNode(newItem, null);// The new item to be added to the queue

        QNode tempNode = front;

        //check if the new item is already in the queue
        boolean inQueue = false;
        while (null != tempNode && !inQueue)
        {
            if (tempNode.word.equalsIgnoreCase(newItem))
            {
                inQueue = true;
                tempNode.lineNumber.enter(lineNum);
            }
            tempNode = tempNode.next;
        }

        if (!inQueue)
        {
            if (null == end)
            {
                front = end = newEnd;
            } else
            {
                end.next = newEnd;
                end = newEnd;
            }

            numIncorrectWords++;
            end.lineNumber.enter(lineNum);
        }
    }

    //------------------------------------------------------
// leave
//
// PURPOSE: removes a word from the queue of incorrect words
// OUTPUT PARAMETERS:
// firstItem: The word which was just removed from the queue
// ------------------------------------------------------
    public String leave()
    {
        String firstItem = "";

        // If the queue is not empty, remove the first item from the queue
        // else remove nothing
        if (null != end)
        {
            firstItem = front.word;
            front = front.next;
        }

        return firstItem;
    }

    //------------------------------------------------------
// print
//
// PURPOSE: prints out all the incorrect words in the queue with their line Numbers on the input file
// ------------------------------------------------------
    public void print()
    {
        System.out.println("There " + (numIncorrectWords == 1 ? "is" : "are")
                + " a total of " + numIncorrectWords + " invalid word" + (numIncorrectWords == 1 ? "" : "s") +
                ((numIncorrectWords > 0) ? ":" : ""));

        while (null != front)
        {
            String lineNumbers = "";

            //Variable to count how many numbers have been printed
            int counter = 0;

            //Temporary NumQueue object used to loop through the queue containing line numbers
            NumQueue temp = front.lineNumber;

            while (null != temp.getFront())
            {
                counter++;
                if (counter > 30)
                {
                    lineNumbers += "\n\t";
                    counter = 0;
                }
                lineNumbers += temp.leave() + " ";
            }

            //Print the line numbers by leaving(deleting) them from their queues
            System.out.println(" Invalid word \"" + leave() + "\" found on lines " + lineNumbers);
        }
    }

    /******************************************************************
     The private node class specifically for the Word Queue
     Each Node in the word queue contains the incorrect word,
     a queue containing the line numbers where the incorrect word occurs
     and the link to the next Node
     *****************************************************************/
    private class QNode
    {
        public String word;
        public NumQueue lineNumber;

        public QNode next; //the next node

        /******************************************************
         * Constructor
         *
         * Purpose: create a new Queue node
         * Input: the data to be stored and link to the next node
         ********************************************************/
        public QNode(String newdata, QNode newlink)
        {
            word = newdata;
            next = newlink;
            lineNumber = new NumQueue();
        }

    }
}