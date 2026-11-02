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
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        // Step 1: Detect whether a cycle exists using fast and slow pointers
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                // Step 2: Cycle found. Move one pointer to head.
                // Both moving 1 step at a time will meet at the cycle entry.
                ListNode ptr1 = head;
                ListNode ptr2 = slow;

                while (ptr1 != ptr2) {
                    ptr1 = ptr1.next;
                    ptr2 = ptr2.next;
                }

                return ptr1; // Starting node of the cycle
            }
        }

        return null; // No cycle
    }
}