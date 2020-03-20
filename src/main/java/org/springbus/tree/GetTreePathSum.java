package org.springbus.tree;

import javafx.scene.transform.Rotate;
import org.springbus.TreeNode;
import org.springbus.TreePrintUtil;

public class GetTreePathSum {


    public  static void treeSum(TreeNode root,int sum) {

    }

  public static void main(String[] args) {
    Integer[] trees = {5, 4, 8, 11, 13, 4, null, 7, 2};
    TreeNode root = TreePrintUtil.makeTree(trees);
    treeSum(root, 22);
    TreePrintUtil.pirnt(root);
    }
}
