package org.springbus.tree;

import org.springbus.TreeNode;
import org.springbus.TreePrintUtil;

public class SameTree {

    public static void main(String[] args) {
        TreeNode p1 = new TreeNode();
        p1.val = 1;
        TreeNode p2 = new TreeNode();
        p2.val = 2;
        p1.left = p2;
        TreeNode p3 = new TreeNode(1);
        TreeNode p4 = new TreeNode(2);
        p3.right = p4;
        boolean o = new SameTree().isSameTree(p1, p3);
        TreePrintUtil.pirnt(p1);
        System.out.println("-----------------------");
        TreePrintUtil.pirnt(p3);
        System.out.println(o);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }


        boolean ok1 = isSameTree(p.left, q.left);
        boolean ok2 = isSameTree(p.right, q.right);
        return ok1 && ok2;
    }

}
