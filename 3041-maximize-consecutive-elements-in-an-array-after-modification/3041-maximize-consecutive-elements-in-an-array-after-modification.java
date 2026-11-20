import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        
        // dp.get(v) stores the max length of a consecutive sequence ending at value v
        Map<Integer, Integer> dp = new HashMap<>();
        int maxLen = 1;
        
        for (int x : nums) {
            // Option 1: Increment x to x + 1 (extends sequence ending at x)
            int lenPlusOne = dp.getOrDefault(x, 0) + 1;
            
            // Option 2: Keep x as x (extends sequence ending at x - 1)
            int lenAsIs = dp.getOrDefault(x - 1, 0) + 1;
            
            // Update dp[x + 1] first so it doesn't use the updated dp[x] from the same element
            dp.put(x + 1, Math.max(dp.getOrDefault(x + 1, 0), lenPlusOne));
            dp.put(x, Math.max(dp.getOrDefault(x, 0), lenAsIs));
            
            maxLen = Math.max(maxLen, Math.max(dp.get(x), dp.get(x + 1)));
        }
        
        return maxLen;
    }
}