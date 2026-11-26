import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            // Go as far left as possible
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            if (--k == 0) {
                return curr.val;
            }

            // Move to the right subtree
            curr = curr.right;
        }

        return -1; // Guaranteed not to reach here per constraints (1 <= k <= n)
    }
}