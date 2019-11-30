package array_3sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// [-1, 0, 1, 2, -1, -4]
// [-4, -3, -1, 0, 1, 2]

class Solution {
    public static void main(String... args) {
        int[] nums = new int[]{-2,0,1,1,2};
        threeSum(nums);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        if (nums == null || nums.length < 3) {
            return res;
        }

        Arrays.sort(nums);

        int minTwoSum = 0;
        int maxTwoSum = 0;

        for (int ix = 0; ix <= 1 && nums[ix] < 0; ix++) {
            minTwoSum += nums[ix];
        }

        for (int ix = nums.length - 1; ix >= nums.length - 2 && nums[ix] > 0; ix--) {
            maxTwoSum += nums[ix];
        }

        int low = 0;
        int high = nums.length - 1;

        // check for values that are too low or too high
        while (low < high && nums[low] <= 0 && nums[high] >= 0) {
            if (-nums[low] > maxTwoSum) {
                low++;
            } else if (nums[high] > -minTwoSum) {
                high--;
            } else {
                break;
            }
        }

        int startIx = low;
        int endIx = high;

        for (int ix = startIx; ix < endIx - 1; ix++) {
            if (nums[ix] > 0) {
                break;
            } else if (ix > 0 && nums[ix] == nums[ix - 1]) {
                continue;
            }

            low = ix + 1;
            high = endIx;

            while (low < high && (nums[low] <= 0 || nums[high] >= 0)) {
                if (low - ix > 1 && nums[low] == nums[low - 1]) {
                    low++;
                } else if (high + 1 <= endIx && nums[high] == nums[high + 1]) {
                    high--;
                } else {
                    int tSum = nums[ix] + nums[low] + nums[high];

                    if (tSum == 0) {
                        res.add(Arrays.asList(nums[ix],nums[low],nums[high]));
                        low++;
                    } else if (tSum < 0) {
                        low++;
                    } else {
                        high--;
                    }
                }
            }
        }

        return res;
    }
}
