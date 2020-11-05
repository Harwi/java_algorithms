package backtobackswe.zigzag_conversion;

class Solution {
    public String zigzag(String s, int rows) {
        if (s == null || s.length() == 0 || rows < 0) {
            throw new IllegalStateException("Invalid arguments");
        }

        if (rows == 1) {
            return s;
        }

        final StringBuilder[] sbArr = new StringBuilder[rows];

        boolean down = false;
        int row = 0;

        for (final Character c : s.toCharArray()) {
            if (sbArr[row] == null)
                sbArr[row] = new StringBuilder();
            sbArr[row].append(c);

            if (row == 0 || row == rows - 1) {
                down = !down;
            }

            if (down) {
                row++;
            } else {
                row--;
            }
        }

        final StringBuilder res = new StringBuilder();
        for (StringBuilder sb : sbArr) {
            if (sb != null)
                res.append(sb);
        }

        return res.toString();
    }
}