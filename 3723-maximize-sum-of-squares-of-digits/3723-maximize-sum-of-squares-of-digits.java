class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // A num-digit positive integer has digit sum between 1 and 9 * num
        if (sum < 1 || (long) num * 9 < sum) {
            return "";
        }

        StringBuilder sb = new StringBuilder(num);

        int count9 = sum / 9;
        int rem = sum % 9;

        // Place as many 9s as possible on the left
        for (int i = 0; i < count9; i++) {
            sb.append('9');
        }

        // Place the single remainder digit if it exists
        if (rem > 0) {
            sb.append((char) ('0' + rem));
        }

        // Fill the remaining digit positions with '0'
        int zeros = num - sb.length();
        for (int i = 0; i < zeros; i++) {
            sb.append('0');
        }

        return sb.toString();
    }
}