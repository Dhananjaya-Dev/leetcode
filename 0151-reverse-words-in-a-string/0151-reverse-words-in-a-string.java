class Solution {
    public String reverseWords(String s) {
        StringBuilder result = new StringBuilder();
        int i = s.length() - 1;

        while (i >= 0) {
            // Skip trailing or intermediate spaces
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }

            if (i < 0) break;

            // Find the start of the current word
            int j = i;
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }

            // Append space separator if this is not the first word
            if (result.length() > 0) {
                result.append(" ");
            }

            // Append the extracted word
            result.append(s.substring(i + 1, j + 1));
        }

        return result.toString();
    }
}