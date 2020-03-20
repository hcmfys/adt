package org.springbus.tree;
//给定一个二叉树，判断其是否是一个有效的二叉搜索树。
//
// 假设一个二叉搜索树具有如下特征：
//
//
// 节点的左子树只包含小于当前节点的数。
// 节点的右子树只包含大于当前节点的数。
// 所有左子树和右子树自身必须也是二叉搜索树。
//
//
// 示例 1:
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
//
//
// 示例 2:
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
//
// Related Topics 树 深度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)

import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.springbus.BTreePrinter;
import org.springbus.TreeNode;
import org.springbus.TreePrintUtil;

import javax.swing.text.rtf.RTFEditorKit;
import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
 * right; TreeNode(int x) { val = x; } }
 */

// leetcode submit region end(Prohibit modification and deletion)

public class IsBst {
  public List<Integer> list = new ArrayList<>();

  public void checkBst(TreeNode root) {

    if (root == null) {
      return;
    } else {

      if (root.left != null) {
        checkBst(root.left);
      }

      list.add(root.val);

      if (root.right != null) {
        checkBst(root.right);
      }
    }
  }

  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    checkBst(root);
    System.out.println(list);
    for (int i = 1; i < list.size(); i++) {
      int a = list.get(i - 1);
      int b = list.get(i);
      if (a >= b) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(10);
    TreeNode node9 = new TreeNode(10);
    TreeNode node20 = new TreeNode(15);
    TreeNode node15 = new TreeNode(60);
    TreeNode node7 = new TreeNode(20);
    TreeNode node10 = new TreeNode(10);
    TreeNode node11 = new TreeNode(11);
    TreeNode node12 = new TreeNode(12);
    root.setLeft(node9);
    root.setRight(node20);

    node20.setLeft(node15);
    node20.setRight(node7);

    node9.setLeft(node10);
    node9.setRight(node11);
    node11.setRight(node12);

    TreePrintUtil.pirnt(root);
    boolean ok = new IsBst().isValidBST(root);
    System.out.println(ok);
    Integer arr[]={1,2,3,4,45,7,9,8};
   TreeNode rootIt= TreePrintUtil.makeTree(arr);
   TreePrintUtil.pirnt(rootIt);

  }
}
