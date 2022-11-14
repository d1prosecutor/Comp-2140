public class Set
{

  private class Node
  {
    public int item;
    public Node next;

    public Node( int newItem, Node newNext )
    {
      item = newItem;
      next = newNext;
    }     
  } 

  private Node top; // A pointer to the dummy node in the circular linked list.


  // Create an empty set:
  //  - top points to a dummy node, and
  //  - the dummny node points to itself.
  public Set()
  {
    top = new Node( Integer.MAX_VALUE, null );
    top.next = top;
  } 


  // Insert an item into a set.
  //   Since sets do not contain duplicate items,
  //   we must first find out whether or not the
  //   item already is in the set.
  public void insert( int newItem )
  {
    Node nodeBefore;

    // Get a pointer to the node that should come before the new item.

    nodeBefore = findPrev( newItem );

    // Only add an item to a set if the set does NOT contain the item.

    if (nodeBefore.next.item != newItem)
    {
      // The set does not contain newItem, so add it.

      nodeBefore.next = new Node( newItem, nodeBefore.next );
    }
  } 


  // Return the node that should come before a node containing
  // the given item (called givenItem in the method).
  // Algorithm:
  //   Start prev at the dummy node, and iterate through the linked
  //   list until the node AFTER prev contains a value >= givenItem.
  // Since we stored Integer.MAX_VALUE in the dummy node to which
  // top points, this algorithm will work even if the given item is
  // larger than everything in the list.
  private Node findPrev( int givenItem )
  {
    Node prev = top;
    
    while (prev.next.item < givenItem)
      prev = prev.next;
      
    return prev;
  }


  // Delete a given item, if it appears in the set.
  public void delete( int givenItem )
  {
    Node nodeBefore = findPrev( givenItem );

    if (nodeBefore.next.item == givenItem)
    {
      // The set contains the given item.
      // Delete the given item.

      nodeBefore.next = nodeBefore.next.next;
    }
    
  } 


  // Return the union of two sets (the two sets should be unchanged by
  // the operation).  The two sets are "this" and "setB".
  //
  // The union of two sets A and B is defined to be another set
  // containing all the items in A and all the items in B.
  // (Note: Any item that is in BOTH A and B appears only once
  // in the union, because no set contains duplicate items.)
  // Example: If A = { 1, 2, 3 } and B = { 2, 4, 5, 6 }, then
  // the union of A and B is the set { 1, 2, 3, 4, 5, 6 }.
  //
  // Since our two lists are sorted, the algorithm is very similar
  // to the merge step in merge sort, except that if the two items
  // that we are examining in the two original sorted lists are
  // equal, then we put only ONE copy of the item into the union
  // list and move to the next item in BOTH original lists.
  public Set union( Set setB )
  {
    Set unionSet = new Set();
    Node nodeA = this.top.next; // Item we're examining in this
    Node nodeB = setB.top.next; // Item we're examining in setB
    Node prevUnion = unionSet.top; // The node before the next item
                                   // to be added to the union list.
    int copyItem; // Item to be copied into the union list.

    // Because both original sets have a dummy node containing MAX_VALUE,
    // we can use an "OR" in the condition of the while-loop.  By using
    // "OR", when we have used up all items in one of the two original
    // lists, the remainder of the other list will be copied into the
    // union by this while-loop.

    while ( (nodeA != this.top) || (nodeB != setB.top)  )
    {
      if (nodeA.item < nodeB.item)
      {
        copyItem = nodeA.item;
        nodeA = nodeA.next;
      }
      else if (nodeB.item < nodeA.item)
      {
        copyItem = nodeB.item;
        nodeB = nodeB.next;
      }
      else
      {  // nodeA.item == nodeB.item
        copyItem = nodeA.item;
        nodeA = nodeA.next;
        nodeB = nodeB.next;
      }
      
      prevUnion.next = new Node( copyItem, prevUnion.next );
      prevUnion = prevUnion.next;
    } // end while

    return unionSet;
  }


  // Return the intersection of two sets (the two sets should be unchanged by
  // the operation).  The two sets are "this" and "setB".
  //
  // We can use set manipulation to use existing methods to implement intersection.
  // A * B = A \ ( A \ B )
  //
  // Is this REALLY the best way to do this?
  public Set intersection( Set setB )
  {
    Set intersectionSet = difference( setB );
    
    intersectionSet = difference( intersectionSet );
    
    return intersectionSet;
  }
  
  
  // Return the difference of two sets (the two sets should be
  // unchanged by the operation).  The two sets are "this" and
  // "setB", and we want to return this\setB.
  //
  // The difference A\B of two sets A and B is defined to be
  // another set containing all the items in A that are NOT in B.
  // Example: If A = { 1, 2, 3 } and B = { 2, 4, 5, 6 }, then
  // the difference A\B of A and B is the set { 1, 3 }.
  //
  // Since our two lists are sorted, the algorithm is similar
  // to the merge step in merge sort, except that
  //  1) we stop the loop when we run out of items in "this";
  //  2) only items from "this" are copied into the difference;
  //  3) if the two items that we are examining in the two
  //     original sorted lists are equal, then we do NOT copy
  //     the item into the difference and we move to the next
  //     item in BOTH lists;
  //  4) if the item we're examining in "this" is less than the
  //     item in setB, then we copy the item in "this" into the
  //     difference and move to the next item in "this";
  //  5) if the item we're examining in setB is less than the
  //     the item in "this", we do not copy the item in setB
  //     into the difference --- we simply move to the next
  //     item in setB.
  public Set difference( Set setB )
  {
    Set differenceSet = new Set();
    Node nodeA = this.top.next; // Item we're examining in this
    Node nodeB = setB.top.next; // Item we're examining in setB
    Node prevDifference = differenceSet.top;
                                // The node before the next item
                                // to be added to the difference list.

    // Because setB has a dummy node containing MAX_VALUE,
    // we don't have to have a separate loop after this one
    // to handle any remaining elements in "this" if we run
    // out of items in setB.

    while ( nodeA != this.top )
    {
      if (nodeA.item < nodeB.item)
      {
        prevDifference.next = new Node( nodeA.item, prevDifference.next );
        prevDifference = prevDifference.next;
        nodeA = nodeA.next;
      }
      else if (nodeB.item < nodeA.item)
      {
        nodeB = nodeB.next;
      }
      else
      {  // nodeA.item == nodeB.item
        nodeA = nodeA.next;
        nodeB = nodeB.next;
      }
    } 

    return differenceSet;
  } 


  // Print out a set, preceded by a given header.
  // Print the items 10 per line, separated by commas.
  // Surround the entire set by braces.
  public void printSet( String header )
  {
    int i=0;  // number of elements printed on the current line so far.
    Node curr = top.next;

    System.out.println( header );
    System.out.print( "{" );

    while (curr != top)
    {
      System.out.print( " " + curr.item );
      i++;
      
      if (curr.next != top)
      {
        System.out.print( "," );
        if (i == 10)
        {
          System.out.println();
          System.out.print( " " );
          i = 0;
        }
      }
      curr = curr.next;
    } 

    System.out.println( " }" );
    
  }


}
