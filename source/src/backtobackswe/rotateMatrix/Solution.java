package backtobackswe.rotateMatrix;

class Solution {
    public int[][] rotate(int[][] matrix) {
        int size = matrix.length - 1;

        for (int layer = 0; layer <= matrix.length / 2; layer++) {
            for (int ix = layer; ix < size - layer; ix++) {
                int top = matrix[layer][ix];
                int right = matrix[ix][size - layer];
                int left = matrix[size - ix][layer];
                int bottom = matrix[size - layer][size - ix];

                matrix[layer][ix] = left;
                matrix[ix][size - layer] = top;
                matrix[size - layer][size - ix] = right;
                matrix[size - ix][layer] = bottom;
            }
        }

        return matrix;
    }
}