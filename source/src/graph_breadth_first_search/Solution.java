package graph_breadth_first_search;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class Solution {

    public static void main(String[] args) {
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        int search = g.searchBfs(0, 3);
        System.out.println();
        search = g.searchDfs(0, 3);
    }

    static class Graph {
        final int vertices;
        final LinkedList<Integer> adj[];

        Graph(int vertices) {
            this.vertices = vertices;
            this.adj = new LinkedList[vertices];
            for (int ix = 0; ix < adj.length; ix++) {
                this.adj[ix] = new LinkedList<>();
            }
        }

        void addEdge(int vertex, int leaf) {
            if (adj.length < vertex) {
                throw new IllegalStateException(String.format("Vertex is out of bound. Graph has vertices number: %s", adj.length));
            }
            adj[vertex].add(leaf);
        }

        // BFS
        int searchBfs(int start, int lkp) {
            System.out.println("Starting breadth first search");

            if (adj.length < start) {
                throw new IllegalStateException(String.format("Start index is out of bound. Graph has vertices number: %s", adj.length));
            }

            // Protect against infinite loops.
            final boolean traversed[] = new boolean[adj.length];

            PriorityQueue<Integer> queue = new PriorityQueue<>();
            queue.add(start);
            traversed[start] = true;

            while (queue.peek() != null) {
                int edge = queue.poll();
                System.out.println(String.format("Traversing through edge: %s", edge));
                if (edge == lkp) {
                    System.out.println(String.format("Look up value is found: %s", edge));
                    return edge;
                }
                LinkedList<Integer> edges = adj[edge];
                edges.listIterator().forEachRemaining(
                        e -> {
                            if (!traversed[e]) {
                                queue.add(e);
                                traversed[e] = true;
                            }
                        }
                );
            }
            return -1;
        }

        // DFS
        int searchDfs(int start, int lkp) {
            System.out.println("Starting depth first search");

            // Protect against infinite loops.
            final boolean traversed[] = new boolean[adj.length];
            traversed[start] = true;

            return searchDfsUtil(start, lkp, traversed);
        }

        int searchDfsUtil(int edge, int lkp, boolean[] traversed) {
            if (adj.length < edge) {
                throw new IllegalStateException(String.format("Start index is out of bound. Graph has vertices number: %s", adj.length));
            }

            System.out.println(String.format("Traversing through edge: %s", edge));
            if (edge == lkp) {
                System.out.println(String.format("Look up value is found: %s", edge));
                return edge;
            }

            LinkedList<Integer> edges = adj[edge];
            edges.listIterator().forEachRemaining(
                    e -> {
                        if (!traversed[e]) {
                            traversed[e] = true;
                            searchDfsUtil(e, lkp, traversed);
                        }
                    }
            );
            return -1;
        }
    }

}
