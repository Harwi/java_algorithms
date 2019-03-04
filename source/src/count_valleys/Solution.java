package count_valleys;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the countingValleys function below.
    static int countingValleys(int n, String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("Input string must contain at least 1 character.");
        }

        final char up = 'U';
        final char down = 'D';
        int valleyCount = 0;

        int seaLevel = 0;
        int altitude = 0;
        int prev_altitude;
        for (int idx = 0; idx < s.length(); idx++) {
            prev_altitude = altitude;
            if (s.charAt(idx) == up) {
                altitude++;
            } else {
                altitude--;
            }
            if (altitude < 0 && prev_altitude == seaLevel) {
                valleyCount++;
            }
        }
        return valleyCount;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        int result = countingValleys(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}