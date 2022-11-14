package Assignments.Assignment4;

public class Tree
{
    //Instance variable of the BST - root of the tree
    private TreeNode root;

    /*********************************************************
     * BST Constructor
     *
     * Purpose: creates an empty tree.
     *********************************************************/
    public Tree()
    {
        root = null;
    }

    /********************************************************
     * search
     *
     * Purpose: searches for the node with a specified data
     * Input: data is the value to be searched for.
     ********************************************************/
    public boolean search(String data)
    {
        return searchRec(data, root);
    }


    /********************************************************
     * insert
     *
     * Purpose: insert a node into the BST.
     * Input: data is content of the node to be inserted.
     ********************************************************/
    public void insert(String data)
    {
        //If the tree is empty just insert at the root
        if (null == root)
        {
            root = new TreeNode(data);
        } else
        {
            //Tree is not empty, look for the correct position and insert there
            insertRec(data, null, root);
        }
    }

    /********************************************************
     * deleteEasyCase
     *
     * Purpose: delete a node with 0 children or 1 child,
     *          where the node is not the root.
     * Input: keyNode is the node to be deleted, and
     *        keyParent is the parent of keyNode.
     ********************************************************/
    public void deleteEasyCase(TreeNode keyNode, TreeNode keyParent)
    {
        if (keyNode.left == null)
        {
            // Replace keyNode with its right child.
            // (Also handles case when both children are null.)

            if (keyNode == keyParent.left)
                keyParent.left = keyNode.right;
            else
                keyParent.right = keyNode.right;
        } else
        {
            // Replace keyNode with its left child.

            if (keyNode == keyParent.left)
                keyParent.left = keyNode.left;
            else
                keyParent.right = keyNode.left;
        }
    } // end deleteEasyCase


    /********************************************************
     * deleteEasyCaseRoot
     *
     * Purpose: delete the root when it has 0 children or 1 child.
     ********************************************************/
    public void deleteRoot()
    {
        if (root.left == null)
        {
            // Replace root with its right child.
            // (Also handles case when both children are null.)

            root = root.right;
        } else
        {
            // Replace root with its left child.

            root = root.left;
        }
    } // end deleteEasyCaseRoot


    /********************************************************
     * deleteTwoChildren
     *
     * Purpose: delete a node with 2 children.
     * Input: keyNode is the node to be deleted.
     ********************************************************/
    public void deleteTwoChildren(TreeNode keyNode)
    {
        TreeNode isParent; // Parent of the inorder successor.
        TreeNode is;       // The inorder successor of keyNode.

        // First, find the inorder successor of keyNode.
        // Inorder successor is the leftmost descendant
        // of the right child of keyNode.
        isParent = keyNode;
        is = keyNode.right;

        while (null != is.left)
        {
            isParent = is;
            is = is.left;
        }

        // Copy the inorder successor's value into keyNode.
        keyNode.word = is.word;


        // Now delete the inorder successor.
        // (The inorder successor is guaranteed to have
        // at most one child and the inorder successor
        // is also not the root, so its deletion is
        // an easy case.)
        deleteEasyCase(is, isParent);

    } // end method deleteTwoChildren

    /************************************************************
     * print
     *
     * Purpose: public driver for the printInorder method.
     *          (If the tree is OK, this should give a sorted
     *          ordering of the values stored in the tree.)
     ************************************************************/
    public void print()
    {
        if (null != root)
        {
            printInorder(root);
        }
    }


    /******************************************************
     Private Recursive Functions
     ******************************************************/

    /************************************************************
     * printInorder
     *
     * Purpose: print out the tree using an inorder traversal.
     *          (If the tree is OK, this should give a sorted
     *          ordering of the values stored in the tree.)
     ************************************************************/
    private void printInorder(TreeNode curr)
    {
        if (null != curr)
        {
            printInorder(curr.left);
            System.out.println(curr.word);
            printInorder(curr.right);
        }
    } // end method printInorder

    /********************************************************
     * searchRec
     *
     * Purpose: private helper method to search for the node containing the specified data.
     * Input Parameters:
     * data: content of the node to be searched for.
     * curr: The current node being walked over.
     ********************************************************/
    private boolean searchRec(String data, TreeNode curr)
    {
        boolean dataFound = false;
        if (null != curr)
        {
            if (curr.word.equals(data))
            {
                dataFound = true;
            } else if (curr.word.compareTo(data) <= 0)
            {
                dataFound = searchRec(data, curr.right);
            } else
            {
                dataFound = searchRec(data, curr.left);
            }
        }
        return dataFound;
    }

    /********************************************************
     * insertRec
     *
     * Purpose: private helper method to insert a node into the BST recursively.
     * Input Parameters:
     * data: content of the node to be inserted.
     * prev: The parent of the current node being walked over.
     * curr: The current node being walked over.
     ********************************************************/
    private void insertRec(String data, TreeNode prev, TreeNode curr)
    {
        if (null == curr)
        {
            TreeNode newNode = new TreeNode(data);
            if (prev.word.compareTo(data) < 0)
            {
                prev.right = newNode;
            } else
            {
                prev.left = newNode;
            }
        } else
        {
            if (curr.word.compareTo(data) <= 0)
            {
                insertRec(data, curr, curr.right);
            } else
            {
                insertRec(data, curr, curr.left);

            }
        }
    }

    /********************************
     * Because why not
     *******************************/
//    private void insertIte(String data)
//    {
//        TreeNode prev = null;
//        TreeNode curr = root;
//        TreeNode newNode = new TreeNode(data);
//
//        if (null == curr)
//        {
//            root = newNode;
//        } else
//        {
//            while (null != curr)
//            {
//                prev = curr;
//                if (curr.word.compareTo(data) < 0)
//                {
//                    curr = curr.right;
//                } else
//                {
//                    curr = curr.left;
//                }
//            }
//
//            if (prev.word.compareTo(data) < 0)
//            {
//                prev.right = newNode;
//            } else
//            {
//                prev.left = newNode;
//            }
//        }
//    }

    /******************************************************************************
     Inner class for the nodes of the tree
     *******************************************************************************/
    private class TreeNode
    {
        public String word;
        public TreeNode right;
        public TreeNode left;

        /******************************************************
         * Constructor
         *
         * Purpose: creates a new node with a given value
         *          (a leaf node essentially)
         * Input: the new word to be contained by the new node
         ******************************************************/
        public TreeNode(String newWord)
        {
            word = newWord;
            right = left = null;
        }
    }
}
