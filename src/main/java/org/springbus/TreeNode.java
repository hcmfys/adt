package org.springbus;


import lombok.Data;

@Data

public class TreeNode {


    /***
     * 1、路径：也就是已经做出的选择。
     * 2、选择列表：也就是你当前可以做的选择。
     * 3、结束条件：也就是到达决策树底层，无法再做选择的条件。
     * 如果你不理解这三个词语的解释，没关系，
     * 我们后面会用「全排列」和「N 皇后问题」这两个经典的回溯算法问题来帮你理解这些词语是什么意思，
     * 现在你先留着印象。
     */

    public TreeNode(){}
    public  TreeNode(int val) {
        this.val = val;
    }
    public TreeNode left;
    public TreeNode right;
    public TreeNode next;
    public int val;

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.val = value;
        this.left = left;
        this.right = right;
    }

    @Override
     public  String toString(){
        return "["+this.val+"]";
    }

    // convenience methods to build trees

    public static TreeNode tree(int value, TreeNode left, TreeNode right) {
        return new TreeNode(value, left, right);
    }

    public static TreeNode treeLeft(int value, TreeNode left) {
        return new TreeNode(value, left, null);
    }

    public static TreeNode treeRight(int value, TreeNode right) {
        return new TreeNode(value, null, right);
    }

    public static TreeNode leaf(int value) {
        return new TreeNode(value, null, null);
    }


    public  TreeNode[] nodesList;





    public TreeNode(int _val, TreeNode _left, TreeNode _right, TreeNode _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

}
