package leetcode.matrix_max_square;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        char[][] arr = new char[5][4];
        arr[0] = new char[]{'0', '0', '0', '1'};
        arr[1] = new char[]{'1', '1', '0', '1'};
        arr[2] = new char[]{'1', '1', '1', '1'};
        arr[3] = new char[]{'0', '1', '1', '1'};
        arr[4] = new char[]{'0', '1', '1', '1'};
        maximalSquare(arr);
    }

    public static int maximalSquare(char[][] matrix) {

        if (matrix.length == 0) {
            return 0;
        }

        int maxSquare = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                int curr = matrix[row][col] - '0';
                if (maxSquare == 0 && curr != 0) {
                    maxSquare = curr;
                }
                if (row > 0 && col > 0 && curr != 0) {
                    int left = matrix[row][col - 1] - '0';
                    int upper = matrix[row - 1][col] - '0';
                    int diag = matrix[row - 1][col - 1] - '0';
                    if (left != 0 && upper != 0 && diag != 0) {
                        curr = Math.min(Math.min(left, upper), diag) + 1;
                        matrix[row][col] = (char) (curr + '0');
                        maxSquare = Math.max(maxSquare, curr);
                    }
                }
            }
        }

        return maxSquare * maxSquare;
    }

}
