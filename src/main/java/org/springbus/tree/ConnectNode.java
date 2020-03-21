package org.springbus.tree;

import org.springbus.Node;
import org.springbus.TreeNode;
import org.springbus.TreePrintUtil;
import sun.misc.LRUCache;

////给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
////
//// struct Node {
////  int val;
////  Node *left;
////  Node *right;
////  Node *next;
////}
////
//// 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
// 如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
////
//// 初始状态下，所有 next 指针都被设置为 NULL。
////
////
////
//你只能使用常量级额外空间。
//使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
public class ConnectNode {


    private  Node lastNode;
    private  int  lastLevel;
    public Node connectNode(Node root,int level) {
        if(root==null) {
            return  null;
        }
        if(lastNode!=null && level==lastLevel) {
            lastNode.next=root;
        }
        System.out.println( root.val +"--->" +level);
        lastNode=root;
        lastLevel=level;
        if(root.left!=null) {
            connectNode(root.left, level+1);
        }
        if(root.right!=null) {
            connectNode(root.right, level + 1);
        }

        return root;
    }
    public Node connect(Node root) {
        if(root==null) {
            return  null;
        }
        connectNode(root,1);
        return root;
    }


    public static void main(String[] args) {
        Integer[] trees = new Integer[]{1, -2, -3, 1, 3, -2, null, -1}; // -1
        trees = new Integer[]{1,2,3,4,5,6,7};

        Node root = TreePrintUtil.makeTreeNode(trees);
        Node treeNode = new ConnectNode().connect(root);
        System.out.println(treeNode);
        TreePrintUtil.pirnt(root);
    }

}
