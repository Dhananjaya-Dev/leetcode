class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int prev1 = 0; // Max money robbed up to house i - 1
        int prev2 = 0; // Max money robbed up to house i - 2
        
        for (int num : nums) {
            int current = Math.max(prev1, prev2 + num);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
}