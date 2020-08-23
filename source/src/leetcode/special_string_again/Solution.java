package leetcode.special_string_again;

import java.io.*;
import java.util.*;

public class Solution {

    // Complete the substrCount function below.
    static long substrCount(int n, String s) {
        long result = 0;
        for (int i = 0; i < n; i++) {
            // if the current symbol is in the middle of palindrome, e.g. aba
            long pali = 0;
            int offset = 1;
            while (i - offset >= 0 && i + offset < n && s.charAt(i - offset) == s.charAt(i + offset) && s.charAt(i - offset) == s.charAt(i - 1)) {
                pali++;
                offset++;
            }
            // if this is repeatable characters aa
            long repeats = 0;
            while (i + 1 < n && s.charAt(i) == s.charAt(i + 1)) {
                repeats++;
                i++;
            }
            result += pali + (repeats * (repeats + 1) / 2);
        }
        return result + n;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}