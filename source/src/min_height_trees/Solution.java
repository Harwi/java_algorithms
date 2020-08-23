package min_height_trees;

import java.util.*;

public class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n < 3) {
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                res.add(i);
            }
            return res;
        }

        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegrees = new int[n];

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge :
                edges) {
            inDegrees[edge[0]]++;
            inDegrees[edge[1]]++;

            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> q = new LinkedList<>();
        int vertices = n;
        // Add leafs
        for (int i = 0; i < vertices; i++) {
            if (inDegrees[i] == 1) {
                q.add(i);
                n--;
            }
        }

        while (!q.isEmpty() && n != 0) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int node = q.poll();
                for (int adj : graph.get(node)) {
                    inDegrees[adj]--;
                    if (inDegrees[adj] == 1) {
                        q.add(adj);
                        // decrement n until last one/two root nodes remain in the queue
                        n--;
                    }
                }
            }
        }

        return new ArrayList<>(q);
    }
}
