package Labs.Lab2;

import java.util.*;

public class LetterSetVec
{
    private ArrayList<String> Letters; //contains all letters picked

    //---------------------
    // Constructor
    //
    // Purpose: creates an empty set of
    // of letters.
    //----------------------

    public LetterSetVec()
    {

        Letters = new ArrayList<String>();
    }

    //-------------------
    // reset
    //
    // Purpose: removes all letters from the list
    //--------------------

    public void reset()
    {

        Letters.clear();
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
            Letters.add(letter);
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
        String testCase;
        boolean Found = false;

        //loop through all letters
        for (int i = 0; i < Letters.size(); i++)
        {
            testCase = (String) Letters.get(i);
            if (testCase.compareTo(letter) == 0)
                Found = true;
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

        //loop through all letters
        for (int i = 0; i < Letters.size(); i++)
        {
            System.out.print((String) Letters.get(i) + " ");
        }

        System.out.println();
    }

    //----------------------
    // main
    //
    // Purpose: to test the ADT
    //----------------------
    public static void main(String args[])
    {
        LetterSetVec test = new LetterSetVec();
        test.addLetter("A");
        test.addLetter("B");
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
        test.addLetter("X");
        test.addLetter("Y");
        test.addLetter("C");
        test.addLetter("D");
        test.display();

    }
}