class Solution {
    public int minMoves(int[] nums) {
        int min = nums[0];
        long sum = 0;
        
        for (int x : nums) {
            if (x < min) {
                min = x;
            }
            sum += x;
        }
        
        return (int) (sum - (long) min * nums.length);
    }
}