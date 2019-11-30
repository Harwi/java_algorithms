package matrix_num_of_islands;

//        Input:
//        11000
//        11000
//        00100
//        00011
//
//        Output: 3

public class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numOfIslands = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    numOfIslands++;
                    traverseDfs(grid, i, j);
                }
            }
        }

        return numOfIslands;
    }

    private static void traverseDfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';

        traverseDfs(grid, i + 1, j);
        traverseDfs(grid, i - 1, j);
        traverseDfs(grid, i, j + 1);
        traverseDfs(grid, i, j - 1);
    }
}
