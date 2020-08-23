package leetcode.array_two_sorted_median;

public class Solution {
    // input arrays are sorted
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] combination = new int[nums1.length + nums2.length];

        int ix1 = 0;
        int ix2 = 0;

        for (int ixComb = 0; ixComb < combination.length; ixComb++) {
            if (ix1 < nums1.length && ix2 < nums2.length ) {
                combination[ixComb] = nums1[ix1] < nums2[ix2] ? nums1[ix1++] : nums2[ix2++];
            } else {
                combination[ixComb] = (ix1 < nums1.length ? nums1[ix1++] : nums2[ix2++]);
            }
        }

        if (combination.length % 2 == 0) {
            return (combination[combination.length / 2] + combination[(combination.length / 2) - 1]) / 2.0;
        } else {
            return combination[combination.length / 2];
        }
    }
}
