class Solution {
    public int singleNumber(int[] nums) {
        int ones = 0;
        int twos = 0;

        for (int num : nums) {
            // Add num to ones if it is not already in twos
            ones = (ones ^ num) & ~twos;
            // Add num to twos if it is now removed from ones
            twos = (twos ^ num) & ~ones;
        }

        return ones;
    }
}