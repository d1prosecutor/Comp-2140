
// A generic stack class that we can use to build our location stack from.
// An array is used since we can define a fixed maximum stack based on the number
// of cells in a maze. This helps us in terms of performance since we don't have
// to continually allocate nodes and we don't have to worry about triggering the
// garbage collector due to lots of stack nodes being popped.
class Stack
{
  /******************************************************************************
  Attributes
  *******************************************************************************/

  protected Object stack[];   // an array to hold our stack data
  protected int size;         // the maximum number of stack elements
  protected int top;          // "pointer" to the top of the stack -- the element to push into

  /******************************************************************************
  Public Methods
  *******************************************************************************/

  // standard constructor
  Stack( int size )
  {
    // allocate a stack with a maximum size
    stack = new Object[size];

    // set our size and pointer
    this.size = size;
    top = 0;
  }

  // adds the passed object to the top of our stack
  public void push( Object element )
  {
    // make sure we have space...
    if ( top < size-1 )
      stack[top++] = element;
  }

  // returns the top object from the stack and removes it from the stack
  public Object pop()
  {
    // initializing to null lets us return a null ref as an error
    Object element = null;  

    // make sure the stack isn't empty...
    if ( top != 0 )
      element = stack[--top];

    return element;
  }

  // returns the top object without removing it from the stack
  public Object top()
  {
    // initializing to null lets us return a null ref as an error
    Object element = null;

    // make sure the stack isn't empty...
    if ( top != 0 )
      element = stack[top-1];

    return element;
  }

  // returns true iff the stack has no elements
  public boolean isEmpty()
  {
    return top==0;
  }

}

