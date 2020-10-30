package backtobackswe.count_sub_arrays;

class Solution {
    public int countSubarrays(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int count = 0;
        int[] sum = new int[arr.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= arr.length; i++)
            sum[i] = sum[i - 1] + arr[i - 1];

        for (int start = 0; start < arr.length; start++) {
            for (int end = start + 1; end <= arr.length; end++) {
                if (sum[end] - sum[start] == k)
                    count++;
            }
        }
        return count;

    }
}
