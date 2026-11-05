import java.util.HashMap;
import java.util.Map;

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();

        // Determine the sign
        if ((numerator < 0) ^ (denominator < 0)) {
            sb.append("-");
        }

        // Convert to long to avoid overflow with Integer.MIN_VALUE
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        // Integral part
        sb.append(num / den);
        long remainder = num % den;

        if (remainder == 0) {
            return sb.toString();
        }

        sb.append(".");

        // Map to store remainder -> position in StringBuilder
        Map<Long, Integer> remainderMap = new HashMap<>();

        while (remainder != 0) {
            if (remainderMap.containsKey(remainder)) {
                int index = remainderMap.get(remainder);
                sb.insert(index, "(");
                sb.append(")");
                break;
            }

            remainderMap.put(remainder, sb.length());
            remainder *= 10;
            sb.append(remainder / den);
            remainder %= den;
        }

        return sb.toString();
    }
}