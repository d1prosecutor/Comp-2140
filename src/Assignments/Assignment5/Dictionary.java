package Assignments.Assignment5;

/*****************************************************************************
 * NAME: Chukwunaza Chukwuocha
 * STUDENT NUMBER: 007928676
 * COURSE: COMP 2140, SECTION: A01
 * INSTRUCTOR: Cuneyt Ackora
 * ASSIGNMENT: Assignment 5
 *
 * REMARKS: Implements a 2-3Tree ADT to store A dictionary used in SpellChecking
 *****************************************************************************/

import java.io.*;
import java.util.*;

// This is my Tree ADT that will hold my list of dictionary words.
public class Dictionary
{
    /*******************************************************************************
     Attributes
     *******************************************************************************/
    private TwoThreeNode root;

    /******************************************************************************
     Public Methods
     *******************************************************************************/

    /********************************************************
     * Dictionary constructor
     *
     * Purpose: reads in the dictionary file so once the object
     * is created we're ready to start the spell check
     * Input Parameters:
     * fileName: the name of the dictionary file
     ********************************************************/
    public Dictionary()
    {
    }

    public Dictionary(String fileName)
    {
        String read_word;

        // read the words into the tree
        try
        {
            Scanner theFile = new Scanner(new File(fileName));

            System.out.print("Loading dictionary");

            // each line contains 1 word...
            while (theFile.hasNextLine())
            {
                // get the next word to process
                read_word = theFile.nextLine();

                System.out.print(".");

                // always work in lower case
                read_word = read_word.toLowerCase();


                // Insert the new node to the Tree
                insert(read_word);
            }

            System.out.println();

            theFile.close();
        } catch (FileNotFoundException ex)
        {
            System.out.println("Could not find " + fileName + ". Check the file name and try again.");
        }
    }

    /********************************************************
     * lookup
     *
     * Purpose: searches for the node with a specified data
     *
     * Input Parameters:
     * lSearch: the value to be searched for.
     *
     * Output Parameters:
     * dataFound: a boolean representing if the Node containing the data was found or not
     ********************************************************/
    public boolean lookup(String lSearch)
    {
        // search for the word in the tree in lower case to ensure reliability
        lSearch = lSearch.toLowerCase();
        boolean dataFound = false;

        TwoThreeNode curr = root;

        if (null != curr)
        {
            while (null != curr.child)
            {
                curr = curr.goToChild(lSearch);
            }
            dataFound = lSearch.equals(curr.key[0]);
        }

        return dataFound;
    }

    /********************************************************
     * insert
     *
     * Purpose: insert a node into the BST.
     * data: content of the node to be inserted.
     ********************************************************/
    public void insert(String data)
    {
        //If the tree is empty just make a new Node the root of the tree
        if (null == root)
        {
            //Tree is empty, the root should be the only Node after insertion
            root = new TwoThreeNode(data, null);
        } else if (null == root.child)
        {
            //Tree contains only root, to insert one more child, create a new root index node
            // and insert the previous root and the new item as children

            String rootData = root.key[0];
            TwoThreeNode leftChild;
            TwoThreeNode rightChild;

            //Update the root index with the maximum value between the previous root and its current sibling
            if (data.compareTo(rootData) < 0)
            {
                //update root index as the previous root node
                leftChild = new TwoThreeNode(data, null);
                rightChild = new TwoThreeNode(rootData, null);

                root = new TwoThreeNode(rootData, root.parent, leftChild, rightChild);

                leftChild.setParent(root);
                rightChild.setParent(root);
            } else
            {
                //update root index as the new node being inserted
                leftChild = new TwoThreeNode(rootData, null);
                rightChild = new TwoThreeNode(data, null);

                root = new TwoThreeNode(data, root.parent, leftChild, rightChild);

                leftChild.setParent(root);
                rightChild.setParent(root);
            }
        } else
        {
            //if there already exists a tree with at least 2 leaf Nodes
            //Check for the position to insert the new data if it doesn't already exist
            if (!lookup(data))
            {
                //First of all go to the leaves to find where to insert using goToChild function
                TwoThreeNode lSearch = root;
                if (null != lSearch)
                {
                    while (null != lSearch.child)
                    {
                        lSearch = lSearch.goToChild(data);
                    }
                }

                //If the position to insert the new node already has 2 nodes as children /
                //insert the third child and update the parent index
                if (lSearch.parent.getChildCount() == 2)
                {
                    lSearch.parent.sortAndInsert3Children(data);
                } else if (lSearch.parent.getChildCount() == 3)
                {
                    //if there already exists 3 children at the spot to insert
                    //insert the new node then insert and push up that branch
                    lSearch.splitAndPushUp(data, null, null);
                }
            }
        }
    }


