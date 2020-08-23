package leetcode.graph_topological_sorting;

import java.util.*;

public class Solution {

    // Directed acyclic graph
    static class Graph {

        final int vertices;
        final List<Integer>[] adj;

        public Graph(int vertices) {
            this.vertices = vertices;
            this.adj = new ArrayList[vertices];
            for (int i = 0; i < adj.length; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        public void addEdge(int from, int to) {
            adj[from].add(to);
        }

        public int[] topologicalSort() {
            int[] res = new int[vertices];
            int[] in = new int[vertices];

            for (int i = 0; i < adj.length; i++) {
                for (int j = 0; j < adj[i].size(); j++) {
                    in[adj[i].get(j)]++;
                }
            }

            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < in.length; i++) {
                if (in[i] == 0) {
                    queue.add(i);
                }
            }

            int cnt = 0;
            while (queue.peek() != null) {
                int v = queue.poll();
                res[cnt] = v;

                for (int a = 0; a < adj[v].size(); a++) {
                    Integer t = adj[v].get(a);
                    in[t]--;
                    if (in[t] == 0) {
                        queue.add(t);
                    }
                }
                cnt++;
            }

            return res;
        }
    }

    public static void main(String args[])
    {
        // Create a graph given in the above diagram
        Graph g = new Graph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        System.out.println("Following is a Topological Sort");
        int[] res = g.topologicalSort();

        System.out.println("Result = " + Arrays.toString(res));

    }
}
