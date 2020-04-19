import org.springbus.ListNode;
import org.springbus.TreePrintUtil;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SwapPairs {

    public static void main(String[] args) {
        Integer arrList[] = {1, 2, 3, 4, 5};
        // arrList=new Integer[]{null};
        ListNode root = TreePrintUtil.makeListNode(arrList);
        TreePrintUtil.printListNode(root);
        ListNode node = new SwapPairs().swapPairs(root);
        TreePrintUtil.printListNode(node);
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode first = head;
        ListNode newRoot = null;
        ListNode joinNode = null;
        for (; ; ) {
            ListNode next = first.next;

            if (joinNode != null) {
                if (next != null) {
                    joinNode.next = next;
                } else {
                    joinNode.next = first;
                }
            }
            if (next == null) {
                break;
            }
            ListNode nextNode = next.next;
            if (newRoot == null) {
                newRoot = next;
            }
            next.next = first;
            first.next = nextNode;
            joinNode = first;
            first = nextNode;

            if (nextNode == null) {
                break;
            }
        }
        if (newRoot == null) {
            newRoot = head;
        }
        return newRoot;
    }


}
