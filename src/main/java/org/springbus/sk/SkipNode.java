package org.springbus.sk;

import lombok.Data;

@Data
public class SkipNode {
    /**
     * 层数组
     */
    private SkipLine[] line;
    /**
     * 前一个节点
     */
    private SkipNode prev;
    /**
     * 前一个节点
     */
    private SkipNode next;

    /**
     * 行数
     */
    private int lineNum;
    private int data;

    public SkipNode(int data, int lineNum) {
        this.data = data;
        this.lineNum = lineNum;
        this.line = new SkipLine[lineNum];
        for (int i = 0; i < lineNum; i++) {
            SkipLine skipLine = new SkipLine();
            skipLine.setNext(null);
            this.line[i] = skipLine;
        }
    }


    @Override
   public String toString(){
        return  "["+this.data +"]";
    }

}
