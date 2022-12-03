import java.util.*;

// This class is used to implement our "maze" ADT
class MazeSolver
{
  /******************************************************************************
  Inner class
  *******************************************************************************/

  // This class is used to define a maze location in terms of row/column coordinates
  private class Cell
  {
    public int row;
    public int column;

    // the constructor simply assigns the values....
    Cell( int new_row, int new_column )
    {
      row = new_row;
      column = new_column;
    }

    // a comparison routine to make it easy for use to check for a match
    public boolean equals( Cell other_cell )
    {
      return (other_cell.row == row) && (other_cell.column == column);
    }
  }

  /******************************************************************************
  Attributes
  *******************************************************************************/

  // a stack of the maze positions yet to check
  private Stack cellStack;

  // a 2D array used to store the maze for this object
  private char maze[][];
  private int maze_rows;
  private int maze_cols;
  // holds the location of the mouse and exit
  private Cell mouse;
  private Cell exit;

  // constant definitions for the different cell states (recommended by ??)
  final private char WALL    = '1';
  final private char SPACE   = '0';
  final private char VISITED = '.';
  final private char MOUSE   = 'm';
  final private char EXIT    = 'e';

  /******************************************************************************
  Public Methods
  *******************************************************************************/

  // constructor creates a new stack and loads the maze from the given file
  MazeSolver()
  {
    // used for reading our file...
    String read_line;
    Scanner theFile = new Scanner(System.in);
    int curr_row = 0;

    // read in the maze

    // the first line of the file tells us how big to make the array
    // note that this assumes the line is always present...
    // this sets our maze_rows and maze_cols values
    read_line = theFile.nextLine();
    extractMazeSize( read_line );

    // allocate the array...
    maze = new char[maze_rows][maze_cols];
    
    // process each maze row in turn
    while ( theFile.hasNextLine() )
    {
      // get the next word to process
      read_line = theFile.nextLine();

      // parse the line and fill in the current maze row
      extractMazeRow( read_line, curr_row++ );
    }

    theFile.close();

    // create our stack; if we get rid of the boundaries we know how many
    // elements we can max out on the stack (it's actually less but this is
    // good enough...)
    cellStack = new Stack( (maze_rows-1) * (maze_cols-1) );

  }

  // all we really need is to solve the object's current maze...
  // returns true if the mouse found the exit
  public boolean solveMaze()
  {
    boolean dead_end = false;
    char curr_cell;
    // we need to track our location based on the mouse start positoin
    // we should check to ensure that it isn't null...
    Cell    curr = mouse;

    print();

    // search for an exit using our Cell stack
    while ( !exit.equals( curr ) && !dead_end )
    {
      // mark the current cell
      maze[curr.row][curr.column] = '.';

      // add in the neighbour cells that are valid moves
      // note the we rely on a boundary of 1's to prevent our exceeding array bounds
      curr_cell = maze[curr.row-1][curr.column];
      if ( curr_cell != WALL && curr_cell != VISITED )
        cellStack.push( (Object)(new Cell( curr.row-1, curr.column ) ) );
      curr_cell = maze[curr.row][curr.column+1];
      if ( curr_cell != WALL && curr_cell != VISITED )
        cellStack.push( (Object)(new Cell( curr.row, curr.column+1 ) ) );
      curr_cell = maze[curr.row][curr.column-1];
      if ( curr_cell != WALL && curr_cell != VISITED )
        cellStack.push( (Object)(new Cell( curr.row, curr.column-1 ) ) );
      curr_cell = maze[curr.row+1][curr.column];
      if ( curr_cell != WALL && curr_cell != VISITED )
        cellStack.push( (Object)(new Cell( curr.row+1, curr.column ) ) );

      if ( cellStack.isEmpty() )
        dead_end = true;
      else
        curr = (Cell)(cellStack.pop() );

      print();
    }

    // if we didn't hit a dead end then we must have exited
    return !dead_end;
  }

  /******************************************************************************
  Private Methods
  *******************************************************************************/

  // extracts the row and column numbers from a string
  private void extractMazeSize( String the_line )
  {
    // used to extract the numbers from the line
    String[] tokens;

    // use the string tokenizer to get each number
    tokens = the_line.split( "\\s+" );

    // assume there are only 2 tokens...
    maze_rows = Integer.parseInt( tokens[0] );
    maze_cols = Integer.parseInt( tokens[1] );
  }

  // extracts the row and column numbers from a string
  private void extractMazeRow( String the_line, int the_row )
  {
    // used to extract the numbers from the line
    String[] tokens;
    int curr_col = 0;

    // use the string tokenizer to get each number
    tokens = the_line.split( "\\s+" );

    // get each element and insert it into our maze
    for ( int i=0 ; i<tokens.length ; i++ )
    {

      // assume each token is 1 character...
      maze[the_row][curr_col] = tokens[i].charAt( 0 );

      // check to see if we have a mouse or exit location
      if ( maze[the_row][curr_col] == MOUSE )
        mouse = new Cell( the_row, curr_col );
      else if ( maze[the_row][curr_col] == EXIT )
        exit = new Cell( the_row, curr_col );

      curr_col++;
    }
  }

  // print the current state of our maze
  // it's private since we only need to use it as we're solving the maze...
  private void print()
  {
    int i, j;

    // standard printing of a matrix
    for ( i=0 ; i<maze_rows ; i++ )
    {
      for ( j=0 ; j<maze_cols ; j++ )
      {
        System.out.print( maze[i][j] );
      }
      System.out.println();
    }

    System.out.println();
  }

}