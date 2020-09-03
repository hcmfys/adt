package org.springbus.tree;

import org.springbus.TreeNode;
import org.springbus.TreePrintUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

//给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
//
// 示例:
//
// 输入: [1,2,3,null,5,null,4]
//输出: [1, 3, 4]
//解释:
//
//   1            <---
// /   \
//2     3         <---
// \     \
//  5     4       <---
//
// Related Topics 树 深度优先搜索 广度优先搜索
public class RightSideView {


    HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {

        Integer arrList[] = {1, 9, 2, 3, 4, 5, 7, 9, 7, 4, 5, 9, 8, 5, 6, 5, 4, 0};
        arrList = new Integer[]{9, 7, 15, 3, null, 16, null};

        TreeNode root = TreePrintUtil.makeTree(arrList);
        TreePrintUtil.pirnt(root);
        List<Integer> lst = new RightSideView().rightSideView(root);
        System.out.println(lst);
    }

    private void dfs(TreeNode root, int level) {

        if (root == null) {
            return;
        }
        if (map.get(level) == null) {
            map.put(level, root.val);
        }
        if (root.right != null) {
            dfs(root.right, level + 1);
        }
        if (root.left != null) {
            dfs(root.left, level + 1);
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> lst = new ArrayList<>();
        dfs(root, 0);
        Iterator<Integer> v = map.values().iterator();
        while (v.hasNext()) {
            lst.add(v.next());
        }
        return lst;

    }
}
