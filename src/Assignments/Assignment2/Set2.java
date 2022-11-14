package Assignments.Assignment2;
//-----------------------------------------
// NAME: Chukwunaza Chukwuocha
// STUDENT NUMBER: 007928676
// COURSE: COMP 2140, SECTION: A01
// INSTRUCTOR: Cuneyt Ackora
// ASSIGNMENT: Assignment 2
//
// REMARKS: To implement a circular linear linked list with a dummy variable
//
//-----------------------------------------

public class Set2
{
    //------------------------------------------------------
// Node Class
//
// PURPOSE: Creates Nodes which act as containers for the items in the list and also
// as pointers to the item immediately next to it in the list.
//------------------------------------------------------
    private class Node
    {
        // Node instance variables
        public int item;
        public Node next;

        // Node Constructor that initializes the Node's item and link variable
        public Node(int newItem, Node nextNode)
        {
            item = newItem;
            next = nextNode;
        }
    }

    //Set instance variables
    private Node top;
    private Node dummy;

    //------------------------------------------------------
// Set()
//
// PURPOSE: Constructor - creates set objects
//------------------------------------------------------
    public Set2()
    {
        dummy = new Node(Integer.MIN_VALUE, top);
        top = dummy;
        dummy.next = top;

    }

    //------------------------------------------------------
// insert
//
// PURPOSE: Inserts a new item into the (ordered) Set in its correct position
// INPUT PARAMETERS:
// value: the value to be inserted into the Set
//------------------------------------------------------
    public void insert(int value)
    {
        Node prev = dummy;
        Node current = dummy.next;

        //Inserts the new item at the front of the Set if the Set is empty
        if (isEmpty())
        {
            dummy.next = new Node(value, top);
        } else
        {
            //Searches for the correct position to insert the value in the Set
            while (current != dummy && current.item < value)
            {
                prev = current;
                current = current.next;
            }

            //Ensures that no duplicates occur in the set
            if (current.item != value)
            {
                prev.next = new Node(value, prev.next);
            }
        }
    }

    //------------------------------------------------------
// delete
//
// PURPOSE: Deletes the value passed in by the user from the Set(if that value is found in the set)
// INPUT PARAMETERS:
// value: the value to be (searched for and) deleted
    //------------------------------------------------------
    public void delete(int value)
    {
        Node prev = dummy;
        Node current = dummy.next;

        //If the Set is not empty
        if (!isEmpty())
        {
            //Searches for the value in the Set
            while (current != dummy && current.item < value)
            {
                prev = current;
                current = current.next;
            }

            //Deletes the value if it's found after the Set has been traversed
            if (current != dummy && (current.item == value))
            {
                prev.next = current.next;
            }
        }
    }

    //------------------------------------------------------
// union
//
// PURPOSE: Takes two sets A and B and creates a new set containing the items that are either in Set A or in Set B
// INPUT PARAMETERS:
// Set A, Set B: The two sets to be compared
//------------------------------------------------------
    public void union(Set2 A, Set2 B)
    {
        Node currentA = A.dummy.next;
        Node currentB = B.dummy.next;

        Set2 unionSet2 = new Set2();

        //if Set A is empty, then the union is just Set B
        if (A.isEmpty())
        {
            while (currentB != B.dummy)
            {
                unionSet2.dummy.next = new Node(currentB.item, currentB.next);

                currentB = currentB.next;
            }

        } else if (B.isEmpty())
        {
            //if Set B is empty, then the union is just Set A
            while (currentA != A.dummy)
            {
                unionSet2.insert(currentA.item);
                currentA = currentA.next;
            }
        } else
        {
            // loops through all the elements in both sets and adding them to the unionSet while
            // avoiding duplicates
            while (currentA != A.dummy && currentB != B.dummy)
            {
                if (currentA.item < currentB.item)
                {
                    unionSet2.insert(currentA.item);
                    currentA = currentA.next;
                } else if (currentA.item > currentB.item)
                {
                    unionSet2.insert(currentB.item);
                    currentB = currentB.next;
                } else
                {
                    unionSet2.insert(currentA.item);
                    currentA = currentA.next;
                    currentB = currentB.next;
                }
            }

            //If Set A has completely added but there are elements remaining in Set B
            // Copy the rest of Set B
            if (A.isEmpty())
            {
                while (currentB != B.dummy)
                {
                    unionSet2.insert(currentB.item);
                    currentB = currentB.next;
                }
            } else
            {
                // If Set B is completely added but there are elements remaining in Set A
                // Copy the rest of Set A

                while (currentA != A.dummy)
                {
                    unionSet2.insert(currentA.item);
                    currentA = currentA.next;
                }
            }
        }

        //Update the pointers of the set object being modified to point to the unionSet
        dummy = unionSet2.dummy;
        top = dummy;
    }

