class Solution {
    public boolean isIsomorphic(String s, String t) {
        // Arrays to store the last seen positions of characters.
        // Size 256 is used to cover all ASCII characters.
        int[] indexS = new int[256];
        int[] indexT = new int[256];
        
        int length = s.length();
        
        for (int i = 0; i < length; i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);
            
            // If the last seen positions don't match, they are not isomorphic
            if (indexS[charS] != indexT[charT]) {
                return false;
            }
            
            // Record the current position (1-based index to distinguish from default 0)
            indexS[charS] = i + 1;
            indexT[charT] = i + 1;
        }
        
        return true;
    }
}