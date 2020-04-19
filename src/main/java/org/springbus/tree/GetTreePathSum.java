package org.springbus.tree;

import org.junit.Test;
import org.springbus.TreeNode;
import org.springbus.TreePrintUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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


    List<List<Integer>> pathList = new ArrayList<>();

    public void hasPathSumList(TreeNode root, int sum, List<Integer> resList) {

        if (root == null) {
            return;
        }
        resList.add(root.val);
        if (root.left == null && root.right == null) {
            boolean ok = root.val == sum;
            System.out.println("val=" + root.val + " -- " + sum + "  find= " + ok);
            if (ok) {
                pathList.add(new ArrayList<>(resList));
            }
            return;
        }

        System.out.println("val=" + root.val + " -->" + sum);

        if (root.left != null) {
            hasPathSumList(root.left, sum - root.val, resList);
            resList.remove(resList.size() - 1);
        }
        if (root.right != null) {
            hasPathSumList(root.right, sum - root.val, resList);
            resList.remove(resList.size() - 1);
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        hasPathSumList(root, sum, new ArrayList<>());
        return pathList;
    }

    public boolean hasPathSum(TreeNode root, int sum) {

        hasPathSumList(root, sum, new LinkedList<>());
        System.out.println(pathList);
        return pathList.size() > 0;
    }

    @Test
    public void run() {
        Integer[] trees = new Integer[]{1, -2, -3, 1, 3, -2, null, -1}; // -1
        trees = new Integer[]{5, 4, 8, 11, null, 13, 9, 7, 2, null, null, null, 1}; // 22
        // trees = new Integer[] {1};  //1
        //trees = new Integer[]{null}; // 1
        // trees = new Integer[]{1, 2}; // 1
        TreeNode root = TreePrintUtil.makeTree(trees);
        boolean isExists = new GetTreePathSum().hasPathSum(root, 22);
        System.out.println(isExists);
        TreePrintUtil.pirnt(root);
    }
}


