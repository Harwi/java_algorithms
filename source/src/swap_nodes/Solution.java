package swap_nodes;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    static class BinaryTree {

        Integer[] tree;
        int levels;

        public BinaryTree(int[][] indexes) {
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


    /*
     * Complete the swapNodes function below.
     */
    static int[][] swapNodes(int[][] indexes, int[] queries) {
        BinaryTree binaryTree = new BinaryTree(indexes);
        int[][] res_indexes = new int[queries.length][];

        for (int ix = 0; ix < queries.length; ix++) {
            binaryTree.swap(queries[ix]);
            res_indexes[ix] = binaryTree.traverse();
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