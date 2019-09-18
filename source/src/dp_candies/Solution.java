package dp_candies;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the candies function below.
    static long candies(int n, int[] arr) {
        int[] res_arr = new int[arr.length];
        Arrays.fill(res_arr, 1);

        for (int ix = 1; ix < arr.length; ix++) {
            if (arr[ix] > arr[ix - 1]) {
                res_arr[ix] = res_arr[ix - 1] + 1;
            }
        }

        long total = res_arr[arr.length - 1];
        for (int ix = arr.length - 2; ix >= 0; ix--) {
            if (arr[ix] > arr[ix + 1]) {
                res_arr[ix] = Math.max(res_arr[ix + 1] + 1, res_arr[ix]);
            }
//            else if (arr[ix] == arr[ix + 1]) {
//                res_arr[ix] = res_arr[ix + 1];
//            }
            total = total + res_arr[ix];
        }

        return total;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int arrItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            arr[i] = arrItem;
        }

        long result = candies(n, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
