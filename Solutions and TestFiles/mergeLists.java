/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

public class mergeLists()
{
    public ListNode mergeTwoLists(ListNode list1, ListNode list2)
    {
        if (list1 == null)
            return list2;
        else if (list2 == null)
            return list1;

        ListNode top = new ListNode(0,null);
        ListNode mergedList = top;

        while (list1 != null && list2 != null)
        {
            if (list1.val <= list2.val)
            {
                top.next = list1;
                list1 = list1.next;
            } else
            {
                top.next = list2;
                list2 = list2.next;
            }
            top = top.next;
        }

        top.next = list1 == null ? list2 : list1;

        return mergedList.next;
    }
}