import java.util.Arrays;
import java.util.TreeMap;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] windowCost = new long[n - x + 1];
        
        WindowMedianTracker tracker = new WindowMedianTracker(x);
        for (int i = 0; i < n; i++) {
            tracker.add(nums[i]);
            if (i >= x) {
                tracker.remove(nums[i - x]);
            }
            if (i >= x - 1) {
                windowCost[i - x + 1] = tracker.getCost();
            }
        }
        
        // dp[j][i] = min cost to pick j subarrays from the first i elements
        // Memory optimization: previous DP row dp[j - 1] and current DP row dp[j]
        long[] prevDp = new long[n + 1];
        long[] currDp = new long[n + 1];
        
        long INF = (long) 1e16;
        Arrays.fill(prevDp, 0); // 0 subarrays cost 0
        
        for (int j = 1; j <= k; j++) {
            Arrays.fill(currDp, INF);
            for (int i = j * x; i <= n; i++) {
                // Option 1: Do not place a subarray ending at index i - 1
                currDp[i] = currDp[i - 1];
                
                // Option 2: Place a subarray ending at index i - 1 (starts at i - x)
                if (prevDp[i - x] != INF) {
                    currDp[i] = Math.min(currDp[i], prevDp[i - x] + windowCost[i - x]);
                }
            }
            // Swap rows
            long[] temp = prevDp;
            prevDp = currDp;
            currDp = temp;
        }
        
        return prevDp[n];
    }

    private static class WindowMedianTracker {
        private final int x;
        private final int leftTargetSize;
        private final TreeMap<Integer, Integer> left = new TreeMap<>();
        private final TreeMap<Integer, Integer> right = new TreeMap<>();
        private int leftSize = 0;
        private int rightSize = 0;
        private long leftSum = 0;
        private long rightSum = 0;

        public WindowMedianTracker(int x) {
            this.x = x;
            this.leftTargetSize = (x + 1) / 2;
        }

        public void add(int val) {
            if (left.isEmpty() || val <= left.lastKey()) {
                addMap(left, val);
                leftSize++;
                leftSum += val;
            } else {
                addMap(right, val);
                rightSize++;
                rightSum += val;
            }
            balance();
        }

        public void remove(int val) {
            if (!left.isEmpty() && val <= left.lastKey() && left.containsKey(val)) {
                removeMap(left, val);
                leftSize--;
                leftSum -= val;
            } else {
                removeMap(right, val);
                rightSize--;
                rightSum -= val;
            }
            balance();
        }

        public long getCost() {
            int median = left.lastKey();
            if (x % 2 == 1) {
                return rightSum - leftSum + median;
            } else {
                return rightSum - leftSum;
            }
        }

        private void balance() {
            while (leftSize > leftTargetSize) {
                int maxLeft = left.lastKey();
                removeMap(left, maxLeft);
                leftSize--;
                leftSum -= maxLeft;

                addMap(right, maxLeft);
                rightSize++;
                rightSum += maxLeft;
            }
            while (leftSize < leftTargetSize && rightSize > 0) {
                int minRight = right.firstKey();
                removeMap(right, minRight);
                rightSize--;
                rightSum -= minRight;

                addMap(left, minRight);
                leftSize++;
                leftSum += minRight;
            }
        }

        private void addMap(TreeMap<Integer, Integer> map, int key) {
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        private void removeMap(TreeMap<Integer, Integer> map, int key) {
            int count = map.get(key);
            if (count == 1) {
                map.remove(key);
            } else {
                map.put(key, count - 1);
            }
        }
    }
}