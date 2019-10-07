package min_area_rectangle;

import java.util.*;

public class Solution {

    public int minAreaRect(int[][] points) {
        Map<Integer,Set<Integer>> map = new HashMap<>();
        int minArea = Integer.MAX_VALUE;

        for(int[] pairXY : points){
            // Group Y coordinates under the same X
            map.computeIfAbsent(pairXY[0], v -> new HashSet<>()).add(pairXY[1]);
        }

        int len = points.length;
        for(int i=0;i<len-1;i++){
            for(int j=i+1;j<len;j++){
                // check for area == 0
                if (points[i][0] == points[j][0] || points[i][1] == points[j][1]) {
                    continue;
                }

                // width * height
                int area = Math.abs((points[j][0] - points[i][0]) * (points[j][1] - points[i][1]));

                if (area > minArea)
                    continue;

                // Check if the corresponding pair of points exists
                if (map.get(points[i][0]).contains(points[j][1]) && map.get(points[j][0]).contains(points[i][1])) {
                    minArea = area;
                }
            }
        }

        return (minArea==Integer.MAX_VALUE) ? 0 : minArea;
    }
}
