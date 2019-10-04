package trees_check_is_bst;

public class Solution {

    class Node {
        int data;
        Node left;
        Node right;
    }

    boolean checkNode(Node node, int min, int max) {
        if (node == null) {
            return true;
        }
        if (node.data <= min || node.data >= max) {
            return false;
        } else {
            return checkNode(node.left, min, node.data)
                    && checkNode(node.right, node.data, max);
        }
    }

    boolean checkBST(Node root) {
        return checkNode(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
