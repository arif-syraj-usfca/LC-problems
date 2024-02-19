import java.util.Arrays;

class maxAreaOfIsland {
    /**
     Iterate over whole grid (m*n)
     If a 1 is found, call DFS on that position
     Initialize sum = 0
     If tile is valid, increment sum by 1, and explore neighbors
     Set tile value to -1 to indicate it has been visited
     Increment sum with dfs(neighbor) for all neighbors
     If neighbor is not valid, return 0, not incrementing sum
     If neighbor is valid, increment sum by dfs of ITS neighbors, and so on
     Return this sum at the end
     Compare current max with global max, and reassign values if necessary
     */

    // Initialize variables to persist over entire function
    int rows, cols;
    int[][] grid;
    int[][] moves = new int[][] {{1,0}, {-1,0}, {0,1}, {0,-1}};
    int global_max = 0;

    // Return true if (x, y) is within the grid, and a land location
    public Boolean is_valid(int x, int y) {
        return ((x >= 0 && x < rows) && (y >= 0 && y < cols) && (grid[x][y] == 1));
    }

    public int DFS(int x, int y) {
        int sum = 0;
        // If tile is valid, increment sum and explore its neighbors.
        // Otherwise, just return 0
        if (is_valid(x, y)) {
            sum += 1;
            grid[x][y] = -1;
            for (int[] move : moves) {
                sum += DFS(x + move[0], y + move[1]);
            }
        }
        return sum;
    }

    public int maxAreaOfIsland(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        this.grid = grid;
        // Iterate over whole grid
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // If land is found, call DFS on it and compare with max so far
                if (grid[row][col] == 1) {
                    global_max = Integer.max(global_max, DFS(row, col));
                }
            }
        }
        return global_max;
    }
}