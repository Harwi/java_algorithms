package calc_liquid_volume;

public class Solution {

    public static void main (String[] args)
    {
        int[] myIntArray = {2, 5, 1, 3, 1, 2, 1, 7, 7, 6};
        System.out.println(calculateVolume(myIntArray));
    }

    private static final Long calculateVolume(int[] vertices) {
        int ixLeft = 0;
        int ixRight = vertices.length - 1;
        int maxLeft = 0;
        int maxRight = 0;
        long volume = 0;

        while (ixLeft < ixRight) {
            // check for left and right maximum every iteration
            if (vertices[ixLeft] > maxLeft) {
                maxLeft = vertices[ixLeft];
            }
            if (vertices[ixRight] > maxRight) {
                maxRight = vertices[ixRight];
            }

            if (maxLeft > maxRight) {
                volume = volume + (maxRight - vertices[ixRight]);
                ixRight--;
            } else {
                volume = volume + (maxLeft - vertices[ixLeft]);
                ixLeft++;
            }
        }

        return volume;
    }
}
