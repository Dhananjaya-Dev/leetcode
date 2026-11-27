class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 1500;
        
        // Count how many items have each factor value
        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }
        
        // Precompute number of items whose factor is a multiple of f
        int[] multiplesCount = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            for (int m = f; m <= maxFactor; m += f) {
                multiplesCount[f] += freq[m];
            }
        }
        
        int[] dp = new int[budget + 1];
        
        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            if (price > budget) continue;
            
            // Free copies exclude the purchased item itself (j != i)
            int firstCopyGain = multiplesCount[factor]; 
            
            // 1. First copy (0/1 knapsack step: iterate backwards)
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + firstCopyGain);
            }
            
            // 2. Additional copies (unbounded knapsack step: iterate forwards)
            for (int b = price; b <= budget; b++) {
                dp[b] = Math.max(dp[b], dp[b - price] + 1);
            }
        }
        
        int maxItems = 0;
        for (int b = 0; b <= budget; b++) {
            maxItems = Math.max(maxItems, dp[b]);
        }
        return maxItems;
    }
}