class Solution {
    public int maxProduct(int[] nums) {
        int max = nums[0];
        int curMax = nums[0];
        int curMin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];

            // If the current number is negative, swapping min and max
            // accounts for the sign flip when multiplied.
            if (num < 0) {
                int temp = curMax;
                curMax = curMin;
                curMin = temp;
            }

            // Decide whether to extend the previous subarray or start fresh at num
            curMax = Math.max(num, curMax * num);
            curMin = Math.min(num, curMin * num);

            // Update the global maximum
            max = Math.max(max, curMax);
        }

        return max;
    }
}