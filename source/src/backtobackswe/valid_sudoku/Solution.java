package backtobackswe.valid_sudoku;

import java.util.*;

public class Solution {
        public boolean validSudoku(int[][] board) {
            if (board == null || board.length == 0) {
                return true;
            }

            final Set<String> occur = new HashSet<>(90, 1f);

            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[row].length; col++) {
                    if (board[row][col] != 0) {
                        final String rowKey = String.format("r(%s)(%s)", row, board[row][col]);
                        final String colKey = String.format("c(%s)(%s)", row, board[row][col]);
                        final String gridKey = String.format("r(%s;%s)(%s)", col / 3, row / 3, board[row][col]);

                        if (occur.contains(rowKey) || occur.contains(colKey) || occur.contains(gridKey)) {
                            return false;
                        }

                        occur.add(rowKey);
                        occur.add(colKey);
                        occur.add(gridKey);
                    }
                }
            }

            return true;
        }
}
