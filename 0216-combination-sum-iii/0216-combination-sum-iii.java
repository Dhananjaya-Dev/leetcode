import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, k, n, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int k, int remain, List<Integer> current, List<List<Integer>> result) {
        // Base case: found a valid combination
        if (current.size() == k && remain == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // Pruning: stop if we exceeded size k or remaining sum is negative
        if (current.size() >= k || remain < 0) {
            return;
        }

        for (int i = start; i <= 9; i++) {
            // Optimization: since numbers increase, no further number can work if i > remain
            if (i > remain) break;
            
            current.add(i);
            backtrack(i + 1, k, remain - i, current, result);
            current.remove(current.size() - 1); // Backtrack
        }
    }
}