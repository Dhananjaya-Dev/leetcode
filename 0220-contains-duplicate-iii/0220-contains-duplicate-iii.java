import java.util.*;

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        if (valueDiff < 0) return false;

        Map<Long, Long> buckets = new HashMap<>();
        long width = (long) valueDiff + 1;

        for (int i = 0; i < nums.length; i++) {
            long num = (long) nums[i];
            long bucketId = getBucketId(num, width);

            // Check the current bucket
            if (buckets.containsKey(bucketId)) {
                return true;
            }
            // Check the left adjacent bucket
            if (buckets.containsKey(bucketId - 1) && num - buckets.get(bucketId - 1) <= valueDiff) {
                return true;
            }
            // Check the right adjacent bucket
            if (buckets.containsKey(bucketId + 1) && buckets.get(bucketId + 1) - num <= valueDiff) {
                return true;
            }

            // Add current number to its bucket
            buckets.put(bucketId, num);

            // Maintain the sliding window of size indexDiff
            if (i >= indexDiff) {
                long oldBucketId = getBucketId((long) nums[i - indexDiff], width);
                buckets.remove(oldBucketId);
            }
        }

        return false;
    }

    private long getBucketId(long num, long width) {
        // Shift by 10^9 so all numbers become non-negative before integer division
        return (num + 1_000_000_000L) / width;
    }
}