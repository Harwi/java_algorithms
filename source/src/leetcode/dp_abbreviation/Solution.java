package leetcode.dp_abbreviation;

import java.io.*;
import java.util.*;

public class Solution {

    private static void print(boolean[][] dp, String a, String b) {

        System.out.print("    ");
        for (int i = 0; i < b.length(); i++) {
            System.out.printf("%2s", b.charAt(i));
        }
        System.out.println();
        System.out.print("  ");
        for (int row = 0; row < dp.length; row++) {
            for (int col = 0; col < dp[0].length; col++) {
                if (col == 0 && row > 0) {
                    System.out.printf("%2s", a.charAt(row - 1));
                }
                if (dp[row][col]) {
                    System.out.printf("%2d", 1);
                } else {
                    System.out.printf("%2d", 0);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    static String abbreviation(String a, String b) {
        boolean[][] dp = new boolean[a.length() + 1][b.length() + 1];
        dp[0][0] = true; // emptyString == emptyString

        boolean uppercase = false;
        for (int row = 1; row <= a.length(); row++) {
            int i = row - 1;

            // This code sets the first column to true until
            // the first instance of an upper-case letter
            if (isUpperCase(a.charAt(i)) || uppercase) {
                uppercase = true;
                dp[row][0] = false; // (upper-case letter) != emptyString
            } // (lower-case -> emptyString) == emptyString
            else dp[row][0] = true;
        }
        // fill in dp matrix
        for (int row = 1; row <= a.length(); row++) {
            int i = row - 1;

            for (int col = 1; col <= b.length(); col++) {
                int j = col - 1;

                if (a.charAt(i) == b.charAt(j)) {
                    dp[row][col] = dp[row-1][col-1];
                } else if (isUpperCase(a.charAt(i))) {
                    dp[row][col] = false;
                } else if (toUpperCase(a.charAt(i)) == b.charAt(j)) {
                    dp[row][col] = dp[row-1][col-1] || dp[row-1][col];
                } else {
                    dp[row][col] = dp[row-1][col];
                }
            }
        }
        print(dp, a, b);
        return dp[a.length()][b.length()] ? "YES" : "NO";
    }

    private static char toUpperCase(char c) {
        int ascii = (int) c;
        if (ascii < 97 || ascii > 122) {
            try {
                throw new Exception("Char must be lower-case: " + c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (char) (ascii - 32);
    }

    private static boolean isUpperCase(char c) {
        String letter = String.valueOf(c);
        return letter.toUpperCase().equals(letter);
    }

//    static class Abbreviation {
//
//        boolean breakLoop = false;
//
//        private static boolean runRecur(String a, String b, int ixA, int ixB, boolean bFin) {
//
//            ixA = Math.min(ixA, a.length() - 1);
//            ixB = Math.min(ixB, b.length() - 1);
//            boolean isUpperCase = Character.isUpperCase(a.charAt(ixA));
//
//            if (isUpperCase && (a.charAt(ixA) != b.charAt(ixB) || bFin)) {
//                return false;
//            }
//            if (ixB == b.length() - 1 && Character.toUpperCase(a.charAt(ixA)) == b.charAt(ixB)) {
//                bFin = true;
//            }
//            if (ixA == a.length() - 1 && ixB == b.length() - 1) {
//                return bFin;
//            }
//
//        System.out.println(a.substring(0, ixA + 1));
//        System.out.println(b.substring(0, ixB + 1));
//        System.out.println("------");
//
//            return (ixB < b.length() ? runRecur(a, b, ixA + 1, ixB + 1, bFin) : false)
//                    ||
//                    (!isUpperCase ? runRecur(a, b, ixA + 1, ixB, bFin) : false)
//                    ||
//                    (!isUpperCase && bFin ? runRecur(a, b, ixA + 1, ixB, false) : false)
//                    ;
//        }
//
//
//        // Complete the abbreviation function below.
//        static String run(String a, String b) {
//            boolean res = runRecur(a, b, 0, 0, false);
//            return res  ? "YES" : "NO";
//        }
//
//    }






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