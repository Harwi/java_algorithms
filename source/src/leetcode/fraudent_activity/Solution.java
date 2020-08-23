package leetcode.fraudent_activity;

import java.io.*;
import java.util.*;

public class Solution {

    static float calcMedian(int[] freq) {
        int[] prefix_sum = new int[201];

        prefix_sum[0] = freq[0];
        for (int ix = 1; ix < freq.length; ix++) {
            prefix_sum[ix] += freq[ix] + prefix_sum[ix - 1];
        }

        int len = prefix_sum[prefix_sum.length - 1];
        int median1Ix = len / 2;
        int median2Ix = median1Ix + 1;

        int median1 = 0;
        int median2 = 0;

        boolean median1Found = false;
        boolean median2Found = false;

        for (int ix = 0; ix < freq.length; ix++) {
            if (!median1Found && median1Ix <= prefix_sum[ix]) {
                median1 = ix;
                median1Found = true;
            }
            if (!median2Found && median2Ix <= prefix_sum[ix]) {
                median2 = ix;
                median2Found = true;
            }
            if (median1Found && median2Found) {
                break;
            }
        }

        if (len % 2 == 0) {
            return (float) (median1 + median2) / 2;
        } else {
            return (float) median2;
        }
    }

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        if (expenditure.length == 0 || d == 0 || d >= expenditure.length) {
            throw new IllegalStateException("Invalid input");
        }

        int notifications = 0;
        // 0 <= x <= 200
        int[] freq = new int[201];

        for (int ix = d; ix < expenditure.length; ix++) {
            if (ix == d) {
                for (int l = 0; l < ix; l++) {
                    freq[expenditure[l]]++;
                }
            } else {
                freq[expenditure[ix - d - 1]]--;
                freq[expenditure[ix - 1]]++;
            }

            float median = calcMedian(freq);
            if (expenditure[ix] >= median * 2) {
                notifications++;
            }
        }

        System.out.println(String.format("Notifications = %s", notifications));

        return notifications;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        //bufferedWriter.close();

        scanner.close();
    }
}