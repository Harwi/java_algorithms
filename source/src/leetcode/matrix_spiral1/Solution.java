package leetcode.matrix_spiral1;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        int[][] res = buildMatrix(6, 5);
    }

    private static void printMatrix(int[][] arr) {
        for (int ix = 0; ix < arr.length; ix++) {
            System.out.println(Arrays.toString(arr[ix]));
        }
        System.out.println();
    }

    public static int[][] buildMatrix(int rows, int cols) {
        return buildMatrixRecur(0, rows - 1, 0, cols - 1, 1, new int[rows][cols]);
    }

    public static int[][] buildMatrixRecur(int startRow, int endRow, int startCol, int endCol, int counter, int[][] arr) {

        for (int ix = startCol; ix <= endCol; ix++) {
            arr[startRow][ix] = counter++;
        }

        for (int ix = startRow + 1; ix <= endRow; ix++) {
            arr[ix][endCol] = counter++;
        }

        for (int ix = endCol - 1; ix >= startCol; ix--) {
            arr[endRow][ix] = counter++;
        }

        for (int ix = endRow - 1; ix > startRow; ix--) {
            arr[ix][startCol] = counter++;
        }

        printMatrix(arr);

        startRow++; endRow--; startCol++; endCol--;

        if (startRow <= endRow && startCol <= endCol) {
            buildMatrixRecur(startRow, endRow, startCol, endCol, counter, arr);
        }

        return arr;
    }

}