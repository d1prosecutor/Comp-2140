
public class LetterSetLL
{
  
  // This class implements a Wheel-of-Fortune set of letters
  // using a linked list.
  private class Node
  {
    
    // This class implements one node (containing one letter)
    // in a linked list of letters.
    
    public char letter;  // The letter contained in the node.
    public Node link;    // The next node in the list of letters.
    
    public Node()
    {
      letter = ' ';
      link = null;
    }
    
    public Node( char k, Node l )
    {
      letter = k;
      link = l;
    }
    
  } // end class Node
  
  private Node top;  // The first node in a linked list
  // containing all the letters.
  
  //------------------------------------------
  // Constructor
  //
  // PURPOSE Creates an empty set of letters.
  //------------------------------------------
  
  public LetterSetLL()
  {
    top = null;
  }
  
  //------------------------------------------------
  // reset
  //
  // PURPOSE Removes all the letters from the list.
  //
  // RETURN void
  //------------------------------------------------
  
  public void reset()
  {
    top = null;
  }
  
  //-------------------------------------------------
  // addLetter
  //
  // PURPOSE Adds a letter to the list.
  //
  // PARAM let
  //       INPUT parameter
  //       Letter to be added to the list.
  //
  // RETURN void
  //--------------------------------------------------
  
  public void addLetter( char let)
  {
    Node newNode; // New node to contain new letter
    // if letter was not chosen before.
    
    if (!wasChosen(let))
    {
      newNode = new Node( let, top );
      this.top = newNode;
    }
  } // end method addLetter
  
  
  //------------------------------------------------------
  // wasChosen
  //
  // PURPOSE Check if a letter has already been chosen.
  //
  // PARAM let
  //       INPUT parameter
  //       The letter to check for.
  //
  // RETURN True if letter has been chosen; false otherwise.
  //--------------------------------------------------------
  
  public boolean wasChosen( char let )
  {
    boolean result;       // Was letter chosen?
    Node curr = this.top; // Current node in list to be examined.
    
    // Loop through all the letters to find let.
    while ( (curr != null) && (curr.letter != let) ) 
    {
      curr = curr.link;
    }
    
    if (curr == null)
      result = false;
    else
      result = true;
    
    return result;
  } // end method wasChosen
  
  //---------------------------------------------------------
  // display
  //
  // PURPOSE Print out a list of all chosen letters.
  //
  // RETURN void
  //---------------------------------------------------------
  
  public void display()
  {
    Node curr = this.top;
    
    System.out.println();
    
    // Loop through all the letters, printing each one.
    while( curr != null )
    {
      System.out.print( curr.letter + " " );
      curr = curr.link;
    }
    System.out.println();
    
  } // end method display
  
  
  //----------------------------------------------------
  // main
  //
  // PURPOSE To test the ADT LetterSet.
  //----------------------------------------------------
  
  public static void main( String ars[] )
  {
    LetterSetLL test = new LetterSetLL();
    test.addLetter( 'A' );
    test.addLetter( 'B' );
    test.display();
    
    if (test.wasChosen( 'A' ))
      System.out.println( "A was chosen." );
    else
      System.out.println( "A was not chosen." );
    
    if (test.wasChosen( 'B' ))
      System.out.println( "B was chosen." );
    else
      System.out.println( "B was not chosen." );
    
    test.reset();
    test.addLetter( 'X' );
    test.addLetter( 'Y' );
    test.addLetter( 'C' );
    test.addLetter( 'D' );
    test.display();
    
  } // end method main
  
} // end class LetterSetLL