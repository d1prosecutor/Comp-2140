package Assignments.Assignment2;

public class removeDuplicates
{
    public static void main(String[] args)
    {
        int[] a = {0, 0, 0, 1, 1, 1, 2, 3, 4, 5, 5, 5, 6};
        int b = removeDuplicates(a);
        for (int i = 0; i < a.length; i++)
        {
            System.out.println(a[i]);
        }
    }

    public static int removeDuplicates(int[] nums)
    {
        int count = 0;
        for (int i = 1; i < nums.length; i++)
        {
            if (nums[i] == nums[i - 1])
            {
                count++;
            } else
            {
                nums[i - count] = nums[i];
            }
        }
        return nums.length - count;
    }
}