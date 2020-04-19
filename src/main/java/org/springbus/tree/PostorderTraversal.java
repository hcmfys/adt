package org.springbus.tree;

import org.springbus.TreeNode;
import org.springbus.TreePrintUtil;

import java.util.ArrayList;
import java.util.List;

//给定一个二叉树，返回它的 前序 遍历。
//
// 示例:
//
// 输入: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//输出: [1,2,3]
//
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
// Related Topics 栈 树

public class PostorderTraversal {

    public static void main(String[] args) {

        Integer arrList[] = {1, 9, 2, 3, 4, 5, 7, 9, 7, 4, 5, 9, 8, 5, 6, 5, 4, 0};
        arrList = new Integer[]{1, 2, 3};

        TreeNode root = TreePrintUtil.makeTree(arrList);
        TreePrintUtil.pirnt(root);
        List<Integer> l = new PostorderTraversal().preorderTraversal(root);
        System.out.println("l=" + l);
    }

    private void preorderTraversalNode(TreeNode root, List<Integer> lst) {
        if (root == null) {
            return;
        }
        lst.add(root.val);
        if (root.left != null) {
            preorderTraversalNode(root.left, lst);
        }
        if (root.right != null) {
            preorderTraversalNode(root.right, lst);
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        preorderTraversalNode(root, res);
        return res;
    }
}
