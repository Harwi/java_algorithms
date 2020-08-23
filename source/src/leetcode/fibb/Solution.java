package leetcode.fibb;

public class Solution {

    public static void main(String... args) {
        long res_recur = recur(20);
        long res_iterative = iterative(20);

        System.out.println("Res recur = " + res_recur);
        System.out.println("Res iterative = " + res_iterative);
    }

    private static long recur(long v) {
        if (v <= 0) {
            throw new IllegalStateException("Value must exceed 0");
        } else if (v == 1 || v == 2) {
            return 1;
        } else {
            return recur(v - 1) + recur(v - 2);
        }
    }

    private static long iterative(int v) {
        if (v <= 0) {
            throw new IllegalStateException("Value must exceed 0");
        } else if (v == 1 || v == 2) {
            return 1;
        } else {
            long cur = 1;
            long prev = 1;
            long res = 0;

            for (int i = 3; i <= v; i++) {
                res = cur + prev;
                prev = cur;
                cur = res;
            }
            return res;
        }
    }
}
