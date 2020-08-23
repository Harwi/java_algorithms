package leetcode.swap_nodes;

import java.io.*;
import java.util.*;

public class Solution {

    static class BinaryTreeArrBased {

        Integer[] tree;
        int levels;

        public BinaryTreeArrBased(int[][] indexes) {
            tree = new Integer[1 + indexes.length * 2];
            levels = (int) Math.ceil(Math.log(tree.length)/Math.log(2));
            int tree_size = (int) Math.pow(2, levels);
            tree[0] = 1;
            for (int ix = 0; ix < indexes.length; ix++) {
                tree[ix * 2 + 1] = indexes[ix][0];
                tree[ix * 2 + 2] = indexes[ix][1];
            }
            ArrayList<Integer> fillGapsTree = new ArrayList<>(Arrays.asList(tree));
            // filling gaps
            for (int ix = 0; leftIx(ix) < tree_size; ix++) {
                if (fillGapsTree.get(ix) == -1) {
                    int leftIx = leftIx(ix);
                    int rightIx = leftIx + 1;
                        fillGapsTree.add(leftIx, -1);
                        fillGapsTree.add(rightIx, -1);
                }
            }
            tree = fillGapsTree.toArray(new Integer[fillGapsTree.size()]);
        }

        private void swapLevel(int lvl) {
            if (lvl > levels) {
                throw new IllegalStateException(String.format("Swap level %s is out of bounds", lvl));
            }
            // Get indexes for direct children
            int start_ix = getLevelStartIx(lvl + 1);
            int end_ix = getLevelEndIx(lvl + 1);
            for (int ix = start_ix; ix < end_ix && end_ix < tree.length; ix += 2) {
                int buff = tree[ix];
                tree[ix] = tree[ix + 1];
                tree[ix + 1] = buff;
            }
        }

        public void swap(int k) {
            int multiplier = 1;
            while (multiplier * k < levels) {
                swapLevel(multiplier * k);
                multiplier++;
            }
        }

        private int parentIx(int ix) {
            return (int) Math.ceil(ix / 2);
        }

        private int leftIx(int parentIx) {
            return parentIx * 2 + 1;
        }

        private int rightIx(int parentIx) {
            return leftIx(parentIx) + 1;
        }

        private int getLevel(int ix) {
            return (int) Math.ceil(Math.log(ix) + 1);
        }

        private int getLevelStartIx(int lvl) {
            return (int) (Math.pow(2, lvl - 1) - 1);
        }

        private int getLevelEndIx(int lvl) {
            return (int) (Math.pow(2, lvl) - 2);
        }

        public int[] traverse() {
            List<Integer> elements = new LinkedList<>();
            // left tree
            traverseRecur(1, elements);
            // root
            elements.add(tree[0]);
            // right tree
            traverseRecur(2, elements);

            return elements.stream().mapToInt(i -> i).toArray();
        }

        private void traverseRecur(int ix, List<Integer> elements) {
            if (leftIx(ix) < tree.length && tree[leftIx(ix)] != -1) {
                int leftIx = leftIx(ix);
                elements.add(tree[leftIx]);
                traverseRecur(leftIx, elements);
            } else if (rightIx(ix) < tree.length && tree[rightIx(ix)] != -1) {
                int rightIx = rightIx(ix);
                elements.add(tree[rightIx]);
                traverseRecur(rightIx, elements);
            }
        }
    }

    static class Node {

        final Integer val;
        Node left;
        Node right;

        public Node(Integer val) {
            this.val = val;

        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public boolean hasChildren() {
            return (this.left != null || this.right != null);
        }

        public void swapNode(int k) {
            swapNodeRecur(this, k, 1);
        }

        private static void swapNodeRecur(Node node, int k, int nodeLevel) {
            if (nodeLevel % k == 0) {
                Node buffNode = node.left;
                node.left = node.right;
                node.right = buffNode;
            }

            if (node.left != null) {
                swapNodeRecur(node.left, k, nodeLevel + 1);
            }
            if (node.right != null) {
                swapNodeRecur(node.right, k, nodeLevel + 1);
            }
        }

        public int[] traverse() {
            ArrayList<Integer> indexesInOrder = new ArrayList<>();

            if (this.left != null) {
                traverseRecur(this.left, indexesInOrder);
            }
            indexesInOrder.add(this.val);
            if (this.right != null) {
                traverseRecur(this.right, indexesInOrder);
            }

            int[] res = new int[indexesInOrder.size()];
            for (int ix = 0; ix < indexesInOrder.size(); ix++) {
                res[ix] = indexesInOrder.get(ix);
            }

            return res;
        }

        public static void traverseRecur(Node node, ArrayList<Integer> indexes) {
            if (node.left != null) {
                traverseRecur(node.left, indexes);
            }
            indexes.add(node.val);
            if (node.right != null) {
                traverseRecur(node.right, indexes);
            }
        }
    }

    /*
     * Complete the swapNodes function below.
     */
    static int[][] swapNodes(int[][] indexes, int[] queries) {
        //BinaryTreeArrBased binaryTree = new BinaryTreeArrBased(indexes);
        Queue<Node> nodeQueue = new LinkedList<>();
        Node rootNode = new Node(1);
        nodeQueue.add(rootNode);

        int[][] res_indexes = new int[queries.length][];

        for (int ix = 0; ix < indexes.length && nodeQueue.peek() != null; ix++) {
            Node parent = nodeQueue.poll();
            if (indexes[ix][0] != -1) {
                parent.setLeft(new Node(indexes[ix][0]));
                nodeQueue.offer(parent.left);
            }
            if (indexes[ix][1] != -1) {
                parent.setRight(new Node(indexes[ix][1]));
                nodeQueue.offer(parent.right);
            }
        }

        for (int ixQuery = 0; ixQuery < queries.length; ixQuery++) {
            rootNode.swapNode(queries[ixQuery]);
            res_indexes[ixQuery] = rootNode.traverse();
        }

        return res_indexes;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] indexes = new int[n][2];

        for (int indexesRowItr = 0; indexesRowItr < n; indexesRowItr++) {
            String[] indexesRowItems = scanner.nextLine().split(" ");

            for (int indexesColumnItr = 0; indexesColumnItr < 2; indexesColumnItr++) {
                int indexesItem = Integer.parseInt(indexesRowItems[indexesColumnItr].trim());
                indexes[indexesRowItr][indexesColumnItr] = indexesItem;
            }
        }

        int queriesCount = Integer.parseInt(scanner.nextLine().trim());

        int[] queries = new int[queriesCount];

        for (int queriesItr = 0; queriesItr < queriesCount; queriesItr++) {
            int queriesItem = Integer.parseInt(scanner.nextLine().trim());
            queries[queriesItr] = queriesItem;
        }

        int[][] result = swapNodes(indexes, queries);

        for (int resultRowItr = 0; resultRowItr < result.length; resultRowItr++) {
            for (int resultColumnItr = 0; resultColumnItr < result[resultRowItr].length; resultColumnItr++) {
                bufferedWriter.write(String.valueOf(result[resultRowItr][resultColumnItr]));

                if (resultColumnItr != result[resultRowItr].length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            if (resultRowItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}