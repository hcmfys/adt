package org.springbus;

public class Treeex {

    /* 有了以上铺垫，详细注释一下代码 */
    static int pathSum(TreeNode root, int sum) {
        System.out.println("pathSum " + sum + "==>" + root);
        if (root == null) return 0;
        int pathImLeading = count(root, sum); // 自己为开头的路径数
        int leftPathSum = pathSum(root.left, sum); // 左边路径总数（相信他能算出来）
        int rightPathSum = pathSum(root.right, sum); // 右边路径总数（相信他能算出来）
        return leftPathSum + rightPathSum + pathImLeading;
    }

    static int count(TreeNode node, int sum) {
        System.out.println("count " + sum + "==>" + node);

        if (node == null) return 0;
        // 我自己能不能独当一面，作为一条单独的路径呢？
        int isMe = (node.val == sum) ? 1 : 0;
        // 左边的小老弟，你那边能凑几个 sum - node.val 呀？
        int leftBrother = count(node.left, sum - node.val);
        // 右边的小老弟，你那边能凑几个 sum - node.val 呀？
        int rightBrother = count(node.right, sum - node.val);
        return isMe + leftBrother + rightBrother; // 我这能凑这么多个
    }

    public static void main(String[] args) {

        /**
         * sum = 8
         *
         10
         /  \
         5   -3
         / \    \
         3   2   11
         / \   \
         3  -2   1
         */

        TreeNode root = new TreeNode(10);

        TreeNode root5 = new TreeNode(5);
        TreeNode root_3 = new TreeNode(-3);
        TreeNode root11 = new TreeNode(11);

        TreeNode root53 = new TreeNode(3);
        TreeNode root52 = new TreeNode(2);

        TreeNode root33 = new TreeNode(3);
        TreeNode root3_2 = new TreeNode(-2);


        TreeNode root521 = new TreeNode(1);


        root.left = root5;
        root.right = root_3;

        root5.left = root53;
        root5.right = root52;

        root52.right = root521;

        root53.left = root33;
        root53.right = root3_2;


        root_3.right = root11;

        int cts = pathSum(root, 8);
        System.out.println("cts=" + cts);


    }

    private static class TreeNode {

        public int val;
        private TreeNode left;
        private TreeNode right;
        public TreeNode() {
        }
        public TreeNode(int val) {
            this.val = val;
        }

        @Override
        public java.lang.String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
