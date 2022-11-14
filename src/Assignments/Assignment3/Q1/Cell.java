package Assignments.Assignment3.Q1;

//-----------------------------------------
// NAME: Chukwunaza Chukwuocha
// STUDENT NUMBER: 007928676
// COURSE: COMP 2140, SECTION: A01
// INSTRUCTOR: Cuneyt Ackora
// ASSIGNMENT: Assignment 3 Question 1
//
// REMARKS: Creates a Cell class which represents and stores the coordinates of each position on the maze
//
//-----------------------------------------

public class Cell
{
    private int row;
    private int column;

    public Cell(int newRow, int newCol)
    {
        row = newRow;
        column = newCol;
    }

    //------------------------------------------------------
// equalCells
//
// PURPOSE: Checks if two cells are equal
// INPUT PARAMETERS:
// this: The first cell to be compared
// compareCell: The second cell to be compared
// OUTPUT PARAMETERS:
// boolFlag: The boolean which states if the cells are equal or not
//------------------------------------------------------
    public Boolean equalCells(Cell compareCell)
    {
        return ((row == compareCell.row) && (column == compareCell.column));
    }

    //returns the row of the cell object
    public int getRow()
    {
        return row;
    }

    //returns the column of the cell object
    public int getColumn()
    {
        return column;
    }
}
