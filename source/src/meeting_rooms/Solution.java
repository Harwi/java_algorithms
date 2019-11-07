package meeting_rooms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    // 0 - start time
    // 1 - end time
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparing((int[] t) -> t[0]));

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int rooms = 0;

        for (int[] interval : intervals) {
            if (queue.isEmpty()) {
                rooms++;
                queue.add(interval[1]);
            } else {
                if (interval[1] > queue.peek()) {
                    queue.poll();
                } else {
                    rooms++;
                }
            }
        }

        return rooms;
    }
}
