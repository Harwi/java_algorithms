package leetcode.longest_path;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedHashSet;

public class Solution2 {

    public static void main(String[] args) {
        int[] pointers = {2, 7, 5, 0, 6, 7, 7, -1};

        System.out.println("Input:");
        for (int pointer : pointers) {
            System.out.print(pointer + " ");
        }

        Instant start = Instant.now();
        Instant end = Instant.now();
        System.out.println(String.format("Max path: %s", calc(pointers)));
        System.out.println(String.format("Elapsed millis: %s", Duration.between(start, end).toMillis()));
    }

    // Dynamic progamming with memoization
    private static int calc(int[] input) {
        int[] path = new int[input.length];
        int[] visited = new int[input.length];

        LinkedHashSet<Integer> visitedLocally = new LinkedHashSet<>();
        int idx = 0;
        int currLen = 0;
        int maxLen = 0;
        int iterCount = 0;

        while (idx < input.length) {
            System.out.println("Idx = " + idx);

            int add;
            boolean stop = false;

            // We know the rest part already
            if (visited[idx] != 0) {
                if (visitedLocally.isEmpty()) {
                    idx++;
                    continue;
                }
                add = path[idx];
                // Stop cycle if come across negative pointer or infinite loop
                stop = true;
            } else if (input[idx] < 0) {
                add = 0;
                stop = true;
            } else {
                add = Math.abs(idx - input[idx]);
            }

            iterCount++;

            // Save current path to prevent infinite loops
            visitedLocally.add(idx);

            visited[idx] = 1;

            if (add > 0) {
                // Updating path length for all the indexes we've visited so far
                for (Integer iter_idx : visitedLocally) {
                    iterCount++;
                    path[iter_idx] = path[iter_idx] + add;
                }
            }

            currLen += add;

            if (stop) {
                idx = visitedLocally.iterator().next() + 1;
                visitedLocally.clear();
                maxLen = Math.max(currLen, maxLen);
                currLen = 0;
            } else {
                idx = input[idx];
            }

        }

        System.out.println(String.format("Number of iterations: %s. Total elements: %s", iterCount, input.length));
        return maxLen;
    }
}
