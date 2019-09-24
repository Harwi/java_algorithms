package graph_connected_cells;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the maxRegion function below.
    static int maxRegion(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int res = -1;

        for (int rowIx = 0; rowIx < grid.length; rowIx++) {
            for (int colIx = 0; colIx < grid[rowIx].length; colIx++) {
                res = Math.max(res, calcRegion(grid, rowIx, colIx, visited));
            }
        }

        System.out.println("Result = " + res);
        return res;
    }

    static int calcRegion(int[][] grid, int row, int col, boolean[][] visited) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) return 0;
        if (visited[row][col]) {
            return 0;
        }
        visited[row][col] = true;
        if (grid[row][col] == 0) return 0;
        int count = 1;
        count += calcRegion(grid, row + 1, col, visited);
        count += calcRegion(grid, row - 1, col, visited);
        count += calcRegion(grid, row, col + 1, visited);
        count += calcRegion(grid, row, col - 1, visited);
        count += calcRegion(grid, row + 1, col + 1, visited);
        count += calcRegion(grid, row - 1, col - 1, visited);
        count += calcRegion(grid, row - 1, col + 1, visited);
        count += calcRegion(grid, row + 1, col - 1, visited);
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] gridRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < m; j++) {
                int gridItem = Integer.parseInt(gridRowItems[j]);
                grid[i][j] = gridItem;
            }
        }

        int res = maxRegion(grid);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}