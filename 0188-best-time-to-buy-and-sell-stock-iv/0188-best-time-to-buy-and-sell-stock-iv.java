class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n <= 1 || k == 0) return 0;
        
        // If k is large enough, this problem reduces to Best Time to Buy and Sell Stock II
        if (k >= n / 2) {
            int maxProfit = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        }
        
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];
        
        Arrays.fill(buy, -prices[0]);
        Arrays.fill(sell, 0);
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                buy[j] = Math.max(buy[j], sell[j - 1] - prices[i]);
                sell[j] = Math.max(sell[j], buy[j] + prices[i]);
            }
        }
        
        return sell[k];
    }
}