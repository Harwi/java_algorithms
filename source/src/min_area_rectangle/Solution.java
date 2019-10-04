package min_area_rectangle;

import java.util.*;

public class Solution {

    public int minAreaRect(int[][] points) {
        Map<Integer,Set<Integer>> map = new HashMap<>();
        int minArea = Integer.MAX_VALUE;

        for(int[] point:points){
            // Group Y coordinates under the same X
            map.computeIfAbsent(point[0], v -> new HashSet<>()).add(point[1]);
        }

        int len = points.length;
        for(int i=0;i<len-1;i++){
            for(int j=i+1;j<len;j++){
                //always get two points
                int width = Math.abs(points[j][0] - points[i][0]);
                int height = Math.abs(points[j][1] - points[i][1]);
                int area = width*height;

                if (area == 0 || area > minArea)
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
