import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }

        int maxPointsOnLine = 1;

        for (int i = 0; i < n; i++) {
            // If the remaining points cannot beat the current maximum, stop early
            if (maxPointsOnLine >= n - i) {
                break;
            }

            Map<String, Integer> slopeCount = new HashMap<>();
            int currentMax = 0;

            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                // Reduce fraction by GCD
                int gcd = gcd(dx, dy);
                dx /= gcd;
                dy /= gcd;

                // Normalize signs so (-dx, -dy) and (dx, dy) match
                if (dx < 0 || (dx == 0 && dy < 0)) {
                    dx = -dx;
                    dy = -dy;
                }

                String slopeKey = dx + "/" + dy;
                int count = slopeCount.getOrDefault(slopeKey, 0) + 1;
                slopeCount.put(slopeKey, count);
                currentMax = Math.max(currentMax, count);
            }

            // +1 to include the anchor point points[i] itself
            maxPointsOnLine = Math.max(maxPointsOnLine, currentMax + 1);
        }

        return maxPointsOnLine;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}