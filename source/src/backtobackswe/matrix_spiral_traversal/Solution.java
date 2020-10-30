package backtobackswe.matrix_spiral_traversal;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        final List<Integer> res = new ArrayList<>();

        if (matrix == null || matrix.length == 0) {
            return res;
        }

        int leftFence = 0, rightFence = matrix[0].length - 1, topFence = 0, bottomFence = matrix.length - 1;

        while (leftFence <= rightFence && topFence <= bottomFence) {
            for (int col = leftFence; col <= rightFence; col++) {
                res.add(matrix[topFence][col]);
            }
            topFence++;

            for (int row = topFence; row <= bottomFence; row++) {
                res.add(matrix[row][rightFence]);
            }
            rightFence--;

            if (topFence > bottomFence) {
                break;
            }

            for (int col = rightFence; col >= leftFence; col--) {
                res.add(matrix[bottomFence][col]);
            }
            bottomFence--;

            if (leftFence > rightFence) {
                break;
            }

            for (int row = bottomFence; row >= topFence; row--) {
                res.add(matrix[row][leftFence]);
            }
            leftFence++;
        }

        return res;
    }
}
