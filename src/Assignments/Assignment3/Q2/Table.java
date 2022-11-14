package Assignments.Assignment3.Q2;

//-----------------------------------------
// NAME: Chukwunaza Chukwuocha
// STUDENT NUMBER: 007928676
// COURSE: COMP 2140, SECTION: A01
// INSTRUCTOR: Cuneyt Ackora
// ASSIGNMENT: Assignment 3 Quesion 2
//
// REMARKS: Implements a table ADT with the functions which are associated with tables
//
//-----------------------------------------

public class Table
{
    // Instance Variables
    private static final int tableSize = 4801; // prime number
    private Node[] hashArray;

    //Table Constructor
    public Table()
    { // create an empty table
        hashArray = new Node[tableSize];

        for (int i = 0; i < tableSize; i++)
        {
            hashArray[i] = null;
        }
    }

    //------------------------------------------------------
// hash
//
// PURPOSE: The driver for the recursive hash function
// INPUT PARAMETERS:
// key: The string value to be hashed into an index
//------------------------------------------------------
    public static int hash(String key)
    {
        //Convert the key and data to lower case to make the spell checker reliable
        key = key.toLowerCase();

        int hashConstant = 13;
        int hashedIndex = -1;

        if (key.length() > 0)
        {
            hashedIndex = hashRec(key, 0, hashConstant);
        }

        return hashedIndex;
    }

    //------------------------------------------------------
// hashRec
//
// PURPOSE: The recursive hash function which converts the given string into an array index within
// the bounds of the table size
// INPUT PARAMETERS:
// key: The string value to be hashed into an array index
// index: The index of the current character if the string being hashed
// hashConst: An arbitrary prime number which is used in the polynomial hash function
//------------------------------------------------------
    private static int hashRec(String key, int index, int hashConst)
    {
        int hashResult = 0;
        if (index < key.length() - 1)
        {
            //Mod the result in each step to avoid integer overflow
            hashResult = ((key.charAt(index) * hashConst) + hashRec(key, index + 1, hashConst)) % tableSize;
        } else if (index == key.length() - 1)
        {
            hashResult = key.charAt(index);
        }

        return hashResult;
    }

    //------------------------------------------------------
// search
//
// PURPOSE: Searches the whole hashTable for a value whose key and data value
// matches the given key and value
// INPUT PARAMETERS:
// key: The string value to be used to search for the data item(Note that in this program, the key
// and data are the same item)
//------------------------------------------------------
    public Boolean search(String key)
    {
        //Convert the key and data to lower case to make the spell checker reliable
        key = key.toLowerCase();

        boolean keyFound = false;

        int keyIndex = hash(key);

        if (keyIndex >= 0 && null != hashArray[keyIndex])
        {
            Node currItem = hashArray[keyIndex];
            while (null != currItem && !keyFound)
            {
                if (currItem.data.equals(key))
                {
                    keyFound = true;
                }
                currItem = currItem.next;
            }
        }

        return keyFound;
    }

    //------------------------------------------------------
// insert
//
// PURPOSE: Inserts a given item into the hash table at the index computed by hashing the given key
// INPUT PARAMETERS:
// key: The string value to be hashed into an array index
// newItem: The item to be inserted into the hash table
//------------------------------------------------------
    public void insert(String newItem, String key)
    {
        //Convert the data to lower case to make the spell checker reliable
        newItem = newItem.toLowerCase();

        int keyIndex = hash(key);

        if (keyIndex >= 0)
        {
            hashArray[keyIndex] = new Node(newItem, hashArray[keyIndex]);
        }
    }


    //------------------------------------------------------
// delete
//
// PURPOSE:deletes a given data from the hashtable
// INPUT PARAMETERS:
// key: The string value to be hashed into an array index (which returns where to search for the item to
// be deleted from the hashtable)
//------------------------------------------------------
    public void delete(String key)
    {
        int keyIndex = hash(key);

        if (keyIndex >= 0 && null != hashArray[keyIndex])
        {
            Node currItem = hashArray[keyIndex];
            Node prevItem = null;
            while (null != currItem && !currItem.data.equals(key))
            {
                prevItem = currItem;
                currItem = currItem.next;
            }

            if (null != currItem)
            {
                if (null != prevItem)
                {
                    prevItem.next = currItem.next;
                } else
                {
                    hashArray[keyIndex] = currItem.next;
                }
            }
        }
    }


    //------------------------------------------------------
// Private Node class
//
// PURPOSE: Creates the node objects which will holds the tables items
//------------------------------------------------------
    private class Node
    {
        public Node next; //the next node
        public String data;

        /******************************************************
         * Constructor
         *
         * Purpose: create a new node
         * Input: the data to be stored and link to the next node
         ********************************************************/
        public Node(String newdata, Node newlink)
        {
            data = newdata;
            next = newlink;
        }

    }//end Node Class

}// end Table Class

