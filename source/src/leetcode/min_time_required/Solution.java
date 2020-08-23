package leetcode.min_time_required;

import java.io.*;
import java.util.*;

public class Solution {

    // Complete the minTime function below.
    static long minTime(long[] machines, long goal) {
        long minDay = goal * Arrays.stream(machines).min().getAsLong() / machines.length;
        long maxDay = goal * Arrays.stream(machines).max().getAsLong() / machines.length;

        long day = 0;
        while (minDay < maxDay) {
            day = (maxDay + minDay) / 2;

            long produced = 0;
            for (long m : machines) {
                produced = produced + day / m;
            }

            if (produced == goal) {
                break;
            }
            if (minDay == maxDay) {
                day = minDay;
                break;
            }

            if (produced < goal) {
                minDay = day + 1;
            } else {
                maxDay = day;
            }
        }

        if (minDay == maxDay) {
            return minDay;
        } else {
            while (day >= minDay) {
                long produced = 0;
                for (long m : machines) {
                    produced = produced + (day - 1) / m;
                }
                if (produced != goal) {
                    break;
                } else {
                    day--;
                }
            }
        }

        return day;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nGoal = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nGoal[0]);

        long goal = Long.parseLong(nGoal[1]);

        long[] machines = new long[n];

        String[] machinesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long machinesItem = Long.parseLong(machinesItems[i]);
            machines[i] = machinesItem;
        }

        long ans = minTime(machines, goal);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
