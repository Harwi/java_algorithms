package leetcode.graph_nearest_clone;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    // Complete the findShortest function below.

    /*
     * For the unweighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] to <name>To[i].
     *
     */
    static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {
        ShortestPath shortestPath = new ShortestPath(graphNodes, graphFrom, graphTo, ids);
        int result = shortestPath.find(val);
        System.out.println("Result = " + result);
        return result;
    }

    static class ShortestPath {

        static class Edge {
            int distance;
            int vertex;
            int rootVertex;

            public Edge(int distance, int vertex, int rootVertex) {
                this.distance = distance;
                this.vertex = vertex;
                this.rootVertex = rootVertex;
            }
        }

        private final ArrayList<Integer>[] adjMatrix;
        private final long[] colors;
        private final boolean[] visited;

        public ShortestPath(int numOfNodes, int[] graphFrom, int[] graphTo, long[] colors) {
            this.adjMatrix = (ArrayList<Integer>[]) new ArrayList[numOfNodes];
            for (int mIx = 0; mIx < this.adjMatrix.length; mIx++) {
                adjMatrix[mIx] = new ArrayList<>();
            }
            for (int gIx = 0; gIx < graphFrom.length; gIx++) {
                adjMatrix[graphFrom[gIx] - 1].add(graphTo[gIx] - 1);
                adjMatrix[graphTo[gIx] - 1].add(graphFrom[gIx] - 1);
            }

            this.colors = colors;
            this.visited = new boolean[numOfNodes];
        }

        public int find(int color) {
            Queue<Edge> queue = new LinkedList<>();
            TreeSet<Integer>[] visited = (TreeSet<Integer>[]) new TreeSet[colors.length];

            for (int nodeIx = 0; nodeIx < colors.length; nodeIx++) {
                if (colors[nodeIx] == color) {
                    final int rootVertex = nodeIx;
                    visited[nodeIx] = new TreeSet<>();
                    visited[nodeIx].add(nodeIx);

                    queue.addAll(
                            adjMatrix[nodeIx]
                                    .stream()
                                    .map(v -> new Edge(1, v, rootVertex))
                                    .collect(Collectors.toList()));
                }
            }

            while (queue.peek() != null) {
                Edge edge = queue.poll();
                if (visited[edge.rootVertex].contains(edge.vertex)) {
                    continue;
                }
                //System.out.println(String.format("Root = %s, vertex = %s, dist = %s", edge.rootVertex, edge.vertex, edge.distance));

                visited[edge.rootVertex].add(edge.vertex);
                if (colors[edge.vertex] == color) {
                    return edge.distance;
                } else {
                    queue.addAll(adjMatrix[edge.vertex]
                            .stream()
                            .map(v -> new Edge(edge.distance + 1, v, edge.rootVertex))
                            .collect(Collectors.toList()));
                }
            }
            return -1;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] graphNodesEdges = br.readLine().split(" ");
        int graphNodes = Integer.parseInt(graphNodesEdges[0].trim());
        int graphEdges = Integer.parseInt(graphNodesEdges[1].trim());

        int[] graphFrom = new int[graphEdges];
        int[] graphTo = new int[graphEdges];

        for (int i = 0; i < graphEdges; i++) {
            String[] graphFromTo = br.readLine().split(" ");
            graphFrom[i] = Integer.parseInt(graphFromTo[0].trim());
            graphTo[i] = Integer.parseInt(graphFromTo[1].trim());
        }

        long[] ids = new long[graphNodes];

        String[] idsItems = br.readLine().split(" ");

        for (int i = 0; i < graphNodes; i++) {
            int idsItem = Integer.parseInt(idsItems[i]);
            ids[i] = idsItem;
        }

        int val = Integer.parseInt(br.readLine().split(" ")[0]);
        br.close();

        int ans = findShortest(graphNodes, graphFrom, graphTo, ids, val);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}