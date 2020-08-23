package leetcode.graph_shortest_reach;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static class Graph {

        static class Node {
            Integer vertex;
            Integer depth;

            public Node(Integer vertex, Integer depth) {
                this.vertex = vertex;
                this.depth = depth;
            }
        }

        final ArrayList<Integer>[] adjMatrix;

        public Graph(int size) {
            adjMatrix = (ArrayList<Integer>[]) new ArrayList[size];
            for (int mIx = 0; mIx < adjMatrix.length; mIx++) {
                adjMatrix[mIx] = new ArrayList<>();
            }
        }

        public void addEdge(int first, int second) {
            adjMatrix[first].add(second);
            adjMatrix[second].add(first);
        }

        public int[] shortestReach(int startId) { // 0 indexed
            int[] res = new int[adjMatrix.length];
            Arrays.fill(res, -1);
            Set<Integer> visited = new TreeSet<>();
            Queue<Node> verticesQueue = new LinkedList<>();
            verticesQueue.addAll(adjMatrix[startId].
                    stream().map(v -> new Node(v, 1)).collect(Collectors.toList()));

            while (verticesQueue.peek() != null) {
                Node node = verticesQueue.poll();
                if (visited.contains(node.vertex)) {
                    continue;
                }
                visited.add(node.vertex);

                res[node.vertex] = node.depth * 6;

                verticesQueue.addAll(adjMatrix[node.vertex].
                        stream().map(v -> new Node(v, node.depth + 1)).collect(Collectors.toList()));
            }

            return res;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int queries = scanner.nextInt();

        for (int t = 0; t < queries; t++) {

            // Create a graph of size n where each edge weight is 6:
            Graph graph = new Graph(scanner.nextInt());
            int m = scanner.nextInt();

            // read and set edges
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;

                // add each edge to the graph
                graph.addEdge(u, v);
            }

            // Find shortest reach from node s
            int startId = scanner.nextInt() - 1;
            int[] distances = graph.shortestReach(startId);

            for (int i = 0; i < distances.length; i++) {
                if (i != startId) {
                    System.out.print(distances[i]);
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        scanner.close();
    }
}