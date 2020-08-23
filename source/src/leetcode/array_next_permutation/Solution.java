package leetcode.array_next_permutation;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{1,5,3,4,2};
        nextPermutation(nums);
        for (Integer num: nums) {
            System.out.print(num + " ");
        }
    }


    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int i = nums.length - 1;
        // find the first preceding element larger than current
        while (i > 0 && nums[i] <= nums[i - 1]) {
            i--;
        }
        // check for descending array
        if (i > 0) {
            Arrays.sort(nums, i, nums.length);
            int k = i;
            while (k < nums.length && nums[k] <= nums[i - 1])
                k++;
            int tmp = nums[i - 1];
            nums[i - 1] = nums[k];
            nums[k] = tmp;
        } else {
            Arrays.sort(nums);
        }
    }
}