    /******************************************************************************
     Inner class for the nodes of the 2-3 tree
     *******************************************************************************/
    private class TwoThreeNode
    {
        /******************************************************************************
         Instance Variables
         *******************************************************************************/
        public TwoThreeNode parent;
        public TwoThreeNode[] child;
        String[] key;
        int numIndexValues;
        Boolean isLeaf;


        /******************************************************
         * Constructor
         *
         * Purpose: creates a new leaf node with a given value
         *
         * Input Parameters:
         * dataItem: The data to be contained in the leaf
         * parentNode: The parent of the leaf node
         ******************************************************/
        public TwoThreeNode(String dataItem, TwoThreeNode parentNode)
        {
            parent = parentNode;
            key = new String[1];
            key[0] = dataItem;
            numIndexValues = 1;
            child = null;
            isLeaf = true;
        }

        /******************************************************
         * Constructor
         *
         * Purpose: creates a new interior node which will hold the indices for the 2-3 Tree
         *
         * Input Parameters:
         * indexString: The index to be contained in the index Node
         * parentNode: The parent of the index Node
         * leftChild: The left child of the index Node
         * rightChild: The right Child of the index Node
         ******************************************************/
        public TwoThreeNode(String indexString, TwoThreeNode parentNode, TwoThreeNode leftChild, TwoThreeNode rightChild)
        {
            parent = parentNode;
            key = new String[2];
            key[0] = indexString;
            numIndexValues = 1;
            child = new TwoThreeNode[3];
            child[0] = leftChild;
            child[1] = rightChild;
            isLeaf = false;
        }

        /******************************************************************************************
         * goToChild
         *
         * Purpose: Goes to the node specified as lSearch which contains the data being searched for
         *
         * Input Parameters:
         * lSearch: The data being searched for
         *
         * Output Parameters:
         * The output is the node which contains the data being searched for
         *******************************************************************************************/
        public TwoThreeNode goToChild(String lSearch)
        {
            int childIndex = numIndexValues - 1;

            while (childIndex >= 0 && lSearch.compareTo(key[childIndex]) < 0)
            {
                childIndex--;
            }
            return child[childIndex + 1];
        }

        /***************************************************************
         * sortAndInsert3Children
         *
         * Purpose: Inserts a new node into a spot containing 2 children.
         * *****    Sorts the 3 sibling nodes in the correct order
         *
         * Input Parameters:
         * data: The data of the new Node to be inserted
         ***************************************************************/
        public void sortAndInsert3Children(String data)
        {
            int i = numIndexValues;

            //Uses a sorting algorithm to sort the siblings in order to
            //insert the new node in the correct location
            while (i >= 0 && data.compareTo(child[i].key[0]) < 0)
            {
                child[i + 1] = new TwoThreeNode(child[i].key[0], this);
                i--;
            }

            //Insert the new node
            child[i + 1] = new TwoThreeNode(data, this);

            //Update the indices contained in the parent Node which was just inserted into
            String smallestIndex = child[1].key[0];
            String largestIndex = child[2].key[0];
            update2NodeParentIndex(smallestIndex, largestIndex);
        }

        /**********************************************************************************************
         * update2NodeParentIndex
         *
         * Purpose: Updates the indices contained in an interior node previously containing 2 children
         * *****    when one more child is inserted (making it 3 children)
         *
         * Input Parameters:
         * data: The data of the new Node to be inserted
         **********************************************************************************************/
        public void update2NodeParentIndex(String firstIndex, String secondIndex)
        {
            key[0] = firstIndex;
            key[1] = secondIndex;

            numIndexValues++;
        }

