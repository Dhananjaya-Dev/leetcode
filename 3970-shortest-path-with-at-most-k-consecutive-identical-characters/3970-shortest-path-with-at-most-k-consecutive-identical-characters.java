import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Special case: already at target
        if (n == 1) {
            return 0;
        }

        // Build adjacency list
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph[u].add(new int[]{v, w});
        }

        char[] labelArr = labels.toCharArray();

        // dist[u][count] = minimum distance to reach node u with consecutive run length 'count'
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // Priority queue stores [cost, u, count]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Starting state at node 0
        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0];
            int u = curr[1];
            int count = curr[2];

            // Reached destination
            if (u == n - 1) {
                return d;
            }

            // Skip outdated states
            if (d > dist[u][count]) {
                continue;
            }

            for (int[] edge : graph[u]) {
                int v = edge[0];
                int w = edge[1];

                int nextCount = (labelArr[u] == labelArr[v]) ? count + 1 : 1;

                if (nextCount <= k && d + w < dist[v][nextCount]) {
                    dist[v][nextCount] = d + w;
                    pq.offer(new int[]{dist[v][nextCount], v, nextCount});
                }
            }
        }

        return -1;
    }
}