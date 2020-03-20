package org.springbus.tree;

import org.junit.Test;
import org.springbus.TreeNode;
import org.springbus.TreePrintUtil;

public class GetTreePathSum   {



  public static boolean treeSum(TreeNode root, int sum) {

    if (root == null) {
      return sum == 0;
    }
    sum = sum - root.val;
    // System.out.println(root.val + " -->" + sum);
    if (sum == 0) {
      System.out.println(" exists root path " + root.val);
      return root.left == null && root.right == null;
    }
    return treeSum(root.left, sum) || treeSum(root.right, sum);
  }

  @Test
  public void run() {
    Integer[] trees = {5, 4, 8, 11, 13, 4, null, 7, 2};
    TreeNode root = TreePrintUtil.makeTree(trees);
    boolean isExists = treeSum(root, 22);
    System.out.println(isExists);
    TreePrintUtil.pirnt(root);
  }
  }