        /**********************************************************************************************
         * splitAndPushUp
         *
         * Purpose: Updates the indices contained in an interior node previously containing 2 children
         * *****    when one more child is inserted (making it 3 children)
         *
         * Input Parameters:
         * newData: The data of the new Node to be inserted
         * leftChild: The left Child of the interior parent node
         * rightChild: The right Child of the interior parent node
         **********************************************************************************************/
        public void splitAndPushUp(String newData, TwoThreeNode leftChild,
                                   TwoThreeNode rightChild)
        {
            if (null == parent)
            {
                root = new TwoThreeNode(newData, null, leftChild, rightChild);
                leftChild.setParent(root);
                rightChild.setParent(root);
            } else if (parent.getChildCount() == 2)
            {
                boolean isRightPosition;
                if (this == parent.child[0])
                {
                    isRightPosition = false;
                    parent.update2NodeParentLink(isRightPosition, leftChild, rightChild, newData);
                } else if (this == parent.child[1])
                {
                    isRightPosition = true;
                    parent.update2NodeParentLink(isRightPosition, leftChild, rightChild, newData);
                }

            } else if (parent.getChildCount() == 3)
            {
                if (this.isLeaf)
                {
                    TwoThreeNode[] tempChildren = new TwoThreeNode[parent.child.length + 1];
                    System.arraycopy(parent.child, 0, tempChildren, 0, parent.child.length);

                    //First sort the 4 Children to decide how to split them
                    parent.insertAndSortOverFlowLeaf(tempChildren, newData);

                    //Store the previous parent's indices to figure out which one should be pushed up
                    String prevParentFirstIndex = parent.key[0];
                    String prevParentLastIndex = parent.key[1];

                    //Get the index which should be pushed up
                    String indexToPush = indexToPush(newData, prevParentFirstIndex, prevParentLastIndex,
                            tempChildren[1].key[0], tempChildren[3].key[0]);

                    //Keep a copy of previous Parent before the parent is updated
                    // (to avoid losing the link to the original parent))
                    TwoThreeNode prevParent = parent;

                    /********************************************************
                     Now split the two Interior Nodes to have 2 children each
                     *******************************************************/

                    //Set the first parent
                    TwoThreeNode newParentFirstIndex = new TwoThreeNode(tempChildren[1].key[0], parent.parent,
                            tempChildren[0], tempChildren[1]);

                    //Update the parents of the first two children
                    tempChildren[0].setParent(newParentFirstIndex);
                    tempChildren[1].setParent(newParentFirstIndex);

                    //Set the second parent
                    TwoThreeNode newParentSecondIndex = new TwoThreeNode(tempChildren[3].key[0], parent.parent,
                            tempChildren[2], tempChildren[3]);

                    //Update the parents of the second two children
                    tempChildren[2].setParent(newParentSecondIndex);
                    tempChildren[3].setParent(newParentSecondIndex);

                    //Keep splitting and pushing up while there is still work left to do to balance the tree
                    prevParent.splitAndPushUp(indexToPush, newParentFirstIndex, newParentSecondIndex);
                } else
                {
                    int numItems = parent.child.length - 1;
                    TwoThreeNode[] tempInteriorChildren = new TwoThreeNode[parent.child.length + 1];

                    int childIndex = getChildIndex();
                    int tempInteriorChildrenCounter = 0;
                    int i = 0;
                    while (i < parent.child.length && tempInteriorChildrenCounter < numItems)
                    {
                        if (i != childIndex)
                        {
                            tempInteriorChildren[tempInteriorChildrenCounter++] = parent.child[i];
                        }
                        i++;
                    }

                    //Insert the left and right children one after the other in sorted order
                    parent.insertAndSortOverFlowIndex(tempInteriorChildren, leftChild, numItems++);
                    parent.insertAndSortOverFlowIndex(tempInteriorChildren, rightChild, numItems);

                    //Store the previous parent's indices to figure out which one should be pushed up
                    String prevParentFirstIndex = parent.key[0];
                    String prevParentLastIndex = parent.key[1];

                    //Get the index which should be pushed up among the previous parent's indices and the new
                    // index
                    ArrayList<String> indices = interiorIndexToPush(newData, prevParentFirstIndex, prevParentLastIndex);
                    String indexToPush = indices.get(1);

                    //Keep a copy of previous Parent before the parent is updated
                    // (to avoid losing the link to the original parent))
                    TwoThreeNode prevParent = parent;

                    /********************************************************
                     Now split the two Interior Nodes to have 2 children each
                     *******************************************************/

                    //Set the first parent
                    TwoThreeNode newParentFirstIndex = new TwoThreeNode(indices.get(0), parent.parent,
                            tempInteriorChildren[0], tempInteriorChildren[1]);

                    //Update the parents of the first two children
                    tempInteriorChildren[0].setParent(newParentFirstIndex);
                    tempInteriorChildren[1].setParent(newParentFirstIndex);

                    //Set the second parent
                    TwoThreeNode newParentSecondIndex = new TwoThreeNode(indices.get(2), parent.parent,
                            tempInteriorChildren[2], tempInteriorChildren[3]);

                    //Update the parents of the second two children
                    tempInteriorChildren[2].setParent(newParentSecondIndex);
                    tempInteriorChildren[3].setParent(newParentSecondIndex);

                    //Keep splitting and pushing up while there is still work left to do to balance the tree
                    prevParent.splitAndPushUp(indexToPush, newParentFirstIndex, newParentSecondIndex);
                }
            }

        }

