package org.springbus;

public class TravelTree {


    public static  void preTraverse(TreeNode root) {
        System.out.print(root.val +" ");
        if(root.getLeft()!=null) {
            preTraverse(root.getLeft());
        }
        if(root.getRight()!=null) {
            preTraverse(root.getRight());
        }

    }
    public static  void postTraverse(TreeNode root) {

        if(root.getLeft()!=null) {
            postTraverse(root.getLeft());
        }
        if(root.getRight()!=null) {
            postTraverse(root.getRight());
        }
        System.out.print(root.val +" ");
    }


    public static  void midTraverse(TreeNode root) {

        if(root.getLeft()!=null) {
            midTraverse(root.getLeft());
        }
        System.out.print(root.val +" ");
        if(root.getRight()!=null) {
            midTraverse(root.getRight());
        }
    }

    /**
     * 获取树的深度
     * @param root
     * @return
     */
    public static  int  getMaxLength(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int a = getMaxLength(root.getLeft());
        int b = getMaxLength(root.getRight());
        return Math.max(a, b) + 1;

    }




    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(3);
        TreeNode c = new TreeNode(4);
        TreeNode d = new TreeNode(5);
        root.setLeft(a);
        root.setRight(b);
        a.setLeft(c);
        a.setRight(d);

        TreePrintUtil.pirnt(root);

        preTraverse(root);
        System.out.println("");
        postTraverse(root);
        System.out.println("");
        midTraverse(root);
        System.out.println("");
        System.out.println("max length=" +getMaxLength(root));
    }

}
