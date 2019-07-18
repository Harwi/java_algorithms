package sherlock_valid_string;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the isValid function below.
    static String isValid(String s) {
        final String good = "YES";
        final String bad = "NO";

        if (s.length() < 3) {
            return good;
        }

        int[] freq = new int['z' - 'a' + 1];

        for (int ix = 0; ix < s.length(); ix++) {
            freq[s.charAt(ix) - 'a']++;
        }

        Arrays.sort(freq);

        // find min non-0 val
        int min = 0;
        int minIx = 0;
        for (int ix = 0; ix < freq.length; ix++) {
            if (freq[ix] > 0) {
                min = freq[ix];
                minIx = ix;
                break;
            }
        }

        int maxIx = freq.length - 1;
        int max = freq[maxIx];
            if (maxIx == minIx
                    || (freq[maxIx - 1] == freq[minIx] && (freq[maxIx] - freq[maxIx - 1] <= 1))
                    || (freq[maxIx] == freq[minIx + 1] && (freq[minIx + 1] - freq[minIx] <= 1 || freq[minIx] == 1))) {
                return good;
            } else {
                return bad;
            }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

//        bufferedWriter.write(result);
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
