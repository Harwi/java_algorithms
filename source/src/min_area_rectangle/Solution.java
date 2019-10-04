package min_area_rectangle;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        int[][] points = new int[][];
        int res = minAreaRect(points);
    }

    public static int minAreaRect(int[][] points) {
        final int limit = 40001;

        Map<Integer, HashSet<Integer>> xMap = new HashMap<>();
        Map<Integer, HashSet<Integer>> yMap = new HashMap<>();

        for (int[] point: points) {
            int x = point[0], y = point[1];
            xMap.computeIfAbsent(x, v -> new HashSet<>()).add(y);
            yMap.computeIfAbsent(y, v -> new HashSet<>()).add(x);
        }





        int ans = Integer.MAX_VALUE;
        Map<Integer, Integer> lastx = new HashMap();
        for (int x: rows.keySet()) {
            List<Integer> row = rows.get(x);
            Collections.sort(row);
            for (int i = 0; i < row.size(); ++i)
                for (int j = i+1; j < row.size(); ++j) {
                    int y1 = row.get(i), y2 = row.get(j);
                    int code = 40001 * y1 + y2;
                    if (lastx.containsKey(code))
                        ans = Math.min(ans, (x - lastx.get(code)) * (y2-y1));
                    lastx.put(code, x);
                }
        }

        return ans < Integer.MAX_VALUE ? ans : 0;
    }
}
