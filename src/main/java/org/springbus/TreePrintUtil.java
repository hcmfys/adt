package org.springbus;

import org.springbus.treePrint.tech.vanyo.treePrinter.TreePrinter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
      printer.setLrAgnostic(true);
    // use square branches
    //printer.setSquareBranches(true);
    printer.setTspace(3);
    printer.printTree(root);
    System.out.println();
  }
}
