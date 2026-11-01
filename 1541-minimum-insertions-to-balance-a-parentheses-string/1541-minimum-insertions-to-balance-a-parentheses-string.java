class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int neededRight = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // If neededRight is odd, the previous '(' only got one ')'
                if (neededRight % 2 != 0) {
                    insertions++; // Insert one ')'
                    neededRight--;
                }
                neededRight += 2;
            } else { // c == ')'
                neededRight--;
                // Unmatched ')' found
                if (neededRight < 0) {
                    insertions++; // Insert one '('
                    neededRight += 2; // '(' needs 2 ')', and we already consumed one
                }
            }
        }
        
        return insertions + neededRight;
    }
}