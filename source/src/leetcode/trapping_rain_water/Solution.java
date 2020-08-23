package leetcode.trapping_rain_water;

class Solution {
    public int trap(int[] height) {
        int ixLeft = 0;
        int ixRight = height.length - 1;
        int maxLeft = 0;
        int maxRight = 0;
        int volume = 0;

        while (ixLeft < ixRight) {
            maxLeft = Math.max(height[ixLeft], maxLeft);
            maxRight = Math.max(height[ixRight], maxRight);

            if (maxLeft > maxRight) {
                volume += maxRight - height[ixRight--];
            } else {
                volume += maxLeft - height[ixLeft++];
            }
        }

        return volume;
    }
}