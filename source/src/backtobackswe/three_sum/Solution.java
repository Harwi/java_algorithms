package backtobackswe.three_sum;

import java.util.*;

public class Solution {

    public List<List<Integer>> threeSum(int[] A) {
        Arrays.sort(A);

        Set<List<Integer>> res = new HashSet<>();

        for (int ix = 0; ix < A.length; ix++) {
            find(ix, A, res);
        }

        return new ArrayList<>(res);
    }

    private void find(int index, int[] source, Set<List<Integer>> res) {
        if (index + 2 < source.length) {
            int left = index + 1;
            int right = source.length - 1;

            while (left < right) {
                int threeSum = source[index] + source[left] + source[right];

                if (threeSum == 0) {
                    res.add(Arrays.asList(source[index], source[left++], source[right--]));
                } else if (threeSum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
    }
}
