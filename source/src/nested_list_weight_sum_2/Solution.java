package nested_list_weight_sum_2;

import java.util.LinkedList;
import java.util.List;

public class Solution {

    public interface NestedInteger {
        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);

        public boolean isInteger();

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    private class Pair {

        public Pair(int val, int depth) {
            this.val = val;
            this.depth = depth;
        }

        int val;
        int depth;
    }

    public int depthSumInverse(List<NestedInteger> nestedList) {
        LinkedList<Pair> list = new LinkedList<>();
        int maxDepth = helper(nestedList, list, 1);
        int res = 0;

        for (Pair pair : list) {
            res += pair.val * (maxDepth + 1 - pair.depth);
        }
        return res;
    }

    public int helper(List<NestedInteger> nestedList, List<Pair> list, int currDepth) {
        int max = currDepth;
        for (NestedInteger n : nestedList) {
            if (n == null) continue;

            if (n.isInteger()) {
                list.add(new Pair(n.getInteger(), currDepth));
            } else {
                max = Math.max(max, helper(n.getList(), list, currDepth + 1));
            }
        }
        return max;
    }
}
