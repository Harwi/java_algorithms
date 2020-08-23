package leetcode.insert_interval;

import java.util.ArrayList;

public class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        if (intervals == null || newInterval == null) {
            return null;
        } else if (intervals.length == 0 && newInterval.length > 0) {
            int[][] res = new int[1][2];
            res[0] = newInterval;
            return res;
        } else if (intervals.length > 0 && newInterval.length == 0) {
            return intervals;
        } else if (intervals.length == 0 && newInterval.length == 0) {
            return new int[0][];
        }

        boolean mergeIsDone = false;

        ArrayList<int[]> result = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            // source start date > target end date
            if (intervals[i][0] > newInterval[1]) {
                // add interval only once
                if (!mergeIsDone) {
                    result.add(newInterval);
                    mergeIsDone = true;
                }
                result.add(intervals[i]);
            // source end date < target start date
            } else if (intervals[i][1] < newInterval[0]) {
                result.add(intervals[i]);
            } else {
                newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
                newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            }
        }

        if (!mergeIsDone) {
            result.add(newInterval);
        }

        return result.toArray(new int[result.size()][2]);
    }
}
