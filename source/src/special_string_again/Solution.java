package special_string_again;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the substrCount function below.
    static long substrCount(int n, String s) {
        long counter = 0;
        long cntBeforeDelim = 0;
        long cntAfterDelim = 0;
        char repChar = s.charAt(0);
        int delimIx = 0;
        boolean canBePali = false;
        for (int ix = 0; ix < n; ix++) {
            if (!canBePali || s.charAt(ix) == s.charAt(ix - 1)) {
                repChar = s.charAt(ix);
                cntBeforeDelim++;
            } else {
                // There is enough space before end to build pali
                if (!canBePali && ix + cntBeforeDelim < n) {
                    canBePali = true;
                    delimIx = ix;
                }
                if (s.charAt(ix) == repChar ) {
                    cntAfterDelim++;
                }
                if (cntAfterDelim >= cntBeforeDelim) {
                    canBePali = false;
                    delimIx = 0;
                }

                if (s.charAt(ix) == repChar ) {
                    cntAfterDelim++;


                } else {
                    if (cntBeforeDelim != cntAfterDelim) {
                        canBePali = false;

                    }
                }


            }
        }

//        for (int i = 0; i < n; i++) {
//            // if the current symbol is in the middle of palindrome, e.g. aba
//            int offset = 1;
//            while (i - offset >= 0 && i + offset < n && s.charAt(i - offset) == s.charAt(i - 1)
//                    && s.charAt(i + offset) == s.charAt(i - 1)) {
//                counter++;
//                offset++;
//            }
//            // if this is repeatable characters aa
//            int repeats = 0;
//            while (i + 1 < n && s.charAt(i) == s.charAt(i + 1)) {
//                repeats++;
//                i++;
//            }
//            counter += repeats * (repeats + 1) / 2;
//        }
//        return counter + n;

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