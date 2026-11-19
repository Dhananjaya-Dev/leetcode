import java.util.*;

class Solution {
    private class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null; // Stores the full word at the leaf node
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode root = buildTrie(words);

        int m = board.length;
        int n = board[0].length;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                dfs(board, r, c, root, result);
            }
        }

        return result;
    }

    private void dfs(char[][] board, int r, int c, TrieNode parent, List<String> result) {
        char letter = board[r][c];
        TrieNode currNode = parent.children[letter - 'a'];

        // Prune if the current character is not a valid prefix
        if (currNode == null) {
            return;
        }

        // Check if we found a complete word
        if (currNode.word != null) {
            result.add(currNode.word);
            currNode.word = null; // Avoid duplicate entries
        }

        // Mark cell as visited
        board[r][c] = '#';

        // Explore all 4 adjacent directions
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length && board[nr][nc] != '#') {
                dfs(board, nr, nc, currNode, result);
            }
        }

        // Restore the cell
        board[r][c] = letter;

        // Optimization: Incrementally remove leaf nodes in the Trie once matched
        if (isEmpty(currNode)) {
            parent.children[letter - 'a'] = null;
        }
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode node = root;
            for (char c : w.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }
                node = node.children[idx];
            }
            node.word = w;
        }
        return root;
    }

    private boolean isEmpty(TrieNode node) {
        for (TrieNode child : node.children) {
            if (child != null) return false;
        }
        return true;
    }
}