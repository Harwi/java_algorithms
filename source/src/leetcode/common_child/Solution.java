package leetcode.common_child;

import java.io.*;
import java.util.*;

public class Solution {

    // Complete the commonChild function below.
    static int commonChild(String s1, String s2) {
        int[][] mx = new int[s1.length()+1][s2.length()+1];

        for (int ix1 = 0; ix1 < s1.length(); ix1++) {
            for (int ix2 = 0; ix2 < s2.length(); ix2++) {
                if (s1.charAt(ix1) == s2.charAt(ix2)) {
                    mx[ix1 + 1][ix2 + 1] = mx[ix1][ix2] + 1;
                } else {
                    mx[ix1 + 1][ix2 + 1] = Math.max(mx[ix1 + 1][ix2], mx[ix1][ix2 + 1]);
                }
            }
        }

        return mx[s1.length()][s2.length()];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
