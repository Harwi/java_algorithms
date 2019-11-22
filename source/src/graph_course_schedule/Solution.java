package graph_course_schedule;

//There are a total of n courses you have to take, labeled from 0 to n-1.
//
//        Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
//
//        Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
//
//        There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.


// EXAMPLE:

//Input: 4, [[1,0],[2,0],[3,1],[3,2]]
//        Output: [0,1,2,3] or [0,2,1,3]
//        Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
//        courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
//        So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0 || prerequisites == null) {
            return new int[0];
        }

        if (numCourses == 1) {
            return new int[]{0};
        }

        List<Integer>[] adj = new ArrayList[numCourses];
        int[] ingrees = new int[numCourses];

        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < prerequisites.length; i++) {
            adj[prerequisites[i][1]].add(prerequisites[i][0]);
            ingrees[prerequisites[i][0]]++;
        }

        int traversed = 0;
        Queue<Integer> queue = new LinkedList<>();

        // Find roots
        for (int i = 0; i < ingrees.length; i++) {
            if (ingrees[i] == 0) {
                queue.add(i);
            }
        }

        if (queue.isEmpty()) {
            // Empty cycle
            return new int[0];
        }

        int[] res = new int[numCourses];
        while (queue.peek() != null) {
            int v = queue.poll();
            res[traversed] = v;

            for (Integer edge : adj[v]) {
                ingrees[edge]--;
                if (ingrees[edge] == 0) {
                    queue.offer(edge);
                }
            }

            traversed++;
        }

        return traversed == numCourses ? res : new int[0];
    }

}
