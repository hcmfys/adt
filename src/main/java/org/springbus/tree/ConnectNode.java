package org.springbus.tree;

import org.springbus.Node;
import org.springbus.TreeNode;
import org.springbus.TreePrintUtil;
import sun.misc.LRUCache;
import sun.security.util.Length;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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



    HashMap<Integer, List<Node>> nodeMap=new HashMap();
    public Node connectNode(Node root,int level) {
        if(root==null) {
            return  null;
        }
        if(nodeMap.get(level)==null) {
            nodeMap.put(level,new ArrayList<>());
        }
        nodeMap.get(level).add(root);
        System.out.println( root.val +"--->" +level);


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
        Iterator<Integer> keys= nodeMap.keySet().iterator();
        while(keys.hasNext()) {
            Integer lv=  keys.next();
            List<Node>  nodeList=nodeMap.get(lv);
            if(nodeList.size()>0) {
                Node lastNode=nodeList.get(0);
                for (int i = 1; i < nodeList.size(); i++) {
                    Node nextNode = nodeList.get(i);
                    lastNode.next=nextNode;
                    lastNode=nextNode;
                }
            }
        }
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
