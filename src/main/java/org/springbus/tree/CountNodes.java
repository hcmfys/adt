package org.springbus.tree;


//给出一个完全二叉树，求出该树的节点个数。
//
// 说明：
//
// 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为
//第 h 层，则该层包含 1~ 2h 个节点。
//
// 示例:
//
// 输入:
//    1
//   / \
//  2   3
// / \  /
//4  5 6
//
//输出: 6
// Related Topics 树 二分查找


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

//leetcode submit region end(Prohibit modification and deletion)

public class CountNodes {

    int i = 0;

    public static void main(String[] args) {

        Integer arrList[] = {3, 9, 20, null, null, 15, 7};
        //arrList=new Integer[]{1,2};
        TreeNode root = TreePrintUtil.makeTree(arrList);
        TreePrintUtil.pirnt(root);
        int l = new CountNodes().countNodes(root);
        System.out.println("ll=" + l);

    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        i++;
        if (root.left != null) {
            dfs(root.left);
        }
        if (root.right != null) {
            dfs(root.right);
        }
    }

    public int countNodes(TreeNode root) {

        dfs(root);
        return i;
    }

}
