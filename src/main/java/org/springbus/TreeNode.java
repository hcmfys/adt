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
    private TreeNode left;
    private TreeNode right;
    private int val;

}