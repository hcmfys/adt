package org.springbus.tree;


//给定一个二叉树，原地将它展开为链表。
//
// 例如，给定二叉树
//
//    1
//   / \
//  2   5
// / \   \
//3   4   6
//
// 将其展开为：
//
//1
// \
//  2
//   \
//    3
//     \
//      4
//       \
//        5
//         \
//          6
// Related Topics 树 深度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)

import org.springbus.TreeNode;
import org.springbus.TreePrintUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

public class FlattenTree {

    List<TreeNode> lst = new ArrayList<>();
    TreeNode last = null;

    public static void main(String[] args) {

        Integer arrList[] = {1, 2, 5, 3, 4, null, 6};
        //arrList=new Integer[]{null};
        TreeNode root = TreePrintUtil.makeTree(arrList);
        TreePrintUtil.pirnt(root);
        new FlattenTree().flatten(root);

        TreePrintUtil.pirnt(root);
    }

    public TreeNode flattenNode(TreeNode root) {
        TreeNode r = root;
        System.out.println(root.val);
        lst.add(r);
        if (root.left != null) {
            flattenNode(root.left);
        }
        if (root.right != null) {
            flattenNode(root.right);
        }
        return r;
    }

    public void flatten3(TreeNode root) {
        flattenNode(root);
        root.left = null;
        for (int i = 1; i < lst.size(); i++) {
            root.right = lst.get(i);
            root = root.right;
            root.left = null;
        }
    }

    void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.right);
        flatten(root.left);
        root.right = last;
        root.left = null;
        last = root;
    }
}
