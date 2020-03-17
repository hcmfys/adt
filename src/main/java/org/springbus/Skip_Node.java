package org.springbus;

public class Skip_Node {
    public Skip_Level level[];            //保存多个层
    public Skip_Node backward;        //后退指针后退步数为1
    public int levelNum;        //层数
    public double score;            //分值，作为key对跳跃表排序
    public Object data;            //数据对象
}
