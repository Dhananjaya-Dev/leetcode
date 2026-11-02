/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        // Step 1: Create a clone of each node and insert it right after the original node
        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }

        // Step 2: Assign random pointers for the cloned nodes
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        // Step 3: Separate the original and copied lists
        curr = head;
        Node dummyHead = new Node(0);
        Node copyTail = dummyHead;

        while (curr != null) {
            Node copy = curr.next;
            curr.next = copy.next;
            
            copyTail.next = copy;
            copyTail = copy;

            curr = curr.next;
        }

        return dummyHead.next;
    }
}