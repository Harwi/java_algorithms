package leetcode.repeated_string;

import java.io.*;
import java.util.*;

public class Solution {

    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("Input string must contain at least 1 character.");
        }
        if (n <= 0) {
            throw new IllegalArgumentException("Input number of reps must exceed 0");
        }
        long len = s.length();
        long reps = 0;
        if (len <= n) {
            reps = Math.floorDiv(n, len);
        }
        int cutOff = (int) (Math.floorMod(n, len));
        long fullStrOccur = 0;
        long cutOffStrOccur = 0;

        for (int idx = 0; idx < s.length(); idx++) {
            if (s.charAt(idx) == 'a') {
                fullStrOccur++;
                if (idx <= cutOff - 1 && cutOff > 0) {
                    cutOffStrOccur++;
                }
            }
        }

        return reps * fullStrOccur + cutOffStrOccur;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        long n = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = repeatedString(s, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
