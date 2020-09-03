package org.springbus.tree;


//给定一个非空二叉树，返回其最大路径和。
//
// 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
//
// 示例 1:
//
// 输入: [1,2,3]
//
//       1
//      / \
//     2   3
//
//输出: 6
//

//
// 示例 2:
//
// 输入: [-10,9,20,null,null,15,7]
//
//   -10
//   / \
//  9  20
//    /  \
//   15   7
//
//输出: 42
// Related Topics 树 深度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)

import org.springbus.TreeNode;
import org.springbus.TreePrintUtil;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
 * right; TreeNode(int x) { val = x; } }
 */
public class MaxPathSum {

    int mx = Integer.MIN_VALUE;

    public static void main(String[] args) {

        Integer arrList[] = {-10, 9, 20, null, null, 15, 7};
        //  arrList=new Integer[]{1,2,3};
        //arrList=new Integer[]{-2,1};
        //arrList=new Integer[]{2,-1};
        TreeNode root = TreePrintUtil.makeTree(arrList);
        int l = new MaxPathSum().maxPathSum(root);
        System.out.println("max l=" + l);
        TreePrintUtil.pirnt(root);
    }

    public int maxPathSum(TreeNode r) {
        maxPath(r);
        return mx;
    }

    public int maxPath(TreeNode root) {

        if (root == null) {
            return 0;
        }
        int l = Math.max(0, maxPath(root.left));
        int m = Math.max(0, maxPath(root.right));
        int t = l + m + root.val;
        mx = Math.max(mx, t);
        return root.val + Math.max(l, m);
    }
}
