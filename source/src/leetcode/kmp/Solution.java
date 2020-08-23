package leetcode.kmp;

public class Solution {

    public static void main(String... args) {
        String txt = "ABCXABCDABXABCDABCDABCY";
        String pat = "ABCDABCY";

        int res = kmp(txt, pat);
        System.out.println("Result index = " + res);

    }

    private static int kmp(String input, String pattern) {
        if (input == null || input.isEmpty() || pattern == null || pattern.isEmpty() || pattern.length() > input.length()) {
            return -1;
        }

        char[] patternArr = pattern.toCharArray();
        char[] inputArr = input.toCharArray();
        int[] compArr = computeArray(patternArr);

        int i = 0; // input pointer
        int p = 0; // pattern pointer

        while (i < input.length()) {
            if (patternArr[p] == inputArr[i]) {
                p++;
                if (p == patternArr.length) {
                    System.out.println("found pattern at " + (i + 1 - p));
                    return (i + 1 - p);
                }
            } else {
                if (p > 0) {
                    p = compArr[p - 1];
                }
            }
            i++;
        }

        return -1;
    }

    private static int[] computeArray(char[] pattern) {
        int i = 0;
        int j = 1;

        int[] res = new int[pattern.length];
        res[0] = 0;

        while (j < res.length) {
            if (pattern[j] == pattern[i]) {
                i++;
                res[j] = i;
                j++;
            } else {
                if (i == 0) {
                    j++;
                    res[j] = 0;
                } else {
                    j++;
                    i = res[i - 1];
                }
            }
        }

        return res;
    }
}
