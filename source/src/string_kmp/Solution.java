package string_kmp;

public class Solution {

    // Function to implement KMP algorithm
    public static int KMP(String search, String pattern) {
        // Base Case 1: Y is null or empty
        if (pattern == null || pattern.length() == 0) {
            System.out.println("Pattern occurs with shift 0");
            return -1;
        }

        // Base Case 2: X is null or X's length is less than that of Y's
        if (search == null || pattern.length() > search.length()) {
            System.out.println("Pattern not found");
            return -1;
        }

        char[] chars = pattern.toCharArray();

        // next[i] stores the index of next best partial match
        int[] next = new int[pattern.length() + 1];
        for (int i = 1; i < pattern.length(); i++) {
            int j = next[i + 1];

            while (j > 0 && chars[j] != chars[i])
                j = next[j];

            if (j > 0 || chars[j] == chars[i])
                next[i + 1] = j + 1;
        }

        for (int i = 0, j = 0; i < search.length(); i++) {
            if (j < pattern.length() && search.charAt(i) == pattern.charAt(j)) {
                if (++j == pattern.length()) {
                    System.out.println("Pattern occurs with shift " +
                            (i - j + 1));
                }
            } else if (j > 0) {
                j = next[j];
                i--;    // since i will be incremented in next iteration
            }
        }
    }


    public int strStr(String haystack, String needle) {
        int[] needleTable = new int[needle.length()];
        int i = 0;
        int j = 1;

        while (i < needle.length() && j < needle.length()) {
            if (needle.charAt(i) == needle.charAt(j)) {

            }
        }

        for (int i = 0, j = 1; i < needle.length() || j < needle.length(); ) {
            for (int j = i + 1; j < needle.length(); j++)
        }

    }
}
