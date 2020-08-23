package leetcode.array_product_except_itself;

public class Solution {

    // Solve without division.
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int prodLeft = 1;
        for (int i = 0; i < res.length; i++) {
            res[i] = nums[i] * prodLeft;
            prodLeft = res[i];
        }
        int prodRight = 1;
        for (int i = res.length - 1; i >= 0; i--) {
            if (i > 0) {
                res[i] = res[i - 1] * prodRight;
                prodRight = prodRight * nums[i];
            } else {
                res[i] = prodRight;
            }
        }
        return res;
    }
}
