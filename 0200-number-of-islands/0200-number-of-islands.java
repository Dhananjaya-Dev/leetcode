class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        
        return count;
    }
    
    private void dfs(char[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Out of bounds or visiting water ('0')
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] == '0') {
            return;
        }
        
        // Mark the current cell as visited by changing '1' to '0'
        grid[r][c] = '0';
        
        // Traverse all 4 adjacent directions
        dfs(grid, r - 1, c); // Up
        dfs(grid, r + 1, c); // Down
        dfs(grid, r - 0, c - 1); // Left (or r, c - 1)
        dfs(grid, r, c + 1); // Right
    }
}