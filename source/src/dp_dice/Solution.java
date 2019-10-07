package dp_dice;

import java.util.Arrays;

public class Solution {

    public static int numRollsToTarget(int d, int f, int target) {
        final int MOD = (int) Math.pow(10, 9) + 7;
        int[][] dp = new int[d + 1][target + 1];
        dp[0][0] = 1;

        printMatrix(dp);

        for (int i = 1; i <= target; i++) {
            for (int j = 1; j <= d; j++) {
                for (int k = 1; k <= f; k++) {
                    if (i - k >= 0) {
                        dp[j][i] = (dp[j][i] + dp[j - 1][i - k]) % MOD;
                        printMatrix(dp);
                    }
                }
            }
        }
        return dp[d][target];
    }

    private static void printMatrix(int[][] m) {
        for (int[] row : m) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
        System.out.println();
    }

    public static void main(String... args) {
        int res = numRollsToTarget(3, 6, 7);
        System.out.println("Res = " + res);
    }
}
