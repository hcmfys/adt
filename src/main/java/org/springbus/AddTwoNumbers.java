package org.springbus;

// 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
// 示例：
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
// 输出：7 -> 0 -> 8
// 原因：342 + 465 = 807
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/add-two-numbers
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class AddTwoNumbers {

    public static void main(String[] args) {
        Integer arrList[] = {9};
        // arrList=new Integer[]{null};
        ListNode l1 = TreePrintUtil.makeListNode(arrList);
        arrList = new Integer[]{9, 9, 9, 9, 9, 9, 9, 9, 9};
        ListNode l2 = TreePrintUtil.makeListNode(arrList);
        ListNode node = new AddTwoNumbers().addTwoNumbers2(l1, l2);
        TreePrintUtil.printListNode(node);
    }

    private int getSize(ListNode node) {
        ListNode root = node;
        int i = 0;
        while (root != null) {
            root = root.next;
            i++;
        }
        return i;
    }

    public void addOne(ListNode nodeC) {

        ListNode nextNode = nodeC.next;
        for (; ; ) {
            int c = nextNode.val + 1;
            if (c < 10) {
                nextNode.val = c;
                break;
            }
            nextNode.val = c - 10;
            nextNode = nextNode.next;
        }
    }

    public void addNext(ListNode nodeA, ListNode nodeC) {

        while (nodeA != null) {
            int a = nodeA.val + nodeC.val;
            if (a >= 10) {
                nodeC.val = a - 10;
                addOne(nodeC);
            } else {
                nodeC.val = a;
            }
            nodeC = nodeC.next;
            nodeA = nodeA.next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int lenA = getSize(l1);
        int lenB = getSize(l2);
        int maxSzie = Math.max(lenA, lenB) + 1;
        System.out.println(maxSzie);
        ListNode newNode = new ListNode(0);
        ListNode lastNode = newNode;
        for (int i = 1; i < maxSzie; i++) {
            ListNode node = new ListNode(0);
            lastNode.next = node;
            lastNode = node;
        }

        ListNode nodeA = l1;
        ListNode nodeB = l2;
        ListNode nodec = newNode;
        while (nodeA != null && nodeB != null) {

            int a = nodeA.val + nodeB.val + nodec.val;

            if (a >= 10) {
                nodec.val = a - 10;
                addOne(nodec);
            } else {
                nodec.val = a;
            }
            nodec = nodec.next;
            nodeA = nodeA.next;
            nodeB = nodeB.next;
        }
        if (nodeA != null) {
            addNext(nodeA, nodec);
        } else {
            addNext(nodeB, nodec);
        }
        lastNode = newNode;
        ListNode pre = lastNode;
        while (lastNode != null) {
            if (lastNode.next == null) {
                break;
            }
            pre = lastNode;
            lastNode = lastNode.next;
        }

        if (lastNode.val == 0) {
            pre.next = null;
        }
        return newNode;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode ll1 = l1;
        ListNode ll2 = l2;
        int carry = 0;
        ListNode ll = result;
        while (ll1 != null || ll2 != null) {
            int a = 0;
            int b = 0;
            if (ll1 != null) {
                a = ll1.val;
                ll1 = ll1.next;
            }
            if (ll2 != null) {
                b = ll2.val;
                ll2 = ll2.next;
            }
            int c = a + b + carry;
            ll.val = c % 10;
            carry = c / 10;

            if (ll1 != null || ll2 != null) {
                ll.next = new ListNode(0);
                ll = ll.next;
            }
        }
        if (carry > 0) {
            ll.next = new ListNode(carry);
        }
        return result;
    }
}
