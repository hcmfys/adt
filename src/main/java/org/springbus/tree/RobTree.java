package org.springbus.tree;

import org.springbus.TreeNode;
import org.springbus.TreePrintUtil;

import java.util.ArrayList;
import java.util.List;
// 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
// 这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“
// 房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
// 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
//
// 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
//
// 示例 1:
//
// 输入: [3,2,3,null,3,null,1]
//
//     3
//    / \
//   2   9
//    \   \
//     5   1
//
// 输出: 7
// 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
//
// 示例 2:
//
// 输入: [3,4,5,1,3,null,1]
//
//      3
//    / \
//   4   5
//  / \   \
// 1   3   1
//
// 输出: 9
// 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
//
// Related Topics 树 深度优先搜索

public class RobTree {

    List<Integer> nodeList = new ArrayList<>();
    private int max = 0;

    public static void main(String[] args) {

        Integer arrList[] = {3, 9, 20, null, null, 15, 7};
        arrList = new Integer[]{3, 4, 5, 1, 3, null, 1};
        // arrList=new Integer[]{3,2,3,null,3,null,1};
        arrList = new Integer[]{3, 2, 3, null, 3, null, 1};
        //arrList = new Integer[] {1, 2, 3};
        TreeNode root = TreePrintUtil.makeTree(arrList);
        int l = new RobTree().rob(root);
        System.out.println("max=" + l);
        TreePrintUtil.pirnt(root);
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }


        dfs(root.left);
        nodeList.add(root.val);
        dfs(root.right);
    }

    public int rob(TreeNode root) {
        dfs(root);
        int m1 = 0;
        int m2 = 0;
        System.out.println(root);
        for (int i = 0; i < nodeList.size(); i++) {
            if (i % 2 != 0) {
                m1 += nodeList.get(i);
            } else {
                m2 += nodeList.get(i);
            }
        }
        return Math.max(m1, m2);
    }
}
