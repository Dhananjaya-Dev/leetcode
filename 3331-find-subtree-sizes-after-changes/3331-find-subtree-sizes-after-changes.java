import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            adj.get(parent[i]).add(i);
        }

        int[] newParent = Arrays.copyOf(parent, n);
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        // First DFS: Determine the new parent for each node
        findNewParents(0, adj, s, lastSeen, newParent);

        // Build the new tree using the updated parents
        List<List<Integer>> newAdj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            newAdj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            newAdj.get(newParent[i]).add(i);
        }

        // Second DFS: Calculate subtree sizes in the final tree
        int[] ans = new int[n];
        calculateSizes(0, newAdj, ans);
        
        return ans;
    }

    private void findNewParents(int u, List<List<Integer>> adj, String s, int[] lastSeen, int[] newParent) {
        int charIdx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[charIdx];

        if (prevAncestor != -1) {
            newParent[u] = prevAncestor;
        }

        lastSeen[charIdx] = u;
        for (int v : adj.get(u)) {
            findNewParents(v, adj, s, lastSeen, newParent);
        }
        // Backtrack
        lastSeen[charIdx] = prevAncestor;
    }

    private int calculateSizes(int u, List<List<Integer>> adj, int[] ans) {
        int size = 1;
        for (int v : adj.get(u)) {
            size += calculateSizes(v, adj, ans);
        }
        ans[u] = size;
        return size;
    }
}