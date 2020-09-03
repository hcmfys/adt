package org.springbus.tree;

import org.springbus.TreeNode;
import org.springbus.TreePrintUtil;

import java.util.ArrayList;
import java.util.List;

public class LevelOrder {

    //
//     3
//   / \
//  9  20
//    /  \
//   15   7
//
//
// 返回其层次遍历结果：
//
// [
//  [3],
//  [9,20],
//  [15,7]
//]
//
// Related Topics 树 广度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */


    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        List<TreeNode> allList = new ArrayList<>();
        allList.add(root);
        while (allList.size() > 0) {
            List<Integer> rootLit = new ArrayList<>();
            for (int i = 0; i < allList.size(); i++) {
                TreeNode node = allList.get(i);
                rootLit.add(node.val);
            }
            list.add(rootLit);
            List<TreeNode> curNodeList = new ArrayList<>();
            for (int i = 0; i < allList.size(); i++) {
                TreeNode node = allList.get(i);
                if (node.left != null) {
                    curNodeList.add(node.left);
                }
                if (node.right != null) {
                    curNodeList.add(node.right);
                }
            }
            allList.clear();
            allList.addAll(curNodeList);
        }

        System.out.println(list);
        return list;

    }

    public static void main(String[] args) {

        //    3
        //   / \
        //  9  20
        //    /  \
        //   15   7
        TreeNode root = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        TreeNode node10 = new TreeNode(10);
        TreeNode node11 = new TreeNode(11);
        TreeNode node12 = new TreeNode(12);
        root.setLeft(node9);
        root.setRight(node20);

        //node20.setLeft(node15);
        node20.setRight(node7);

        node9.setLeft(node10);
        node9.setRight(node11);
        //node11.setRight(node12);

        TreePrintUtil.pirnt(root);
        levelOrder(root);


    }
}
