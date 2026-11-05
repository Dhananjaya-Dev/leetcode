class Solution {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int minVal = nums[0];
        int maxVal = nums[0];
        for (int num : nums) {
            minVal = Math.min(minVal, num);
            maxVal = Math.max(maxVal, num);
        }

        int n = nums.length;
        // Minimum possible gap
        int bucketSize = Math.max(1, (maxVal - minVal) / (n - 1));
        int bucketCount = (maxVal - minVal) / bucketSize + 1;

        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];
        boolean[] bucketHasVal = new boolean[bucketCount];

        for (int num : nums) {
            int idx = (num - minVal) / bucketSize;
            if (!bucketHasVal[idx]) {
                bucketMin[idx] = num;
                bucketMax[idx] = num;
                bucketHasVal[idx] = true;
            } else {
                bucketMin[idx] = Math.min(bucketMin[idx], num);
                bucketMax[idx] = Math.max(bucketMax[idx], num);
            }
        }

        int maxGap = 0;
        int previousMax = minVal;
        for (int i = 0; i < bucketCount; i++) {
            if (!bucketHasVal[i]) {
                continue;
            }
            maxGap = Math.max(maxGap, bucketMin[i] - previousMax);
            previousMax = bucketMax[i];
        }

        return maxGap;
    }
}