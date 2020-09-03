package org.springbus.tree;

import org.springbus.TreeNode;
import org.springbus.TreePrintUtil;
//给定一个二叉树，其中所有的右节点要么是具有兄弟节点（拥有相同父节点的左节点）的叶节点，要么为空，
// 将此二叉树上下翻转并将它变成一棵树，
// 原来的右节点将转换成左叶节点。返回新的根。
//
// 例子:
//
// 输入: [1,2,3,4,5]
//
//    1
//   / \
//  2   3
// / \
//4   5
//
//输出: 返回二叉树的根 [4,5,2,#,#,3,1]
//
//   4
//  / \
// 5   2
//    / \
//   3   1
//
//
// 说明:
//
// 对 [4,5,2,#,#,3,1] 感到困惑? 下面详细介绍请查看 二叉树是如何被序列化的。
//
// 二叉树的序列化遵循层次遍历规则，当没有节点存在时，'#' 表示路径终止符。
//
// 这里有一个例子:
//
//    1
//  / \
// 2   3
//    /
//   4
//    \
//     5
//
//
// 上面的二叉树则被序列化为 [1,2,3,#,#,4,#,#,5].
// Related Topics 树

//leetcode submit region begin(Prohibit modification and deletion)
public class UpsideDownBinaryTree {
    //递归，只要找到最左子树就行
    //然后反转
    //唯一要注意的一点是最后原本的根节点root的左右子树需要置null，否则会形成环

    /* 经过翻转之后，原来的左子节点变成了根节点，
    右子节点变成了左子节点，根节点变成了右子节点。
    先递归找到最终的根节点，
   保存到全局变量result中，递归函数Dfs返回的是需要被填充的节点
   （返回到上一层递归之后，赋值左孩子和右孩子） */
    TreeNode result = null;

    public static void main(String[] args) {

        Integer arrList[] = {1, 9, 2, 3, 4, 5, 7, 9, 7, 4, 5, 9, 8, 5, 6, 5, 4, 0};
        arrList = new Integer[]{1, 2, 3, 4, 5};
        // arrList=new Integer[]{-2,1};
        // arrList=new Integer[]{2,-1};

        TreeNode root = TreePrintUtil.makeTree(arrList);
        TreePrintUtil.pirnt(root);
        root = new UpsideDownBinaryTree().upsideDownBinaryTree2(root);
        TreePrintUtil.pirnt(root);
    }

    ///* 经过翻转之后，原来的左子节点变成了根节点，右子节点变成了左子节点，
    // 根节点变成了右子节点。先递归找到最终的根节点，
    //   保存到全局变量result中，
    //   递归函数Dfs返回的是需要被填充的节点（返回到上一层递归之后，赋值左孩子和右孩子） */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) return root;
        TreeNode ans = helper(root.left, root);
        //防止形成环
        root.left = null;
        root.right = null;
        return ans;
    }

    public TreeNode helper(TreeNode left, TreeNode p) {
        TreeNode ans;
        if (left.left == null) {
            ans = left;
        } else {
            ans = helper(left.left, left);
        }
        left.left = p.right;
        left.right = p;
        return ans;
    }

    private TreeNode Dfs(TreeNode root) {

        if (root == null || root.left == null) {
            result = root;
            return result;
        }
        TreeNode p = Dfs(root.left);
        System.out.println("p=" + p);
        p.left = root.right;
        p.right = root;
        root.left = null;
        root.right = null;
        return root;
    }

    public TreeNode upsideDownBinaryTree2(TreeNode root) {
        Dfs(root);
        return result;
    }


}
