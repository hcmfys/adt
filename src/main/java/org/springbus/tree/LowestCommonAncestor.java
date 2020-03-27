package org.springbus.tree;

//给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。”
//
// 例如，给定如下二叉搜索树: root = [6,2,8,0,4,7,9,null,null,3,5]
//
//
//
//
//
// 示例 1:
//
// 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
//输出: 6
//解释: 节点 2 和节点 8 的最近公共祖先是 6。
//
//
// 示例 2:
//
// 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
//输出: 2
//解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
//
//
//
// 说明:
//
//
// 所有节点的值都是唯一的。
// p、q 为不同节点且均存在于给定的二叉搜索树中。
//
// Related Topics 树
import jdk.nashorn.internal.ir.ReturnNode;
import org.springbus.TreeNode;
import org.springbus.TreePrintUtil;
import sun.reflect.generics.tree.Tree;

import javax.swing.text.rtf.RTFEditorKit;

public class LowestCommonAncestor {

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
      return null;
    }
    int p1 = p.val;
    int q1 = q.val;
    int max = Math.max(p1, q1);
    int min = Math.min(p1, q1);

    // 两个都在左边
    if (root.val > max) {
      if (root.val == min) {
        return root;
      }
      return lowestCommonAncestor(root.left, p, q);

    } else {
      // 两个在两边
      if (root.val < max && root.val > min) {
        return root;
      } else if (root.val < min) {
        // 两个在右边
        if (root.val == max) {
          return root;
        }
        return lowestCommonAncestor(root.right, p, q);
      }
    }

    return root;
  }

  public TreeNode findNode(TreeNode root, TreeNode q) {

    if (root == null) {
      return null;
    }
    if (root.val == q.val) {
      return root;
    }
    if (root.left != null) {
      TreeNode p1 = findNode(root.left, q);
      if (p1 != null) {
        return p1;
      }
    }
    if (root.right != null) {
      TreeNode p1 = findNode(root.right, q);
      if (p1 != null) {
        return p1;
      }
    }
    return null;
  }

  public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {

    if (root == null) {
      return null;
    }
    if (root.val == p.val || root.val == q.val) {
      return root;
    }

    if (root.left != null) {
      TreeNode p1 = findNode(root.left, p);
      TreeNode p2 = findNode(root.left, q);
      if (p1 != null && p2 != null) {
        return lowestCommonAncestor2(root.left, p, q);
      } else {
        if(p1!=null || p2!=null)
        return root;
      }
    }
    if (root.right != null) {
      TreeNode p1 = findNode(root.right, p);
      TreeNode p2 = findNode(root.right, q);
      if (p1 != null && p2 != null) {
        return lowestCommonAncestor2(root.right, p, q);
      } else {
        if(p1!=null || p2!=null)
          return root;
      }

    }
    return null;
  }

  public static void main(String[] args) {

    Integer[] trees = new Integer[] {6, 2, 8, 0, 4, 7, 9, null, null, 3, 5};
    trees = new Integer[]  {3,5,1,6,2,0,8,null,null,7,4};

    TreeNode root = TreePrintUtil.makeTree(trees);
    TreeNode p = root.nodesList[9];
    TreeNode q = root.nodesList[10];
    TreePrintUtil.pirnt(root);

    TreeNode node = new LowestCommonAncestor().lowestCommonAncestor2(root, p, q);
    System.out.println(node.val);

  }
}
