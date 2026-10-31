class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        // dp[r][c][dir]: number of ways to reach cell (r, c) facing direction `dir`
        // dir = 0: arrived moving Right
        // dir = 1: arrived moving Down
        int[][][] dp = new int[m][n][2];

        // Base case: at start cell (0, 0), which is empty (0),
        // we can move Right to (0, 1) and Down to (1, 0).
        if (n > 1) {
            dp[0][1][0] = (dp[0][1][0] + 1) % MOD;
        }
        if (m > 1) {
            dp[1][0][1] = (dp[1][0][1] + 1) % MOD;
        }

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (r == 0 && c == 0) continue;
                if (r == m - 1 && c == n - 1) continue;

                if (grid[r][c] == 1) {
                    // Mirror cell reflections:
                    // 1. Entering Right (dir = 0) -> turned Down to (r + 1, c)
                    if (dp[r][c][0] > 0 && r + 1 < m) {
                        dp[r + 1][c][1] = (dp[r + 1][c][1] + dp[r][c][0]) % MOD;
                    }
                    // 2. Entering Down (dir = 1) -> turned Right to (r, c + 1)
                    if (dp[r][c][1] > 0 && c + 1 < n) {
                        dp[r][c + 1][0] = (dp[r][c + 1][0] + dp[r][c][1]) % MOD;
                    }
                } else {
                    // Empty cell: total ways reaching (r, c)
                    long ways = (dp[r][c][0] + dp[r][c][1]) % MOD;
                    if (ways == 0) continue;

                    // Can move Right
                    if (c + 1 < n) {
                        dp[r][c + 1][0] = (int) ((dp[r][c + 1][0] + ways) % MOD);
                    }
                    // Can move Down
                    if (r + 1 < m) {
                        dp[r + 1][c][1] = (int) ((dp[r + 1][c][1] + ways) % MOD);
                    }
                }
            }
        }

        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
}