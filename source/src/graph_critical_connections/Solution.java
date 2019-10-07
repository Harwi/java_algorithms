package graph_critical_connections;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class Solution {

    private static AtomicInteger time = new AtomicInteger(0); // current time of discovery

    public static void main(String... args) {
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(0, 1));
        input.add(Arrays.asList(1, 2));
        input.add(Arrays.asList(2, 0));
        input.add(Arrays.asList(1, 3));
        criticalConnections(4, input);
    }

    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (List<Integer> conn : connections) {
            int n1 = conn.get(0);
            int n2 = conn.get(1);
            graph[n1].add(n2);
            graph[n2].add(n1);
        }

        int[] disc = new int[n]; // discovery time of each node
        int[] low = new int[n]; // earliest discovered node reachable from this node in DFS
        boolean[] visited = new boolean[n]; // whether this node has been visited in DFS
        List<List<Integer>> out = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, -1, disc, low, visited, graph, out);
            }
        }

        return out;
    }

    private static void dfs(int vertex, int parent, int[] disc, int[] low, boolean[] visited, List<Integer>[] graph, List<List<Integer>> out) {
        visited[vertex] = true;
        disc[vertex] = time.addAndGet(1);
        low[vertex] = disc[vertex];

        for (Integer adj : graph[vertex]) {
            if (adj.equals(parent)) {
                continue;
            }

            if (!visited[adj]) {
                dfs(adj, vertex, disc, low, visited, graph, out);
                low[vertex] = Math.min(low[vertex], low[adj]);
                if (disc[vertex] < low[adj]) {
                    out.add(Arrays.asList(vertex, adj));
                    System.out.println(String.format("Pair: %s, %s", vertex, adj));
                }
            } else {
                low[vertex] = Math.min(low[vertex], disc[adj]);
            }
        }
    }
}
