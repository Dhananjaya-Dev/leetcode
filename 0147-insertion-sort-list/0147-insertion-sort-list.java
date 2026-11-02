/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode lastSorted = head; // Last node of the sorted portion
        ListNode curr = head.next;  // Node currently being considered for insertion

        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                // Already in correct relative order; extend the sorted portion
                lastSorted = lastSorted.next;
            } else {
                // Find where to insert curr starting from dummy
                ListNode prev = dummy;
                while (prev.next.val < curr.val) {
                    prev = prev.next;
                }

                // Remove curr from its current position and insert it after prev
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }

            // Move to the next unsorted node
            curr = lastSorted.next;
        }

        return dummy.next;
    }
}