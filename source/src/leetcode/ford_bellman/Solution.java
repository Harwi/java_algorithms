package leetcode.ford_bellman;

import java.util.*;

public class Solution {

    static class Node implements Comparator<Node> {
        public int vertex;
        public int cost;

        public Node() {
        }

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compare(Node node1, Node node2) {
            return 0;
        }
    }

    private static long[] run(List<List<Node>> nodes, int startIx) {
        if (nodes.size() - 1 < startIx) {
            throw new IllegalStateException("Start index is out of bounds");
        }

        long[][] res = new long[nodes.size() - 1][nodes.size()];
        for (int iter = 0; iter < res.length; iter++) {
            Arrays.fill(res[iter], Long.MAX_VALUE);
            res[iter][startIx] = 0;
        }

        // Fill in adj to start ix distances
        nodes.get(startIx).forEach(
                v -> {
                    res[0][v.vertex] = v.cost;
                }
        );

        // Then fill in based on values we already have
        // n - 1
        for (int iter = 1; iter < res.length; iter++) {
            // n
            for (int nodeIx = 0; nodeIx < nodes.size(); nodeIx++) {
                List<Node> adjNodes = nodes.get(nodeIx);
                // log(n)
                for (int adjNodeIx = 0; adjNodeIx < adjNodes.size(); adjNodeIx++) {
                    Node adjNode = adjNodes.get(adjNodeIx);
                    if (res[iter - 1][nodeIx] != Long.MAX_VALUE) {
                        res[iter][adjNode.vertex] = Math.min(res[iter - 1][adjNode.vertex], res[iter - 1][nodeIx] + adjNode.cost);
                    }
                }
            }
        }

        return res[res.length - 1];

        // res: n * n * log(n)
    }

    // Driver code
    public static void main(String arg[])
    {
        int V = 5;
        int source = 0;

        // Adjacency list representation of the
        // connected edges
        List<List<Node>> adj = new ArrayList<>();

        // Initialize list for every node
        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<>();
            adj.add(item);
        }

        // Inputs for the DPQ graph
        adj.get(0).add(new Node(1, 9));
        adj.get(0).add(new Node(2, 6));
        adj.get(0).add(new Node(3, 5));
        adj.get(0).add(new Node(4, 3));

        adj.get(2).add(new Node(1, 2));
        adj.get(2).add(new Node(3, 4));

        // Calculate the single source shortest path
        long[] res = run(adj, 0);

        // Print the shortest path to all the nodes
        // from the source node
        System.out.println("The shorted path from node :");
        for (int i = 0; i < res.length; i++)
            System.out.println(source + " to " + i + " is "
                    + res[i]);
    }
}