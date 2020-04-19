package org.springbus;

public class Skip_List {
    public Skip_Node head;            //表头拥有最大层数，不保存数据
    public Skip_Node tail;            //指向尾节点
    int level;            //除表头之外的最大层数
    int length;            //跳跃表的长度，也就表示节点个数
}
