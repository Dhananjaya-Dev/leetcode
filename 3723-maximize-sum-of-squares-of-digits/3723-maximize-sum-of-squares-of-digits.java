class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // A valid positive integer of length 'num' must have:
        // 1 <= sum <= 9 * num
        if (sum < 1 || (long) num * 9 < sum) {
            return "";
        }

        StringBuilder sb = new StringBuilder(num);

        int count9 = sum / 9;
        int rem = sum % 9;
        int count0 = num - count9 - (rem > 0 ? 1 : 0);

        // Append all 9's
        for (int i = 0; i < count9; i++) {
            sb.append('9');
        }

        // Append the remainder digit if non-zero
        if (rem > 0) {
            sb.append(rem);
        }

        // Append the remaining 0's
        for (int i = 0; i < count0; i++) {
            sb.append('0');
        }

        return sb.toString();
    }
}