        //Inserts a 4th child thereby causing the Node to overfilled
        //It also sorts these 4 children so that the splitting can be easy
        public void insertAndSortOverFlowLeaf(TwoThreeNode[] unSortedChildren, String newChild)
        {
            int i = numIndexValues;
            while (i >= 0 && newChild.compareTo(unSortedChildren[i].key[0]) < 0)
            {
                unSortedChildren[i + 1] = new TwoThreeNode(child[i].key[0], this);
                i--;
            }
            unSortedChildren[i + 1] = new TwoThreeNode(newChild, this);
        }

        public void insertAndSortOverFlowIndex(TwoThreeNode[] unSortedChildren, TwoThreeNode newChild, int numItems)
        {
            int i = numItems - 1;
            while (i >= 0 && newChild.key[0].compareTo(unSortedChildren[i].key[0]) < 0)
            {
                unSortedChildren[i + 1] = unSortedChildren[i];
                i--;
            }
            unSortedChildren[i + 1] = newChild;
            unSortedChildren[i + 1].setParent(this);
        }


        //Returns the index which should be pushed up
        public String indexToPush(String newData, String... indices)
        {
            String pushIndex = "";
            ArrayList<String> indexList = new ArrayList<String>();

            for (String index : indices)
            {
                if (!indexList.contains(index))
                {
                    indexList.add(index);
                }
            }

            Collections.sort(indexList);
            if (indexList.size() == 2)
            {
                pushIndex = newData;
            } else if (indexList.size() == 3)
            {
                pushIndex = indexList.get(1);
            }

            return pushIndex;
        }

        public ArrayList<String> interiorIndexToPush(String... indices)
        {
            ArrayList<String> indexList = new ArrayList<String>();

            for (String index : indices)
            {
                if (!indexList.contains(index))
                {
                    indexList.add(index);
                }
            }

            Collections.sort(indexList);

            return indexList;
        }

        //Joins the split children to the parent which was not previously full (contained 1 indexNode/2Children)
        //Makes that parent full(Contain 3 children and 2 indices)
        public void update2NodeParentLink(boolean isRightChild, TwoThreeNode firstChild, TwoThreeNode secondChild, String newIndex)
        {
            if (isRightChild)
            {
                child[1] = firstChild;
                child[2] = secondChild;
                update2NodeParentIndex(key[0], newIndex);
            } else
            {
                //Move the Right Child one step to the right when you're splitting the left child
                //So there is space to insert the 2 children recently split
                child[2] = child[1];
                child[0] = firstChild;
                child[1] = secondChild;

                //Also shift the only index it contains one step to the right to insert the new(smaller index)
                key[1] = key[0];
                update2NodeParentIndex(newIndex, key[1]);
            }

        }

        public int getChildIndex()
        {
            int childIndex = -1;

            for (int i = 0; i < parent.child.length; i++)
            {
                if (this == parent.child[i])
                {
                    childIndex = i;
                }
            }
            return childIndex;
        }

        public void setParent(TwoThreeNode newParent)
        {
            parent = newParent;
        }

        public int getChildCount()
        {

            int numChildren = 0;

            if (!isLeaf)
            {
                numChildren = numIndexValues + 1;
            }

            return numChildren;
        }

    }
}