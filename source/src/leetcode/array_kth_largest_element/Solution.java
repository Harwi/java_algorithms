package leetcode.array_kth_largest_element;

import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public static int findKthLargest(int[] nums, int k) {
        int queueSize = k;
        PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize, Comparator.naturalOrder());

        for (int num : nums) {
            // Don't allow the queue to grow
            if (queue.size() >= queueSize) {
                if (queue.peek() < num) {
                    queue.poll();
                    queue.add(num);
                }
            } else {
                queue.add(num);
            }
        }

        return queue.peek();
    }

    public static void main(String... args) {
        String arr = "3,2,1,5,6,4";
        int res = findKthLargest(Arrays.stream(arr.split(",")).mapToInt(Integer::parseInt).toArray(), 2);
        System.out.println(res);
    }
}
