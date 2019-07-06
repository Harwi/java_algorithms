package fraudent_activity;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Solution {

    static float calcMedian(List<Integer> input) {
        if (input.size() == 0) {
            return -1;
        }
        else if (input.size() == 1) {
            return input.get(0);
        }
        else if (input.size() % 2 == 0) {
            return (float) (input.get(input.size() / 2) + input.get(input.size() / 2 + 1)) / 2;
        } else {
            return input.get(input.size() / 2);
        }
    }

    static Integer find(List<Integer> arr, Integer search) {
        int l = 0, r = arr.size() - 1, m = 0;
        while (l <= r) {
            m = l + (r - l) / 2;

            // Check if x is present at mid
            if (arr.get(m).equals(search))
                return m;

            // If x greater, ignore left half
            if (arr.get(m) < search)
                l = m + 1;

                // If x is smaller, ignore right half
            else
                r = m - 1;
        }

        return m;
    }

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        if (expenditure.length == 0 || d == 0 || d >= expenditure.length) {
            throw new IllegalStateException("Invalid input");
        }

        int notifications = 0;
        List<Integer> rangeArr = new ArrayList<>();

        for (int ix = d; ix < expenditure.length; ix++) {
            if (ix == d) {
                rangeArr = Arrays.stream( Arrays.copyOfRange(expenditure, ix - d, ix) ).boxed().collect(Collectors.toCollection(ArrayList::new));
                rangeArr.sort(Integer::compareTo);
            } else {
                int rangeIdxRemove = find(rangeArr, expenditure[ix - d - 1]);
                rangeArr.remove(rangeIdxRemove);

                if (rangeArr.get(0) > expenditure[ix - 1]) {
                    rangeArr.add(0, expenditure[ix - 1]);
                } else if (rangeArr.get(rangeArr.size() - 1) < expenditure[ix - 1]) {
                    rangeArr.add(expenditure[ix - 1]);
                } else {
                    rangeArr.add(find(rangeArr, expenditure[ix - 1]) + 1, expenditure[ix - 1]);
                }
            }

            float median = calcMedian(rangeArr);
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