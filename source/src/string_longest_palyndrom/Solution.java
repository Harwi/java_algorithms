package string_longest_palyndrom;

import java.util.Arrays;

class Solution {

    static final Character DELIM = '#';

    // Manacher's algorithm
    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }

        final String newString = addSplitter(s.toCharArray());
        final char[] arr = newString.toCharArray();
        final int[] l = new int[newString.length()];
        Arrays.fill(l, 0);

        int center = 0;
        int rightBoundary = 0;
        int resCenterIx = 0;
        int maxLen = 0;

        for (int i = 0; i < arr.length; i++) {
            int ixMirror = center - (i - center);

            // Use mirror value in case right boundary is not reached yet
            if (i < rightBoundary) {
                l[i] = Math.min(rightBoundary - i, l[ixMirror]);
            }

            int left = i - (l[i] + 1);
            int right = i + (l[i] + 1);

            // expand over center
            while (right < newString.length() && left >= 0 && arr[left] == arr[right]) {
                l[i]++;
                left--;
                right++;
            }

            // keep track of max length and corresponding center index
            if (l[i] > maxLen) {
                resCenterIx = i;
                maxLen = l[i];
            }

            if ((i + l[i]) > rightBoundary) {
                center = i;
                rightBoundary = i + l[i];
            }
        }

        return String.join("", newString.substring(resCenterIx - maxLen, resCenterIx + maxLen + 1).split(DELIM.toString()));


    }

    private static String addSplitter(char[] arr) {

        StringBuilder resBuilder = new StringBuilder();
        resBuilder.append(DELIM);
        for (char c : arr) {
            resBuilder.append(c).append(DELIM);
        }

        return resBuilder.toString();
    }


    // dp
//    public String longestPalindrome(String s) {
//        char[] arr = s.toCharArray();
//        boolean[][] dp = new boolean[arr.length + 1][arr.length + 1];
//
//        int resStartIx = 0;
//        int resEndIx = 0;
//
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j <= i; j++) {
//                if (arr[i] == arr[j] && (i - j <= 1 || dp[j + 1][i - 1])) {
//                    dp[j][i] = true;
//                    if (i - j > resEndIx - resStartIx) {
//                        resStartIx = j;
//                        resEndIx = i + 1;
//                    }
//                }
//            }
//        }
//
//        return s.substring(resStartIx, resEndIx);
//
//    }
}
