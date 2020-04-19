package org.springbus;

// 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
//
// 示例：
//
// 给定一个链表: 1->2->3->4->5, 和 n = 2.
//
// 当删除了倒数第二个节点后，链表变为 1->2->3->5.
// 说明：
//
// 给定的 n 保证是有效的。
//
// 进阶：
//
// 你能尝试使用一趟扫描实现吗？
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class RemoveNthFromEnd {


    public static void main(String[] args) {
        Integer arrList[] = {1, 2, 3, 4, 5, 6};
        // arrList = new Integer[]{1,2,3,4,5};
        arrList = new Integer[]{1, 2, 3};
        ListNode l1 = TreePrintUtil.makeListNode(arrList);

        ListNode node = new RemoveNthFromEnd().removeNthFromEnd(l1, 3);
        TreePrintUtil.printListNode(node);
    }

    private int getNum(ListNode head) {
        int i = 0;
        ListNode root = head;
        while (root != null) {
            i++;
            root = root.next;
        }
        return i;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {

        int size = getNum(head);
        int index = size - n;
        ListNode root = head;
        ListNode prev = head;
        if (size <= 2) {
            if (index == 1) {
                head.next = null;
                return head;
            } else if (index == 0) {
                head = head.next;
                return head;
            }
        }
        if (index == 0) {
            head = head.next;
            return head;
        }
        while (index > 0 && root != null) {
            index--;
            prev = root;
            root = root.next;
        }
        if (prev != null) {

            prev.next = root.next;
            System.out.println(root.val);
        }
        return head;
    }

}

