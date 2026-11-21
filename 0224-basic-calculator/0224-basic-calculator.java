import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;
        int number = 0;
        int sign = 1; // 1 represents '+', -1 represents '-'

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            } else if (c == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                // Save the current result and sign before entering parentheses
                stack.push(result);
                stack.push(sign);
                // Reset for the new sub-expression inside parentheses
                result = 0;
                sign = 1;
            } else if (c == ')') {
                result += sign * number;
                number = 0;
                // Pop the sign before '(' and apply it to the sub-expression result
                result *= stack.pop();
                // Pop the result accumulated before '(' and add to it
                result += stack.pop();
            }
        }

        // Add any remaining number at the end of the string
        return result + (sign * number);
    }
}