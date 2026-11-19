class Solution {
    public String shortestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        
        String rev = new StringBuilder(s).reverse().toString();
        String combined = s + "#" + rev;
        
        int[] lps = computeLPS(combined);
        int longestPalindromicPrefixLen = lps[combined.length() - 1];
        
        String suffixToAdd = new StringBuilder(s.substring(longestPalindromicPrefixLen))
                                 .reverse()
                                 .toString();
        
        return suffixToAdd + s;
    }
    
    private int[] computeLPS(String pattern) {
        int n = pattern.length();
        int[] lps = new int[n];
        int len = 0;
        int i = 1;
        
        while (i < n) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        
        return lps;
    }
}