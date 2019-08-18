package dp_abbreviation;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static boolean abbreviationRecur(String a, String b, int ixA, int ixB) {
        if (ixA >= a.length() && ixB >= b.length()) {
            return true;
        }
        else if (ixA >= a.length() && ixB < b.length()) {
            return false;
        }
        ixA = ixA >= a.length() ? a.length() - 1 : ixA;
        ixB = ixB >= b.length() ? b.length() - 1 : ixB;

        boolean canSkip = false;
        if (Character.isLowerCase(a.charAt(ixA))) {
            canSkip = true;
        }
        if (!canSkip && a.charAt(ixA) != b.charAt(ixB)) {
            return false;
        }
        return (a.charAt(ixA) == b.charAt(ixB) ? abbreviationRecur(a, b, ixA + 1, ixB + 1) : false )
                ||
                (canSkip ? abbreviationRecur(a, b, ixA + 1, ixB) : false);
    }

    // Complete the abbreviation function below.
    static String abbreviation(String a, String b) {
        boolean res = abbreviationRecur(a, b, 0, 0);
        return res  ? "YES" : "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            String result = abbreviation(a, b);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}