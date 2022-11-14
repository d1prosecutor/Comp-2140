package Labs.Lab3;
/*Stack for Lab 3*/

public class Stack
{
    private Node top;  // Pointer to the top of the stack.

    /*****************************************
     * Constructor
     *
     * Purpose: creates an empty stack
     ******************************************/
    public Stack()
    {
        top = null;
    }


    /****************************************
     * push
     *
     * Purpose: add an item to the top of the
     *          stack
     * Input: int to add
     *****************************************/
    public void push(int element)
    {
        top = new Node(element, top);
    }


    /****************************************
     * pop
     *
     * Purpose: return the data in the first
     *          node and delete that node.
     ****************************************/
    public int pop()
    {
        int element = Integer.MIN_VALUE;  // initialized to nothing...

        if (top != null)
        {
            element = top.data;
            top = top.next;
        }

        return element;
    }


    /***********************************************
     * top
     *
     * Purpose: return the data in the first node.
     ************************************************/
    public int top()
    {
        int element = Integer.MIN_VALUE;

        if (top != null)
            element = top.data;

        return element;
    }


    /*************************************************
     * isEmpty
     *
     * Purpose: return the data in the first node.
     **************************************************/
    public boolean isEmpty()
    {
        return (top == null);
    }

    /***************************************************************************
     *                          INNER CLASS DEFINITIONS                         *
     ***************************************************************************/

    /************************************
     * Node Class
     *
     * Purpose: holds data items and links
     * to the next node in the list.
     *************************************/

    private class Node
    {
        public Node next; //the next node
        public int data;

        /******************************************************
         * Constructor
         *
         * Purpose: create a new node
         * Input: the data to be stored and link to the next node
         ********************************************************/
        public Node(int newdata, Node newlink)
        {
            data = newdata;
            next = newlink;
        }

    }
}
