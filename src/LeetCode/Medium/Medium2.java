package LeetCode.Medium;

/********************************************888****************
 You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order,
 and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 example:  Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 Output: [8,9,9,9,0,0,0,1]
 **************************************************************/

public class Medium2
{
    public class ListNode
    {
        int val;
        ListNode next;

        ListNode()
        {
        }

        ListNode(int val)
        {
            this.val = val;
        }

        ListNode(int val, ListNode next)
        {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        return (addTwoNumbersRec(l1, l2, 0));

    }

    public ListNode addTwoNumbersRec(ListNode l1, ListNode l2, int carryOver)
    {
        ListNode result = null;
        if (null == l1 && null == l2)
        {
            if (carryOver > 0)
                result = new ListNode(carryOver);
        } else
        {
            if (null != l1 && null != l2)
            {
                result = new ListNode((l1.val + l2.val + carryOver) % 10,
                        addTwoNumbersRec(l1.next, l2.next, (l1.val + l2.val + carryOver) / 10));
            } else if (null != l1)
            {
                result = new ListNode((l1.val + carryOver) % 10,
                        addTwoNumbersRec(l1.next, l2, (l1.val + carryOver) / 10));


            } else
            {
                result = new ListNode((l2.val + carryOver) % 10,
                        addTwoNumbersRec(l1, l2.next, (l2.val + carryOver) / 10));
            }
        }
        return result;
    }
}
