import java.util.*;

public class a2
{
  public static void main( String[] args )
  {
    Scanner theFile = new Scanner(System.in);
    String inLine;
    String[] tokens;    // The elements from a line of input.

    Set[] sets; // The array of sets.
    int numSets; // The number of sets.

    int set1 = 0, set2 = 0, set3 = 0; // Index of a set used in a set operation
    int cmdItem; // Item to be inserted or deleted, given in a set operation.

    // The first line is the number of sets
    inLine = theFile.nextLine();
    numSets = Integer.parseInt(inLine);

    //  Allocate the array of sets.

    sets = new Set[ numSets ];
    for (int i = 0; i < numSets; i++)
    {
      sets[i] = new Set();
    }
    
    // Perform the set operations.
    while (theFile.hasNextLine())
    {
      inLine = theFile.nextLine();

      // Parse the current line of input.
      // Each line contains a set operation (one letter) followed
      // by the arguments needed by the set operation (all separated
      // by blanks).
      // The input commands are assumed to be error-free.
      
      tokens = inLine.split( "\\s+" );

      // default to set information
      set1 = Integer.parseInt( tokens[1] );
      if (tokens.length > 2 )
        set2 = Integer.parseInt( tokens[2] );
      if (tokens.length > 3 )
        set3 = Integer.parseInt( tokens[3] );

      // First, get the set operation (one letter).
      if (tokens[0].equals( "I" ))
      {
        // Insert command.

        // Get item to insert.
        
        cmdItem = Integer.parseInt( tokens[2] );
        
        // Perform the insertion.
        
        sets[set1].insert( cmdItem );
      }
      else if (tokens[0].equals( "D" ))
      {
        // Delete command.
        
        // Get item to delete.
        
        cmdItem = Integer.parseInt( tokens[2] );
        
        // Perform the deletion.
        
        sets[set1].delete( cmdItem );
      }
      else if (tokens[0].equals( "U" ))
      {
        // Union command.

        sets[set3] = sets[set1].union( sets[set2] );
      }
      else if (tokens[0].equals( "*" ))
      {
        // Intersection command.
        
        sets[set3] = sets[set1].intersection( sets[set2] );
      }
      else if (tokens[0].equals( "\\" ))
      {
        // Difference command.

        sets[set3] = sets[set1].difference( sets[set2] );
      }
      else if (tokens[0].equals( "P" ))
      {
        // Print command.

        sets[set1].printSet( ("Set " + set1) );
      }
    } 

    theFile.close();
  } 

} 
