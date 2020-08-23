package leetcode.list_add_two_nums;

class Solution {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode res = head;

        int buf = 0;
        while (l1 != null || l2 != null || buf > 0) {
            int total = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + buf;
            res.next = new ListNode(total % 10);
            buf = total / 10;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;

            res = res.next;
        }

        return head.next;
    }
}