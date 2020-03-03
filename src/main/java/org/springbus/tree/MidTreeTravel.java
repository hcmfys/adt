package org.springbus.tree;

import org.springbus.TreeNode;

import java.util.ArrayList;
import java.util.List;


//给定一个二叉树，返回它的中序 遍历。
//
// 示例:
//
// 输入: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//输出: [1,3,2]
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
// Related Topics 栈 树 哈希表


//leetcode submit region begin(Prohibit modification and deletion)




public class MidTreeTravel {


    void travel(List<Integer> list, TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            travel(list, root.left);
        }
        list.add(root.val);
        if (root.right != null) {
            travel(list, root.right);
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        travel(list, root);
        return list;
    }
}
