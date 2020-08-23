package leetcode.trees_bt_height;

import java.util.*;

class Node {
    Node left;
    Node right;
    int data;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Solution {

    private static int traverse(Node node, int depth) {
        int leftDepth = depth;
        int rightDepth = depth;
        if (node.left != null) {
            leftDepth = traverse(node.left, depth + 1);
        }
        if (node.right != null) {
            rightDepth = traverse(node.right, depth + 1);
        }
        return Math.max(leftDepth, rightDepth);
    }

    public static int height(Node root) {
        return traverse(root, 0);
    }

    public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        int height = height(root);
        System.out.println(height);
    }
}