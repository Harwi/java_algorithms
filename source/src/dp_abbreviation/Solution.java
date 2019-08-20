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

        ixA = Math.min(ixA, a.length() - 1);
        ixB = Math.min(ixB, b.length() - 1);

        if (Character.isUpperCase(a.charAt(ixA)) && a.charAt(ixA) != b.charAt(ixB)) {
            return false;
        } else if (ixA == a.length() - 1 && ixB == b.length() - 1 && Character.toUpperCase(a.charAt(ixA)) != b.charAt(ixB)) {
            return false;
        }

        if (ixA == a.length() - 1 && ixB == b.length() - 1) {
            return true;
        }

//        System.out.println(a.substring(0, ixA + 1));
//        System.out.println(b.substring(0, ixB + 1));
//        System.out.println("------");

        return (ixB < b.length() ? abbreviationRecur(a, b, ixA + 1, ixB + 1) : false)
                ||
                (Character.isLowerCase(a.charAt(ixA)) ? abbreviationRecur(a, b, ixA + 1, ixB) : false);
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