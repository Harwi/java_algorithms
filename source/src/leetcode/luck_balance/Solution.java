package leetcode.luck_balance;

import java.io.*;
import java.util.*;

public class Solution {

    // Complete the luckBalance function below.
    static int luckBalance(int k, int[][] contests) {
        int luck_balance = 0;
        LinkedList<Integer> important = new LinkedList<>();
        for (int ix = 0; ix < contests.length; ix++) {
            if (contests[ix][1] == 1) {
                important.add(contests[ix][0]);
            } else {
                luck_balance += contests[ix][0];
            }
        }
        important.sort(Collections.reverseOrder());
        for (int ix = 0; ix < important.size(); ix++) {
            luck_balance += (( ix < k ) ? +important.get(ix) : -important.get(ix));
        }
        return luck_balance;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[][] contests = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] contestsRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int contestsItem = Integer.parseInt(contestsRowItems[j]);
                contests[i][j] = contestsItem;
            }
        }

        int result = luckBalance(k, contests);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}