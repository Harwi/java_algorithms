package swap_nodes;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    static class BinaryTree {

        int[] tree;
        int levels;

        public BinaryTree(int[][] indexes) {
            tree = new int[1 + indexes.length * 2];
            tree[0] = 1;
            for (int ix = 0; ix < indexes.length; ix++) {
                tree[ix * 2 + 1] = indexes[ix][0];
                tree[ix * 2 + 2] = indexes[ix][1];
            }
            levels = (int) Math.log(tree.length);
        }

        public void swapLevel(int lvl) {
            if (lvl > levels) {
                throw new IllegalStateException(String.format("Swap level %s is out of bounds", lvl));
            }
            int start_ix = (int) (Math.pow(2, lvl - 1) - 1);
            int end_ix = (int) (Math.pow(2, lvl) - 1);
            for (int ix = start_ix; ix < end_ix; ix =+ 2) {
                int buff = tree[ix];
                tree[ix] = tree[ix + 1];
                tree[ix + 1] = buff;
            }
        }

        public void swap(int k) {
            int multiplier = 1;
            while (multiplier * k <= levels) {
                swapLevel(multiplier * k);
                multiplier++;
            }
        }
    }


    /*
     * Complete the swapNodes function below.
     */
    static int[][] swapNodes(int[][] indexes, int[] queries) {
        int[] tree = new int[1 + indexes.length * 2];

        /*
         * Write your code here.
         */
        tree[0] = 1;
        for (int ix = 0; ix < indexes.length; ix++) {
            tree[ix * 2 + 1] = indexes[ix][0];
            tree[ix * 2 + 2] = indexes[ix][1];
        }



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