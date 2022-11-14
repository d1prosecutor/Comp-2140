//-----------------------------------------
// NAME: Chukwunaza Chukwuocha
// STUDENT NUMBER: 007928676
// COURSE: COMP 2140, SECTION: A01
// INSTRUCTOR: Cuneyt Ackora
// Lab: 2
//
// REMARKS: To implement a basic linear linked list
//
//-----------------------------------------

package Labs.Lab2;

public class LetterSet
{
    private class Node
    {
        public String item;
        public Node next;

        public Node(String newItem, Node nextNode)
        {
            item = newItem;
            next = nextNode;
        }
    }

    private Node top; //contains all letters picked

    //---------------------
    // Constructor
    //
    // Purpose: creates an empty set of
    // of letters.
    //----------------------
    public LetterSet()
    {
        top = null;
    }

    //-------------------
    // reset
    //
    // Purpose: removes all letters from the list
    //--------------------
    public void reset()
    {
        top = null;
    }

    //-----------------------------
    // addLetter
    //
    // Purpose: adds a letter to the list
    // Input Parameters; the letter to be added
    //-----------------------------
    public void addLetter(String letter)
    {
        //check if the letter is already there
        if (!wasChosen(letter))
        {
            top = new Node(letter, top);
        }
    }

    //-----------------------------
    // addToEnd
    //
    // Purpose: adds a letter to the end of list
    // Input Parameters; the letter to be added
    //-----------------------------
    public void addToEnd(String letter)
    {
        Node prev = null;
        Node curr = top;

        if (!wasChosen(letter))
        {
            while (curr != null)
            {
                prev = curr;
                curr = curr.next;
            }

            if (prev != null)
                prev.next = new Node(letter, null);
            else
                top = new Node(letter, null);
        }
    }

    //-----------------------------
    // wasChosen
    //
    // Purpose: check if a letter has already been chosen
    // Input Parameters : the letter to check
    // Output: return true if it has been chosen, false otherwise
    //-----------------------------
    public boolean wasChosen(String letter)
    {
        Node curr = top;

        String testCase;
        boolean Found = false;

        //loop through all letters
        while (curr != null)
        {
            testCase = curr.item;

            if (testCase.compareTo(letter) == 0)
                Found = true;

            curr = curr.next;
        }

        return Found;
    }

    //-----------------------
    // display
    //
    // Purpose: Print out a list of all chosen letters
    //-----------------------
    public void display()
    {
        System.out.println();

        Node curr = top;

        //loop through all letters
        while (curr != null)
        {
            System.out.print((String) curr.item + " ");
            curr = curr.next;
        }

        System.out.println();
    }


    //----------------------
    // main
    //
    // Purpose: to test the Linked List implementation of the ADT
    //----------------------
    public static void main(String args[])
    {
        LetterSet test = new LetterSet();
        test.addLetter("B");
        test.addLetter("A");
        test.display();

        if (test.wasChosen("A"))
            System.out.println("A was chosen.");
        else
            System.out.println("A was not chosen.");

        if (test.wasChosen("B"))
            System.out.println("B was chosen.");
        else
            System.out.println("B was not chosen.");

        if (test.wasChosen("C"))
            System.out.println("C was chosen.");
        else
            System.out.println("C was not chosen.");

        test.reset();
        test.addLetter("D");
        test.addLetter("C");
        test.addLetter("Y");
        test.addLetter("X");
        test.display();

    }
}