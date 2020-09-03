package org.springbus.tree;
//翻转一棵二叉树。
//
// 示例：
//
// 输入：
//
//     4
//   /   \
//  2     7
// / \   / \
//1   3 6   9
//
// 输出：
//
//     4
//   /   \
//  7     2
// / \   / \
//9   6 3   1
//
// 备注:
//这个问题是受到 Max Howell 的 原问题 启发的 ：
//
// 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
// Related Topics 树


//leetcode submit region begin(Prohibit modification and deletion)

import org.springbus.TreeNode;
import org.springbus.TreePrintUtil;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */


public class InvertTree {

    public static void main(String[] args) {

        Integer arrList[] = {4, 2, 7, 1, 3, 6, 9};
        arrList = new Integer[]{1, 2};
        TreeNode root = TreePrintUtil.makeTree(arrList);
        TreePrintUtil.pirnt(root);
        TreeNode l = new InvertTree().invertTree(root);
        TreePrintUtil.pirnt(l);

    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = right;
        root.right = left;


        if (root.left != null) {
            dfs(root.left);
        }
        if (root.right != null) {
            dfs(root.right);
        }

    }

    public TreeNode invertTree(TreeNode root) {
        dfs(root);
        return root;
    }

}
