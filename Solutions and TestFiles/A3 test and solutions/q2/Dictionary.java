import java.io.*;
import java.util.*;

// This is our table ADT that will hold our hashed lists of dictionary words.
class Dictionary
{

  /******************************************************************************
  Inner class
  *******************************************************************************/

  private class Element
  {
    public String word;
    public Element link;

    // standard linked list constructor
    Element( String new_word, Element new_link )
    {
      word = new_word;
      link = new_link;
    }
  }

  /******************************************************************************
  Attributes
  *******************************************************************************/

  private final int TABLE_SIZE = 2011;
  // Our table. It consists of a list of pointers to the individual hash chains.
  // It's essentially a list of top pointers.
  private Element table[];

  /******************************************************************************
  Public Methods
  *******************************************************************************/

  // our constructor will read in and hash the dictionary file so once the object
  // is created we're ready to start the spell check
  Dictionary(String fileName )
  {
    int i;
    int hash_value;
    String read_word;

    // start by initializing the table to a list of null pointers
    table = new Element[TABLE_SIZE];
    for ( i=0 ; i<TABLE_SIZE ; i++ )
      table[i] = null;

    // read the words into the table
    try
    {
      Scanner theFile = new Scanner(new File(fileName));

      System.out.print( "Loading dictionary" );
      
      // each line contains 1 word...
      while ( theFile.hasNextLine() )
      {
        // get the next word to process
        read_word = theFile.nextLine();

        System.out.print( "." );
        
        // always work in lower case
        read_word = read_word.toLowerCase();

        // the hash value gives us the index into the table, which tells us the list
        // to use for creating the new node
        hash_value = hash( read_word );

        // create a new node at the top of the appropriate list so this is our insert
        table[hash_value] = new Element( read_word, table[hash_value] );
      }

      System.out.println();

      theFile.close();
    }

    catch( FileNotFoundException ex )
    {
      System.out.println( "Could not find " + fileName + ". Check the file name and try again." );
    }
  }

  // try to find the given word in the dictionary, returns true if present
  public boolean lookup( String the_word )
  {
    boolean found = false;
    // make sure we only work in lower case
    String  hash_word = the_word.toLowerCase();
    // we start our search by hashing to the appropriate list
    int     table_index = hash( hash_word );
    Element next = table[table_index];

    // search for the word in the list
    while ( !found && next != null )
    {
      if ( hash_word.equals( next.word ) )
        found = true;
      else
        next = next.link;
    }

    return found;
  }

  // test method to see how the hashing is working...
  public void print_table()
  {
    int i;
    Element next;

    for ( i=0 ; i<TABLE_SIZE ; i++ )
    {
      System.out.print( "Chain " + i + " contains: " );

      next = table[i];
      while ( next != null )
      {
        System.out.print( next.word + " " );
        next = next.link;
      }

      System.out.println( "\n" );
    }
  }

  /******************************************************************************
  Private Methods
  *******************************************************************************/

  // we have a private hash function since using hashing is an implementation detail
  // it returns the hash value for the given string
  private int hash( String the_word )
  {
    final int HASHING_CONSTANT = 13;   // pick a prime!
    int i;
    int hash = 0;

    // use Horner's method to compute the hash efficiently
    //x^k-1 + a(x^k-2 + a(x^k-3 + ... + a(x^2 + a(x^1 + ax^0))...))
    for ( i=0 ; i < the_word.length()-1 ; i++ )
    {
      hash += (int)the_word.charAt( i );
      hash *= HASHING_CONSTANT;
    }
    hash += (int)the_word.charAt( the_word.length()-1 );

    return Math.abs( hash )%TABLE_SIZE;
  }
}