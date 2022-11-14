package LeetCode.Medium;

/****************************************
 Given an integer n, return the number of structurally unique BST's (binary search trees)
 which has exactly n nodes of unique values from 1 to n.
 *****************************************/
public class Medium96
{
    public static void main(String[] args)
    {
        System.out.println(numTrees(7));
        System.out.println(numTreesRec(7));
    }

    public static int numTrees(int n)
    {
        int[] numOfUniqueTrees = new int[n + 1];
        numOfUniqueTrees[0] = numOfUniqueTrees[1] = 1;

        for (int i = 2; i <= n; i++)
        {
            for (int j = 1; j <= i; j++)
            {
                numOfUniqueTrees[i] += numOfUniqueTrees[j - 1] * numOfUniqueTrees[i - j];
            }
        }
        return numOfUniqueTrees[n];
    }

    public static int numTreesRec(int n)
    {
        int result = 0;

        if (n <= 1)
        {
            result = 1;
        } else
        {
            for (int i = 1; i <= n; i++)
            {
                result += numTreesRec(i - 1) * numTreesRec(n - i);
            }
        }
        return result;
    }
}
