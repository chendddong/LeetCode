/**
 * Given a linked list, return the node where the cycle begins. If there is no
 * cycle, return null.
 */

/*
    Note: Do not modify the linked list.

    Follow up:
    Can you solve it without using extra space?
 */

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        /* Limit the list longer than two */
        if (head == null || head.next == null) {
            return null;
        }

        /* Set the 'pointer' */
        ListNode slow = head;
        ListNode fast = head;
        
        /* Find the cycle */
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        /* !! We have to do the error check first */
        if (fast == null || fast.next == null) {
            return null;
        }

        /* Set the slow back to the head both pointers move at the same step */
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
    }
}