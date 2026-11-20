import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> events = new ArrayList<>();
        
        // Create start (-height) and end (+height) events
        for (int[] b : buildings) {
            events.add(new int[]{b[0], -b[2]});
            events.add(new int[]{b[1], b[2]});
        }
        
        // Sort by x-coordinate, then by height
        events.sort((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        
        List<List<Integer>> result = new ArrayList<>();
        // TreeMap stores <Height, Count> in descending order of height
        TreeMap<Integer, Integer> heightMap = new TreeMap<>((a, b) -> Integer.compare(b, a));
        heightMap.put(0, 1);
        
        int prevMaxHeight = 0;
        
        for (int[] event : events) {
            int x = event[0];
            int h = event[1];
            
            if (h < 0) {
                // Start of a building: add height
                heightMap.put(-h, heightMap.getOrDefault(-h, 0) + 1);
            } else {
                // End of a building: remove height
                int count = heightMap.get(h);
                if (count == 1) {
                    heightMap.remove(h);
                } else {
                    heightMap.put(h, count - 1);
                }
            }
            
            int currMaxHeight = heightMap.firstKey();
            if (currMaxHeight != prevMaxHeight) {
                result.add(Arrays.asList(x, currMaxHeight));
                prevMaxHeight = currMaxHeight;
            }
        }
        
        return result;
    }
}