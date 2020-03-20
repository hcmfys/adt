package org.springbus.tree;

import org.junit.Test;
import org.springbus.TreeNode;
import org.springbus.TreePrintUtil;
import org.w3c.dom.ls.LSException;

// 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
//
// 说明: 叶子节点是指没有子节点的节点。
//
// 示例:
// 给定如下二叉树，以及目标和 sum = 22，
//
//               5
//             / \
//            4   8
//           /   / \
//          11  13  4
//         /  \      \
//        7    2      1
//
//
// 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
// Related Topics 树 深度优先搜索

// leetcode submit region begin(Prohibit modification and deletion)

public class GetTreePathSum {

  private TreeNode lastNode;

  public boolean hasPathSum(TreeNode root, int sum) {

    if (root == null) {
      return sum == 0 && lastNode != null && lastNode.left==null && lastNode.right==null;
    }

    lastNode = root;

    if (sum == 0) {

      boolean isExists = root.left == null && root.right == null;
      System.out.println(" exists root path " + root.val + "  exists=" + isExists);
      return isExists;
    }
    sum=sum - root.val;
    System.out.println("val=" + root.val + " -->" + (sum ));

    return hasPathSum(root.left, sum ) || hasPathSum(root.right, sum );
  }

  @Test
  public void run() {
    Integer[] trees = new Integer[] {1, -2, -3, 1, 3, -2, null, -1}; // -1
    trees = new Integer[] {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1}; // 22
    trees = new Integer[] {1};  //1

    TreeNode root = TreePrintUtil.makeTree(trees);
    boolean isExists = new GetTreePathSum().hasPathSum(root, 0);
    System.out.println(isExists);
    TreePrintUtil.pirnt(root);
  }
  }


