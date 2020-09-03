package org.springbus.tree;

import org.springbus.TreeNode;
import org.springbus.TreePrintUtil;

import java.util.ArrayList;
import java.util.List;
//1431

//设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
//
//如果指定节点没有对应的“下一个”节点，则返回null。
//
//示例 1:
//
//输入: root = [2,1,3], p = 1
//
//  2
// / \
//1   3
//
//输出: 2
//示例 2:
//
//输入: root = [5,3,6,2,4,null,null,1], p = 6
//
//      5
//     / \
//    3   6
//   / \
//  2   4
// /
//1
//
//输出: null
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/successor-lcci
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class InorderSuccessor {


    List<TreeNode> allList = new ArrayList<>();
    private boolean isExists = false;
    private TreeNode lastNode;

    public static void main(String[] args) {

        Integer arrList[] = {5, 3, 6, 2, 4, null, null, 1};
        arrList = new Integer[]{2, 1, 3};
        arrList = new Integer[]{5, 3, 6, 2, 4, null, null, 1};
        TreeNode root = TreePrintUtil.makeTree(arrList);
        TreeNode p = root.getNodesList()[3];
        TreePrintUtil.pirnt(root);
        System.out.println("p=" + p.val);
        TreeNode l = new InorderSuccessor().inorderSuccessor2(root, p);
        TreePrintUtil.pirnt(l);

    }

    public void dfs(TreeNode root) {

        if (root == null) {
            return;
        }
        if (root.left != null) {
            dfs(root.left);
        }
        allList.add(root);
        if (root.right != null) {
            dfs(root.right);
        }
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        dfs(root);
        for (int i = 0; i < allList.size(); i++) {
            if (p == allList.get(i)) {
                int j = i + 1;
                if (j <= allList.size() - 1) {
                    return allList.get(j);
                }
            }
        }
        return null;
    }

    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        System.out.println("------------\n");
        TreeNode n = dfs(root, p);
        System.out.println("\n--------------\n");

        System.out.println(" in node=" + (lastNode == null ? "null" : lastNode.val));
        return n;
    }

    public TreeNode dfs(TreeNode root, TreeNode p) {

        if (root == null) {
            return null;
        }

        if (root.left != null) {
            dfs(root.left, p);
        }

        if (isExists) {
            lastNode = root;
        }
        System.out.print(root.val + " ");
        if (p == root && !isExists) {
            isExists = true;
        }

        if (root.right != null) {
            dfs(root.right, p);
        }
        return root;
    }

}
