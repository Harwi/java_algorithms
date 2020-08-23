package paint_house;

import java.util.Arrays;

public class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;

        for (int row = 1; row < costs.length; row++) {
            for (int col = 0; col < costs[row].length; col++) {
                if (col == 0) {
                    costs[row][col] += Math.min(costs[row - 1][1], costs[row - 1][2]);
                } else if (col == 1) {
                    costs[row][col] += Math.min(costs[row - 1][0], costs[row - 1][2]);
                } else {
                    costs[row][col] += Math.min(costs[row - 1][0], costs[row - 1][1]);
                }
                min = Math.min(min, costs[row][col]);
            }
        }

        return Arrays.stream(costs[costs.length - 1]).min().getAsInt();
    }
}
