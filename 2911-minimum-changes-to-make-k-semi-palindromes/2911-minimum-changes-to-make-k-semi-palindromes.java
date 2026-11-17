import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int INF = 1_000_000_000;

        // Step 1: Precompute proper divisors for each length from 1 to n
        List<Integer>[] divisors = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            divisors[i] = new ArrayList<>();
        }
        for (int length = 1; length <= n; length++) {
            for (int d = 1; d < length; d++) {
                if (length % d == 0) {
                    divisors[length].add(d);
                }
            }
        }

        // Step 2: cost[i][j] = min changes to make substring s[i...j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cost[i], INF);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int length = j - i + 1;
                int minChanges = INF;

                // Try every valid divisor d
                for (int d : divisors[length]) {
                    int currentChanges = 0;
                    // Each group has length d, starting at offset g
                    for (int g = 0; g < d; g++) {
                        List<Character> groupChars = new ArrayList<>();
                        int idx = i + g;
                        while (idx <= j) {
                            groupChars.add(s.charAt(idx));
                            idx += d;
                        }

                        // Count changes to make groupChars a palindrome
                        int left = 0, right = groupChars.size() - 1;
                        while (left < right) {
                            if (!groupChars.get(left).equals(groupChars.get(right))) {
                                currentChanges++;
                            }
                            left++;
                            right--;
                        }
                    }
                    minChanges = Math.min(minChanges, currentChanges);
                }
                cost[i][j] = minChanges;
            }
        }

        // Step 3: DP to partition the string into k semi-palindromes
        // dp[i][parts] = min changes to partition prefix s[0...i-1] into parts semi-palindromes
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;

        for (int parts = 1; parts <= k; parts++) {
            for (int i = 0; i < n; i++) {
                if (dp[i][parts - 1] == INF) continue;
                // Try ending the current semi-palindrome at index j-1
                for (int j = i + 1; j <= n - (k - parts); j++) {
                    dp[j][parts] = Math.min(dp[j][parts], dp[i][parts - 1] + cost[i][j - 1]);
                }
            }
        }

        return dp[n][k];
    }
}