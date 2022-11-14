package Assignments.Assignment3.Q1;

//-----------------------------------------
// NAME: Chukwunaza Chukwuocha
// STUDENT NUMBER: 007928676
// COURSE: COMP 2140, SECTION: A01
// INSTRUCTOR: Cuneyt Ackora
// ASSIGNMENT: Assignment 3 Question 1
//
// REMARKS: Implements a Stack ADT along with the methods associated with a stack
//
//-----------------------------------------

public class Stack
{
    private Cell[] list;  // Pointer to the top of the stack.
    private int listIndex;

    /*****************************************
     * Constructor
     *
     * Purpose: creates an empty stack
     ******************************************/
    public Stack(int listSize)
    {
        list = new Cell[listSize];
        listIndex = -1;
    }


    /****************************************
     * push
     *
     * Purpose: add a Cell to the top of the
     *          stack
     * Input: Cell to add
     *****************************************/
    public void push(Cell newCell)
    {
        // Check if the cell is not already in the list, and adds it if it is not
        Boolean isInList = false;

        for (int i = 0; i < listIndex; i++)
        {
            if (list[i].equalCells(newCell))
            {
                isInList = true;
            }
        }

        if (!isInList)
            list[++listIndex] = new Cell(newCell.getRow(), newCell.getColumn());
    }

    /****************************************
     * pop
     *
     * Purpose: return the Cell at the top of the stack
     * and remove it from the Stack
     ****************************************/
    public Cell pop()
    {
        Cell topOfStack = null;

        if (!isEmpty())
        {
            topOfStack = new Cell(list[listIndex].getRow(), list[listIndex].getColumn());
            listIndex--;
        }

        return topOfStack;
    }


    /***********************************************
     * top
     *
     * Purpose: return the Cell at the top of the stack without deleting it .
     ************************************************/
    public Cell top()
    {
        Cell topOfStack = null;

        if (!isEmpty())
        {
            topOfStack = new Cell(list[listIndex].getRow(), list[listIndex].getColumn());
        }

        return topOfStack;
    }


    /*************************************************
     * isEmpty
     *
     * Purpose: Checks if the Stack is empty
     **************************************************/
    public boolean isEmpty()
    {
        return (listIndex == -1);
    }

    /*************************************************
     * isFull
     *
     * Purpose: Checks if the Stack is full
     **************************************************/
    public boolean isFull()
    {
        return (list.length == listIndex + 1);
    }

}
