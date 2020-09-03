package org.springbus;

// 反转一个单链表。
//
// 示例:
//
// 输入: 1->2->3->4->5->NULL
// 输出: 5->4->3->2->1->NULL
//
// 进阶:
// 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
// Related Topics 链表

// leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode next; ListNode(int
 * x) { val = x; } }
 */
public class ReverseList {
    public static void main(String[] args) {
        Integer arrList[] = {1, 2, 3, 4, 5, 6};
        // arrList=new Integer[]{null};
        ListNode root = TreePrintUtil.makeListNode(arrList);
        ListNode node = new ReverseList().reverseList(root);
        TreePrintUtil.printListNode(node);
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode nextNode = head.next;
        ListNode nextNextNode = null;
        ListNode prev = head;
        while (nextNode != null) {
            nextNextNode = nextNode.next;
            nextNode.next = prev;
            prev = nextNode;
            nextNode = nextNextNode;
        }
        head.next = null;
        head = prev;
        return head;
    }
}
