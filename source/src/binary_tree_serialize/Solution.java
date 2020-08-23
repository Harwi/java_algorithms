package binary_tree_serialize;

import java.util.concurrent.atomic.AtomicInteger;

public class Solution {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private static final String DELIM = "#";
    private static final String EMPTY = "*";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }

        StringBuilder res = new StringBuilder();
        serializeRecur(root, res);
        System.out.println(res.toString());
        return res.toString().substring(0, res.length() - 1);
    }

    // serialize left tree first
    private void serializeRecur(TreeNode node, StringBuilder stringBuilder) {
        if (node == null) {
            stringBuilder.append(EMPTY).append(DELIM);
        } else {
            stringBuilder.append(node.val).append(DELIM);
            serializeRecur(node.left, stringBuilder);
            serializeRecur(node.right, stringBuilder);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }

        String[] arr = data.split(DELIM);

        return deserializeRecur(arr, new AtomicInteger(0));
    }

    private TreeNode deserializeRecur(String[] arr, AtomicInteger ix) {
        if (ix.get() >= arr.length) {
            return null;
        }

        String s = arr[ix.getAndIncrement()];
        if (s.equals(EMPTY)) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(s));
        node.left = deserializeRecur(arr, ix);
        node.right = deserializeRecur(arr, ix);

        return node;
    }

}
