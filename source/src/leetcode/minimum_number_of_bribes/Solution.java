package leetcode.minimum_number_of_bribes;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
        if (q == null || q.length == 0) {
            throw new IllegalStateException("Input queue is empty");
        } else if (q.length == 1) {
            System.out.println(0);
        }

        int swapNum = 0;
        LinkedList<Integer> target = new LinkedList<>(Arrays.stream(q).sorted().boxed().collect(Collectors.toList()));
        for (int number : q) {
            int index = target.indexOf(number);
            if (index >= 3) {
                System.out.println("Too chaotic");
                return;
            }

            swapNum += index;
            target.remove(index);
        }

        System.out.println(swapNum);


    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}