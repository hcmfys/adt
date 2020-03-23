package org.springbus.tree;


//实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
//
// 调用 next() 将返回二叉搜索树中的下一个最小的数。
//
//
//
// 示例：
//
//
//
// BSTIterator iterator = new BSTIterator(root);
//iterator.next();    // 返回 3
//iterator.next();    // 返回 7
//iterator.hasNext(); // 返回 true
//iterator.next();    // 返回 9
//iterator.hasNext(); // 返回 true
//iterator.next();    // 返回 15
//iterator.hasNext(); // 返回 true
//iterator.next();    // 返回 20
//iterator.hasNext(); // 返回 false
//
//
//
// 提示：
//
//
// next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
// 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
//
// Related Topics 栈 树 设计


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


import org.springbus.TreeNode;
import org.springbus.TreePrintUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
//leetcode submit region end(Prohibit modification and deletion)
public  class BSTIterator {

    private int index;
    List<Integer> lst;

    public BSTIterator(TreeNode root) {
        lst = new ArrayList<>();
        dfs(root);
    }
    private  void dfs(TreeNode root){

        if(root==null) {
            return;
        }
        if(root.left!=null) {
            dfs(root.left);
        }
        lst.add(root.val);
        if(root.right!=null) {
            dfs(root.right);
        }


    }

    /**
     * @return the next smallest number
     */
    public int next() {
        int d = lst.get(index);
        index++;
        return d;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return index <=lst.size() - 1;
    }


    public static void main(String[] args) {

        Integer arrList[] = {1, 9, 2, 3, 4, 5, 7, 9, 7, 4, 5, 9, 8, 5, 6, 5, 4, 0};
        arrList = new Integer[]{9, 7, 15, 3, null, null, 20};


        TreeNode root = TreePrintUtil.makeTree(arrList);
        TreePrintUtil.pirnt(root);
        BSTIterator obj = new BSTIterator(root);
        while (obj.hasNext()) {
            int param_1 = obj.next();
            boolean param_2 = obj.hasNext();
            System.out.println(param_1 + "--->" + param_2);
        }
    }
}
