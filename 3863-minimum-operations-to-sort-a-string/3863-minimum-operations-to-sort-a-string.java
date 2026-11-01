class Solution {
    public int minOperations(String s) {
        int n = s.length();

        // Check if already sorted
        boolean sorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;

        // If length is 2 and not sorted, no valid proper substring can fix it
        if (n == 2) return -1;

        // Find minimum and maximum characters
        char minChar = 'z';
        char maxChar = 'a';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c < minChar) minChar = c;
            if (c > maxChar) maxChar = c;
        }

        // 1 operation if min is already at the start or max is at the end
        if (s.charAt(0) == minChar || s.charAt(n - 1) == maxChar) {
            return 1;
        }

        // Count occurrences of min and max characters
        int minCount = 0;
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == minChar) minCount++;
            if (s.charAt(i) == maxChar) maxCount++;
        }

        // Check if all maximum characters are concentrated at index 0
        // AND all minimum characters are concentrated at index n - 1
        // (i.e. no minChar exists in s[0...n-2] and no maxChar exists in s[1...n-1])
        boolean onlyMaxAtStart = (s.charAt(0) == maxChar && maxCount == 1);
        boolean onlyMinAtEnd = (s.charAt(n - 1) == minChar && minCount == 1);

        if (onlyMaxAtStart && onlyMinAtEnd) {
            return 3;
        }

        return 2;
    }
}