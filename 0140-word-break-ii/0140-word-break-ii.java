import java.util.*;

class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Map<Integer, List<String>> memo = new HashMap<>();
        return dfs(s, 0, wordSet, memo);
    }

    private List<String> dfs(String s, int start, Set<String> wordSet, Map<Integer, List<String>> memo) {
        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        List<String> validSentences = new ArrayList<>();

        // Base case: reached the end of the string
        if (start == s.length()) {
            validSentences.add("");
            return validSentences;
        }

        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if (wordSet.contains(word)) {
                List<String> subSentences = dfs(s, end, wordSet, memo);
                for (String sub : subSentences) {
                    if (sub.isEmpty()) {
                        validSentences.add(word);
                    } else {
                        validSentences.add(word + " " + sub);
                    }
                }
            }
        }

        memo.put(start, validSentences);
        return validSentences;
    }
}