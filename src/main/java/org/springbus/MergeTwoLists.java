package org.springbus;

public class MergeTwoLists {

  /**
   * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
   *
   * <p>示例：
   *
   * <p>输入：1->2->4, 1->3->4 输出：1->1->2->3->4->4
   *
   * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
   * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   *
   * @param l1
   * @param l2
   * @return
   */
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode newNode = new ListNode(0);
    return newNode;
  }

  public static void main(String[] args) {
    Integer arr[] = {1, 2, 4};
    ListNode l1 = TreePrintUtil.makeListNode(arr);
    arr = new Integer[] {1, 3, 4};
    ListNode l2 = TreePrintUtil.makeListNode(arr);

    ListNode a = new MergeTwoLists().mergeTwoLists(l1, l2);
    TreePrintUtil.printListNode(a);
        }

}
