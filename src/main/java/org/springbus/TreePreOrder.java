package org.springbus;


import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.image.renderable.RenderableImage;
import java.util.Stack;

public class TreePreOrder {

    static  void preOrder2(TreeNode root){
        if(root==null){
            return;
        }
        System.out.print(root.val +"  ");
        preOrder2(root.left);
        preOrder2(root.right);

    }

    static  void preOrder(TreeNode root){
        System.out.println("");
        Stack<TreeNode> stack =new Stack<>();
        if(root!=null){
            stack.push(root);
        }

        while (!stack.empty()) {
           TreeNode node= stack.pop();
           System.out.print(node.val + "  ");
           if(node!=null) {
               if(node.right!=null) {
                   stack.push(node.right);
               }
               if(node.left!=null) {
                   stack.push(node.left);
               }
           }
        }
    }

    static  void inOrder(TreeNode root) {
        if(root==null){
            return;
        }

        inOrder(root.left);
        System.out.print(root.val +"  ");
        inOrder(root.right);
    }
    static  void inOrder2(TreeNode root) {
        System.out.println();
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        do {
            //依次将左节点均加入栈中
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                root = stack.pop();
                System.out.print(root.val +  "  ");
                root = root.right;
            }
        } while (!stack.isEmpty() || root != null);

    }

    static  void postOrder(TreeNode root){
        if(root==null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val+"  ");
    }
    public static void postOrder2(TreeNode biTree) {
        System.out.println();
        //后序遍历非递归实现
        int left = 1;//在辅助栈里表示左节点
        int right = 2;//在辅助栈里表示右节点
        Stack<TreeNode> stack = new Stack<TreeNode>();
        //辅助栈，用来判断子节点返回父节点时处于左节点还是右节点。
        Stack<Integer> stack2 = new Stack<Integer>();

        while (biTree != null || !stack.empty()) {
            //将节点压入栈1，并在栈2将节点标记为左节点
            while (biTree != null) {
                stack.push(biTree);
                stack2.push(left);
                biTree = biTree.left;
            }
            //如果是从右子节点返回父节点，则任务完成，将两个栈的栈顶弹出
            while (!stack.empty() && stack2.peek() == right) {
                stack2.pop();
                System.out.print(stack.pop().val +"  ");
            }
            //如果是从左子节点返回父节点，则将标记改为右子节点
            if (!stack.empty() && stack2.peek() == left) {
                stack2.pop();
                stack2.push(right);
                biTree = stack.peek().right;
            }

        }
    }




    public static void main(String[] args) {
        TreeNode root=TreePrintUtil.makeTree(new Integer[]{3,2,5,4,1,45,8,7,9,12,34,189,89,88});
        TreePrintUtil.pirnt(root);
        //preOrder2(root);
        //preOrder(root);
        //inOrder(root);
        //inOrder2(root);
        postOrder(root);
        postOrder2(root);
    }
}
