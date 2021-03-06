package leetcode.max_subarray_mod;

import java.io.*;
import java.util.*;

public class Solution {

    // Complete the maximumSum function below.
    static long maximumSum(long[] a, long m) {

        long maxSum = 0;
        long currSum = 0;
        TreeSet<Long> prefixes = new TreeSet<>();

        for (int ix = 0; ix < a.length; ix++) {
            currSum = (a[ix] + currSum % m) % m;
            maxSum = Math.max(maxSum, currSum);

            Long prefixHF = prefixes.higher(currSum);
            if (prefixHF != null) {
                maxSum = Math.max(maxSum, (currSum - prefixHF + m) % m);
            }

            prefixes.add(currSum);
        }

        return maxSum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            long m = Long.parseLong(nm[1]);

            long[] a = new long[n];

            String[] aItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                long aItem = Long.parseLong(aItems[i]);
                a[i] = aItem;
            }

            long result = maximumSum(a, m);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}