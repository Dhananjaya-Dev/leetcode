class Solution {
    public int calculate(String s) {
        int n = s.length();
        int currentNumber = 0;
        int lastNumber = 0;
        int result = 0;
        char lastOp = '+';

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                currentNumber = currentNumber * 10 + (c - '0');
            }

            // Trigger evaluation when hitting an operator or the end of the string
            if ((!Character.isDigit(c) && !Character.isWhitespace(c)) || i == n - 1) {
                if (lastOp == '+') {
                    result += lastNumber;
                    lastNumber = currentNumber;
                } else if (lastOp == '-') {
                    result += lastNumber;
                    lastNumber = -currentNumber;
                } else if (lastOp == '*') {
                    lastNumber = lastNumber * currentNumber;
                } else if (lastOp == '/') {
                    lastNumber = lastNumber / currentNumber;
                }

                lastOp = c;
                currentNumber = 0;
            }
        }

        return result + lastNumber;
    }
}