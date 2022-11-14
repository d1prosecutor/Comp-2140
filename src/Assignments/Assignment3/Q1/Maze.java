package Assignments.Assignment3.Q1;

//-----------------------------------------
// NAME: Chukwunaza Chukwuocha
// STUDENT NUMBER: 007928676
// COURSE: COMP 2140, SECTION: A01
// INSTRUCTOR: Cuneyt Ackora
// ASSIGNMENT: Assignment 3 Question 1
//
// REMARKS: Implements The backtracking algorithm and Stack to make a simple maze game
//
//-----------------------------------------

import java.util.Scanner;

public class Maze
{
    //-------------------------------------------------------------------------------------
// CONSTANTS and TYPES
//-------------------------------------------------------------------------------------
    private static final int MAX_DIMENSION = 20;

    // constant definitions for the different cell states
    private static final char WALL = '1';
    private static final char SPACE = '0';
    private static final char VISITED = '.';
    private static final char MOUSE = 'm';
    private static final char EXIT = 'e';

    //-------------------------------------------------------------------------------------
// VARIABLES
//-------------------------------------------------------------------------------------

    //Stack object used to hold the cells
    private static Stack cellStack;

    // a 2D array used to store the maze
    private static char[][] maze = new char[MAX_DIMENSION][MAX_DIMENSION];
    private static int mazeRows;
    private static int mazeCols;

    // holds the location of the mouse and escape hatch
    private static Cell mouse;
    private static Cell escape;

    public static void main(String[] args) throws Exception
    {
        loadMaze();

        if (solveMaze())
            System.out.println("The mouse is free!!!!");
        else
            System.out.println("The mouse is trapped!!!!");

        System.out.println("End of processing");
    }

    //------------------------------------------------------
// loadMaze
//
// PURPOSE: Reads input from standard input and uses the input read to create the maze
// and initialize all its required cells(Walls, spaces, starting postion and the exit).
//------------------------------------------------------
    public static void loadMaze()
    {
        Scanner inputFile = new Scanner(System.in);

        // Reads the first line(Containing two integers-the row and column sizes of the maze)
        mazeRows = inputFile.nextInt();
        mazeCols = inputFile.nextInt();

        cellStack = new Stack(mazeRows * mazeCols);

        // consumes the newLine (and possibly carriage return) character which is left in the buffer by scanf
        inputFile.nextLine();

        // Variables for the current position while filling up the maze
        int rowIndex = 0;

        // New temp char array used to read each line of the input file and fill up the maze
        String newLine;

        while ((rowIndex < mazeRows) && inputFile.hasNextLine())
        {
            newLine = inputFile.nextLine(); // The current line being read
            int columnIndex = 0;      // index of the next column in the maze

            int newCharIndex = 0; // index of each character in the new Line being read


            while (newCharIndex < newLine.length() && columnIndex < mazeCols)
            {
                if (newLine.charAt(newCharIndex) == SPACE || newLine.charAt(newCharIndex) == EXIT ||
                        newLine.charAt(newCharIndex) == MOUSE || newLine.charAt(newCharIndex) == WALL)
                {
                    maze[rowIndex][columnIndex] = newLine.charAt(newCharIndex);

                    // Initialize the start and escape cells for the maze
                    if (newLine.charAt(newCharIndex) == MOUSE)
                    {
                        mouse = new Cell(rowIndex, columnIndex);
                    } else if (newLine.charAt(newCharIndex) == EXIT)
                    {
                        escape = new Cell(rowIndex, columnIndex);
                    }
                    columnIndex++;
                }
                //Move to the next character in the String being read
                newCharIndex++;
            }

            // Move to the next row
            rowIndex++;
        }

        printMaze();
    }

    //------------------------------------------------------
// solveMaze
//
// PURPOSE: Guides the mouse from its starting position to the exit position if there
// exists a path (hence trying to solve the maze).
// OUTPUT PARAMETERS:
// mazeSolved: A boolean that states if there exists a path from the mouse's starting position
// to the exit position or not
//------------------------------------------------------
    public static Boolean solveMaze()
    {
        // The flag to check if there is a solution or not
        Boolean mazeSolved = true;

        // Starting Point
        Cell currentCell = new Cell(mouse.getRow(), mouse.getColumn());

        while ((!currentCell.equalCells(escape)) && mazeSolved)
        {
            maze[currentCell.getRow()][currentCell.getColumn()] = VISITED;
            printMaze();

            // Right
            if ((maze[currentCell.getRow()][currentCell.getColumn() + 1] == SPACE) ||
                    (maze[currentCell.getRow()][currentCell.getColumn() + 1] == EXIT))
            {
                cellStack.push(new Cell(currentCell.getRow(), currentCell.getColumn() + 1));
            }

            //Left
            if ((maze[currentCell.getRow()][currentCell.getColumn() - 1] == SPACE) ||
                    (maze[currentCell.getRow()][currentCell.getColumn() - 1] == EXIT))
            {
                cellStack.push(new Cell(currentCell.getRow(), currentCell.getColumn() - 1));

            }

            //Up
            if ((maze[currentCell.getRow() - 1][currentCell.getColumn()] == SPACE) ||
                    (maze[currentCell.getRow() - 1][currentCell.getColumn()] == EXIT))
            {
                cellStack.push(new Cell(currentCell.getRow() - 1, currentCell.getColumn()));

            }

            //Down
            if ((maze[currentCell.getRow() + 1][currentCell.getColumn()] == SPACE) ||
                    (maze[currentCell.getRow() + 1][currentCell.getColumn()] == EXIT))
            {
                cellStack.push(new Cell(currentCell.getRow() + 1, currentCell.getColumn()));
            }

            if (cellStack.isEmpty())
            {
                mazeSolved = false;
            } else
            {
                currentCell = cellStack.pop();
            }
        }

        return mazeSolved;
    }

    //------------------------------------------------------
// printMaze
//
// PURPOSE: Prints the maze
//------------------------------------------------------
    public static void printMaze()
    {
        for (int i = 0; i < mazeRows; i++)
        {
            for (int j = 0; j < mazeCols; j++)
            {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
