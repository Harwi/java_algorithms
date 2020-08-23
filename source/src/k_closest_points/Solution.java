package k_closest_points;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
    // Euclidean distance
    // N * (logK)
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Map.Entry<int[],Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        for (int[] point : points) {
            Map.Entry<int[], Integer> e = new AbstractMap.SimpleImmutableEntry(point, calcEucDistance(point));
            if (pq.size() < K || pq.peek().getValue() > calcEucDistance(point)) {
                System.out.println();
                if (pq.size() + 1 > K) {
                    pq.poll();
                }
                pq.offer(e);
            }
        }

        int[][] res = new int[pq.size()][2];
        int count = 0;
        while (pq.peek() != null) {
            res[count++] = pq.poll().getKey();
        }

        return res;
    }

    private Integer calcEucDistance(int[] point) {
        return point[0]*point[0] + point[1]*point[1];
    }
}
