
public class a3q1
{
  public static void main( String args[] )
  {
    MazeSolver theMaze = new MazeSolver();

    if ( theMaze.solveMaze() )
      System.out.println( "The mouse is free!!!!" );
    else
      System.out.println( "The mouse is scared!!!!" );
    
    System.out.println( "\nEnd of processing" );
  }
}
