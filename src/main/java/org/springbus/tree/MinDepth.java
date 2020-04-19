package org.springbus.tree;

//给定一个二叉树，找出其最小深度。
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
//
// 说明: 叶子节点是指没有子节点的节点。
//
// 示例:
//
// 给定二叉树 [3,9,20,null,null,15,7],
//
//    3
//   / \
//  9  20
//    /  \
//   15   7
//
// 返回它的最小深度 2.
// Related Topics 树 深度优先搜索 广度优先搜索


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
public class MinDepth {

    int levelMin = Integer.MAX_VALUE;

    public static void main(String[] args) {

        Integer arrList[] = {3, 9, 20, null, null, 15, 7};
        //arrList=new Integer[]{1,2};
        TreeNode root = TreePrintUtil.makeTree(arrList);
        int l = new MinDepth().minDepth(root);
        System.out.println("ll=" + l);
        TreePrintUtil.pirnt(root);
    }

    public int minDepthLevel(TreeNode root, int level) {


        if (root == null) {
            System.out.println(" level=" + level);
            return level;
        }

        if (root.left == null && root.right == null) {
            if (levelMin > level) {
                levelMin = level;
            }
            System.out.println(" leaf  level=" + level);
            return level;
        }

        if (root.left != null) {
            minDepthLevel(root.left, level + 1);
        }
        if (root.right != null) {
            minDepthLevel(root.right, level + 1);
        }
        return levelMin;

    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return minDepthLevel(root, 1);
    }
}
