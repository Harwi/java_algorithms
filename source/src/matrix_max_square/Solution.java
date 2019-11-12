package matrix_max_square;

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
        int maxSquare = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (maxSquare == 0 && matrix[row][col] != '0') {
                    maxSquare = Character.getNumericValue(matrix[row][col]);
                }
                if (row > 0 && col > 0 && matrix[row][col] != '0') {
                    if (matrix[row - 1][col - 1] != '0' && matrix[row - 1][col] != '0' && matrix[row][col - 1] != '0') {
                        int left = Character.getNumericValue(matrix[row][col - 1]);
                        int upper = Character.getNumericValue(matrix[row - 1][col]);
                        int diag = Character.getNumericValue(matrix[row - 1][col - 1]);

                        matrix[row][col] = Character.forDigit(Math.min(Math.min(left, upper), diag) + 1, 10);
                        maxSquare = Math.max(maxSquare, Character.getNumericValue(matrix[row][col]));
                    }
                }
            }
        }

        return maxSquare * maxSquare;
    }

}
