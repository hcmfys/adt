package org.springbus;
public  class Node  extends  TreeNode{

    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
        super();
    }

    public Node(int _val) {
        super(_val);
        val = _val;
    }
    public Node(int value, Node left, Node right) {
        super(value,left,right);
    }
    public  void setLeft( Node left){
        super.left=left;
        this.left=left;
    }
    public  void setRight( Node right) {
        super.right = right;
        this.right = right;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        super(_val,_left,_right,_next);
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

}