    //------------------------------------------------------
// intersection
//
// PURPOSE: Takes two sets A and B and creates a new set containing the items that are in Set A and at the
// same time in Set B
// INPUT PARAMETERS:
// Set A, Set B: The two sets to be compared
//------------------------------------------------------
    public void intersection(Set2 A, Set2 B)
    {
        Node currentA = A.dummy.next;
        Node currentB = B.dummy.next;

        Set2 intersectionSet2 = new Set2();

        //If the sets are not empty
        if (!(A.isEmpty() || B.isEmpty()))
        {
            //Traversing all the items in set A and comparing each of them to every element in set B
            while (currentA != A.dummy && currentB != B.dummy)
            {
                if (currentA.item < currentB.item)
                {
                    currentA = currentA.next;
                } else if (currentA.item > currentB.item)
                {
                    currentB = currentB.next;
                } else
                {
                    intersectionSet2.insert(currentA.item);
                    currentA = currentA.next;
                    currentB = currentB.next;
                }
            }
        }

        //Update the pointers of the set object being modified to point to the intersectionSet
        dummy = intersectionSet2.dummy;
        top = dummy;
    }

    //------------------------------------------------------
// difference
//
// PURPOSE: Takes two sets A and B and creates a new set containing the items that are in Set A, but not Set B
// INPUT PARAMETERS:
// Set A, Set B: The two sets to be compared
//------------------------------------------------------
    public void difference(Set2 A, Set2 B)
    {
        Node currentA = A.dummy.next;
        Node currentB = B.dummy.next;

        Set2 differenceSet2 = new Set2();

        //If A is empty, then A\B is empty
        if (!(A.isEmpty()))
        {
            //If Set B is empty, then the difference is just Set A
            if (B.isEmpty())
            {
                while (currentA != A.dummy)
                {
                    differenceSet2.insert(currentA.item);
                    currentA = currentA.next;
                }
            } else
            {
                //Traversing all the items in set A and comparing each of them to every element in set B
                while (currentA != A.dummy)
                {
                    if (currentB != B.dummy && currentA.item != currentB.item)
                    {
                        currentB = currentB.next;
                    } else if (currentB == B.dummy)
                    {
                        differenceSet2.insert(currentA.item);
                        currentA = currentA.next;
                        currentB = B.dummy.next;
                    } else
                    {
                        currentA = currentA.next;
                        currentB = B.dummy.next;
                    }
                }
            }
        }

        //Update the pointers of the set object being modified to point to the differenceSet
        dummy = differenceSet2.dummy;
        top = dummy;
    }

    //------------------------------------------------------
// print
//
// PURPOSE: It prints out all the items in  the set
// INPUT PARAMETERS:
// setIndex: the index of the Set to be printed
//------------------------------------
    public void print(int setIndex)
    {
        final int NUM_ITEMS_PER_LINE = 10;

        Node currItem = dummy.next;
        Node nextItem = currItem.next;

        int numItems = 0;
        String output = "{";

        //if the current set object is not empty then add a space after the opening brace
        // and then append all the items of the set to the output string
        if (!isEmpty())
        {
            output += " ";


            while (currItem != dummy)
            {
                if (numItems >= NUM_ITEMS_PER_LINE)
                {
                    output += ("\n  ");
                    numItems = 0;
                }

                output += (nextItem == dummy) ? currItem.item + " " : currItem.item + ", ";
                numItems++;

                currItem = currItem.next;
                nextItem = nextItem.next;
            }
        }

        output += "}";

        System.out.println("Printing Set " + setIndex + ": ");
        System.out.println(output + "\n");
    }

    //------------------------------------------------------
// isEmpty
//
// PURPOSE: checks if the set is empty
//------------------------------------
    public boolean isEmpty()
    {
        return dummy.next == top; // Same as dummy.next == dummy
    }

}

