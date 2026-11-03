class Solution {
    public int minMoves(int[] nums) {
        int maxVal = nums[0];
        int sum = 0;

        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
            sum += num;
        }

        return (nums.length * maxVal) - sum;
    }
}