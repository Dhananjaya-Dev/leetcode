import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private int[] tin, tout, depth;
    private int[][] up;
    private int timer = 0;
    private int LOG;
    private int[] bit;
    private int N;

    // BIT Range XOR update and Point query
    private void bitUpdate(int i, int val) {
        for (; i <= N; i += i & -i) {
            bit[i] ^= val;
        }
    }

    private int bitQuery(int i) {
        int sum = 0;
        for (; i > 0; i -= i & -i) {
            sum ^= bit[i];
        }
        return sum;
    }

    private void updateSubtree(int u, int val) {
        bitUpdate(tin[u], val);
        bitUpdate(tout[u] + 1, val);
    }

    private void dfs(int u, int p, int d, int currentMask, char[] chars) {
        tin[u] = ++timer;
        depth[u] = d;
        up[u][0] = p;
        for (int i = 1; i < LOG; i++) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }

        int nodeMask = 1 << (chars[u] - 'a');
        int nextMask = currentMask ^ nodeMask;
        
        // Initial path mask from root to u
        bitUpdate(tin[u], nextMask);
        bitUpdate(tin[u] + 1, nextMask);

        for (int v : adj[u]) {
            if (v != p) {
                dfs(v, u, d + 1, nextMask, chars);
            }
        }
        tout[u] = timer;
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int tmp = u; u = v; v = tmp;
        }
        for (int i = LOG - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = up[u][i];
            }
        }
        if (u == v) return u;
        for (int i = LOG - 1; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }
        return up[u][0];
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.N = n;
        this.LOG = 32 - Integer.numberOfLeadingZeros(n) + 1;
        this.adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        up = new int[n][LOG];
        bit = new int[n + 2];
        char[] chars = s.toCharArray();

        dfs(0, 0, 0, 0, chars);

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            if (q.startsWith("update")) {
                String[] parts = q.split(" ");
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                if (chars[u] != c) {
                    int diff = (1 << (chars[u] - 'a')) ^ (1 << (c - 'a'));
                    chars[u] = c;
                    updateSubtree(u, diff);
                }
            } else {
                String[] parts = q.split(" ");
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int lca = getLCA(u, v);
                int maskU = bitQuery(tin[u]);
                int maskV = bitQuery(tin[v]);
                int lcaCharMask = 1 << (chars[lca] - 'a');

                int pathMask = maskU ^ maskV ^ lcaCharMask;
                ans.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return ans;
    }
}