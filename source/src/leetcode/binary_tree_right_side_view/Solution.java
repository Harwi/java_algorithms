package leetcode.binary_tree_right_side_view;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        traverse(root, res, 0);

        return res;
    }

    private void traverse(TreeNode node, List<Integer> res, int rank) {
        if (node == null) {
            return;
        }

        if (res.size() > rank) {
            res.set(rank, node.val);
        } else {
            res.add(node.val);
        }

        traverse(node.left, res, rank + 1);
        traverse(node.right, res, rank + 1);
    }
}
