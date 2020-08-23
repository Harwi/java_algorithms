package leetcode.dp_max_array_sum;

import java.io.*;
import java.util.*;

public class Solution {

    // Complete the maxSubsetSum function below.
    static int maxSubsetSum(int[] arr) {
        int[] sum = new int[arr.length];
        if (arr.length > 1) {
            sum[0] = arr[0];
            sum[1] = Math.max(arr[0], arr[1]);
            int max = Math.max(sum[0], sum[1]);

            if (arr.length > 2) {
                for (int ix = 2; ix < arr.length; ix++) {
                    sum[ix] = Math.max(sum[ix - 1], Math.max(Math.max(arr[ix], sum[ix - 2]), arr[ix] + sum[ix - 2]));
                    max = Math.max(sum[ix], max);
                }
                return max;
            } else {
                return max;
            }
        } else {
            return arr[0];
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = maxSubsetSum(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
