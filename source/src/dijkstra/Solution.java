package dijkstra;

import java.util.*;

public class Solution {

    static class DPQ {
        private final int dist[];
        private final Set<Integer> relaxed;
        private final PriorityQueue<Node> queue;
        private final int verticesSize;
        // Adjacency list representation of the
        // connected edges
        List<List<Node>> adjEdges;

        public DPQ(int vertices) {
            this.verticesSize = vertices;
            dist = new int[vertices];
            relaxed = new HashSet<>();
            queue = new PriorityQueue<>(vertices, new Node());
        }

        public void run(List<List<Node>> adjEdges, int startVertex) {
            this.adjEdges = adjEdges;

            for (int i = 0; i < this.verticesSize; i++) {
                dist[i] = Integer.MAX_VALUE;
            }

            // Add source node to the queue
            queue.add(new Node(startVertex, 0));
            // Set distance to the source is always 0
            dist[startVertex] = 0;

            // Proceed until relax all the vertices
            while (queue.peek() != null) {
                Node node = queue.remove();
                relaxed.add(node.vertex);
                relaxVertex(node.vertex);
            }
        }

        public void relaxVertex(int vertex) {
            int newDistance;

            // Traverse through all the neighbors of the vertex
            for (int nb = 0; nb < adjEdges.get(vertex).size(); nb++) {
                Node adjNode = adjEdges.get(vertex).get(nb);

                if (!relaxed.contains(adjNode.vertex)) {
                    if (dist[adjNode.vertex] == Integer.MAX_VALUE) {
                        newDistance = dist[vertex] + adjNode.cost;
                    } else if (dist[vertex] + adjNode.cost < dist[adjNode.vertex]) {
                        newDistance = dist[vertex] + adjNode.cost;
                    } else {
                        newDistance = dist[adjNode.vertex];
                    }
                    dist[adjNode.vertex] = newDistance;

                    queue.add(new Node(adjNode.vertex, dist[vertex]));
                }
            }
        }
    }

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

    // Driver code
    public static void main(String arg[])
    {
        int V = 5;
        int source = 0;

        // Adjacency list representation of the
        // connected edges
        List<List<Node> > adj = new ArrayList<>();

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
        DPQ dpq = new DPQ(V);
        dpq.run(adj, source);

        // Print the shortest path to all the nodes
        // from the source node
        System.out.println("The shorted path from node :");
        for (int i = 0; i < dpq.dist.length; i++)
            System.out.println(source + " to " + i + " is "
                    + dpq.dist[i]);
    }
}
