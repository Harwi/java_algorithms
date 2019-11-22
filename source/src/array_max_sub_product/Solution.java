package array_max_sub_product;

//Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
//
//        Example 1:
//
//        Input: [2,3,-2,4]
//        Output: 6
//        Explanation: [2,3] has the largest product 6.
//        Example 2:
//
//        Input: [-2,0,-1]
//        Output: 0
//        Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

public class Solution {

    // O(2*N)
    public int maxProduct(int[] nums) {
        int prodSoFar = 1;
        int maxProd = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            prodSoFar = prodSoFar * nums[i];
            maxProd = Math.max(maxProd, prodSoFar);
            prodSoFar = prodSoFar == 0 ? 1 : prodSoFar;
        }

        prodSoFar = 1;
        for (int j = nums.length - 1; j >= 0; j--) {
            prodSoFar = prodSoFar * nums[j];
            maxProd = Math.max(maxProd, prodSoFar);
            prodSoFar = prodSoFar == 0 ? 1 : prodSoFar;
        }

        return maxProd;
    }

}
