class Solution {
    public int minCut(String s) {
        int n = s.length();
        if (n <= 1) return 0;

        // isPal[i][j] will be true if substring s[i..j] is a palindrome
        boolean[][] isPal = new boolean[n][n];

        // dp[i] stores the minimum cuts needed for substring s[0..i]
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            // Maximum cuts needed for s[0..i] is i (cutting every single character)
            int minCuts = i;

            for (int j = 0; j <= i; j++) {
                // Check if substring s[j..i] is a palindrome
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || isPal[j + 1][i - 1])) {
                    isPal[j][i] = true;

                    // If s[0..i] is itself a palindrome, no cuts are needed
                    if (j == 0) {
                        minCuts = 0;
                    } else {
                        // Otherwise, cut before j and take min cuts of s[0..j-1] + 1
                        minCuts = Math.min(minCuts, dp[j - 1] + 1);
                    }
                }
            }

            dp[i] = minCuts;
        }

        return dp[n - 1];
    }
}