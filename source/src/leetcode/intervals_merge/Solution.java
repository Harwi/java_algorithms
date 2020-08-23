package leetcode.intervals_merge;

import java.util.Arrays;

public class Solution {
    // TC: N(logN)
    // SC: N
    public int[][] merge(int[][] intervals) {
        if (intervals == null) {
            throw new IllegalStateException("Input is null");
        } else if (intervals.length <= 1) {
            return intervals;
        }

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int[][] res = new int[intervals.length][2];

        int[] prevInterval = intervals[0];
        int count = 0;
        for (int ix = 1; ix < intervals.length; ix++) {
            if (intervals[ix][0] >= prevInterval[0] && intervals[ix][0] <= prevInterval[1]) {
                prevInterval[1] = Math.max(intervals[ix][1], prevInterval[1]);
            } else {
                res[count++] = prevInterval;
                prevInterval = intervals[ix];
            }

            if (ix == (intervals.length - 1)) {
                res[count] = prevInterval;
            }
        }

        return Arrays.copyOf(res, count + 1);
    }
}
