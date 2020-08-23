package leetcode.bt_find_leaves;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        dfs(root, ans);
        return ans;
    }

    private int dfs(TreeNode node, List<List<Integer>> ans) {
        // is leaf?
        if (node.left == null && node.right == null) {
            if (ans.size() == 0) {
                ans.add(new ArrayList<>());
            }
            ans.get(0).add(node.val);
            return 1;
        }

        int l = -1, r = -1;
        if (node.left != null) l = dfs(node.left, ans);
        if (node.right != null) r = dfs(node.right, ans);

        int index = Math.max(l, r);
        if (ans.size() <= index) {
            ans.add(new ArrayList<>());
        }
        ans.get(index).add(node.val);
        return index + 1;
    }

}
