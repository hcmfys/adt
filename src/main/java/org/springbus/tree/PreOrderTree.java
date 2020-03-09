package org.springbus.tree;

import com.sun.xml.internal.ws.api.pipe.ServerTubeAssemblerContext;
import jdk.internal.dynalink.beans.StaticClass;
import org.springbus.Queue;
import org.springbus.TreeNode;
import org.springbus.TreePrintUtil;

import javax.swing.text.TabableView;
import java.nio.channels.NonReadableChannelException;
import java.util.LinkedList;
import java.util.Stack;

public class PreOrderTree {


    /**
     * 对于当前节点，先输出该节点，然后输出他的左孩子，最后输出他的右孩子。以上图为例，递归的过程如下
     * @param root
     */
    public static  void preOrder(TreeNode root) {
        System.out.print("先序遍历: ");
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode t = stack.pop();
            System.out.print(t.val +" ");
            TreeNode rightNode = t.right;
            if (rightNode != null) {
                stack.push(rightNode);
            }
            TreeNode leftNode = t.left;
            if (leftNode != null) {
                stack.push(leftNode);
            }
        }
        System.out.println("");
    }

    /**
     * 对于当前结点，先输出它的左孩子，然后输出该结点，最后输出它的右孩子。以上图为例：
     * @param root
     */

    public static  void inOrder(TreeNode root) {
        if(root==null){
            return;
        }
        System.out.print("中序遍历: ");
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while(true) {
            TreeNode t = stack.peek();
            if (t != null) {
                stack.push(t.left);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    break;
                } else {
                    t = stack.pop();
                    if (t == null) continue;
                    System.out.print(t.val+ " ");
                    stack.push(t.right);
                }
            }
        }

        System.out.println("");

    }

    /**
     * 对于当前结点，先输出它的左孩子，然后输出它的右孩子，最后输出该结点。依旧以上图为例：
     * @param root
     */
    public static  void postOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        TreeNode last = null;
        System.out.print("后序遍历: ");
        while (true) {
            TreeNode node = stack.peek();
            if (node == null) {
                return;
            }
            if ((node.left == null && node.right == null)
                    || (last != null && (last == node.left || last == node.right))) {

                System.out.print(node.val+" ");
                last = node;
                stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
            } else {
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(10);
        TreeNode l=new TreeNode(2);
        TreeNode r=new TreeNode(12);
        root.left=l;
        root.right=r;

        TreeNode lr=new TreeNode(5);
        TreeNode rl=new TreeNode(8);
        TreeNode ll=new TreeNode(9);
        l.left=lr;
        r.right=rl;
        l.right=ll;
        TreePrintUtil.pirnt(root);

        preOrder(root);
        inOrder(root);

        postOrder(root);



    }
}
