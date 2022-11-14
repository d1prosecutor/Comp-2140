package Assignments.Assignment3.Q2;

//-----------------------------------------
// NAME: Chukwunaza Chukwuocha
// STUDENT NUMBER: 007928676
// COURSE: COMP 2140, SECTION: A01
// INSTRUCTOR: Cuneyt Ackora
// ASSIGNMENT: Assignment 3 Question 2
//
// REMARKS: Tests the Set class program
//
//-----------------------------------------

public class NumQueue
{
    private NumNode front;
    private NumNode end;

    //Constructor for the queue of the numbers where each incorrect word
    // appears in the file
    public NumQueue()
    {
        front = end = null;
    }

    //------------------------------------------------------
// enter
//
// PURPOSE: Adds the line number where the incorrect word appears in the file to the queue
// INPUT PARAMETERS:
// newItem: the new number to be added
// ------------------------------------------------------
    public void enter(int newItem)
    {
        NumNode newEnd = new NumNode(newItem, null);

        if (end == null)
        {
            front = end = newEnd;
        } else
        {
            end.next = newEnd;
            end = newEnd;
        }
    }

    //------------------------------------------------------
// leave
//
// PURPOSE: removes the first line number where the incorrect word appears in the file from the queue
// OUTPUT PARAMETERS:
// firstItem: The number which was just removed from the queue
// ------------------------------------------------------

    public int leave()
    {
        int firstItem = -1;

        if (null != end)
        {
            firstItem = front.lineNum;
            front = front.next;
        }

        return firstItem;
    }

    //returns the first item in the queue
    public NumNode getFront()
    {
        return front;
    }


    /******************************************************************************
     The private node class specifically for the Num Queue Data type
     Each Node in the num queue contains the line number where the incorrect word
     the line number where the incorrect word occurs and the link to the next number
     to the next Node
     ******************************************************************************/
    private class NumNode
    {
        public int lineNum;
        public NumNode next; //the next node


        /******************************************************
         * Constructor
         *
         * Purpose: create a new node
         * Input: the data to be stored and link to the next node
         ********************************************************/
        public NumNode(int newdata, NumNode newlink)
        {
            lineNum = newdata;
            next = newlink;
        }
    }
}
