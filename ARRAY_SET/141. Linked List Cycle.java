/**
 * Given a linked list, determine if it has a cycle in it.
 */

/*
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

//////////////////
// Two Pointers //
//////////////////

public class Solution {
    /* If "slow" and "fast" collide, we then have a cycle */    
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }    

        ListNode slow = head; /* Moves 1 node at a time */
        ListNode fast = head; /* Moves 2 nodes at a time */

        /* Work both for odd and even number of Nodes; Remember it's && */
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }
}

////////////////////
// More Intuitive //
////////////////////

public boolean hasCycle(ListNode head) {
    /* Check both */ 
    if (head == null || head.next == null) {
        return false;
    }
    /* One step ahead */
    ListNode slow = head;
    ListNode fast = head.next;
    while (slow != fast) {
        if (fast == null || fast.next == null) {
            return false;
        }
        slow = slow.next;
        fast = fast.next.next;
    }
    return true;
}