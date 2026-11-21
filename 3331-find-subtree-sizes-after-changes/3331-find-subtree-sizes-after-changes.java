import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int i = 1; i < n; i++) adj.get(parent[i]).add(i);
        
        int[] newParent = parent.clone();
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);
        
        dfsReparent(0, adj, s, lastSeen, newParent);
        
        List<List<Integer>> newAdj = new ArrayList<>();
        for (int i = 0; i < n; i++) newAdj.add(new ArrayList<>());
        for (int i = 1; i < n; i++) newAdj.get(newParent[i]).add(i);
        
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        dfsSize(0, newAdj, ans);
        
        return ans;
    }
    
    private void dfsReparent(int u, List<List<Integer>> adj, String s, int[] lastSeen, int[] newParent) {
        int c = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[c];
        
        if (prevAncestor != -1) {
            newParent[u] = prevAncestor;
        }
        
        lastSeen[c] = u;
        for (int v : adj.get(u)) {
            dfsReparent(v, adj, s, lastSeen, newParent);
        }
        lastSeen[c] = prevAncestor;
    }
    
    private int dfsSize(int u, List<List<Integer>> newAdj, int[] ans) {
        for (int v : newAdj.get(u)) {
            ans[u] += dfsSize(v, newAdj, ans);
        }
        return ans[u];
    }
}