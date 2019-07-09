package making_anagrams;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {
        int[] asciArr1 = new int[256];
        int remains1 = 0;
        int remains2 = 0;
        for (int ix = 0; ix < a.length(); ix++) {
            asciArr1[(int) a.charAt(ix)]++;
            remains1++;
        }
        for (int ix = 0; ix < b.length(); ix++) {
            if (asciArr1[(int) b.charAt(ix)] > 0) {
                asciArr1[(int) b.charAt(ix)]--;
                remains1--;
            } else {
                remains2++;
            }
        }
        return remains1 + remains2;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String a = scanner.nextLine();

        String b = scanner.nextLine();

        int res = makeAnagram(a, b);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
