package org.springbus;

import org.springbus.treePrint.tech.vanyo.treePrinter.TreePrinter;

import java.util.*;

public class TreePrintUtil {

  /**
   * 打印 tree
   *
   * @param root
   */
  public static void pirnt(TreeNode root) {

    TreePrinter<TreeNode> printer =
        new TreePrinter<>(n -> "" + n.val, n -> n.getLeft(), n -> n.getRight());
    printer.setHspace(2);
    //printer.setLrAgnostic(true);
    // use square branches
    // printer.setSquareBranches(true);
    printer.setTspace(2);
    printer.printTree(root);
    System.out.println();
  }

  /**
   * 产生tree
   *
   * @param arr
   * @return
   */
  public static TreeNode makeTree(Integer arr[]) {

    TreeNode[] treeNodeList = new TreeNode[arr.length];
    for (int i = 0; i < arr.length; i++) {

      if (arr[i] != null) {
        treeNodeList[i] = new TreeNode(arr[i]);
      }else{
        treeNodeList[i] = null;
      }
    }
    for (int i = 0; i < arr.length/2; i ++) {
      int idx = (int) 2*i;
      TreeNode node=treeNodeList[i];
      if (node != null) {
        if (idx + 1 <= arr.length - 1) {
          node.left = treeNodeList[idx + 1];
        }
        if (idx + 2 <= arr.length - 1) {
          node.right = treeNodeList[idx + 2];
        }
      }
    }
    treeNodeList[0].nodesList=treeNodeList;
    return treeNodeList[0];
  }

  /**
   *
   * @param arr
   * @return
   */
  public static  Node makeTreeNode(Integer arr[]) {

    Node[] treeNodeList = new Node[arr.length];
    for (int i = 0; i < arr.length; i++) {

      if (arr[i] != null) {
        treeNodeList[i] = new Node(arr[i]);
      } else {
        treeNodeList[i] = null;
      }
    }
    for (int i = 0; i < arr.length / 2; i++) {
      int idx = (int) 2 * i;
      Node node = treeNodeList[i];
      if (node != null) {
        if (idx + 1 <= arr.length - 1) {
          node.setLeft( treeNodeList[idx + 1]);
        }
        if (idx + 2 <= arr.length - 1) {
          node.setRight( treeNodeList[idx + 2]);
        }
      }
    }
    return treeNodeList[0];
  }
}
