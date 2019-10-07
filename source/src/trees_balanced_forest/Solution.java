package trees_balanced_forest;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static class Node {
        List<Node> children;
        long cumulativeSum;
        int index;

        public Node(int index) {
            this(new ArrayList<>(), index);
        }

        public Node(List<Node> children, int index) {
            this.children = children;
            this.cumulativeSum = 0;
            this.index = index;
        }

        public void setCumulativeSum(long cumulativeSum) {
            this.cumulativeSum = cumulativeSum;
        }

        public List<Node> getChildren() {
            return children;
        }

        public long getCumulativeSum() {
            return cumulativeSum;
        }

        public int getIndex() {
            return index;
        }

        public void addChild(Node node) {
            this.children.add(node);
        }
    }

    // Complete the balancedForest function below.
    static int balancedForest(int[] c, int[][] edges) {
        Node[] nodes = new Node[edges.length];

        for (int egdeIx = 0; egdeIx < edges.length; egdeIx++) {
            Node node = new Node(edges[egdeIx][0]);
            node.addChild(new Node(edges[egdeIx][1]));
            //nodes[node.getIndex()] =
        }

        return 0;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] c = new int[n];

            String[] cItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int cItem = Integer.parseInt(cItems[i]);
                c[i] = cItem;
            }

            int[][] edges = new int[n - 1][2];

            for (int i = 0; i < n - 1; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int result = balancedForest(c, edges);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

